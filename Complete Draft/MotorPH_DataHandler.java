public static void searchAndDisplayEmployee(String idToFind, boolean isStaff) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean found = false;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data[1].equals(idToFind)) {
                    displayPayrollFromData(data, isStaff); 
                    found = true;
                    break;
                }
            }

            if (!found) {
                // Triggered if the Employee # does not exist in the CSV
                System.out.println("\nEmployee # unidentified. Please check the # and try again. \n Program Terminated.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Error reading CSV file.");
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
            System.out.println("\nAll records processed. \n Program Terminated.");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error processing payroll.");
        }
    }
