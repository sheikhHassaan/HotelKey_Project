package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.example.MySQLConnection.loadConnection;

public class CRUD{
    public static ResultSet runSelect(Connection connection, String selectQuery) throws SQLException{

        Statement statement;
        ResultSet execute;

        // Statement object allows executing statements on the connected database. This object is made using the connection object.
        statement = connection.createStatement();

        // ResultSet object holds the result that comes from the query execution. Query is executed using the statement object.
        execute = statement.executeQuery(selectQuery);

        // execute.next() returns true when there exists next record, and returns false after the last record.
        while (execute.next()) {
            System.out.print(execute.getInt("EMPID") + " ");
            System.out.print(execute.getString("EmpNaMe") + " ");
            System.out.print(execute.getInt("EmpAgE") + " ");
            System.out.print(execute.getString("EmPdEpT"));
            System.out.println();
        }

        return execute;
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

    public static List<Employee> listEmployees() throws SQLException, ConnectionNotFoundException, ClassNotFoundException{
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            List<Employee> employees = new ArrayList<>();
            connection = loadConnection("HotelKey");
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM EMPLOYEES;");

            Employee employee;
            while (result.next()) {
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
        finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (result != null && !result.isClosed()){
                result.close();
            }
            if (statement != null && !statement.isClosed()){
                statement.close();
            }
        }
    }

    public static Employee getEmployeeById(int employeeId) throws SQLException, ConnectionNotFoundException, ClassNotFoundException{
        Connection connection= null;
        ResultSet result = null;
        try{
            Employee employee = new Employee();
            connection = loadConnection("HotelKey");
            Statement statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM HotelKey.EMPLOYEES WHERE EMPID = "+employeeId+';');

            while(result.next()){
                employee.setEmpId(result.getInt(1));
                employee.setName(result.getString(2));
                employee.setAge(result.getInt(3));
                employee.setDept(result.getString(4));
            }
            System.out.println("getEmployeeById executed.");
            return employee;
        }
        finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (result != null && !result.isClosed()){
                result.close();
            }
        }
    }

}
