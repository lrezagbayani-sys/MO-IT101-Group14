// Method for the Employee Portal
    public static void runEmployeePortal(Scanner input) {
        System.out.println("\n[MOTORPH EMPLOYEE PORTAL]");
        System.out.println("1. View My Employee Details");
        System.out.println("2. Exit Program");
        System.out.print("Select: ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            System.out.print("Enter Your Employee Number: ");
            String idInput = input.nextLine();
            searchAndDisplayEmployee(idInput, false); // false = Employee View
        } 
        else if (choice.equals("2")) {
            System.out.println("\nThank you for using the MotorPH Payroll System! \n Program Terminated.");
            System.exit(0);
        } 
        else {
            System.out.println("\nSelected option unidentified. Please select from the given numbers and try again. \n Program Terminated.");
            System.exit(0);
        }
    }

    // Method for the Staff Portal
    public static void runStaffPortal(Scanner input) {
        System.out.println("\n[MOTORPH PAYROLL STAFF PORTAL]");
        System.out.println("1. Process Payroll for One Employee");
        System.out.println("2. Process Payroll for All Employees");
        System.out.println("3. Exit Program");
        System.out.print("Select: ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            System.out.print("Enter Employee Number: ");
            String idInput = input.nextLine();
            searchAndDisplayEmployee(idInput, true); // true = Staff View
        } 
        else if (choice.equals("2")) {
            processAllEmployees();
        } 
        else if (choice.equals("3")) {
            System.out.println("\nThank you for using the MotorPH Payroll System! \n Program Terminated.");
            System.exit(0);
        } 
        else {
            System.out.println("\nSelected option unidentified. Please select from the given numbers and try again. \n Program Terminated.");
            System.exit(0);
        }
    }
