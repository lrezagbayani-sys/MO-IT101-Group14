// Method to format and display the data extracted from the CSV
    public static void displayPayrollFromData(String[] data) {
        // Extract basic info
        String empID = data[1];
        String name = data[2];
        String position = data[4];
        
        // Clean numeric strings: remove quotes and commas before converting to double
        double sss = Double.parseDouble(data[6].replace("\"", "").replace(",", ""));
        double philHealth = Double.parseDouble(data[7].replace("\"", "").replace(",", ""));
        double pagIbig = Double.parseDouble(data[8].replace("\"", "").replace(",", ""));
        double tax = Double.parseDouble(data[9].replace("\"", "").replace(",", ""));
        
        // Hours for each week
        String w1 = data[10], w2 = data[11], w3 = data[12], w4 = data[13];
        
        // Payroll totals from CSV
        double grossSemi = Double.parseDouble(data[16].replace("\"", "").replace(",", ""));
        double netSalary = Double.parseDouble(data[17].replace("\"", "").replace(",", ""));

        // --- DISPLAY LAYOUT ---
        System.out.println("\n--- EMPLOYEE DETAILS ---");
        System.out.println("Employee #: " + empID);
        System.out.println("Full Name: " + name);
        System.out.println("Position: " + position);

        System.out.println("\n--- PAYROLL INFO ---");
        System.out.println("Weekly Hours: W1:" + w1 + " | W2:" + w2 + " | W3:" + w3 + " | W4:" + w4);
        
        // Cutoff 1 Display
        System.out.println("\nCutoff Date: June 1 to June 15");
        System.out.printf("Gross Salary: PHP %.2f\n", grossSemi);
        System.out.printf("Net Salary: PHP %.2f\n", grossSemi); // No deductions for 1st cutoff

        // Cutoff 2 Display (Deductions included)
        System.out.println("\nCutoff Date: June 16 to June 30 (Deductions applied)");
        System.out.printf("Gross Salary: PHP %.2f\n", grossSemi);

        System.out.println("\nDeductions Applied:");
        System.out.printf("    SSS: PHP %.2f\n", sss);
        System.out.printf("    PhilHealth: PHP %.2f\n", philHealth);
        System.out.printf("    Pag-IBIG: PHP %.2f\n", pagIbig);
        System.out.printf("    Tax: PHP %.2f\n", tax);
        
        double totalDeductions = sss + philHealth + pagIbig + tax;
        System.out.printf("Total Deductions = PHP %.2f\n", totalDeductions);
        System.out.printf("Net Salary (2nd Cutoff): PHP %.2f\n", (grossSemi - totalDeductions));
        
        System.out.println("\nTotal Monthly Net: PHP " + data[17].replace("\"", ""));
    }

    // Standard termination message
    public static void terminateNormally() {
        System.out.println("\nThank you for using the MotorPH Payroll System!");
        System.out.println("PROGRAM END.");
        System.exit(0);
    }
}
