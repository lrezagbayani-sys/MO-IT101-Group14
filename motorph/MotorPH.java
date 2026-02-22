package com.mycompany.motorph;

package com.mycompany.motorph;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*; //this import let creates classes for POI
import org.apache.poi.xssf.usermodel.XSSFWorkbook; //this import let POI use its function for excel

public class MotorPH {
    public static void main(String[] args) {
        System.out.printf("%-12s %-20s %-20s%n", //headers for category, add new headers for new primitives (PhilHealth, TIN, Pag-Ibig etc. etc.)
                "Employee #", 
                "First Name", 
                "Last Name");

        try (FileInputStream fis = new FileInputStream("employeedata.xlsx"); Workbook workbook = new XSSFWorkbook(fis)) { //Opens the Excel for our file

            for (Row row : workbook.getSheetAt(0)) {
                if (row.getRowNum() == 0) continue; //skips the header for category

                Cell c0 = row.getCell(0); //takes employee number from column A (For POI, its 0 and B=1, C=2, so on)
                Cell c1 = row.getCell(1); //takes first name from column b
                Cell c2 = row.getCell(2); //takes last name from column c

                if (c0 == null || c1 == null || c2 == null) continue; //skips if function didnt read anything

                System.out.printf("%-12d %-20s %-20s%n", //prints employee number using .get_CellValues functions
                        (int) c0.getNumericCellValue(), //this needs a integer cast
                        c1.getStringCellValue(),
                        c2.getStringCellValue());
            }
        } catch (Exception e) { e.printStackTrace(); //
        }
    }
}

/* //OLD VERSION
public class MotorPH {
    public static void main(String[] args) {
        
        System.out.printf(
            "%-12s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s%n",
            "Employee #", 
            "Name", 
            "Phone", 
            "SSS #", 
            "PhilHealth #",
            "TIN #", 
            "Pag-Ibig #", 
            "Status", 
            "Role", 
            "Supervisor"
        );
        
        Employee emp1 = new Employee(
            1,               
            "Ethan",
            "Ziv",
            "09999999999",
            "44-4506057-3",
            "820126853951",
            "442-605-657-000",
            "691295330870",
            true,
            Roles.CUSTOMER_SERVICE,
            "bossName"
        );

        emp1.displayInfo();
        
        Employee emp2 = new Employee(
            1,               
            "First",
            "Person",
            "091111111111",
            "44-4506057-2",
            "820126853951",
            "442-605-657-000",
            "691295330870",
            true,
            Roles.CUSTOMER_SERVICE,
            "bossName"
        );

        emp2.displayInfo();
    }
}
*/
