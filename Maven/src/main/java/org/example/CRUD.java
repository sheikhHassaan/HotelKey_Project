package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.example.MySQLConnection.loadConnection;

public class CRUD{
    public static ResultSet runSelect(Connection connection, String selectQuery){

        try {
            // Statement object allows executing statements on the connected database. This object is made using the connection object.
            Statement statement = connection.createStatement();

            // ResultSet object holds the result that comes from the query execution. Query is executed using the statement object.
            ResultSet execute = statement.executeQuery(selectQuery);

            // execute.next() returns true when there exists next record, and returns false after the last record.
            while (execute.next()) {
                System.out.print(execute.getInt("EMPID") + " ");
                System.out.print(execute.getString("EmpNaMe") + " ");
                System.out.print(execute.getInt("EmpAgE") + " ");
                System.out.print(execute.getString("EmPdEpT"));
                System.out.println();
            }

            return execute;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static int runInsert(Connection connection, String insertQuery){

        try {
            Statement statement = connection.createStatement();
            System.out.println("Insert executed.");
            return statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static int runUpdate(Connection connection, String updateQuery){
        try {
            Statement statement = connection.createStatement();
            System.out.println("Update executed.");
            return statement.executeUpdate(updateQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static int runDelete(Connection connection, String deleteQuery){
        try {
            Statement statement = connection.createStatement();
            System.out.println("Delete executed.");
            return statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static List<Employee> listEmployees(){
        try {
            List<Employee> employees = new ArrayList<>();
            Connection connection = loadConnection("HotelKey");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM EMPLOYEES;");

            Employee employee = new Employee();
            while (result.next()){
                employee = new Employee();

                employee.setEmpId(result.getInt(1));
                employee.setName(result.getString(2));
                employee.setAge(result.getInt(3));
                employee.setDept(result.getString(4));

                employees.add(employee);

            }
            System.out.println("listEmployees executed.");
            return employees;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Employee getEmployeeById(int employeeId){
        try{
            Employee employee = new Employee();
            Connection connection = loadConnection("HotelKey");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM HotelKey.EMPLOYEES WHERE EMPID = "+employeeId+';');

            while(result.next()){
                employee.setEmpId(result.getInt(1));
                employee.setName(result.getString(2));
                employee.setAge(result.getInt(3));
                employee.setDept(result.getString(4));
            }
            System.out.println("getEmployeeById executed.");
            return employee;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
