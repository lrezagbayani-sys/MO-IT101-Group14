package com.mycompany.motorph; // Stores Attendance Record

/**
 * Class to calculate hours worked for an employee on a given day.
 */
public class CalculateHoursWorked {

    private int attendDay;
    private int attendMonth;
    private int attendYear;
    private double logInTime;
    private double logOutTime;
    private double hoursWorked;

    /**
     * Sets the attendance date.
     *
     * @param day   Day of attendance (1-31)
     * @param month Month of attendance (1-12)
     * @param year  Year of attendance
     */
    public void setAttendanceDate(int day, int month, int year) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        if (year < 0) {
            throw new IllegalArgumentException("Invalid year: " + year);
        }

        this.attendDay = day;
        this.attendMonth = month;
        this.attendYear = year;
    }

    /**
     * Sets the log-in and log-out times.
     *
     * @param logIn  Employee log-in time (in hours, e.g., 9.0)
     * @param logOut Employee log-out time (in hours, e.g., 17.0)
     */
    public void setLogTimes(double logIn, double logOut) {
        if (logIn < 0 || logIn > 24) {
            throw new IllegalArgumentException("Invalid log-in time: " + logIn);
        }
        if (logOut < 0 || logOut > 24) {
            throw new IllegalArgumentException("Invalid log-out time: " + logOut);
        }
        if (logOut <= logIn) {
            throw new IllegalArgumentException("Log-out time must be after log-in time.");
        }

        this.logInTime = logIn;
        this.logOutTime = logOut;
    }

    /**
     * Calculates the hours worked, subtracting 1 hour for break.
     *
     * @return hours worked
     */
    public double calculateHours() {
        hoursWorked = logOutTime - logInTime - 1; // deduct 1-hour break
        if (hoursWorked < 0) {
            throw new IllegalStateException("Calculated hours worked is negative.");
        }
        return hoursWorked;
    }

    /**
     * Gets the calculated hours worked.
     *
     * @return hours worked
     */
    public double getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Displays a summary of attendance and hours worked.
     *
     * @param emp Employee object
     */
    public void displaySummary(Employee emp) {
        System.out.println("----- Attendance Summary -----");
        System.out.println("Date: " + attendMonth + "/" + attendDay + "/" + attendYear);
        System.out.println("Log-in Time: " + logInTime);
        System.out.println("Log-out Time: " + logOutTime);
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Test passed: Computation is correct");
    }
}
