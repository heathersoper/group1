import java.sql.*;
import java.util.Scanner;

public class OrderStatusTransaction {
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exitMenu = false;

        while (!exitMenu) {
            System.out.println("\n+*+ Order Status Transaction Menu +*+");
            System.out.println("1. View Current Order Status");
            System.out.println("2. Update Order Status");
            System.out.println("0. Return to Main Menu");

            int choice = Input(scanner);

            switch (choice) {
                case 1:
                    System.out.println("Viewing current order status...");
                    viewOrderStatus(scanner);
                    break;
                case 2:
                    System.out.println("Updating order status...");
                    updateOrderStatus(scanner);
                    break;
                case 0:
                    exitMenu = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("[!] Invalid choice. Enter a number between 0 and 2.");
            }
        }
    }

    private static int Input(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                String input = scanner.nextLine().trim();
                if (!input.isEmpty() && input.matches("\\d+")) {
                    return Integer.parseInt(input);
                }
                System.out.println("[!] Invalid input. Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("[!] Error reading input: " + e.getMessage());
            }
        }
    }

    public static void viewOrderStatus(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter Order ID: ");
            int orderID = scanner.nextInt();

            String query = "SELECT order_status, order_date FROM orders WHERE orderID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, orderID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String status = rs.getString("order_status");
                Date orderDate = rs.getDate("order_date");
                System.out.printf("Order Status: %s | Order Date: %s%n", status, orderDate);
            } else {
                System.out.println("[!] Order not found.");
            }
        } catch (SQLException e) {
            System.err.println("[!] Error while viewing order status: " + e.getMessage());
        }
        scanner.nextLine();
    }

    public static void updateOrderStatus(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter Order ID: ");
            int orderID = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter New Order Status (Pending, Cancelled, Completed): ");
            String newStatus = scanner.nextLine();

            String query = "UPDATE orders SET order_status = ?, order_date = NOW() WHERE orderID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("[!] Order status updated successfully.");
            } else {
                System.out.println("[!] Failed to update order status. Order ID may not exist.");
            }
        } catch (SQLException e) {
            System.err.println("[!] Error while updating order status: " + e.getMessage());
        }
    }
}