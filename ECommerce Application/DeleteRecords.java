import java.sql.*;
import java.util.Scanner;

public class DeleteRecords {
    public static void showDeleteRecordsMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exitDeleteRecordsMenu = false;

        while (!exitDeleteRecordsMenu) {
            System.out.println("\nChoose a table to delete records from:");
            System.out.println("1. Customer");
            System.out.println("2. Product");
            System.out.println("3. Payment");
            System.out.println("4. Order");
            System.out.println("0. Return to Main Menu");

            int choice = Input(scanner);

            switch (choice) {
                case 1:
                    deleteTableMenu("customer", scanner);
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
                    exitDeleteRecordsMenu = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("\n[!] Invalid choice. Please choose again.");
            }
        }
    }

    private static void showProductMenu(Scanner scanner) {
        boolean exitProductMenu = false;

        while (!exitProductMenu) {
            System.out.println("\nChoose a product-related table:");
            System.out.println("1. Products");
            System.out.println("2. Categories");
            System.out.println("3. Vendors");
            System.out.println("4. Purchase Orders");
            System.out.println("0. Return to Delete Records Menu");

            int productChoice = Input(scanner);

            switch (productChoice) {
                case 1:
                    deleteTableMenu("product", scanner);
                    break;
                case 2:
                    deleteTableMenu("category", scanner);
                    break;
                case 3:
                    deleteTableMenu("vendor", scanner);
                    break;
                case 4:
                    deleteTableMenu("purchase_order", scanner);
                    break;
                case 0:
                    exitProductMenu = true;
                    System.out.println("Returning to Delete Records Menu...");
                    break;
                default:
                    System.out.println("\n[!] Invalid choice.");
            }
        }
    }

    private static void showPaymentMenu(Scanner scanner) {
        boolean exitPaymentMenu = false;

        while (!exitPaymentMenu) {
            System.out.println("\nChoose a payment-related table:");
            System.out.println("1. Payments");
            System.out.println("2. Payment Methods");
            System.out.println("3. Discounts");
            System.out.println("0. Return to Delete Records Menu");

            int paymentChoice = Input(scanner);

            switch (paymentChoice) {
                case 1:
                    deleteTableMenu("payment", scanner);
                    break;
                case 2:
                    deleteTableMenu("payment_method", scanner);
                    break;
                case 3:
                    deleteTableMenu("discount", scanner);
                    break;
                case 0:
                    exitPaymentMenu = true;
                    System.out.println("Returning to Delete Records Menu...");
                    break;
                default:
                    System.out.println("\n[!] Invalid choice.");
            }
        }
    }

    private static void showOrderMenu(Scanner scanner) {
        boolean exitOrderMenu = false;

        while (!exitOrderMenu) {
            System.out.println("\nChoose an order-related table:");
            System.out.println("1. Orders");
            System.out.println("2. Order Items");
            System.out.println("3. Reviews");
            System.out.println("0. Return to Delete Records Menu");

            int orderChoice = Input(scanner);

            switch (orderChoice) {
                case 1:
                    deleteTableMenu("orders", scanner);
                    break;
                case 2:
                    deleteTableMenu("order_items", scanner);
                    break;
                case 3:
                    deleteTableMenu("review", scanner);
                    break;
                case 0:
                    exitOrderMenu = true;
                    System.out.println("Returning to Delete Records Menu...");
                    break;
                default:
                    System.out.println("\n[!] Invalid choice.");
            }
        }
    }

    private static void deleteTableMenu(String tableName, Scanner scanner) {
        System.out.printf("\nYou selected '%s'.%n", tableName);
        displayTableContents(tableName);

        System.out.print("\nEnter the column to filter by: ");
        String column = scanner.nextLine().trim();

        System.out.printf("Enter the value for '%s' to delete: ", column);
        String value = scanner.nextLine().trim();

        deleteRecord(tableName, column, value);
    }

    private static void deleteRecord(String tableName, String column, String value) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM " + tableName + " WHERE " + column + " = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, value);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\nRecord(s) deleted successfully.");
            } else {
                System.out.println("\nNo matching record(s) found.");
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error while deleting record: " + e.getMessage());
        }
    }

    private static void displayTableContents(String tableName) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM " + tableName;
            PreparedStatement stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
                totalWidth += width;
            }
            totalWidth += (columnCount - 1) * 2;

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
            System.err.println("[!] Error while fetching table contents: " + e.getMessage());
        }
    }

    private static int Input(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                String input = scanner.nextLine().trim();

                if (input.matches("\\d+")) {
                    return Integer.parseInt(input);
                } else {
                    System.out.println("\n[!] Invalid input. Please enter a valid number.");
                }
            } catch (Exception e) {
                System.out.println("\n[!] Error reading input: " + e.getMessage());
            }
        }
    }
}
