public class CalculateNetWage {
    double gross;
    double deductions;
    double netWage;

    public void computeNet() {
        netWage = gross - deductions;
    }

    public void displayNet() {
        System.out.printf("Net Salary: PHP %.2f\n", netWage);
    }
}
