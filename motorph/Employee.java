package com.mycompany.motorph;

package com.mycompany.motorph;

public class Employee { //this is the main employee role, refer to Roles.java for enum primitive
    int employeeNum;
    String firstName;
    String lastName;

    public Employee(int 
            employeeNum, 
            String firstName, String lastName) {
        this.employeeNum = employeeNum;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void displayInfo() {
        System.out.printf(
            "%-12d %-20s%n",
            employeeNum,
            firstName + " " + lastName
        );
    }
}

/*
public class Employee {
    int employeeNum;
    String firstName;
    String lastName;
    //LocalDate birthday; (We'll add this function)
    String phoneNum;
    String sssNum;
    String phNum;
    String tinNum;
    String ibigNum;
    boolean isRegular;
    Roles role; 
    String supervisor;
    //int basicSalary;
    //int riceSubsidy;
    //int phoneAllowance;
    //int clothAllowance;
    //int grossRate;
    //int hourlyRate;
    
    public Employee(
            int employeeNum, 
            String firstName, 
            String lastName, 
            String phoneNum, 
            String sssNum,
            String phNum,
            String tinNum,
            String ibigNum,
            boolean isRegular,
            Roles role, 
            String supervisor) {
        this.employeeNum = employeeNum;
        this.firstName = firstName;
        this.lastName = lastName;
        // this.birthday = LocalDate.of(Month, Day, Year);
        this.phoneNum = phoneNum; 
        this.sssNum = sssNum;
        this.phNum = phNum;
        this.tinNum = tinNum;
        this. ibigNum = ibigNum;
        this.isRegular = isRegular;
        this.role = role;
        this.supervisor = supervisor;
    }

    public void displayInfo() {
        System.out.printf(
            "%-12d %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s%n",
            employeeNum,
            firstName + " " + lastName,
            phoneNum,
            sssNum,
            phNum,
            tinNum,
            ibigNum,
            isRegular ? "Regular" : "Probationary",
            role,
            supervisor
        );
    }
}
*/
