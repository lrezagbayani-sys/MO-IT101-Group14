package com.mycompany.motorphsalaryattendance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.Scanner;

public class MotorPHSalaryAttendance {

    private static final String ATTENDANCE_CSV = "Employee Attendance Record.csv";
    private static final String DATABASE_CSV   = "Employee Database.csv";
    private static final String SSS_CSV        = "SSS Deduction.csv";

    /* Classes*/

    static class HoursWorked {
        int attendDay;
        int attendMonth;
        int attendYear;
        double logInTime;
        double logOutTime;
        double hoursWorked;

        public void setAttendanceDate(int day, int month, int year) {
            if (day < 1 || day > 31)  throw new IllegalArgumentException("Invalid day");
            if (month < 1 || month > 12) throw new IllegalArgumentException("Invalid month");
            if (year < 0)             throw new IllegalArgumentException("Invalid year");

            this.attendDay   = day;
            this.attendMonth = month;
            this.attendYear  = year;
        }

        public void setLogTimes(double logIn, double logOut) {
            if (logIn < 0 || logIn > 24)   throw new IllegalArgumentException("Invalid log-in");
            if (logOut < 0 || logOut > 24)  throw new IllegalArgumentException("Invalid log-out");
            if (logOut <= logIn)            throw new IllegalArgumentException("Log-out must be after log-in");

            this.logInTime  = Math.max(logIn, 8.0);
            this.logOutTime = Math.min(logOut, 17.0);
        }

        public double calculateHours() {
            hoursWorked = logOutTime - logInTime - 1.0;
            if (hoursWorked < 0) hoursWorked = 0;
            return hoursWorked;
        }

        public double getHoursWorked() { return hoursWorked; }

        public static void printHeader() {
            System.out.printf("%-12s %-10s %-8s %-8s%n",
                    "Date", "Log-In", "Log-Out", "Hours");
            System.out.println("------------------------------------------------------------");
        }

        public void printRow() {
            String date = String.format("%02d/%02d/%04d", attendMonth, attendDay, attendYear);
            System.out.printf("%-12s %-10.2f %-8.2f %-12.2f%n",
                    date, logInTime, logOutTime, hoursWorked);
        }
    }

    static class AttendanceRecord {
        String employeeId;
        String lastName;
        String firstName;
        HoursWorked hw = new HoursWorked();

        double getHours() { return hw.getHoursWorked(); }
    }

    static class HourlyRate {
        String employeeId;
        String firstName;
        String lastName;
        double ratePerHour;

        public HourlyRate(String employeeId, String lastName, String firstName, double ratePerHour) {
            if (ratePerHour < 0) throw new IllegalArgumentException("Rate cannot be negative");
            this.employeeId  = employeeId;
            this.lastName    = lastName;
            this.firstName   = firstName;
            this.ratePerHour = ratePerHour;
        }

        public double getRatePerHour() { return ratePerHour; }
    }

    static class SemiMonthlyPay {
        String employeeId;
        String firstName;
        String lastName;
        int month;
        int year;
        int cutoff;

        double hoursWorked;
        double hourlyRate;
        double grossPay;

        double pagibigDeduction;
        double philhealthDeduction;
        double sssDeduction;
        double taxDeduction;
        double totalDeduction;
        double netPay;

        private static double computePagibig(double gross) {
            if (gross >= 1000 && gross <= 1500) return gross * 0.03;
            if (gross > 1500)                   return gross * 0.04;
            return 0;
        }

        private static double computePhilhealth(double gross) {
            return gross * 0.03;
        }

        private static double computeSSS(double gross, List<double[]> sssBrackets) {
            for (double[] bracket : sssBrackets) {
                double min       = bracket[0];
                double max       = bracket[1];
                double deduction = bracket[2];
                if (gross >= min && gross <= max) return deduction;
            }
            return 0;
        }

        private static double computeTax(double gross) {
            if (gross <= 20832) return 0;
            if (gross <= 33332) return gross * 0.20;
            if (gross <= 66666) return 2500 + gross * 0.25;
            if (gross <= 166666) return 10833 + gross * 0.30;
            return 10833 + gross * 0.30;
        }

