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
                    System.out.println("\nNo valid payment methods found. Customer does not have any transactions yet....");
                }
            } else {
                System.out.println("\nCustomer not found or not eligible to place an order. Customer ID not found... ");
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
            con.setAutoCommit(false); // Start transaction

            System.out.print("\nEnter customer ID: ");
            int customerID = scanner.nextInt();

            // Check if customer exists
            String checkCustomerQuery = "SELECT COUNT(*) AS count FROM customer WHERE customerID = ?";
            PreparedStatement customerStmt = con.prepareStatement(checkCustomerQuery);
            customerStmt.setInt(1, customerID);
            ResultSet customerResult = customerStmt.executeQuery();

            if (!customerResult.next() || customerResult.getInt("count") == 0) {
                System.out.println("\n[!] Error: Customer ID not found. Please provide a valid ID.");
                return; // Exit the method if customer ID is invalid
            }

            System.out.print("Enter product ID: ");
            int productID = scanner.nextInt();
            System.out.print("Enter quantity ordered: ");
            int orderQuantity = scanner.nextInt();

            try {
                // Check if product exists and has enough stock
                String checkProductQuery = "SELECT price, qty_instock FROM product WHERE productID = ?";
                PreparedStatement productStmt = con.prepareStatement(checkProductQuery);
                productStmt.setInt(1, productID);
                ResultSet productResult = productStmt.executeQuery();

                if (!productResult.next()) {
                    System.out.println("\n[!] Error: Product ID not found. Please provide a valid ID.");
                    con.rollback();
                    return;
                }

                double productPrice = productResult.getDouble("price");
                int stock = productResult.getInt("qty_instock");

                if (stock < orderQuantity) {
                    System.out.println("\n[!] Error: Insufficient stock. Available quantity: " + stock);
                    con.rollback();
                    return;
                }

                // Calculate total order amount
                double orderTotal = productPrice * orderQuantity;

                // Insert the order into the orders table
                String orderQuery = "INSERT INTO orders (order_total, customerID, productID, order_date, order_status) VALUES (?, ?, ?, NOW(), 'Pending')";
                PreparedStatement orderStmt = con.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);
                orderStmt.setDouble(1, orderTotal);
                orderStmt.setInt(2, customerID);
                orderStmt.setInt(3, productID);

                int rowsAffected = orderStmt.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet generatedKeys = orderStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int orderID = generatedKeys.getInt(1);

                        // Insert into order_items table
                        String orderItemsQuery = "INSERT INTO order_items (orderID, productID, order_quantity, item_total) VALUES (?, ?, ?, ?)";
                        PreparedStatement orderItemsStmt = con.prepareStatement(orderItemsQuery);
                        orderItemsStmt.setInt(1, orderID);
                        orderItemsStmt.setInt(2, productID);
                        orderItemsStmt.setInt(3, orderQuantity);
                        orderItemsStmt.setDouble(4, orderTotal);

                        rowsAffected = orderItemsStmt.executeUpdate();
                        if (rowsAffected > 0) {
                            // Update inventory (decrease stock)
                            String updateInventoryQuery = "UPDATE product SET qty_instock = qty_instock - ? WHERE productID = ?";
                            PreparedStatement inventoryStmt = con.prepareStatement(updateInventoryQuery);
                            inventoryStmt.setInt(1, orderQuantity);
                            inventoryStmt.setInt(2, productID);
                            inventoryStmt.executeUpdate();

                            con.commit(); // Commit transaction
                            System.out.println("\nOrder created successfully with Order ID: " + orderID);
                        } else {
                            con.rollback(); // Rollback transaction
                            System.out.println("\n[!] Error: Failed to create order items. Transaction rolled back.");
                        }
                    } else {
                        con.rollback(); // Rollback transaction
                        System.out.println("\n[!] Error: Failed to retrieve Order ID. Transaction rolled back.");
                    }
                } else {
                    con.rollback(); // Rollback transaction
                    System.out.println("\n[!] Error: Failed to create the order. Transaction rolled back.");
                }
            } catch (SQLException e) {
                con.rollback(); // Rollback transaction
                System.err.println("\n[!] Error during order creation: " + e.getMessage());
            } finally {
                con.setAutoCommit(true); // Restore default behavior
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error connecting to database: " + e.getMessage());
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

            // Check available stock
            String checkQuery = "SELECT qty_instock FROM product WHERE productID = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setInt(1, productID);

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                int qtyInStock = rs.getInt("qty_instock");

                if (qtyInStock >= quantityOrdered) {
                    // Proceed with the update
                    String updateQuery = "UPDATE product SET qty_instock = qty_instock - ? WHERE productID = ?";
                    PreparedStatement updateStmt = con.prepareStatement(updateQuery);
                    updateStmt.setInt(1, quantityOrdered);
                    updateStmt.setInt(2, productID);

                    int rowsAffected = updateStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("\nInventory updated successfully.");
                    } else {
                        System.out.println("\nFailed to update inventory.");
                    }
                } else {
                    System.out.println("\nInsufficient stock. Available quantity: " + qtyInStock);
                }
            } else {
                System.out.println("\nProduct not found.");
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error while updating inventory: " + e.getMessage());
        }
        scanner.nextLine();
    }
}

