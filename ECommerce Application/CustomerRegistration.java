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

        // Input for phone number
        String phone_number;
        do {
            System.out.print("Enter Phone Number (required): ");
            phone_number = scanner.nextLine().trim();
            if (phone_number.isEmpty()) {
                System.out.println("[!] Phone number is mandatory.");
            }
        } while (phone_number.isEmpty());

        // Input for email
        String email;
        do {
            System.out.print("Enter Email (required): ");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("[!] Email is mandatory.");
            }
        } while (email.isEmpty());

        // Input for street
        String street;
        do {
            System.out.print("Enter Address (required) starting with Street: ");
            street = scanner.nextLine().trim();
            if (street.isEmpty()) {
                System.out.println("[!] Street is mandatory.");
            }
        } while (street.isEmpty());

        // Input for city
        String city;
        do {
            System.out.print("Enter City (required): ");
            city = scanner.nextLine().trim();
            if (city.isEmpty()) {
                System.out.println("[!] City is mandatory.");
            }
        } while (city.isEmpty());

        // Input for state
        String state;
        do {
            System.out.print("Enter State (required): ");
            state = scanner.nextLine().trim();
            if (state.isEmpty()) {
                System.out.println("[!] State is mandatory.");
            }
        } while (state.isEmpty());

        // Input for postal_code
        String postal_code;
        do {
            System.out.print("Enter Postal Code (required): ");
            postal_code = scanner.nextLine().trim();
            if (postal_code.isEmpty()) {
                System.out.println("[!] Postal Code is mandatory.");
            }
        } while (postal_code.isEmpty());

        // Input for country
        String country;
        do {
            System.out.print("Enter Country (required): ");
            country = scanner.nextLine().trim();
            if (country.isEmpty()) {
                System.out.println("[!] Country is mandatory.");
            }
        } while (country.isEmpty());

        // Insert data into the database
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO customer (first_name, last_name, phone_number, email, street, city, state, postal_code, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phone_number);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, street);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, state);
            preparedStatement.setString(8, postal_code);
            preparedStatement.setString(9, country);

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

