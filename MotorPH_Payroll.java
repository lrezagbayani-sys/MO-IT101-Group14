import java.util.Scanner;

public class MotorPH_Payroll {

    // --- DATA LAYER ---
    static String[] firstNames = {"Samuel", "Maria"};
    static String[] lastNames = {"Ibarra", "Santos"};
    static String[] ids = {"10001", "10002"};
    static String[] birthdays = {"April 2, 1979", "May 17, 2000"};
    static double[] hourlyRates = {500.0, 500.0};

    static int[] cutoff1Hours = {80, 75};
    static int[] cutoff2Hours = {88, 82};

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== MotorPH Payroll System ===");
        System.out.print("Username: ");
        String username = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();

        if (password.equals("12345") &&
                (username.equals("payroll_staff") || username.equals("employee"))) {

            if (username.equals("employee")) {
                runEmployeePortal(input);
            } else {
                runStaffPortal(input);
            }

        } else {
            System.out.println("\nIncorrect username and/or password. Program terminated.");
            System.exit(0);
        }
        input.close();
    }

    public static void runEmployeePortal(Scanner input) {
        System.out.println("\n[MOTORPH EMPLOYEE PORTAL]");
        System.out.println("1. Enter your employee number");
        System.out.println("2. Exit the program");
        System.out.print("Select: ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            System.out.print("Enter Employee Number: ");
            String idInput = input.nextLine();
            int index = findEmployeeIndex(idInput);

            if (index != -1) {
                displayEmployeeDetails(index);
                terminateNormally();
            } else {
                System.out.println("\nEmployee number does not exist. Program terminated.");
                printDivider();
                terminateNormally();
            }
        } else if (choice.equals("2")) {
            terminateNormally();
        } else {
            terminateWithInvalid();
        }
    }

    public static void runStaffPortal(Scanner input) {
        System.out.println("\n[MOTORPH PAYROLL STAFF PORTAL]");
        System.out.println("1. Process Payroll");
        System.out.println("2. Exit the program");
        System.out.print("Select: ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            System.out.println("\nPROCESS PAYROLL OPTIONS:");
            System.out.println("1. One employee\n2. All employees\n3. Exit");
            System.out.print("Select: ");
            String subChoice = input.nextLine();

            if (subChoice.equals("1")) {
                System.out.print("Enter Employee Number: ");
                String idInput = input.nextLine();
                int index = findEmployeeIndex(idInput);
                if (index != -1) {
                    // Staff view: Details + the new Payroll Layout
                    displayEmployeeDetails(index);
                    calculatePayroll(index);
                    printDivider();
                    terminateNormally();
                } else {
                    System.out.println("\nEmployee number does not exist. Program terminated.");
                    printDivider();
                    terminateNormally();
                }
            } else if (subChoice.equals("2")) {
                for (int i = 0; i < ids.length; i++) {
                    displayEmployeeDetails(i);
                    calculatePayroll(i);
                    printDivider();
                }
                terminateNormally();
            } else if (subChoice.equals("3")) {
                terminateNormally();
            } else {
                terminateWithInvalid();
            }
        } else if (choice.equals("2")) {
            terminateNormally();
        } else {
            terminateWithInvalid();
        }
    }

    public static void printDivider() {
        System.out.println("\n------------------------------------------------------------------");
    }

    public static void displayEmployeeDetails(int index) {
        System.out.println("\n--- EMPLOYEE DETAILS ---");
        System.out.println("Employee #: " + ids[index]);
        System.out.println("Employee Name: " + firstNames[index] + " " + lastNames[index]);
        System.out.println("Birthday: " + birthdays[index]);
    }

    public static void calculatePayroll(int index) {
        // Calculations
        int h1 = cutoff1Hours[index], h2 = cutoff2Hours[index];
        double rate = hourlyRates[index];
        double gross1 = h1 * rate, gross2 = h2 * rate;

        double sss = gross2 * 0.045, ph = gross2 * 0.02, pi = 100.0, tax = gross2 * 0.10;
        double totalDeductions = sss + ph + pi + tax;
        double net2 = gross2 - totalDeductions;

        // --- NEW DISPLAY LAYOUT BASED ON YOUR DOCUMENT ---
        System.out.println("\n--- PAYROLL INFO ---");

        // Cutoff 1
        System.out.println("Cutoff Date: June 1 to June 15");
        System.out.println("Total Hours Worked: " + h1);
        System.out.printf("Gross Salary: PHP %.2f\n", gross1);
        System.out.printf("Net Salary: PHP %.2f\n", gross1);

        // Cutoff 2
        System.out.println("\nCutoff Date: June 16 to June 30 (Second payout includes all deductions)");
        System.out.println("Total Hours Worked: " + h2);
        System.out.printf("Gross Salary: PHP %.2f\n", gross2);

        System.out.println("\nEach Deduction:");
        System.out.printf("    SSS: PHP %.2f\n", sss);
        System.out.printf("    PhilHealth: PHP %.2f\n", ph);
        System.out.printf("    Pag-IBIG: PHP %.2f\n", pi);
        System.out.printf("    Tax: PHP %.2f\n", tax);

        System.out.printf("Total Deductions = PHP %.2f\n", totalDeductions);
        System.out.printf("Net Salary: PHP %.2f\n", net2);
    }

    public static void terminateNormally() {
        System.out.println("\nThank you for using the MotorPH Payroll System!");
        System.out.println("\nPROGRAM END.");
        System.exit(0);
    }

    public static void terminateWithInvalid() {
        System.out.println("\nInvalid number selected. Please select a number within the choices. Program terminated.");
        printDivider();
        terminateNormally();
    }

    public static int findEmployeeIndex(String id) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i].equals(id)) return i;
        }
        return -1;
    }
}
