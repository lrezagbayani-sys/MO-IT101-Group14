# CP1 - MS2 Source Code
## Basic Payroll Program (Group 14)


This program reads employee information and attendance records from CSV files, calculates total hours worked per payroll cutoff, applies government deductions, and displays a comprehensive salary summary.

---

## 🛠️ How the Program Works

### **1. Imports**
* `import java.io.BufferedReader;`
* `import java.io.FileReader;`
* `import java.util.Scanner;`
> **Purpose:** `BufferedReader` and `FileReader` are used to read the CSV databases line by line. `Scanner` is used to capture user credentials and menu selections.

### **2. Main Class and Method**
* **Class:** `MO_IT101_Group14`
* **Entry Point:** `public static void main(String[] args)`
> The system handles a secure login system and routes users to either the **Employee** or **Payroll Staff** portal based on their role.

### **3. Credentials and Roles**
* **Username:** `employee` or `payroll_staff`
* **Password:** `12345`
> The system uses **Role-Based Access Control** to determine which menu options are displayed.

---

## 📂 File Handling & Data Parsing

### **External Files**
* `Employee Database.csv`: Stores ID, Name, Birthday, and Hourly Rate.
* `Employee Attendance Record.csv`: Stores logs for June to December 2024.



### **Input Validation**
The program uses **Regex Validation** (`!employeeNumber.matches("\\d+")`) to catch invalid characters (like letters) entered into the ID field immediately. It also uses a **Regex Split** to handle names that contain commas inside quotes so the data doesn't "shift" columns.

---

## ⚙️ Payroll Logic

### **Attendance Parsing & Calculation**
* **Grace Period:** Logins are recognized starting at **8:00 AM**.
* **Shift End:** Logouts are capped at **5:00 PM (17:00)**.
* **Lunch Break:** The system automatically subtracts **1 hour** for the mandatory break.
* **Cutoff Split:** * **Days 1-15:** First Cutoff
    * **Days 16-31:** Second Cutoff

### **Deduction Engine**
Calculated based on the **combined monthly gross**:
* **SSS:** 4.5% of gross.
* **PhilHealth:** 3.0% (total divided by 2).
* **Pag-IBIG:** Fixed PHP 100.00.
* **Withholding Tax:** Nested branching logic applying **20%, 25%, or 30%** based on taxable income brackets.

---

## 🖥️ User Interface

### **Payroll Summary Display**
* **First Cutoff:** Displays Hours, Gross Salary, and Net Salary (pre-deductions).
* **Second Cutoff:** Displays Hours, Gross, an **itemized list** of all 4 deductions, and the final Net Salary.



---

## 📝 Technical Notes
* **Precision:** All monetary values and hours are displayed at **3 decimal places** for maximum accuracy.
* **Security:** The program uses `System.exit(0)` to ensure a clean termination after errors or task completion.
* **Location:** CSV files must be in the **project’s root folder** (the same folder as your `.java` or `.class` file).

---

## 👥 Development Team (Group 14)
* **Delos Santos, Q.L.**
* **Palayaban, M.**
* **Saga, G.M.**
* **Agbayani, E.Z.**
* **Elviña, J.N.**
