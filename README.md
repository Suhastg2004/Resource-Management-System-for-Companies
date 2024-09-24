# Resource-Management-System-for-Companies
This project is a Java-based frontend application that enables managing company resources. It provides a facility to input resource details such as resource ID, name, timeline, quantity, and cost, which can be stored in a MySQL database. The application also allows users to retrieve and display all the resource details.

Features
Insert Resources: Input details for new resources and save them in the MySQL database.
View Resources: Retrieve and display all the resources stored in the database.

Technologies Used
Java (Swing for GUI)
MySQL (Database)
JDBC (Database connection)
JSP (Web functionality, if required)
Frontend: Java Swing components for resource management
Steps to Run the Project

Set Up Database:

Create a database in MySQL called company_db.
Create a table resource with the following schema:

CREATE TABLE resource (
    resource_id INT AUTO_INCREMENT PRIMARY KEY,
    resource_name VARCHAR(255) NOT NULL,
    timeline VARCHAR(100),
    quantity INT,
    cost DOUBLE
);

Configure the Java Project:

Set up the Java project in your IDE (Eclipse/IntelliJ).
Add MySQL JDBC Driver to the project library.
Update the getConnection() method in the code with your MySQL credentials.
Run the Application:

Compile and run the ResourceManagementApp class.
Use the interface to add new resources and view existing ones in the company database.
How to Use
Insert Resource:

Fill in the details of the resource (Resource ID, Name, Timeline, Quantity, Cost).
Click on the Insert button to save the resource to the database.

View Resources:
Click on the Select button to view a list of all resources in the database.

Future Enhancements:
Implement additional resource filtering based on various criteria (e.g., cost, timeline).
Add update and delete functionalities for resource management.
Improve the GUI design for a more user-friendly experience.
