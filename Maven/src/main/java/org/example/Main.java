package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {

            connection = MySQLConnection.loadConnection("HotelKey");
            EmployeeServiceImpl service = new EmployeeServiceImpl();

            ArrayList<Employee> employee1 = service.getEmployee(1);                                     // SELECT
            ArrayList<Employee> employee2 = service.getEmployee("Muhammad Hassaan");                 // SELECT

            System.out.println("Employee 1: "+employee1);
            System.out.println("Employee 2: "+employee2);

            Employee messi = new Employee(11, "Messi", 34, "Sports");
            Employee ronaldo = new Employee(11, "Ronaldo", 36, "Sports");

            boolean isAddedSuccessfully = service.addEmployee(messi);                                          // INSERT
            System.out.println("isAddedSuccessfully: "+isAddedSuccessfully);

            int recordsUpdated = service.updateEmployee(ronaldo);                                              // UPDATE
            System.out.println(recordsUpdated);

            int recordsDeleted = service.deleteEmployee(11);                                            // DELETE
            System.out.println(recordsDeleted);

        }
        catch (ClassNotFoundException | SQLException | ConnectionNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        catch (Exception e){
            System.out.println("Unknown exception occured. "+e.getMessage());
        }
        finally {
            if (connection != null && !connection.isClosed())
                    connection.close();
        }
    }
}