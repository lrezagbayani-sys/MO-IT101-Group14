// Method for the Employee View
    public static void runEmployeePortal(Scanner input) {
        System.out.println("\n[MOTORPH EMPLOYEE PORTAL]");
        System.out.println("1. View My Payroll Details");
        System.out.println("2. Exit the program");
        System.out.print("Select: ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            System.out.print("Enter Your Employee Number: ");
            String idInput = input.nextLine();
            // Call the search method to find and display this specific ID
            searchAndDisplayEmployee(idInput, false); 
        } else {
            terminateNormally(); // Close program
        }
    }

    // Method for the Payroll Staff View
    public static void runStaffPortal(Scanner input) {
        System.out.println("\n[MOTORPH PAYROLL STAFF PORTAL]");
        System.out.println("1. Process Payroll for One Employee");
        System.out.println("2. Process Payroll for All Employees");
        System.out.println("3. Exit the program");
        System.out.print("Select: ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            System.out.print("Enter Employee Number: ");
            String idInput = input.nextLine();
            // Search and display a single employee from CSV
            searchAndDisplayEmployee(idInput, true);
        } else if (choice.equals("2")) {
            // Process the entire CSV file
            processAllEmployees();
        } else {
            terminateNormally();
        }
    }
