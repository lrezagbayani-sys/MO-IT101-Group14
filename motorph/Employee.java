package com.mycompany.motorph;

public class Employee {
    int employeeNum;
    String firstName;
    String lastName;
    int birthDay;
    int birthMonth;
    int birthYear;
    String phoneNum; //String since leading 0 are removed by int
    String sssNum; //From sssNum to ibigNum, string is used because of too long integers or dashes(-)
    String phNum;
    String tinNum;
    String ibigNum;
    boolean isRegular; //Boolean where Regular = True | Probitionary = False
    String roles;
    String supervisor;
    //int basicSalary;
    //int riceSubsidy;
    //int phoneAllowance;
    //int clothAllowance;
    //int grossRate;
    double hourlyRate; 

    public void displayInfo() {
        System.out.println("Employee #: " + employeeNum);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Birthdate: " + birthMonth + "/" + birthDay + "/" + birthYear);
        System.out.println("Phone: " + phoneNum);
        System.out.println("SSS: " + sssNum);
        System.out.println("PhilHealth: " + phNum);
        System.out.println("TIN: " + tinNum);
        System.out.println("Pag-IBIG: " + ibigNum);
        System.out.println("Status: " + (isRegular ? "Regular" : "Probationary"));
        System.out.println("Role: " + roles);
        System.out.println("Supervisor: " + supervisor);
    }
}
