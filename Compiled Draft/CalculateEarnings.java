public class CalculateEarnings {
    double rate;
    int hours;
    double grossWage;

    public void calculateGross() {
        grossWage = hours * rate;
    }

    public void displayGrossInfo(String cutoff) {
        System.out.println("Cutoff Date: " + cutoff);
        System.out.println("Total Hours Worked: " + hours);
        System.out.printf("Gross Salary: PHP %.2f\n", grossWage);
    }
}
