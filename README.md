🏢 MotorPH Payroll System: Phase 1
Computer Programming 1 | Group 14 (H1101)

👥 Development Team

1. Delos Santos, Q.L.,
2. Palayaban, M.,
3. Saga, G.M.,
Agbayani, E.Z.,
Elviña, J.N.

Effort Estimation & Project Plan: https://docs.google.com/spreadsheets/d/14QEroycZBSs3QpdtPJ-ELushEXd8EPXep8-dBjpRDtk/edit?usp=sharing

🚀 System Overview

The MotorPH Payroll System is a Java console application designed to handle bimonthly payroll processing. It utilizes a secure, role-based access system to ensure that sensitive salary information is only accessible to authorized payroll staff.

🛠️ Key Features

🔐 Role-Based Authentication

Employee Account: (Username: employee) Allows staff to view their basic profile details (ID, Name, and Birthday) while keeping payroll data private.

Staff Account: (Username: payroll_staff) Grants full access to process payroll for individuals or the entire company.

Security: Centralized password verification (12345) for all accounts.

👤 Data Management (Hardcoded Data Layer)

Unlike systems that rely on external files, this version uses internal Static Arrays for maximum portability and speed. It stores:

Employee Metadata: IDs, Full Names, and Birthdays.

Financial Data: Hourly rates and pre-logged hours worked for two specific cut-off periods.

💰 Payroll Engine

The system automates complex calculations per month:
- Bimonthly Cut-offs and Gross pay calculation.
- Gross pay calculation minus mandatory government deductions.
- Deduction Logic: Automatic calculation of SSS (4.5%), PhilHealth (2%), Pag-IBIG (Fixed PHP 100), and Withholding Tax (10%).

💻 Technical Implementation

Language: Java

Input Handling: java.util.Scanner for interactive terminal menus.

Formatting: System.out.printf for professional-grade currency and decimal alignment.

Error Handling: Custom logic to catch invalid menu selections and non-existent employee IDs.

🏃 How to Run
Ensure you have JDK 8 or higher installed.

Compile the file:
javac MO-IT101-Group14.java

Execute the program:
java MO-IT101-Group14

🛡️ Error Handling Mechanisms
The system is programmed to terminate securely and notify the user if:

- Incorrect login credentials are provided.
- An invalid menu number is selected.
- An employee ID is entered that does not exist in the system's database.
- Payroll could not be processed.

Note: This project is for academic purposes for Computer Programming 1.
