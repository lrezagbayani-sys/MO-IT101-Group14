import java.util.Scanner;

public class MotorPH_LoginSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("=== MotorPH Payroll System ===");
        System.out.print("Username: ");
        String user = input.nextLine();
        System.out.print("Password: ");
        String pass = input.nextLine();

        // Access Control Logic
        if (pass.equals("12345") && (user.equals("payroll_staff") || user.equals("employee"))) {
            System.out.println("\nLogin Successful.");
            // In a real OOP project, you would call the other classes here
        } else {
            System.out.println("\nAccess Denied. Program terminated.");
        }
        input.close();
    }
}
