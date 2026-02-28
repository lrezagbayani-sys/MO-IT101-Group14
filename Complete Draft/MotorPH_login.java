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
        System.out.println("=== MotorPH Payroll System (Final) ===");
        
        // Ask for the username
        System.out.print("Username: ");
        String username = input.nextLine();
        
        // Ask for the password
        System.out.print("Password: ");
        String password = input.nextLine();

        // Check if the credentials match the requirements (12345 for password)
        if (password.equals("12345") && 
           (username.equals("payroll_staff") || username.equals("employee"))) {

            // Redirect to Employee Portal if the user is an employee
            if (username.equals("employee")) {
                runEmployeePortal(input);
            } 
            // Redirect to Staff Portal if the user is a payroll staff
            else {
                runStaffPortal(input);
            }

        } else {
            // Error message if login fails
            System.out.println("\nIncorrect username and/or password. Program terminated.");
            System.exit(0); // Exit the program immediately
        }
        
        // Close the scanner to prevent memory leaks
        input.close();
    }
