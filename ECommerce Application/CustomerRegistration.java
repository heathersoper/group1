import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerRegistration {

    public void registerCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Customer Registration ===");

        // Input for first name
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine().trim();
        while (firstName.isEmpty()) {
            System.out.println("[!] First name is mandatory.");
            System.out.print("Enter First Name: ");
            firstName = scanner.nextLine().trim();
        }

        // Input for last name
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine().trim();
        while (lastName.isEmpty()) {
            System.out.println("[!] Last name is mandatory.");
            System.out.print("Enter Last Name: ");
            lastName = scanner.nextLine().trim();
        }

        // Input for contact number
        String contactNumber;
        do {
            System.out.print("Enter Contact Number (required): ");
            contactNumber = scanner.nextLine().trim();
            if (contactNumber.isEmpty()) {
                System.out.println("[!] Contact number is mandatory.");
            }
        } while (contactNumber.isEmpty());

        // Input for address
        String address;
        do {
            System.out.print("Enter Address (required): ");
            address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("[!] Address is mandatory.");
            }
        } while (address.isEmpty());

        // Insert data into the database
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO customer (first_name, last_name, contact_number, address) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, contactNumber);
            preparedStatement.setString(4, address);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("[!] Customer registered successfully.");
            } else {
                System.out.println("[!] Registration failed. Please try again.");
            }
        } catch (SQLException e) {
            System.err.println("[!] Error during registration: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        CustomerRegistration customerRegistration = new CustomerRegistration();
        customerRegistration.registerCustomer();
    }
}

