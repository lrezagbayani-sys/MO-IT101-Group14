public class MotorPH {
    
    //Create employee test object
    
    public static void main(String[] args) {

        Employee emp1 = new Employee();

        emp1.employeeNum = 10000;
        emp1.firstName = "Ethan";
        emp1.lastName = "Agbayani";
        emp1.birthMonth = 1;
        emp1.birthDay = 1;
        emp1.birthYear = 1;
        emp1.phoneNum = "placeHolder"; 
        emp1.sssNum = "placeHolder"; 
        emp1.phNum = "placeHolder"; 
        emp1.tinNum = "placeHolder";
        emp1.ibigNum = "placeHolder";
        emp1.isRegular = true;
        emp1.roles = "Customer Service";
        emp1.supervisor = "placeHolder";

        emp1.displayInfo();
    }
}

    //Declares primitive/data type for each variable within the employee class

    class Employee {
        int employeeNum;
        String firstName;
        String lastName;
        int birthDay;
        int birthMonth;
        int birthYear;
        String phoneNum;
        String sssNum;
        String phNum;
        String tinNum;
        String ibigNum;
        boolean isRegular;
        String roles;
        String supervisor;
        
    //Presents data in a format/structure using println 

    public void displayInfo() {
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
