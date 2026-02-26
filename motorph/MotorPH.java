package com.mycompany.motorph;

public class MotorPH {
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
