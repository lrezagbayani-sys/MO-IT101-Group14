// Method to search for a specific ID in the CSV
    public static void searchAndDisplayEmployee(String idToFind, boolean isStaff) {
        // Use try-with-resources to ensure the file closes automatically
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean found = false;
            
            br.readLine(); // Read and skip the first line (the header)

            // Loop through the file until the end
            while ((line = br.readLine()) != null) {
                // Regex split: Handles commas inside quotes (e.g., "1,000")
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                
                // data[1] is the Employee ID column
                if (data[1].equals(idToFind)) {
                    displayPayrollFromData(data); // Call the display method with the row data
                    found = true;
                    break; // Stop searching once found
                }
            }
            if (!found) {
                System.out.println("\nEmployee ID " + idToFind + " not found.");
            }
            terminateNormally();
        } catch (Exception e) {
            System.out.println("Error reading CSV file. Please check if the file exists.");
        }
    }

    // Method to process every row in the CSV (Staff option 2)
    public static void processAllEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                displayPayrollFromData(data); // Display current row
                System.out.println("------------------------------------------------------------------");
            }
            terminateNormally();
        } catch (Exception e) {
            System.out.println("Error processing payroll list.");
        }
    }
