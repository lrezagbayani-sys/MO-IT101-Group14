package com.mycompany.motorph; //Stores Attendance Record

public class CalculateHoursWorked {
    int attendDay;
    int attendMonth;
    int attendYear;
    double logIn;
    double logOut;
    double hoursWork;

    public void calculateHours() {
        hoursWork = logOut - logIn;
    }

    public void displayInfo() {
        System.out.println("Attendance: " + attendMonth + "/" + attendDay + "/" + attendYear);
        System.out.println("Log-in Time: " + logIn);
        System.out.println("Log-Out Time: " + logOut);
        System.out.println("Total Hours Worked: " + hoursWork);
    }
}

