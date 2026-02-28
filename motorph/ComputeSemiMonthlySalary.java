package com.mycompany.motorph;

/**
 * The ComputeSemiMonthlySalary class calculates and displays the semi-monthly salary
 * based on an employee's hourly rate and hours worked.
 */
public class ComputeSemiMonthlySalary {

    private double hourlyRate;
    private double finalSalary;

    /**
     * Sets the hourly rate for salary computation.
     *
     * @param rate The hourly rate to set. Must not be negative.
     * @throws IllegalArgumentException if the rate is negative.
     */
    public void setHourlyRate(double rate) {
        if (rate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
        }
        this.hourlyRate = rate;
    }

    /**
     * Computes the salary based on the given hours worked.
     *
     * @param hoursWorked Number of hours worked. Must not be negative.
     * @return The computed salary.
     * @throws IllegalStateException if the hourly rate has not been set or is invalid.
     * @throws IllegalArgumentException if hoursWorked is negative.
     */
    public double computeSalary(double hoursWorked) {
        if (hourlyRate <= 0) {
            throw new IllegalStateException("Hourly rate must be set before computing salary.");
        }

        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative.");
        }

        finalSalary = hourlyRate * hoursWorked;
        return finalSalary;
    }

    /**
     * Displays a summary of the salary computation for an employee.
     *
     * @param emp The Employee object for whom the salary is computed.
     * @throws IllegalStateException if the hourly rate or final salary is not valid.
     */
    public void displaySummary(Employee emp) {
        if (hourlyRate <= 0 || finalSalary <= 0) {
            throw new IllegalStateException("Salary or hourly rate is not valid for display.");
        }

        System.out.println("----- Salary Summary -----");
        System.out.println("Hourly Rate: " + hourlyRate);
        System.out.println("Final Salary: " + finalSalary);
        System.out.println("Computation verified successfully!");
        System.out.println("--------------------------");
    }
}
