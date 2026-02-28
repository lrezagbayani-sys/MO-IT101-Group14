// Method for the Employee View
    public static void runEmployeePortal(Scanner input) {
        System.out.println("\n[MOTORPH EMPLOYEE PORTAL]");
        System.out.println("1. View My Employee Details");
        System.out.println("2. Exit Program");
        System.out.print("Select: ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            System.out.print("Enter Your Employee Number: ");
            String idInput = input.nextLine();
            // We pass 'false' because they are NOT staff
            searchAndDisplayEmployee(idInput, false); 
        } 
        else if (choice.equals("2")) {
            // YOUR REQUESTED MESSAGE
            System.out.println("\nThank you for using the MotorPH Payroll System! \n Program Terminated.");
            System.exit(0);
        } 
        else {
            // YOUR REQUESTED MESSAGE
            System.out.println("\nSelected option unidentified. Please select from the given numbers and try again. \n Program Terminated.");
            System.exit(0);
        }
    }

    // Method for the Payroll Staff View
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
            // We pass 'true' because they ARE staff
            searchAndDisplayEmployee(idInput, true);
        } 
        else if (choice.equals("2")) {
            processAllEmployees();
        } 
        else if (choice.equals("3")) {
            // YOUR REQUESTED MESSAGE
            System.out.println("\nThank you for using the MotorPH Payroll System! \n Program Terminated.");
            System.exit(0);
        } 
        else {
            // YOUR REQUESTED MESSAGE
            System.out.println("\nSelected option unidentified. Please select from the given numbers and try again. \n Program Terminated.");
            System.exit(0);
        }
    }
