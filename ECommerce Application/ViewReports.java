import java.sql.*;
import java.util.Scanner;

public class ViewReports {
    public static void showMenu () {
        Scanner scanner = new Scanner(System.in);
        boolean exitViewReportsMenu = false;

        while (!exitViewReportsMenu) {

            System.out.println("\nPlease choose a report to view:");
            System.out.println("1. Inventory and Sales Report");
            System.out.println("2. Revenue and Payment Method Report");
            System.out.println("3. Customer Purchase History Report");
            System.out.println("4. Order Status Report");

            int choice = Input(scanner); // Get user input

            // SQL queries for different reports
            String query = "";
            switch (choice) {
                case 1:
                    query = "SELECT p.productID, " +
                            "p.product_name, " +
                            "DATE_FORMAT(o.order_date, '%Y-%m') AS sale_month, " +
                            "SUM(oi.order_quantity) AS total_quantity_sold, " +
                            "p.qty_instock - SUM(oi.order_quantity) AS remaining_inventory, " +
                            "SUM(oi.item_total) AS total_sales_value " +
                            "FROM product p " +
                            "JOIN order_items oi ON p.productID = oi.productID " +
                            "JOIN orders o ON oi.orderID = o.orderID " +
                            "GROUP BY p.productID, sale_month " +
                            "ORDER BY sale_month, p.productID;";
                    break;

                case 2:
                    query = "SELECT o.order_date, " +
                            "SUM(o.order_total) AS total_revenue, " +
                            "pm.method_name, " +
                            "COUNT(p.paymentID) AS payment_method_usage_count, " +
                            "SUM(p.amount) AS total_amount_by_payment_method " +
                            "FROM orders o " +
                            "JOIN payment p ON o.paymentID = p.paymentID " +
                            "JOIN payment_method pm ON p.paymentmethodID = pm.paymentmethodID " +
                            "GROUP BY o.order_date, pm.method_name " +
                            "ORDER BY o.order_date, pm.method_name;";
                    break;

                case 3:
                    query = "SELECT c.customerID, " +
                            "c.last_name, " +
                            "c.first_name, " +
                            "c.email, " +
                            "c.phone_number, " +
                            "YEAR(o.order_date) AS order_year, " +
                            "MONTH(o.order_date) AS order_month, " +
                            "COUNT(o.orderID) AS total_purchases, " +
                            "SUM(o.order_total) AS amount_spent " +
                            "FROM customer c " +
                            "LEFT JOIN orders o ON c.customerID = o.customerID " +
                            "WHERE o.order_status = 'Completed' " +
                            "GROUP BY c.customerID, YEAR(o.order_date), MONTH(o.order_date) " +
                            "ORDER BY c.customerID, order_year, order_month;";
                    break;

                case 4:
                    query = "SELECT " +
                            "YEAR(order_date) AS year, " +
                            "MONTH(order_date) AS month, " +
                            "order_status, " +
                            "COUNT(*) AS order_count " +
                            "FROM orders " +
                            "GROUP BY YEAR(order_date), MONTH(order_date), order_status " +
                            "ORDER BY year, month, order_status;";
                    break;
                case 0:
                    exitViewReportsMenu = true;
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("\n[!] Invalid choice. Please choose a valid report.");
            }

            try (Connection conn = DatabaseConnection.getConnection()) {
                // Assuming you have a method to get the database connection
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                // Print headers based on the report type
                switch (choice) {
                    case 1:
                        System.out.println("Viewing Inventory and Sales Report...");
                        printInventoryAndSalesReportHeader();
                        break;
                    case 2:
                        System.out.println("Viewing Revenue and Payment Method Report...");
                        printRevenueAndPaymentMethodReportHeader();
                        break;
                    case 3:
                        System.out.println("Viewing Customer Purchase History Report...");
                        printCustomerPurchaseHistoryReportHeader();
                        break;
                    case 4:
                        System.out.println("Viewing Order Status Report...");
                        printOrderStatusReportHeader();
                        break;
                }

                // Display the results in table format
                while (rs.next()) {
                    if (choice == 1) {
                        printInventoryAndSalesReport(rs);
                    } else if (choice == 2) {
                        printRevenueAndPaymentMethodReport(rs);
                    } else if (choice == 3) {
                        printCustomerPurchaseHistoryReport(rs);
                    } else if (choice == 4) {
                        printOrderStatusReport(rs);
                    }
                }
            } catch (SQLException e) {
                System.out.println("\n[!] Error reading input: " + e.getMessage());
            }
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

    // Header for Inventory and Sales Report
    private static void printInventoryAndSalesReportHeader() {
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-50s %-15s %-20s %-20s %-25s\n", "Product ID", "Product Name", "Sale Month", "Total Quantity Sold", "Remaining Inventory", "Total Sales Value");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
    }

    // Header for Revenue and Payment Method Report
    private static void printRevenueAndPaymentMethodReportHeader() {
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-25s %-20s %-30s %-25s\n", "Order Date", "Total Revenue", "Payment Method", "Payment Method Usage Count", "Total Amount by Payment Method");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }

    // Header for Customer Purchase History Report
    private static void printCustomerPurchaseHistoryReportHeader() {
        System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-30s %-20s %-20s %-15s %-20s %-20s\n", "Customer ID", "Last Name", "First Name", "Email", "Phone Number", "Order Year", "Order Month", "Total Purchases", "Amount Spent");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    // Header for Order Status Report
    private static void printOrderStatusReportHeader() {
        System.out.println("\n---------------------------------------------------------------");
        System.out.printf("%-10s %-10s %-20s %-15s\n", "Year", "Month", "Order Status", "Order Count");
        System.out.println("---------------------------------------------------------------");
    }

    // Rows for Inventory and Sales Report
    private static void printInventoryAndSalesReport(ResultSet rs) throws SQLException {
        System.out.printf("%-12d %-50s %-15s %-20d %-20d %-25.2f\n",
                rs.getInt("productID"),
                rs.getString("product_name"),
                rs.getString("sale_month"),
                rs.getInt("total_quantity_sold"),
                rs.getInt("remaining_inventory"),
                rs.getDouble("total_sales_value"));
    }

    // Rows for Revenue and Payment Method Report
    private static void printRevenueAndPaymentMethodReport(ResultSet rs) throws SQLException {
        System.out.printf("%-15s %-25.2f %-20s %-30d %-25.2f\n",
                rs.getDate("order_date"),
                rs.getDouble("total_revenue"),
                rs.getString("method_name"),
                rs.getInt("payment_method_usage_count"),
                rs.getDouble("total_amount_by_payment_method"));
    }

    // Rows for Customer Purchase History Report
    private static void printCustomerPurchaseHistoryReport(ResultSet rs) throws SQLException {
        System.out.printf("%-10d %-15s %-15s %-30s %-20s %-20d %-15d %-20d %-20.2f\n",
                rs.getInt("customerID"),
                rs.getString("last_name"),
                rs.getString("first_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getInt("order_year"),
                rs.getInt("order_month"),
                rs.getInt("total_purchases"),
                rs.getDouble("amount_spent"));
    }

    // Rows for Order Status Report
    private static void printOrderStatusReport(ResultSet rs) throws SQLException {
        System.out.printf("%-10d %-10d %-20s %-15d\n",
                rs.getInt("year"),
                rs.getInt("month"),
                rs.getString("order_status"),
                rs.getInt("order_count"));
    }
}