        public void applyDeductions(List<double[]> sssBrackets) {
            if (cutoff != 2) {
                pagibigDeduction    = 0;
                philhealthDeduction = 0;
                sssDeduction        = 0;
                taxDeduction        = 0;
                totalDeduction      = 0;
                netPay              = grossPay;
                return;
            }

            pagibigDeduction    = computePagibig(grossPay);
            philhealthDeduction = computePhilhealth(grossPay);
            sssDeduction        = computeSSS(grossPay, sssBrackets);
            taxDeduction        = computeTax(grossPay);
            totalDeduction      = pagibigDeduction + philhealthDeduction + sssDeduction + taxDeduction;
            netPay              = grossPay - totalDeduction;
        }

        public static void printHeader(int cutoff) {
            if (cutoff == 1) {
                System.out.printf("%-12s %-15s %-15s %-12s %-14s %-15s%n",
                        "Employee #", "First Name", "Last Name",
                        "Hourly Rate", "Hours Worked", "Gross Pay");
            } else {
                System.out.printf("%-12s %-15s %-15s %-12s %-14s %-15s %-12s %-14s %-10s %-15s %-15s%n",
                        "Employee #", "First Name", "Last Name",
                        "Hourly Rate", "Hours Worked", "Gross Pay",
                        "Pag-IBIG", "PhilHealth", "SSS", "Tax", "Net Pay");
            }
            System.out.println("-".repeat(cutoff == 1 ? 80 : 145));
        }

        public void printRow() {
            if (cutoff == 1) {
                System.out.printf("%-12s %-15s %-15s %-12.2f %-14.2f PHP %.2f%n",
                        employeeId, firstName, lastName,
                        hourlyRate, hoursWorked, grossPay);
            } else {
                System.out.printf("%-12s %-15s %-15s %-12.2f %-14.2f %-15.2f %-12.2f %-14.2f %-10.2f %-15.2f PHP %.2f%n",
                        employeeId, firstName, lastName,
                        hourlyRate, hoursWorked, grossPay,
                        pagibigDeduction, philhealthDeduction, sssDeduction,
                        taxDeduction, netPay);
            }
        }
    }

    /* Parsing Functions */

    private static double parseTime(String raw) {
        raw = raw.trim();
        if (raw.contains(":")) {
            String[] parts = raw.split(":");
            int h = Integer.parseInt(parts[0].trim());
            int m = Integer.parseInt(parts[1].trim());
            return h + m / 60.0;
        }
        return Double.parseDouble(raw);
    }

    private static int[] parseDate(String raw) {
        raw = raw.trim().replaceAll("\\s*/\\s*", "/");
        String[] p = raw.split("/");
        int month = Integer.parseInt(p[0]);
        int day   = Integer.parseInt(p[1]);
        int year  = Integer.parseInt(p[2]);
        return new int[]{day, month, year};
    }

    /* Prompt helpers + Error Returns */

    private static Integer promptMonth(Scanner input) {
        while (true) {
            System.out.print("Select Month (1-12) or type 'All': ");
            String raw = input.nextLine().trim();

            if (raw.equalsIgnoreCase("All") || raw.isEmpty()) return null;

            try {
                int month = Integer.parseInt(raw);
                if (month >= 1 && month <= 12) return month;
                else System.out.println("  [!] Invalid month. Please enter a number between 1 and 12, or 'All'.");
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input. Please enter a number between 1 and 12, or 'All'.");
            }
        }
    }

    private static Integer promptYear(Scanner input) {
        while (true) {
            System.out.print("Enter Year (4-digit) or type 'All': ");
            String raw = input.nextLine().trim();

            if (raw.equalsIgnoreCase("All") || raw.isEmpty()) return null;

            try {
                int year = Integer.parseInt(raw);
                if (raw.length() == 4 && year >= 1900 && year <= 2100) return year;
                else System.out.println("  [!] Invalid year. Please enter a valid 4-digit year (e.g. 2024), or 'All'.");
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input. Please enter a valid 4-digit year (e.g. 2024), or 'All'.");
            }
        }
    }

    private static String promptEmployeeId(Scanner input, boolean allowAll) {
        while (true) {
            System.out.print(allowAll ? "Enter Employee Number or type 'All': " : "Enter Your Employee Number: ");
            String raw = input.nextLine().trim();

            if (raw.isEmpty()) {
                System.out.println("  [!] Employee number cannot be empty. Please try again.");
                continue;
            }
            if (allowAll && raw.equalsIgnoreCase("All")) return "All";
            if (raw.matches("\\d+")) return raw;

            System.out.println("  [!] Invalid employee number. Please enter digits only" +
                    (allowAll ? ", or 'All'" : "") + ".");
        }
    }

