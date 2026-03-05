This README has been fully updated to align with the latest logic, file-handling methods, and the strict naming convention (MO_IT101_Group14.java) of your source code.

CP1 - MS2 Source Code
Basic Payroll Program (Group 14)
This program reads employee information and attendance records from CSV files, calculates total hours worked per payroll cutoff, applies government deductions, and displays a comprehensive salary summary.

How the Program Works
Imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

BufferedReader and FileReader: Used to read the CSV databases line by line.

Scanner: Used to capture user credentials and menu selections.

Main Class and Method
public class MO_IT101_Group14 {
public static void main(String[] args) {

MO_IT101_Group14 is the main class.

main method handles the secure login system and routes users to either the Employee or Payroll Staff portal.

Credentials and Roles
Username: employee or payroll_staff

Password: 12345

The system uses Role-Based Access Control to determine which menu options are displayed.

File Paths and Scanner
Employee Database.csv: Stores ID, Name, Birthday, and Hourly Rate.

Employee Attendance Record.csv: Stores login/logout logs for June to December 2024.

Scanner scanner = new Scanner(System.in); reads input from the terminal.

Get Employee Number (Employee Portal)
System.out.print("Enter your employee number: ");

The program asks the user for their ID. It includes a regex validation (!employeeNumber.matches("\\d+")) to catch invalid characters immediately.

Read Employee Details
try (BufferedReader reader = new BufferedReader(new FileReader("Employee Database.csv"))) {

Reads the CSV line by line, skipping the header.

Uses a Regex Split split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)") to correctly handle names that contain commas inside quotes.

If the ID is found, it stores the name and birthday.

Check if Employee Exists
If the ID is not found in the CSV, the program displays: Access Denied: Employee number unidentified. and terminates securely.

Payroll Processing (Staff Portal)
The staff can choose to process One employee or All employees.

Pre-Validation: For single employee processing, the system checks if the ID exists before asking for the month to avoid wasted input.

Month Selection: Choices range from June (1) to December (7), plus "Show All Months" (8).

Attendance Parsing & Calculation
Grace Period & Limits: Logins are recognized starting at 8:00 AM. Logouts are capped at 5:00 PM (17:00).

Lunch Break: The system automatically subtracts 1 hour for the lunch break.

Cutoff Split: - Days 1-15 go to the First Cutoff.

Days 16-31 go to the Second Cutoff.

Deduction Engine
Government deductions are calculated on the combined monthly gross:

SSS: 4.5% of gross.

PhilHealth: 3.0% (divided by 2).

Pag-IBIG: Fixed PHP 100.00.

Withholding Tax: A nested branching logic applies 20%, 25%, or 30% depending on the taxable income bracket.

Display Payroll Summary
System.out.printf("Gross Salary: %.3f\n", firstGross);

First Cutoff: Shows Hours, Gross, and Net (Deductions are not yet applied).

Second Cutoff: Shows Hours, Gross, an itemized list of all 4 deductions, and the final Net Salary.

Notes
CSV Location: Both Employee Database.csv and Employee Attendance Record.csv must be in the project’s root folder.

Precision: All monetary values and hours are displayed at 3 decimal places for maximum accuracy.

Security: The program uses System.exit(0) to ensure a clean and secure termination after errors or completion.

Development Team (Group 14)

Delos Santos, Q.L.

Palayaban, M.

Saga, G.M.

Agbayani, E.Z.

Elviña, J.N.
