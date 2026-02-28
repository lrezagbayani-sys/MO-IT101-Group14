package com.mycompany.motorph;

public class MotorPH { 
    public static void main(String[] args) {

        // Create testing employee and set details
        Employee emp1 = new Employee();
        emp1.setEmployeeNum(10001);
        emp1.setFirstName("Garcia");
        emp1.setLastName("Manuel III");
        emp1.setBirthDate(11, 10, 1983); // Day, Month, Year
        emp1.setPhoneNum("966860270");
        emp1.setSssNum("44-4506057-3");
        emp1.setPhNum("820126853951");
        emp1.setTinNum("442-605-657-000");
        emp1.setIbigNum("691295330870");
        emp1.setIsRegular(true);
        emp1.setRoles("Chief Executive Officer");
        emp1.setSupervisor("N/A");

        emp1.displayInfo();

        // Calculate testing hours worked
        CalculateHoursWorked hoursCalc = new CalculateHoursWorked();
        try {
            hoursCalc.setAttendanceDate(3, 6, 2024); // Day, Month, Year
            hoursCalc.setLogTimes(8.98, 18.52);      // Log-in and Log-out
            hoursCalc.calculateHours();
            hoursCalc.displaySummary(emp1);

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error in hours calculation: " + e.getMessage());
        }

        // Compute testing semi-monthly salary
        ComputeSemiMonthlySalary salaryCalc = new ComputeSemiMonthlySalary();
        try {
            salaryCalc.setHourlyRate(535.71); // Hourly rate
            salaryCalc.computeSalary(hoursCalc.getHoursWorked());
            salaryCalc.displaySummary(emp1);

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error in salary computation: " + e.getMessage());
        }
    }
}

/**
 * Employee class with proper setters and encapsulation
 */
class Employee {

    private int employeeNum;
    private String firstName;
    private String lastName;
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    private String phoneNum;
    private String sssNum;
    private String phNum;
    private String tinNum;
    private String ibigNum;
    private boolean isRegular;
    private String roles;
    private String supervisor;

    // Setters
    public void setEmployeeNum(int employeeNum) { this.employeeNum = employeeNum; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setBirthDate(int day, int month, int year) {this.birthDay = day; this.birthMonth = month; this.birthYear = year;}
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }
    public void setSssNum(String sssNum) { this.sssNum = sssNum; }
    public void setPhNum(String phNum) { this.phNum = phNum; }
    public void setTinNum(String tinNum) { this.tinNum = tinNum; }
    public void setIbigNum(String ibigNum) { this.ibigNum = ibigNum; }
    public void setIsRegular(boolean isRegular) { this.isRegular = isRegular; }
    public void setRoles(String roles) { this.roles = roles; }
    public void setSupervisor(String supervisor) { this.supervisor = supervisor; }

    // Display employee info
    public void displayInfo() {
        System.out.println("----- Employee Info -----");
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
