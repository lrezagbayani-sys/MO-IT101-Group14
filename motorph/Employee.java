package com.mycompany.motorph;

//import java.time.LocalDate; (Found online, testing)

public class Employee {
    int employeeNum;
    String firstName;
    String lastName;
    //LocalDate birthday; (Same as import)
    String phoneNum;
    String sssNum;
    String healthNum;
    String tinNum;
    String ibigNum;
    boolean isRegular;
    Roles role; 
    String supervisor;
    int basicSalary;
    int riceSubsidy;
    int phoneAllowance;
    int clothAllowance;
    int grossRate;
    int hourlyRate;
    
    public Employee(int employeeNum, String firstName, String lastName, Roles role, String phoneNum, boolean isRegular) {
        this.employeeNum = employeeNum;
        this.firstName = firstName;
        this.lastName = lastName;
        // this.birthday = LocalDate.of(Month, Day, Year);
        this.role = role;
        this.phoneNum = phoneNum; 
        this.isRegular = isRegular;
    }

   
    public void displayInfo() {
        System.out.println("Employee Number: " + employeeNum);
        System.out.println("Name: " + firstName + " " + lastName);
        // System.out.println("Birthday: " + birthday);
        System.out.println("Role: " + role);
        System.out.println("Phone: " + phoneNum);
        System.out.println("Status: " + (isRegular ? "Regular" : "Probationary"));
    }
}