    private static String promptMenuChoice(Scanner input, String prompt, String... validChoices) {
        Set<String> valid = new HashSet<>(Arrays.asList(validChoices));
        while (true) {
            System.out.print(prompt);
            String raw = input.nextLine().trim();
            if (valid.contains(raw)) return raw;
            System.out.println("  [!] Invalid choice. Please enter one of: " + String.join(", ", validChoices));
        }
    }

    private static String promptUsername(Scanner input) {
        while (true) {
            System.out.print("Username: ");
            String raw = input.nextLine().trim();
            if (!raw.isEmpty()) return raw;
            System.out.println("  [!] Username cannot be empty. Please try again.");
        }
    }

    private static String promptPassword(Scanner input) {
        while (true) {
            System.out.print("Password: ");
            String raw = input.nextLine().trim();
            if (!raw.isEmpty()) return raw;
            System.out.println("  [!] Password cannot be empty. Please try again.");
        }
    }

    private static int promptInt(Scanner input, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String raw = input.nextLine().trim();

            if (raw.isEmpty()) { System.out.println("  [!] Input cannot be empty. Please try again."); continue; }
            if (!raw.matches("-?\\d+")) { System.out.println("  [!] Invalid input. Please enter numbers only."); continue; }
            if (raw.replaceAll("-", "").length() > 6) { System.out.println("  [!] Input is too large. Please enter a valid number."); continue; }

            int value;
            try { value = Integer.parseInt(raw); }
            catch (NumberFormatException e) { System.out.println("  [!] Input is out of range. Please try again."); continue; }

            if (value < min || value > max) { System.out.println("  [!] Value must be between " + min + " and " + max + "."); continue; }

            return value;
        }
    }

    private static int[] getMonthYearCutoff(Scanner input) {
        int month  = promptInt(input, "Enter Month (1-12): ",                              1,    12);
        int year   = promptInt(input, "Enter Year (4-digit): ",                         1900,  9999);
        int cutoff = promptInt(input, "Enter Cutoff (1 = Days 1-16 | 2 = Days 17-31): ",  1,     2);
        return new int[]{month, year, cutoff};
    }

    private static void promptReturnToMenu(Scanner input) {
        System.out.print("\nPress Enter to return to menu: ");
        input.nextLine();
    }

    /* CSV Scanners */

    public static Map<String, List<AttendanceRecord>> loadAttendance(int maxRows) {
        Map<String, List<AttendanceRecord>> map = new LinkedHashMap<>();
        int rowsRead = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ATTENDANCE_CSV))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null && rowsRead < maxRows) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] cols = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)|\t");
                if (cols.length < 6) continue;

                try {
                    AttendanceRecord rec = new AttendanceRecord();
                    rec.employeeId = cols[0].replace("\"", "").trim();
                    rec.lastName   = cols[1].replace("\"", "").trim();
                    rec.firstName  = cols[2].replace("\"", "").trim();

                    int[] date = parseDate(cols[3].replace("\"", ""));
                    rec.hw.setAttendanceDate(date[0], date[1], date[2]);

                    double logIn  = parseTime(cols[4].replace("\"", ""));
                    double logOut = parseTime(cols[5].replace("\"", ""));
                    rec.hw.setLogTimes(logIn, logOut);
                    rec.hw.calculateHours();

                    map.computeIfAbsent(rec.employeeId, k -> new ArrayList<>()).add(rec);
                    rowsRead++;
                } catch (Exception ignored) {}
            }
        } catch (Exception e) {
            System.out.println("Error loading attendance file: " + e.getMessage());
        }

        return map;
    }

    public static Map<String, HourlyRate> loadHourlyRates() {
        Map<String, HourlyRate> map = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(DATABASE_CSV))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] cols = line.split("\t|,(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (cols.length < 8) continue;

                try {
                    String empId     = cols[0].replace("\"", "").trim();
                    String firstName = cols[1].replace("\"", "").trim();
                    String lastName  = cols[2].replace("\"", "").trim();
                    double rate      = Double.parseDouble(
                                           cols[7].replace("\"", "").replace(",", "").trim());
                    map.put(empId, new HourlyRate(empId, lastName, firstName, rate));
                } catch (Exception ignored) {}
            }
        } catch (Exception e) {
            System.out.println("  [!] Error loading database file: " + e.getMessage());
        }

        return map;
    }

    public static List<double[]> loadSssBrackets() {
        List<double[]> brackets = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(SSS_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] cols = line.split(",");
                if (cols.length < 3) continue;

                try {
                    double min       = Double.parseDouble(cols[0].trim());
                    double max       = Double.parseDouble(cols[1].trim());
                    double deduction = Double.parseDouble(cols[2].trim());
                    brackets.add(new double[]{min, max, deduction});
                } catch (Exception ignored) {}
            }
        } catch (Exception e) {
            System.out.println("  [!] Error loading SSS deduction file: " + e.getMessage());
        }

        return brackets;
    }

    // Display for Attendance //

    public static void displayAttendanceSummary(String employeeId,
                                                Map<String, List<AttendanceRecord>> attendance,
                                                Integer filterMonth,
                                                Integer filterYear) {
        List<AttendanceRecord> records = attendance.get(employeeId);

        if (records == null || records.isEmpty()) {
            System.out.println("No attendance records found for Employee #" + employeeId + ".");
            return;
        }

        List<AttendanceRecord> filtered = new ArrayList<>();
        for (AttendanceRecord r : records) {
            boolean monthMatch = (filterMonth == null || r.hw.attendMonth == filterMonth);
            boolean yearMatch  = (filterYear  == null || r.hw.attendYear  == filterYear);
            if (monthMatch && yearMatch) filtered.add(r);
        }

        if (filtered.isEmpty()) {
            System.out.println("No records found for Employee #" + employeeId +
                    " with the selected filters.");
            return;
        }

        String monthLabel = (filterMonth == null) ? "All Months" : String.format("Month %02d", filterMonth);
        String yearLabel  = (filterYear  == null) ? "All Years"  : String.valueOf(filterYear);

        AttendanceRecord first = filtered.get(0);
        System.out.println("\n============================================================");
        System.out.printf("  Employee #%-10s  %s, %s%n", employeeId, first.lastName, first.firstName);
        System.out.printf("  Period: %-15s  Year: %s%n", monthLabel, yearLabel);
        System.out.println("============================================================");

        HoursWorked.printHeader();

        double totalHours = 0;
        for (AttendanceRecord r : filtered) {
            r.hw.printRow();
            totalHours += r.getHours();
        }

        System.out.println("------------------------------------------------------------");
        System.out.printf("%-31s %-12.2f %-12s%n",
                "Total Hours Worked:", totalHours, "(" + filtered.size() + " day(s))");
        System.out.println("============================================================");
    }

    public static void displayAllAttendance(Map<String, List<AttendanceRecord>> attendance,
                                            Integer filterMonth,
                                            Integer filterYear) {
        if (attendance.isEmpty()) {
            System.out.println("No attendance records loaded.");
            return;
        }
        for (String empId : attendance.keySet()) {
            displayAttendanceSummary(empId, attendance, filterMonth, filterYear);
        }
    }

    /* Display for Salary Computation and Deductions */

    public static SemiMonthlyPay computePay(String employeeId,
                                            int targetMonth,
                                            int targetYear,
                                            int cutoff,
                                            Map<String, List<AttendanceRecord>> attendance,
                                            Map<String, HourlyRate> hourlyRates,
                                            List<double[]> sssBrackets) {

        HourlyRate rate = hourlyRates.get(employeeId);
        if (rate == null) {
            System.out.println("  [!] No hourly rate found for Employee #" + employeeId + ".");
            return null;
        }

        List<AttendanceRecord> records = attendance.get(employeeId);
        if (records == null || records.isEmpty()) {
            System.out.println("  [!] No attendance records found for Employee #" + employeeId + ".");
            return null;
        }

        double totalHours = 0;

        for (AttendanceRecord rec : records) {
            int day   = rec.hw.attendDay;
            int month = rec.hw.attendMonth;
            int year  = rec.hw.attendYear;
            double hours = rec.getHours();

            if (month != targetMonth || year != targetYear) continue;

            if (cutoff == 1 && day >= 1  && day <= 16) totalHours += hours;
            if (cutoff == 2 && day >= 17 && day <= 31) totalHours += hours;
        }

        SemiMonthlyPay pay = new SemiMonthlyPay();
        pay.employeeId  = employeeId;
        pay.firstName   = rate.firstName;
        pay.lastName    = rate.lastName;
        pay.month       = targetMonth;
        pay.year        = targetYear;
        pay.cutoff      = cutoff;
        pay.hourlyRate  = rate.getRatePerHour();
        pay.hoursWorked = totalHours;
        pay.grossPay    = totalHours * rate.getRatePerHour();

        pay.applyDeductions(sssBrackets);

        return pay;
    }

    /* Menu Selections */ 

    public static void runStaffPortal(Scanner input) {
        Map<String, List<AttendanceRecord>> attendance  = loadAttendance(5000);
        Map<String, HourlyRate>             rates       = loadHourlyRates();
        List<double[]>                      sssBrackets = loadSssBrackets();

        while (true) {
            System.out.println("\n[MOTORPH STAFF PORTAL]");
            System.out.println("1. View Attendance");
            System.out.println("2. View Salary Computation");
            System.out.println("3. Exit");

            String choice = promptMenuChoice(input, "Choice: ", "1", "2", "3");

            switch (choice) {
                case "1":
                    String empIdA = promptEmployeeId(input, true);
                    Integer filterMonth = promptMonth(input);
                    Integer filterYear  = promptYear(input);

                    if (empIdA.equalsIgnoreCase("All")) {
                        displayAllAttendance(attendance, filterMonth, filterYear);
                    } else {
                        displayAttendanceSummary(empIdA, attendance, filterMonth, filterYear);
                    }

                    promptReturnToMenu(input);
                    break;

                case "2":
                    String empIdS = promptEmployeeId(input, true);
                    int[] myc = getMonthYearCutoff(input);

                    SemiMonthlyPay.printHeader(myc[2]);

                    if (empIdS.equalsIgnoreCase("All")) {
                        for (String id : attendance.keySet()) {
                            SemiMonthlyPay pay = computePay(id, myc[0], myc[1], myc[2], attendance, rates, sssBrackets);
                            if (pay != null) pay.printRow();
                        }
                    } else {
                        SemiMonthlyPay pay = computePay(empIdS, myc[0], myc[1], myc[2], attendance, rates, sssBrackets);
                        if (pay != null) pay.printRow();
                    }

                    promptReturnToMenu(input);
                    break;

                case "3":
                default:
                    System.out.println("Program Terminated.");
                    return;
            }
        }
    }

    public static void runEmployeePortal(Scanner input) {
        Map<String, List<AttendanceRecord>> attendance  = loadAttendance(5000);
        Map<String, HourlyRate>             rates       = loadHourlyRates();
        List<double[]>                      sssBrackets = loadSssBrackets();

        String empId = promptEmployeeId(input, false);

        while (true) {
            System.out.println("\n[MOTORPH EMPLOYEE PORTAL]");
            System.out.println("1. View Attendance");
            System.out.println("2. View Salary Computation");
            System.out.println("3. Exit");

            String choice = promptMenuChoice(input, "Choice: ", "1", "2", "3");

            switch (choice) {
                case "1":
                    Integer filterMonth = promptMonth(input);
                    Integer filterYear  = promptYear(input);
                    displayAttendanceSummary(empId, attendance, filterMonth, filterYear);

                    promptReturnToMenu(input);
                    break;

                case "2":
                    int[] myc = getMonthYearCutoff(input);
                    SemiMonthlyPay.printHeader(myc[2]);
                    SemiMonthlyPay pay = computePay(empId, myc[0], myc[1], myc[2], attendance, rates, sssBrackets);
                    if (pay != null) pay.printRow();

                    promptReturnToMenu(input);
                    break;

                case "3":
                default:
                    System.out.println("Program Terminated.");
                    return;
            }
        }
    }

    /* Log-in screen */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== MOTORPH SYSTEM ===");

        String username = promptUsername(input);
        String password = promptPassword(input);

        if (!password.equals("12345")) {
            System.out.println("Access Denied.");
            input.close();
            return;
        }

        if (username.equalsIgnoreCase("payroll_staff")) {
            runStaffPortal(input);
        } else if (username.equalsIgnoreCase("employee")) {
            runEmployeePortal(input);
        } else {
            System.out.println("Access Denied.");
        }

        input.close();
    }
}
