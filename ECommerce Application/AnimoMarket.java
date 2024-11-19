import java.util.Scanner;

public class AnimoMarket {
    public static void main(String[] args) {
        if (DatabaseConnection.login()) { // Only navigate to the menu if login is successful
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\nWelcome to the AnimoMarket Management Database!");
                System.out.println("1. Order Processing");
                System.out.println("2. Customer Registration");
                System.out.println("3. Payment Transaction");
                System.out.println("4. Order Status");
                System.out.println("5. View Data");
                System.out.println("6. View Reports Generated");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character

                switch (choice) {
                    case 1:
                        System.out.println("Order Processing selected.");
                        // Call order processing method or class here
                        break;
                    case 2:
                        System.out.println("Customer Registration selected.");
                        // Call customer registration method or class here
                        break;
                    case 3:
                        System.out.println("Payment Transaction selected.");
                        PaymentTransaction.showMenu();
                        break;
                    case 4:
                        System.out.println("Order Status selected.");
                        // Call order status method or class here
                        break;
                    case 0:
                        exit = true;
                        System.out.println("[!] Exiting... Thank you for using the AnimoMarket Management Database.");
                        break;
                    default:
                        System.out.println("[!] Invalid choice. Please try again.");
                }
            }
            scanner.close();
        } else {
            System.out.println("[!] Access denied. Exiting program.");
        }
    }
}
