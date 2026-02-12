package com.mycompany.motorph;

public class MotorPH {
    public static void main(String[] args) {
        Employee emp1 = new Employee(
            101,               
            "John",
            "Doe",
            Roles.CUSTOMER_SERVICE,
            "09171234567",    
            true
        );

        emp1.displayInfo();
    }
}

