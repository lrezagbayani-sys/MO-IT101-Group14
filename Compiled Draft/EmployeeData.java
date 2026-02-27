public class EmployeeData {
    String id;
    String fullName;
    String birthday;

    public void displayDetails() {
        System.out.println("\n--- EMPLOYEE DETAILS ---");
        System.out.println("Employee #: " + id);
        System.out.println("Employee Name: " + fullName);
        System.out.println("Birthday: " + birthday);
    }
}
