import java.sql.*;
import java.util.Scanner;

public class OrderProcessingTransaction {

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exitOrderProcessingMenu = false;

        while (!exitOrderProcessingMenu) {
            System.out.println("\n+*+ Order Processing Menu +*+");
            System.out.println("1. Check Customer Eligibility");
            System.out.println("2. Check Product Availability");
            System.out.println("3. Create New Order Record");
            System.out.println("4. Update Inventory");
            System.out.println("5. Create Transaction Record");
            System.out.println("0. Return To Main Menu");

            int orderChoice = Input(scanner); // Get validated input

            switch (orderChoice) {
                case 1:
                    System.out.println("Checking customer eligibility...");
                    OrderTransactionMenu.checkCustomerEligibility(scanner);
                    break;
                case 2:
                    System.out.println("Checking product availability...");
                    OrderTransactionMenu.readInventory(scanner);
                    break;
                case 3:
                    System.out.println("Creating a new order...");
                    OrderTransactionMenu.createOrder(scanner);
                    break;
                case 4:
                    System.out.println("Updating inventory...");
                    OrderTransactionMenu.updateInventory(scanner);
                    break;
                case 5:
                    System.out.println("Recording transaction...");
                    OrderTransactionMenu.createTransactionRecord(scanner);
                    break;
                case 0:
                    exitOrderProcessingMenu = true;
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("[!] Invalid choice. Please enter a number between 0 and 5.");
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
}

class OrderTransactionMenu {

    // Check customer eligibility to place an order
    public static void checkCustomerEligibility(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter customer ID: ");
            int customerID = scanner.nextInt();

            // Query to check if the customer exists
            String customerQuery = "SELECT * FROM customer WHERE customerID = ?";
            PreparedStatement customerStmt = con.prepareStatement(customerQuery);
            customerStmt.setInt(1, customerID);

            ResultSet customerRS = customerStmt.executeQuery();
            if (customerRS.next()) {
                System.out.println("\nCustomer found: " + customerRS.getString("first_name") + " " + customerRS.getString("last_name"));

                // Check valid payment methods from past transactions
                System.out.println("\nChecking customer's valid payment methods...");
                String paymentMethodQuery = """
                SELECT DISTINCT pm.method_name
                FROM payment p
                JOIN payment_method pm ON p.paymentmethodID = pm.paymentmethodID
                WHERE p.paymentID IN (
                    SELECT paymentID
                    FROM orders
                    WHERE customerID = ?
                )
            """;
                PreparedStatement paymentMethodStmt = con.prepareStatement(paymentMethodQuery);
                paymentMethodStmt.setInt(1, customerID);

                ResultSet paymentMethodRS = paymentMethodStmt.executeQuery();
                if (paymentMethodRS.next()) {
                    System.out.println("\nCustomer has used the following payment methods:");
                    do {
                        System.out.println("- " + paymentMethodRS.getString("method_name"));
                    } while (paymentMethodRS.next());
                } else {
                    System.out.println("\nNo valid payment methods found for the customer in past transactions.");
                }
            } else {
                System.out.println("\nCustomer not found or not eligible to place an order.");
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error while checking customer eligibility: " + e.getMessage());
        }
        scanner.nextLine(); // Consume newline
    }

    // Read product availability in inventory
    public static void readInventory(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter product ID to check availability: ");
            int productID = scanner.nextInt();

            String query = "SELECT product_name, qty_instock FROM product WHERE productID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, productID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.printf("\nProduct: %s | Available Quantity: %d%n", rs.getString("product_name"), rs.getInt("qty_instock"));
            } else {
                System.out.println("\nProduct not found.");
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error while reading inventory: " + e.getMessage());
        }
        scanner.nextLine();
    }

    // Create a new order record
    public static void createOrder(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter customer ID: ");
            int customerID = scanner.nextInt();
            System.out.print("Enter product ID: ");
            int productID = scanner.nextInt();
            System.out.print("Enter total order amount: ");
            double orderTotal = scanner.nextDouble();

            // Insert the order into the orders table
            String query = "INSERT INTO orders (order_total, customerID, productID, order_date, order_status) VALUES (?, ?, ?, NOW(), 'Pending')";
            PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, orderTotal);
            stmt.setInt(2, customerID);
            stmt.setInt(3, productID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderID = generatedKeys.getInt(1);
                    System.out.println("\nOrder created successfully with Order ID: " + orderID);
                }
            } else {
                System.out.println("\nFailed to create the order.");
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error while creating order: " + e.getMessage());
        }
        scanner.nextLine();
    }

    // Update inventory after an order
    public static void updateInventory(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter product ID: ");
            int productID = scanner.nextInt();
            System.out.print("Enter quantity ordered: ");
            int quantityOrdered = scanner.nextInt();

            // Update product inventory
            String query = "UPDATE product SET qty_instock = qty_instock - ? WHERE productID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, quantityOrdered);
            stmt.setInt(2, productID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nInventory updated successfully.");
            } else {
                System.out.println("\nFailed to update inventory.");
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error while updating inventory: " + e.getMessage());
        }
        scanner.nextLine();
    }

    // Create a transaction record after a successful order
    public static void createTransactionRecord(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter order ID: ");
            int orderID = scanner.nextInt();
            System.out.print("Enter payment amount: ");
            double amount = scanner.nextDouble();
            System.out.print("Enter payment method ID: ");
            int paymentMethodID = scanner.nextInt();

            // Insert into the payment table
            String query = "INSERT INTO payment (paymentmethodID, payment_date, amount) VALUES (?, NOW(), ?)";
            PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, paymentMethodID);
            stmt.setDouble(2, amount);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int paymentID = generatedKeys.getInt(1);

                    // Update order with paymentID
                    String updateOrderQuery = "UPDATE orders SET paymentID = ? WHERE orderID = ?";
                    PreparedStatement updateStmt = con.prepareStatement(updateOrderQuery);
                    updateStmt.setInt(1, paymentID);
                    updateStmt.setInt(2, orderID);

                    updateStmt.executeUpdate();
                    System.out.println("\nTransaction record created and order updated with payment.");
                }
            } else {
                System.out.println("\nFailed to create the transaction record.");
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error while creating transaction record: " + e.getMessage());
        }
        scanner.nextLine();
    }
}

