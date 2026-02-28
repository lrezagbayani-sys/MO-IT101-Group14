package com.mycompany.motorph;

public class ComputeSemiMonthlySalary {

    private double hourlyRate;
    private double finalSalary;

    public void setHourlyRate(double rate) {
        if (rate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
        }
        this.hourlyRate = rate;
    }

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