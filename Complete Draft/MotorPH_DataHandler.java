public static void searchAndDisplayEmployee(String idToFind, boolean isStaff) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean found = false;
            br.readLine(); // Skip CSV Header

            while ((line = br.readLine()) != null) {
                // Splits data and handles commas inside quotes
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data[1].equals(idToFind)) {
                    displayPayrollFromData(data, isStaff); 
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("\nEmployee # unidentified. Please check the # and try again. \nProgram Terminated.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Error reading the CSV file database.");
        }
    }

    public static void processAllEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                displayPayrollFromData(data, true); 
                System.out.println("-------------------------------------------------------");
            }
            System.out.println("\nThank you for using the MotorPH Payroll System! \nProgram Terminated.");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error processing the complete payroll list.");
        }
    }
