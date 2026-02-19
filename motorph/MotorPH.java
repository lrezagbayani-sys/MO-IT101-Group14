package com.mycompany.motorph;

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

