import java.sql.*;
import java.util.Scanner;

public class PaymentTransaction {
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exitPaymentMenu = false;

        while (!exitPaymentMenu) {
            System.out.println("\n+*+ Payment Transaction Menu +*+");
            System.out.println("1. Check customer’s details for payment eligibility");
            System.out.println("2. Read invoice record");
            System.out.println("3. Create a payment record for an order");
            System.out.println("4. Update customer’s payment status");
            System.out.println("5. Create a receipt for the payment transaction");
            System.out.println("6. Check eligibility for a discount");
            System.out.println("0. Return to main menu");

            int paymentChoice = Input(scanner); // Get validated input

            switch (paymentChoice) {
                case 1:
                    System.out.println("Reading customer’s details...");
                    PaymentTransactionMenu.checkCustomerDetails(scanner);
                    break;
                case 2:
                    System.out.println("Reading invoice record...");
                    PaymentTransactionMenu.readInvoiceRecord(scanner);
                    break;
                case 3:
                    System.out.println("Creating a payment record...");
                    PaymentTransactionMenu.createPaymentRecord(scanner);
                    break;
                case 4:
                    System.out.println("Updating payment status...");
                    PaymentTransactionMenu.updatePaymentStatus(scanner);
                    break;
                case 5:
                    System.out.println("Creating a receipt...");
                    PaymentTransactionMenu.createReceipt(scanner);
                    break;
                case 6:
                    System.out.println("Checking discount eligibility...");
                    PaymentTransactionMenu.checkDiscountEligibility(scanner);
                    break;
                case 0:
                    exitPaymentMenu = true;
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("[!] Invalid choice. Please enter a number between 0 and 6.");
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

class PaymentTransactionMenu {
    public static void checkCustomerDetails(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter customer ID: ");
            int customerID = scanner.nextInt();
            System.out.print("Enter payment method ID: ");
            int paymentMethodID = scanner.nextInt();

            // Query to validate customer and payment method via orders
            String query = "SELECT * FROM customer c " +
                    "JOIN orders o ON c.customerID = o.customerID " +
                    "JOIN payment p ON o.paymentID = p.paymentID " +
                    "WHERE c.customerID = ? AND p.paymentmethodID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, customerID);
            stmt.setInt(2, paymentMethodID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("\nCustomer details found and payment method is valid.");
            } else {
                System.out.println("\nCustomer details not found or invalid payment method.");
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error while checking customer details: " + e.getMessage());
        }
        scanner.nextLine(); // To handle any remaining input
    }


    public static void readInvoiceRecord(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter order ID: ");
            int orderID = scanner.nextInt();

            String query = "SELECT order_total, order_date FROM orders WHERE orderID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, orderID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double orderTotal = rs.getDouble("order_total");
                Date orderDate = rs.getDate("order_date");
                System.out.printf("\nOrder Total: Php %.2f | Order Date: %s%n", orderTotal, orderDate);
            } else {
                System.out.println("[!] Order not found.");
            }
        } catch (SQLException e) {
            System.err.println("[!] Error while reading invoice record: " + e.getMessage());
        }
        scanner.nextLine();
    }

    public static void createPaymentRecord(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter payment ID: ");
            int orderID = scanner.nextInt();
            System.out.print("Enter payment method ID: ");
            int paymentMethodID = scanner.nextInt();
            System.out.print("Enter payment amount: ");
            double paymentAmount = scanner.nextDouble();

            String query = "INSERT INTO payment (paymentID, paymentmethodID, payment_date, amount) " +
                    "VALUES (?, ?, NOW(), ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, orderID); // Assuming orderID as paymentID for simplicity
            stmt.setInt(2, paymentMethodID);
            stmt.setDouble(3, paymentAmount);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nPayment record created successfully.");
            } else {
                System.out.println("\nFailed to create payment record.");
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error while creating payment record: " + e.getMessage());
        }
        scanner.nextLine();
    }

