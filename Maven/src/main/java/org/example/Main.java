package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import static org.example.CRUD.*;

public class Main
{
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {

            connection = MySQLConnection.loadConnection("HotelKey");
            String selectQuery = "SELECT * FROM HotelKey.Employees";
            //TODO: Create a function that retrieves the values from employee object and populates the data in DB.
            String insertQuery = "INSERT INTO HotelKey.Employees (EmpId, EmpName, EmpAge, EmpDept) VALUES (10, 'Bon Jovie', 30, 'Sales');";
            String updateQuery = "UPDATE HotelKey.Employees SET EmpName = \"Muhammad Hassaan\", EmpAge = 24, EmpDept = \"IT\" WHERE EmpId = 3;";
            String deleteQuery = "DELETE FROM HotelKey.Employees WHERE EmpId = 10;";


            System.out.println("Return Set of Select: "+runSelect(connection, selectQuery)); // Running Select query.
            System.out.println("----------------------------------");
            System.out.println("Insert rows effected: "+runInsert(connection, insertQuery)); // Running Insert query.
            System.out.println("Update rows effected: "+runUpdate(connection, updateQuery)); // Running Update query.
            System.out.println("Delete rows effected: "+runDelete(connection, deleteQuery)); // Running Delete query.

            // Printing Employee with a particular id ('7').
            System.out.println("EmployeeId '7' : "+getEmployeeById(7));

            // Printing all the Employees.
            System.out.println("All Employees: -\n"+listEmployees());
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            // Closing the connection.
            if (connection != null && !connection.isClosed())
                    connection.close();
        }
    }
}