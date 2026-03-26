package com.mycompany.mo_it101_group14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class MO_IT101_Group14
{
    // CSV path constants for NetBeans and cross-platform compatibility.
    private static final String ATTENDANCE_CSV = "Employee Attendance Record.csv";
    private static final String DATABASE_CSV = "Employee Database.csv";

    // System navigation constants
    public static final int OPTION_ONE = 1;
    public static final int OPTION_TWO = 2;
    public static final int OPTION_THREE = 3;
    public static final int OPTION_MAX_MONTH = 8;

    // System capacity constants to prevent magic numbers
    public static final int MAX_EMPLOYEE_LIMIT = 100;
    public static final int MAX_ATTENDANCE_RECORDS = 5000;

    // -------------------------------------------------------------------------
    // MAIN ENTRY POINT (Login)
    // -------------------------------------------------------------------------

    public static void main(String[] args)
    {
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("--- MotorPH Payroll System ---\n\n");

        System.out.print("Please Enter Login Credentials to Continue\n");

        System.out.print("Username: ");
        String usernameInput = inputScanner.nextLine().trim();

        System.out.print("Password: ");
        String passwordInput = inputScanner.nextLine().trim();

        // Securing entry by validating against hardcoded system credentials
        if (passwordInput.equals("12345") && (usernameInput.equals("employee") || usernameInput.equals("payroll_staff")))
        {
            if (usernameInput.equals("employee"))
            {
                System.out.println("\nLOGIN SUCCESSFUL! \n\n--- Welcome, MotorPH Employee! ---");
                processEmployeePortal(inputScanner);
            }
            else
            {
                System.out.println("\nLOGIN SUCCESSFUL! \n\n--- Welcome, MotorPH Payroll Staff! ---");
                processStaffPortal(inputScanner);
            }
        }
        else
        {
            // Lockout protocol for unauthorized access attempts
            System.out.println("\nLOGIN FAILED.");
            System.out.println("\nAccess Denied: Invalid login credentials. Please try again.");
            System.out.println("\nPROGRAM TERMINATED.");
            System.exit(0);
        }
    }

    // -------------------------------------------------------------------------
    // HELPER METHODS (Modularity & Reusability)
    // -------------------------------------------------------------------------

    // Enhanced File Reader to handle GitHub/IDE path discrepancies
    public static BufferedReader openCSV(String fileName) {
        // List of potential locations where the CSV might be hidden, depending on the IDE
        String[] potentialPaths = {
                fileName,                                       // 1. Project Root (Standard)
                "src/" + fileName,                              // 2. Inside src folder (VS Code/Manual)
                "../" + fileName,                               // 3. One folder up (Terminal/Build folders)
                System.getProperty("user.dir") + "/" + fileName, // 4. Absolute System Path
                "MO_IT101_Group14/" + fileName                  // 5. For IntelliJ-specific sub-folder setup
        };

        for (String path : potentialPaths) {
            try {
                File fileCheck = new File(path);
                if (fileCheck.exists()) {
                    return new BufferedReader(new FileReader(path));
                }
            } catch (Exception e) {
                // Silently try the next path if this one fails
            }
        }

        // Final attempt: using the ClassLoader (Useful for JARs or complex IDE setups)
        try {
            java.io.InputStream is = MO_IT101_Group14.class.getResourceAsStream("/" + fileName);
            if (is != null) {
                return new BufferedReader(new java.io.InputStreamReader(is));
            }
        } catch (Exception e) { }

        return null;
    }

    // Validates employee existence to prevent downstream calculation errors
    public static boolean validateEmployee(String empId)
    {
        BufferedReader reader = openCSV(DATABASE_CSV);
        if (reader == null) return false;

        try
        {
            reader.readLine(); // Skip header
            String currentLine;
            while ((currentLine = reader.readLine()) != null)
            {
                String[] data = currentLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data[0].trim().equals(empId))
                {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        }
        catch (Exception e) {}
        return false;
    }

    // Applying 8AM-5PM window to comply with standard labor policies
    public static double computeDailyHours(String loginTime, String logoutTime)
    {
        String[] in = loginTime.trim().split(":");
        String[] out = logoutTime.trim().split(":");

        double logIn = Double.parseDouble(in[0]) + (Double.parseDouble(in[1]) / 60.0);
        double logOut = Double.parseDouble(out[0]) + (Double.parseDouble(out[1]) / 60.0);

        double effectiveLogin = Math.max(logIn, 8.0);
        double effectiveLogout = Math.min(logOut, 17.0);

        // Subtract 1 hour mandatory unpaid lunch break
        double dailyHours = (effectiveLogout - effectiveLogin) - 1.0;

        return Math.max(0, dailyHours); // Prevent negative hours
    }

    // --- Statutory Deductions ---
    public static double computeSSS(double gross)
    {
        return gross * 0.045;
    }

    public static double computePhilHealth(double gross)
    {
        return (gross * 0.03) / 2;
    }

    public static double computePagIbig(double gross)
    {
        return Math.min(gross * 0.02, 100.0); //Dynamic Calculations
    }

    // --- Tax Brackets ---
    // Applies withholding tax based on tiered income brackets
    public static double calculateTax(double taxableIncome)
    {
        if (taxableIncome > 20832 && taxableIncome <= 33333)
        {
            return (taxableIncome - 20833) * 0.20;
        }
        else if (taxableIncome > 33333 && taxableIncome <= 66667)
        {
            return 2500 + ((taxableIncome - 33333) * 0.25);
        }
        else if (taxableIncome > 66667)
        {
            return 10833 + ((taxableIncome - 66667) * 0.30);
        }
        return 0.0;
    }

    // -------------------------------------------------------------------------
    // EMPLOYEE PORTAL METHOD by Maribel
    // -------------------------------------------------------------------------

    public static void processEmployeePortal(Scanner scanner)
    {
        System.out.println("[1] Enter your employee number");
        System.out.println("[2] Exit the program");
        System.out.print("Select option: ");

        String optionChoice = scanner.nextLine().trim();

        if (optionChoice.equals(String.valueOf(OPTION_ONE)))
        {
            System.out.print("Enter your employee number: ");
            String employeeNumber = scanner.nextLine().trim();

            // Guard clause to prevent numerical parsing exceptions
            if (!employeeNumber.matches("\\d+"))
            {
                System.out.println("\nAccess Denied: Invalid characters identified. Please enter a valid employee number.");
                System.out.println("\nPROGRAM TERMINATED.");
                System.exit(0);
            }

            if (!validateEmployee(employeeNumber))
            {
                System.out.println("\nAccess Denied: Employee number unidentified. Please check the number again.");
                System.out.println("\nPROGRAM TERMINATED.");
                System.exit(0);
            }

            String employeeName = "";
            String employeeBirthday = "";

            BufferedReader reader = openCSV(DATABASE_CSV);
            if (reader != null)
            {
                try
                {
                    reader.readLine();
                    String currentLine;
                    while ((currentLine = reader.readLine()) != null)
                    {
                        String[] employeeData = currentLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                        if (employeeData[0].trim().equals(employeeNumber))
                        {
                            employeeName = employeeData[2].replace("\"", "").trim() + " " + employeeData[1].replace("\"", "").trim();
                            employeeBirthday = employeeData[3].trim();
                            break;
                        }
                    }
                    reader.close();
                }
                catch (Exception e) {}
            }

            System.out.println("\n--- MOTORPH EMPLOYEE DETAILS ---\n");
            System.out.println("Employee #: " + employeeNumber);
            System.out.println("Name: " + employeeName);
            System.out.println("Birthday: " + employeeBirthday);
            System.out.println("\n-------------------------------------------------------");
            System.out.println("\nThank you for using the MotorPH Payroll System!");
        }
        else if (optionChoice.equals(String.valueOf(OPTION_TWO)))
        {
            System.out.println("\nThank you for using the MotorPH Payroll System!");
            System.exit(0);
        }
        else
        {
            if (optionChoice.isEmpty() || !optionChoice.matches("\\d+"))
            {
                System.out.println("\nAccess Denied: Invalid characters identified. Please select one numerical symbol from the given options.");
            }
            else
            {
                System.out.println("\nAccess Denied: Invalid option identified. Please select one from the given options.");
            }
            System.out.println("\nPROGRAM TERMINATED.");
            System.exit(0);
        }
    }

    // -------------------------------------------------------------------------
    // PAYROLL STAFF PORTAL METHOD by Maribel
    // -------------------------------------------------------------------------

    public static void processStaffPortal(Scanner scanner)
    {
        System.out.println("[1] Process Payroll");
        System.out.println("[2] Exit the program");
        System.out.print("Select option: ");

        String staffChoice = scanner.nextLine().trim();

        if (staffChoice.equals(String.valueOf(OPTION_TWO)))
        {
            System.out.println("\nThank you for using the MotorPH Payroll System!");
            System.exit(0);
        }
        else if (!staffChoice.equals(String.valueOf(OPTION_ONE)))
        {
            if (staffChoice.isEmpty() || !staffChoice.matches("\\d+"))
            {
                System.out.println("\nAccess Denied: Invalid characters identified. Please select one numerical symbol from the given options.");
            }
            else
            {
                System.out.println("\nAccess Denied: Invalid option identified. Please select one from the given options.");
            }
            System.out.println("\nPROGRAM TERMINATED.");
            System.exit(0);
        }

        System.out.println("\n[1] One employee");
        System.out.println("[2] All employees");
        System.out.println("[3] Exit the program");
        System.out.print("Select option: ");

        String subChoice = scanner.nextLine().trim();

        if (subChoice.equals(String.valueOf(OPTION_ONE)))
        {
            System.out.print("Enter the employee number: ");
            String targetEmployee = scanner.nextLine().trim();
            processPayrollData(scanner, targetEmployee, true);
        }
        else if (subChoice.equals(String.valueOf(OPTION_TWO)))
        {
            processPayrollData(scanner, "ALL", false);
        }
        else if (subChoice.equals(String.valueOf(OPTION_THREE)))
        {
            System.out.println("\nThank you for using the MotorPH Payroll System!");
            System.exit(0);
        }
        else
        {
            if (subChoice.isEmpty() || !subChoice.matches("\\d+"))
            {
                System.out.println("\nAccess Denied: Invalid characters identified. Please select one numerical symbol from the given options.");
            }
            else
            {
                System.out.println("\nAccess Denied: Invalid option identified. Please select one from the given options.");
            }
            System.out.println("\nPROGRAM TERMINATED.");
            System.exit(0);
        }
    }

    // -------------------------------------------------------------------------
    // CORE PAYROLL DATA ENGINE by Ethan & Quesha
    // -------------------------------------------------------------------------

    //Prints the promopt for the months choice
    public static int promptMonthChoice(Scanner scanner, boolean isSingleEmployee) {
        System.out.println("Select the month to process payroll:");
        System.out.println("1. June");
        System.out.println("2. July");
        System.out.println("3. August");
        System.out.println("4. September");
        System.out.println("5. October");
        System.out.println("6. November");
        System.out.println("7. December");
        if (isSingleEmployee) System.out.println("8. Show All Months");
        System.out.print("Select option: ");

        //Returns error handle if the input is an invalid character or number
        String raw = scanner.nextLine().trim();
        int choice = 0;
        try {
            choice = Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            System.out.println("\nAccess Denied: Invalid characters identified. Please select one numerical symbol from the given options.");
            System.out.println("\nPROGRAM TERMINATED.");
            System.exit(0);
        }
        if (choice < 1 || (isSingleEmployee && choice > 8) || (!isSingleEmployee && choice > 7)) {
            System.out.println("\nAccess Denied: Invalid option identified. Please select one from the given options.");
            System.out.println("\nPROGRAM TERMINATED.");
            System.exit(0);
        }
        return choice;
    }

    //Returns the appropriate month for inputs from prompmtMonthChoice
    public static String getMonthName(int month) {
        switch (month) {
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "";
        }
    }

    //Reusable capitilizing method for the Months
    public static String getMonthNameUpper(int month) {
        return getMonthName(month).toUpperCase();
    }

    //Identifies all months to have 31 days, except June, September and November
    public static String getLastDay(int month) {
        switch (month) {
            case 6:
            case 9:
            case 11:
                return "30";
            default:
                return "31";
        }
    }

    //Identifies all months last day to end with (30th), other than July, August, October and December
    public static String getLastDaySuffix(int month) {
        switch (month) {
            case 7:
            case 8:
            case 10:
            case 12:
                return "ST";
            default:
                return "TH";
        }
    }

    //Loads and read the Employee Attendance Record CSV, then assign variables for the array
    public static int loadEmployeeDatabase(String targetEmployee, boolean isSingleEmployee,
                                           String[] empIds, String[] empNames, String[] empBirthdays, double[] empRates) {
        int count = 0;
        BufferedReader reader = openCSV(DATABASE_CSV);
        if (reader == null) return count;

        try {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String id = data[0].trim();
                if (!isSingleEmployee || id.equals(targetEmployee)) {
                    empIds[count] = id;
                    empNames[count] = data[2].replace("\"", "").trim() + " " + data[1].replace("\"", "").trim();
                    empBirthdays[count] = data[3].trim();
                    String rateStr = data[data.length - 1].replace("\"", "").replace(",", "").trim();
                    empRates[count] = Double.parseDouble(rateStr);
                    count++;
                }
            }
            reader.close();
        } catch (Exception e) {
        }
        return count;
    }

    //Loads and read the Employee Attendance Record CSV, then assign variables for the array
    public static int loadAttendanceRecords(String[] attIds, int[] attMonths, int[] attDays, double[] attHours) {
        int count = 0;
        BufferedReader reader = openCSV(ATTENDANCE_CSV);
        if (reader == null) return count;

        try {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                String[] dateParts = data[3].trim().split("/");
                attIds[count] = data[0].trim();
                attMonths[count] = Integer.parseInt(dateParts[0]);
                attDays[count] = Integer.parseInt(dateParts[1]);
                attHours[count] = computeDailyHours(data[4], data[5]);
                count++;
            }
            reader.close();
        } catch (Exception e) {
        }
        return count;
    }

    //Prints the employee's header and adding the variables from the previous loaders
    public static void printPayrollHeader(String empId, String empName, String empBirthday,
                                          int monthChoice) {
        if (monthChoice >= 1 && monthChoice <= 7) {
            int m = monthChoice + 5;
            System.out.println("\n--- PAYROLL OF MOTORPH EMPLOYEE #" + empId
                    + " FOR " + getMonthNameUpper(m)
                    + " 1ST TO " + getLastDay(m) + getLastDaySuffix(m) + " ---");
        } else {
            System.out.println("\n--- PAYROLL OF MOTORPH EMPLOYEE #" + empId
                    + " FOR JUNE TO DECEMBER ---");
        }
        System.out.println("\nEmployee #: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Birthday: " + empBirthday);
    }

    //Computes the first and last cutoffs of each month (First 1-15, Second = <=16)
    public static double[] computeCutoffHours(String empId, int month,
                                              String[] attIds, int[] attMonths, int[] attDays, double[] attHours, int attendanceCount) {
        double raw1 = 0.0, raw2 = 0.0;
        for (int i = 0; i < attendanceCount; i++) {
            if (attIds[i].equals(empId) && attMonths[i] == month) {
                if (attDays[i] <= 15) raw1 += attHours[i];
                else raw2 += attHours[i];
            }
        }
        double h1 = (long) (raw1 * 1_000_000_000_000.0) / 1_000_000_000_000.0;
        double h2 = (long) (raw2 * 1_000_000_000_000.0) / 1_000_000_000_000.0;
        return new double[]{h1, h2};
    }

    //Displays the employee monthly payroll, seperating cut-offs and calculating overall deduction
    public static void printMonthlyPayroll(String empId, int actualMonth, double hourlyRate,
                                           String[] attIds, int[] attMonths, int[] attDays, double[] attHours, int attendanceCount,
                                           boolean isSingleEmployee, int monthChoice) {
        if (isSingleEmployee && monthChoice == 8) {
            System.out.println("\n--- " + getMonthNameUpper(actualMonth) + " PAYROLL ---");
        }

        double[] cutoffs = computeCutoffHours(empId, actualMonth,
                attIds, attMonths, attDays, attHours, attendanceCount);
        double firstCutoffHours = cutoffs[0];
        double secondCutoffHours = cutoffs[1];

        double firstGross = firstCutoffHours * hourlyRate;
        double secondGross = secondCutoffHours * hourlyRate;
        double combinedGross = firstGross + secondGross;

        double sss = 0, ph = 0, pi = 0, tax = 0;
        if (combinedGross > 0) {
            sss = computeSSS(combinedGross);
            ph = computePhilHealth(combinedGross);
            pi = computePagIbig(combinedGross);
            tax = calculateTax(combinedGross - (sss + ph + pi));
        }

        double totalDeductions = sss + ph + pi + tax;
        double netCutoff2 = secondGross - totalDeductions;
        String monthName = getMonthName(actualMonth);

        System.out.println("\n1. Cutoff Date: " + monthName + " 1 to 15");
        System.out.printf("2. Total Hours Worked: %.12f\n", firstCutoffHours);
        System.out.printf("3. Gross Salary: PHP %.12f\n", firstGross);
        System.out.printf("4. Net Salary: PHP %.12f\n\n", firstGross);

        System.out.println("1. Cutoff Date: " + monthName + " 16 to End");
        System.out.printf("2. Total Hours Worked: %.12f\n", secondCutoffHours);
        System.out.printf("3. Gross Salary: PHP %.12f\n", secondGross);
        System.out.println("4. Each Deduction:");
        System.out.printf("- SSS: PHP %.12f\n", sss);
        System.out.printf("- PhilHealth: PHP %.12f\n", ph);
        System.out.printf("- Pag-IBIG: PHP %.12f\n", pi);
        System.out.printf("- Withholding Tax: PHP %.12f\n", tax);
        System.out.printf("5. Total Deductions: PHP %.12f\n", totalDeductions);
        System.out.printf("6. Net Salary: PHP %.12f\n", netCutoff2);
    }

    //Error handling, employee database displaying and processing end
    public static void processPayrollData(Scanner scanner, String targetEmployee, boolean isSingleEmployee) {
        if (isSingleEmployee) { //Error handling for the employee database input
            if (!targetEmployee.matches("\\d+")) {
                System.out.println("\nAccess Denied: Invalid characters identified. Please enter a valid employee number.");
                System.out.println("\nPROGRAM TERMINATED.");
                System.exit(0);
            }
            if (!validateEmployee(targetEmployee)) {
                System.out.println("\nAccess Denied: Employee number unidentified. Please check the number again.");
                System.out.println("\nPROGRAM TERMINATED.");
                System.exit(0);
            }
        }

        int monthChoice = promptMonthChoice(scanner, isSingleEmployee);

        //Applies Employee Database capacity to only 100, then counts the number of employee data
        String[] empIds = new String[MAX_EMPLOYEE_LIMIT];
        String[] empNames = new String[MAX_EMPLOYEE_LIMIT];
        String[] empBirthdays = new String[MAX_EMPLOYEE_LIMIT];
        double[] empRates = new double[MAX_EMPLOYEE_LIMIT];
        int employeeCount = loadEmployeeDatabase(targetEmployee, isSingleEmployee,
                empIds, empNames, empBirthdays, empRates);

        //Applies Employee Attendance capacity to only 100, then counts the number of attendance data
        String[] attIds = new String[MAX_ATTENDANCE_RECORDS];
        int[] attMonths = new int[MAX_ATTENDANCE_RECORDS];
        int[] attDays = new int[MAX_ATTENDANCE_RECORDS];
        double[] attHours = new double[MAX_ATTENDANCE_RECORDS];
        int attendanceCount = loadAttendanceRecords(attIds, attMonths, attDays, attHours);

        //Converts the Month Choice input to the appropriate month
        int startMonth = (monthChoice == 8) ? 6 : (monthChoice + 5);
        int endMonth = (monthChoice == 8) ? 12 : (monthChoice + 5);

        //Process data for selecting option for all employees
        for (int empIndex = 0; empIndex < employeeCount; empIndex++) {
            printPayrollHeader(empIds[empIndex], empNames[empIndex],
                    empBirthdays[empIndex], monthChoice);

            for (int actualMonth = startMonth; actualMonth <= endMonth; actualMonth++) {
                printMonthlyPayroll(empIds[empIndex], actualMonth, empRates[empIndex],
                        attIds, attMonths, attDays, attHours, attendanceCount,
                        isSingleEmployee, monthChoice);
            }
        }

        //System confirmation of the payroll has successfully processed
        System.out.println("\n-------------------------------------------------------");
        System.out.println("\nPayroll Processed Successfully.\n\nThank you for using the MotorPH Payroll System!");
        System.exit(0);
    }
}