    public static void updatePaymentStatus(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter order ID: ");
            int orderID = scanner.nextInt();

            // Prompt user for status choice
            System.out.println("\nSelect the new payment status:");
            System.out.println("1. Completed");
            System.out.println("2. Pending");
            System.out.println("3. Cancelled");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            String newStatus;
            switch (choice) {
                case 1:
                    newStatus = "Completed";
                    break;
                case 2:
                    newStatus = "Pending";
                    break;
                case 3:
                    newStatus = "Cancelled";
                    break;
                default:
                    System.out.println("\n[!] Invalid choice. No changes made.");
                    return;
            }

            // Update the order status in the database
            String query = "UPDATE orders SET order_status = ? WHERE orderID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderID);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\n[!] Order payment status updated to '" + newStatus + "'.");
            } else {
                System.out.println("\n[!] Failed to update payment status. Order ID may not exist.");
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error while updating payment status: " + e.getMessage());
        }
    }


    public static void createReceipt(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter order ID: ");
            int orderID = scanner.nextInt();

            String query = "SELECT o.orderID, o.order_total, c.first_name, c.last_name, p.payment_date, p.amount " +
                    "FROM orders o " +
                    "JOIN customer c ON o.customerID = c.customerID " +
                    "JOIN payment p ON o.paymentID = p.paymentID " +
                    "WHERE o.orderID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, orderID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("\n==== AnimoMarket Receipt ====");
                System.out.printf("Order ID: %d%n", rs.getInt("orderID"));
                System.out.printf("Customer Name: %s %s%n", rs.getString("first_name"), rs.getString("last_name"));
                System.out.printf("Order Total: Php %.2f%n", rs.getDouble("order_total"));
                System.out.printf("Payment Date: %s%n", rs.getDate("payment_date"));
                System.out.printf("Amount Paid: Php %.2f%n", rs.getDouble("amount"));
            } else {
                System.out.println("\n[!] Receipt not found for the given order ID.");
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error while creating receipt: " + e.getMessage());
        }
        scanner.nextLine();
    }

    public static void checkDiscountEligibility(Scanner scanner) {
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.print("\nEnter payment ID: ");
            int paymentID = scanner.nextInt();

            // Check for discount eligibility
            String eligibilityQuery = "SELECT d.discountID, d.discount_name, d.minim_spend, d.discount_percent " +
                    "FROM discount d " +
                    "WHERE d.active = TRUE AND d.paymentID IS NULL " +
                    "AND EXISTS (SELECT 1 FROM payment p WHERE p.paymentID = ? AND p.amount >= d.minim_spend)";
            PreparedStatement eligibilityStmt = con.prepareStatement(eligibilityQuery);
            eligibilityStmt.setInt(1, paymentID);

            ResultSet rs = eligibilityStmt.executeQuery();

            if (rs.next()) {
                int discountID = rs.getInt("discountID");
                String discountName = rs.getString("discount_name");
                double discountPercent = rs.getDouble("discount_percent");

                System.out.printf("\nDiscount Eligible: %s%n", discountName);
                System.out.printf("Discount Percent: %.2f%%%n", discountPercent);

                // Check if the payment already has a discount
                String paymentCheckQuery = "SELECT discountID FROM payment WHERE paymentID = ?";
                PreparedStatement paymentCheckStmt = con.prepareStatement(paymentCheckQuery);
                paymentCheckStmt.setInt(1, paymentID);
                ResultSet paymentRs = paymentCheckStmt.executeQuery();

                if (paymentRs.next() && paymentRs.getInt("discountID") == 0) {
                    // Assign the paymentID to the discount in the discount table
                    String updateDiscountQuery = "UPDATE discount SET paymentID = ? WHERE discountID = ?";
                    PreparedStatement updateDiscountStmt = con.prepareStatement(updateDiscountQuery);
                    updateDiscountStmt.setInt(1, paymentID);
                    updateDiscountStmt.setInt(2, discountID);
                    updateDiscountStmt.executeUpdate();

                    // Assign the discountID to the payment in the payment table
                    String applyDiscountQuery = "UPDATE payment SET discountID = ? WHERE paymentID = ?";
                    PreparedStatement applyDiscountStmt = con.prepareStatement(applyDiscountQuery);
                    applyDiscountStmt.setInt(1, discountID);
                    applyDiscountStmt.setInt(2, paymentID);

                    int rowsAffected = applyDiscountStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("\n[!] Discount applied successfully.");
                    } else {
                        System.out.println("\n[!] Failed to apply the discount to the payment.");
                    }
                } else {
                    System.out.println("\nThis payment already has a discount or is not eligible.");
                }
            } else {
                System.out.println("\nNo eligible discounts available or minimum spend not met.");
            }
        } catch (SQLException e) {
            System.err.println("\n[!] Error while checking and applying discount: " + e.getMessage());
        }
        scanner.nextLine();
    }
}