public static void displayPayrollFromData(String[] data, boolean isStaff) {
        System.out.println("\n-- EMPLOYEE DETAILS --");
        System.out.println("Employee #:    " + data[1]);
        System.out.println("Full Name:     " + data[2]);
        System.out.println("Birthday:      " + data[3]);

        if (isStaff) {
            System.out.println("\n-- PAYROLL INFORMATION --");
            
            // First Cutoff Logic
            double hoursWeek1 = Double.parseDouble(data[10]);
            double hoursWeek2 = Double.parseDouble(data[11]);
            System.out.println("Cutoff Date:       June 1 to June 15");
            System.out.println("Total Hours Worked: " + (hoursWeek1 + hoursWeek2));
            System.out.println("Gross Salary:      " + data[16]);
            System.out.println("Net Salary:        " + data[16]);

            // Second Cutoff Logic
            double hoursWeek3 = Double.parseDouble(data[12]);
            double hoursWeek4 = Double.parseDouble(data[13]);
            System.out.println("\nCutoff Date:       June 16 to June 30 (Second payout includes all deductions)");
            System.out.println("Total Hours Worked: " + (hoursWeek3 + hoursWeek4));
            System.out.println("Gross Salary:      " + data[16]);

            System.out.println("\nEACH DEDUCTION:");
            System.out.println("SSS:               " + data[6]);
            System.out.println("PhilHealth:        " + data[7]);
            System.out.println("Pag-IBIG:          " + data[8]);
            System.out.println("Withholding Tax:   " + data[9]);

            // Math to calculate Total Deductions
            double sss = Double.parseDouble(data[6].replace("\"", "").replace(",", ""));
            double ph = Double.parseDouble(data[7].replace("\"", "").replace(",", ""));
            double pi = Double.parseDouble(data[8].replace("\"", "").replace(",", ""));
            double tax = Double.parseDouble(data[9].replace("\"", "").replace(",", ""));
            double totalDeductions = sss + ph + pi + tax;
            
            System.out.println("Total Deductions:  " + String.format("%.2f", totalDeductions));
            System.out.println("\nNet Salary:        " + data[17]);
        } else {
            System.out.println("\n-- PAYROLL INFORMATION --");
            System.out.println("Note: Payroll and Attendance details are restricted.");
            System.out.println("Please contact Human Resources for salary inquiries.");
        }

        // Final Termination Message
        System.out.println("\nThank you for using the MotorPH Payroll System! \nProgram Terminated.");
    }
