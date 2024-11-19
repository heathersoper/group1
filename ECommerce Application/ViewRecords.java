import java.sql.*;
import java.util.Scanner;

public class ViewRecords {
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exitViewRecordsMenu = false;

        while (!exitViewRecordsMenu) {
            System.out.println("\nChoose a record to view:");
            System.out.println("1. Customer Record");
            System.out.println("2. Product Record");
            System.out.println("3. Payment Record");
            System.out.println("4. Order Record");
            System.out.println("0. Return to Main Menu");

            int choice = Input(scanner);

            switch (choice) {
                case 1:
                    viewTable("customer", scanner);
                    break;
                case 2:
                    showProductMenu(scanner);
                    break;
                case 3:
                    showPaymentMenu(scanner);
                    break;
                case 4:
                    showOrderMenu(scanner);
                    break;
                case 0:
                    exitViewRecordsMenu = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("\n[!] Invalid choice. Please choose again.");
            }
        }
    }

    private static void showProductMenu(Scanner scanner) {
        boolean exitShowProductMenu = false;

        while (!exitShowProductMenu) {
            System.out.println("\nChoose a product-related table:");
            System.out.println("1. Products");
            System.out.println("2. Categories");
            System.out.println("3. Vendors");
            System.out.println("4. Purchase Orders");
            System.out.println("0. Return to View Records Menu");

            int productChoice = Input(scanner);

            switch (productChoice) {
                case 1:
                    System.out.println("Viewing Products...");
                    viewTable("product", scanner);
                    break;
                case 2:
                    System.out.println("Viewing Categories...");
                    viewTable("category", scanner);
                    break;
                case 3:
                    System.out.println("Viewing Vendors...");
                    viewTable("vendor", scanner);
                    break;
                case 4:
                    System.out.println("Viewing Purchase Orders...");
                    viewTable("purchase_order", scanner);
                    break;
                case 0:
                    exitShowProductMenu = true;
                    System.out.println("Returning to View Records Menu...");
                    break;
                default:
                    System.out.println("\n[!] Invalid choice.");
            }
        }
    }

    private static void showPaymentMenu(Scanner scanner) {
        boolean exitShowPaymentMenu = false;

        while (!exitShowPaymentMenu) {
            System.out.println("\nChoose a payment-related table:");
            System.out.println("1. Payments");
            System.out.println("2. Payment Methods");
            System.out.println("3. Discounts");
            System.out.println("0. Return to View Records Menu");

            int paymentChoice = Input(scanner);

            switch (paymentChoice) {
                case 1:
                    viewTable("payment", scanner);
                    break;
                case 2:
                    viewTable("payment_method", scanner);
                    break;
                case 3:
                    viewTable("discount", scanner);
                    break;
                case 0:
                    exitShowPaymentMenu = true;
                    System.out.println("Returning to View Records Menu...");
                    break;
                default:
                    System.out.println("\n[!] Invalid choice.");
            }
        }
    }

    private static void showOrderMenu(Scanner scanner) {
        boolean exitShowOrderMenu = false;

        while (!exitShowOrderMenu) {
            System.out.println("\nChoose an order-related table:");
            System.out.println("1. Orders");
            System.out.println("2. Order Items");
            System.out.println("3. Reviews");
            System.out.println("0. Return to View Records Menu");

            int orderChoice = Input(scanner);

            switch (orderChoice) {
                case 1:
                    viewTable("orders", scanner);
                    break;
                case 2:
                    viewTable("order_items", scanner);
                    break;
                case 3:
                    viewTable("review", scanner);
                    break;
                case 0:
                    exitShowOrderMenu = true;
                    System.out.println("Returning to View Records Menu...");
                    break;
                default:
                    System.out.println("\n[!] Invalid choice.");
            }
        }
    }

    private static void viewTable(String tableName, Scanner scanner) {
        System.out.printf("\nYou selected '%s'.%n", tableName);
        System.out.print("Enter the column to filter by (or leave blank to view all records): ");
        String column = scanner.nextLine().trim();

        try (Connection con = DatabaseConnection.getConnection()) {
            String query;
            PreparedStatement stmt;
            if (column.isEmpty()) {
                query = "SELECT * FROM " + tableName;
                stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            } else {
                System.out.printf("Enter the value for %s: ", column);
                String value = scanner.nextLine().trim();
                query = "SELECT * FROM " + tableName + " WHERE " + column + " = ?";
                stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt.setString(1, value);
            }

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Determine column widths
            int[] columnWidths = new int[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnWidths[i - 1] = Math.max(metaData.getColumnName(i).length(), 15); // Minimum width of 15
            }

            // Adjust column widths based on data
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String data = rs.getString(i);
                    if (data != null) {
                        columnWidths[i - 1] = Math.max(columnWidths[i - 1], data.length());
                    }
                }
            }

            // Calculate total width of the table
            int totalWidth = 0;
            for (int width : columnWidths) {
                totalWidth += width; // Adding 2 for spacing between columns
            }
            totalWidth += (columnCount - 1) * 2; // Extra space for the column separators

            // Reset ResultSet to the beginning
            rs.beforeFirst();

            // Print header
            System.out.printf("\n'%s' Table:%n", tableName);
            System.out.println("-".repeat(totalWidth));
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-" + columnWidths[i - 1] + "s  ", metaData.getColumnName(i));
            }
            System.out.println();
            System.out.println("-".repeat(totalWidth));

            // Print rows
            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                for (int i = 1; i <= columnCount; i++) {
                    String data = rs.getString(i);
                    System.out.printf("%-" + columnWidths[i - 1] + "s  ", data != null ? data : "NULL");
                }
                System.out.println();
            }

            if (!hasResults) {
                System.out.println("No records found.");
            }

            System.out.println("-".repeat(totalWidth));
        } catch (SQLException e) {
            System.err.println("\n[!] Error while viewing records: " + e.getMessage());
        }
    }

    private static int Input(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                String input = scanner.nextLine().trim(); // Read the full line and remove any extra spaces

                if (input.isEmpty()) {
                    continue; // Skip if input is empty
                }

                // Check if the input is numeric
                if (input.matches("\\d+")) {
                    return Integer.parseInt(input); // Return valid input as an integer
                } else {
                    System.out.println("\n[!] Invalid input. Please enter a valid number.");
                }
            } catch (Exception e) {
                System.out.println("\n[!] Error reading input: " + e.getMessage());
            }
        }
    }
}