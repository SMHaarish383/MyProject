# **CRUD-JDBC GUI Application using Java Swing & MySQL**  

## **Overview**  
This is a **Java Swing-based GUI application** that allows users to interact with a **MySQL database** using JDBC (Java Database Connectivity). The application enables users to **Create, Read, update, delete, and retrieve** student records from a MySQL table named **Container**.  

## **Features**  
✔ **Insert Data** – Add new records to the MySQL database.  
✔ **Update Data** – Modify existing records based on ID.  
✔ **Delete Data** – Remove records from the database.  
✔ **View Data** – Display records dynamically in a **JTable**.  
✔ **Click-to-Edit** – Select a row in the JTable to populate fields for editing.  
✔ **Clear Form** – Reset input fields easily.  

## **Technologies Used**  
- **Java Swing** – GUI design and event handling.  
- **JDBC (Java Database Connectivity)** – Database communication.  
- **MySQL** – Backend database for storing records.  
- **Prepared Statements** – Secure SQL queries to prevent SQL injection.  

## **How It Works**  
1. **GUI Components** – The interface contains text fields for ID, Name, Degree, and Age, along with buttons for CRUD operations.  
2. **Database Operations** – The application interacts with a MySQL database using JDBC.  
3. **JTable with Mouse Click Events** – Users can click on a table row to auto-fill the fields for updating or deleting records.  
4. **Live Updates** – The JTable updates dynamically after each operation.  

## **Setup & Installation**  
### **Prerequisites:**  
- Java Development Kit (**JDK 8 or higher**)  
- MySQL Database Server  
- MySQL JDBC Driver (Connector/J)  

### **Steps to Run the Application:**  
1. **Create MySQL Database & Table:**  
   ```sql
   CREATE DATABASE haarish;
   USE haarish;
   CREATE TABLE Container (
       id INT PRIMARY KEY,
       Name VARCHAR(100),
       Degree VARCHAR(100),
       Age INT
   );
   ```
  



## **Future Enhancements**  
🚀 Improve UI with modern JavaFX instead of Swing.  
🔒 Implement user authentication for database access.  
📊 Add additional filtering and search options in JTable.  

---  
📢 **Contributions & Issues**  
Feel free to **open issues** or **submit pull requests** to improve the project! 🎯  
