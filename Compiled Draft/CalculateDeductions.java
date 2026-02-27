public class CalculateDeductions {
    double gross;
    double sss, ph, pi, tax, totalDeductions;

    public void computeDeductions() {
        sss = gross * 0.045;
        ph = gross * 0.02;
        pi = 100.0;
        tax = gross * 0.10;
        totalDeductions = sss + ph + pi + tax;
    }

    public void displayDeductions() {
        System.out.println("\nEach Deduction:");
        System.out.printf("    SSS: PHP %.2f\n", sss);
        System.out.printf("    PhilHealth: PHP %.2f\n", ph);
        System.out.printf("    Pag-IBIG: PHP %.2f\n", pi);
        System.out.printf("    Tax: PHP %.2f\n", tax);
        System.out.printf("Total Deductions = PHP %.2f\n", totalDeductions);
    }
}
