# CP1 - TA Source Code - Basic Payroll Program (Group 14 - H1101)

Our program reads employee information and attendance records from CSV files, calculates total hours worked per payroll cutoff, applies government deductions, and displays a comprehensive salary summary.

---

## 🚀 Quick Start: How to Run

To ensure our program finds the database files correctly, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Irezagbayani-sys/MO-IT101-Group14.git
   ```
2. **Open in IDE:** Open the root folder in **NetBeans, IntelliJ, or VS Code**.
3. **CSV Location:** Ensure `Employee Database.csv` and `Employee Attendance Record.csv` remain inside the root folder (not the `src` folder).
4. **Compile & Run:** Execute `MO_IT101_Group14.java`.

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

> The system handles a secure login process and routes users to either the **Employee** or **Payroll Staff** portal based on their role.

### **3. Credentials and Roles**
* **Usernames:** `employee` or `payroll_staff`
* **Password:** `12345`

> The system uses **Role-Based Access Control** to determine which menu options and data are displayed to the active user.

---

## 📂 File Handling & Data Parsing

### **External Files**
* `Employee Database.csv`: Stores Employee ID, Name, Birthday, and Hourly Rate.
* `Employee Attendance Record.csv`: Stores daily time-in/time-out logs for June to December.

### **Input Validation**
The program uses **Regex Validation** (`!employeeNumber.matches("\\d+")`) to catch invalid characters (like letters) entered into the ID field immediately. It also uses a **Regex Split** to handle names that contain commas inside quotes so the data doesn't "shift" columns during parsing.

---

## ⚙️ Payroll Logic

### **Attendance Parsing & Calculation**
* **Grace Period:** Logins are recognized starting at **8:00 AM**.
* **Shift End:** Logouts are capped at **5:00 PM (17:00)**.
* **Lunch Break:** The system automatically subtracts **1 hour** for the mandatory break.
* **Cutoff Split:**
  * **Days 1-15:** First Cutoff
  * **Days 16-30/31:** Second Cutoff

### **Deduction Engine**
Calculations are applied based on the **combined monthly gross**:
* **SSS:** Computed directly at **4.5%** of the gross income.
* **PhilHealth:** Computed at **3.0%** of the gross income (total divided by 2).
* **Pag-IBIG:** Dynamic calculation capped at a maximum of **PHP 100.00**.
* **Withholding Tax:** Nested branching logic applying **20%, 25%, or 30%** based on tiered taxable income brackets.

---

## 🖥️ User Interface

### **Payroll Summary Display**
* **First Cutoff:** Displays Hours Worked, Gross Salary, and Net Salary (pre-deductions).
* **Second Cutoff:** Displays Hours Worked, Gross Salary, an **itemized list** of all 4 statutory deductions, and the final Net Salary.

---

## 📝 Technical Notes

* **Precision & Truncation:** To comply with strict accounting standards, all monetary values and hours worked are displayed at **12 decimal places**. The system utilizes mathematical truncation to maintain absolute data integrity without rounding errors.
* **Cross-IDE Compatibility:** The program features a centralized `openCSV()` method with fallback path-finding logic that automatically detects the project root, ensuring it runs seamlessly across **NetBeans, IntelliJ, and VS Code**.
* **Security:** Unauthorized login attempts or invalid numerical inputs trigger a secure `System.exit(0)` protocol to protect system memory and prevent downstream calculation errors.

---

## 👥 Development Team (Group 14 - H1101)

* **Delos Santos, Q.L.** - Finalized and tested the structure, logic, and compatibility of the code using IntelliJ. Compiled the final program and CSV files on GitHub.
* **Palayaban, M.** - Ensured the code is aligned with the rubrics and further feedback from our mentor. Compiled and created the Login code and CSV files. 
* **Saga, G.M.** - Gathered initial requirements and finalized the needed structure of the Payroll System. Tested the final code using Apache NetBeans and gave feedback.
* **Agbayani, E.Z.** - Led the Effort Estimation and Project Planning. Compiled and created the Hours Worked Calculation, Gross and Net Computation, and Deduction Application codes.
* **Elviña, J.N.** - Gathered initial requirements and finalized the needed structure of the Payroll System. Tested the final code using Apache NetBeans and gave feedback.

* **Project Plan Link:** [View Spreadsheet](https://docs.google.com/spreadsheets/d/14QEroycZBSs3QpdtPJ-ELushEXd8EPXep8-dBjpRDtk/edit?usp=sharing)
