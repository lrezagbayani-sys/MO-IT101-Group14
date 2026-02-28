import java.io.BufferedReader; // Import for reading the file line by line
import java.io.FileReader;     // Import for opening the CSV file
import java.util.Scanner;      // Import for getting keyboard input from user

public class MotorPH_Payroll_Final {

    // Define the file name as a constant for easy access
    private static final String CSV_FILE = "Employee Roster and Payroll Computation - Employee Roster and Payroll Computation.csv";

    public static void main(String[] args) {
        // Create a scanner object to read input from the console
        Scanner input = new Scanner(System.in);

        // Display the system header
        System.out.println("=== MotorPH Payroll System ===");
        
        // Ask for the username
        System.out.print("Username: ");
        String username = input.nextLine();
        
        // Ask for the password
        System.out.print("Password: ");
        String password = input.nextLine();

        // Check if the credentials match the requirements (12345 for password)
        if (password.equals("12345")) {

            // Logic for Payroll Staff Login
            if (username.equals("payroll_staff")) {
                // ADDED: Fun personalized welcome message for Staff
                System.out.println("\nLogin Successful! Welcome, Payroll Staff.");
                runStaffPortal(input);
            } 
            // Logic for Employee Login
            else if (username.equals("employee")) {
                // ADDED: Fun personalized welcome message for Employees
                System.out.println("\nLogin Successful! Welcome, MotorPH Employee.");
                runEmployeePortal(input);
            } 
            // If the password is correct, but the username isn't one of the two roles
            else {
                System.out.println("\nAccess Denied: Unknown Username. Program terminated.");
                System.exit(0);
            }

        } else {
            // Error message if password fails
            System.out.println("\nIncorrect username and/or password. Program terminated.");
            System.exit(0); // Exit the program immediately
        }
        
        // Close the scanner to prevent memory leaks
        input.close();
    }
    
    // Note: The portal methods (runEmployeePortal/runStaffPortal) remain 
    // exactly the same as the previous version I sent you.
}
