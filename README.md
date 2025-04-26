# Library Management System 📚 (Java Swing + JDBC)

This is a simple **Library Management System** project built using **Java Swing** for GUI and **JDBC** for database operations.  
It allows users to perform **CRUD (Create, Read, Update, Delete)** operations on a library database.

---

## ✨ Features
- Add new books to the library 📖
- View all books stored in the database 👀
- Update book information 🖊️
- Delete books from the library 🗑️
- Interactive GUI using Java Swing
- Database connectivity using JDBC (MySQL)

- Some Pictures :-

- ![ChatGPT Image Apr 26, 2025, 09_40_09 PM](https://github.com/user-attachments/assets/c135f8b9-acc0-43a9-a1be-c167e8f38533)


---

## 🛠️ Technologies Used
- Java (JDK 8 or higher)
- Swing (GUI Design)
- JDBC (Database Connectivity)
- MySQL (Database)
- MySQL Connector/J (JAR file)

---

## 🔧 How to Set Up and Run

### 1. Set up Database
- Open your MySQL Workbench or phpMyAdmin.
- Run the following SQL commands:

```sql
CREATE DATABASE librarydb;

USE librarydb;

CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    year INT
);
