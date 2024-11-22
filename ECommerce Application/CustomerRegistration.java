import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerRegistration {

    public void registerCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Customer Registration ===");

        // Input collection
        String lastName = getMandatoryInput(scanner, "Enter Last Name: ");
        String firstName = getMandatoryInput(scanner, "Enter First Name: ");
        String phoneNumber = getMandatoryInput(scanner, "Enter Phone Number: ");
        String email = getMandatoryInput(scanner, "Enter Email: ");
        String street = getMandatoryInput(scanner, "Enter Address (Street): ");
        String city = getMandatoryInput(scanner, "Enter City: ");
        String state = getMandatoryInput(scanner, "Enter State: ");
        String postalCode = getMandatoryInput(scanner, "Enter Postal Code: ");
        String country = getMandatoryInput(scanner, "Enter Country: ");

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Generate next customerID
            int customerID = getNextCustomerID(connection);

            // Insert data into the database
            String query = "INSERT INTO customer (customerID, last_name, first_name, phone_number, email, street, city, state, postal_code, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, customerID);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, street);
            preparedStatement.setString(7, city);
            preparedStatement.setString(8, state);
            preparedStatement.setString(9, postalCode);
            preparedStatement.setString(10, country);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("[!] Customer registered successfully with ID: " + customerID);
            } else {
                System.out.println("[!] Registration failed. Please try again.");
            }
        } catch (SQLException e) {
            System.err.println("[!] Error during registration: " + e.getMessage());
        }
    }

    private int getNextCustomerID(Connection connection) throws SQLException {
        String query = "SELECT MAX(customerID) FROM customer";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                int maxID = resultSet.getInt(1); // Get the current maximum customerID
                return maxID + 1; // Increment for the next ID
            } else {
                return 1; // Start from 1 if no records exist
            }
        }
    }

    private String getMandatoryInput(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("[!] This field is mandatory.");
            }
        } while (input.isEmpty());
        return input;
    }
}
