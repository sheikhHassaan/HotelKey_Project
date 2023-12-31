package org.example;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeServiceImpl implements EmployeeService {

    HikariConnection hikariConnection;
    Connection connection;

    public EmployeeServiceImpl(){
        connection = null;
        if (hikariConnection == null){
            hikariConnection = new HikariConnection();

        }
    }

    public boolean isExist(int empId) throws SQLException, ConnectionNotFoundException {

        ResultSet resultSet = null;
        try {
            String query = "SELECT COUNT(*) AS COUNT FROM HOTELKEY.EMPLOYEES WHERE EMPID = ?;";
//            int count = 0;
            boolean existsFlag = false;
            connection = hikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, empId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
//                count = resultSet.getInt(1);
                existsFlag = true;
            }
            return existsFlag;
        } finally {
//            connection.close();
//            resultSet.close();
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    public boolean isExist(String empName) throws SQLException, ConnectionNotFoundException {

        ResultSet resultSet = null;
        try {
            String query = "SELECT COUNT(*) AS COUNT FROM HOTELKEY.EMPLOYEES WHERE EMPNAME = ?;";
            int count;
            connection = hikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, empName);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            count = resultSet.getInt(1);
            return count > 0;
        } finally {
//            connection.close();
//            resultSet.close();
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }
    }

    @Override
    public boolean addEmployee(Employee employee) throws SQLException, ConnectionNotFoundException {

        if (!isExist(employee.getEmpId())){
            System.out.println("A record with same the Employee ID"+employee.getEmpId()+" already exists.");
            return false;
        }

        try{
            String query = "INSERT INTO HOTELKEY.EMPLOYEES (EMPID, EMPNAME, EMPAGE, EMPDEPT) VALUES (?,?,?,?);";

            connection = hikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, employee.getEmpId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getDept());

            preparedStatement.executeUpdate();
            return true;

        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public int updateEmployee(Employee employee) throws SQLException, ConnectionNotFoundException {

        if (!isExist(employee.getEmpId())){
            System.out.println("The record you want to update("+employee.getEmpId()+") doesn't exist.");
            return -1;
        }

        int rowsUpdated;
        try {

            String query = "UPDATE HOTELKEY.EMPLOYEES SET EMPNAME = ?, EMPAGE = ?, EMPDEPT = ? WHERE EMPID = ?;";

            connection = hikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getAge());
            preparedStatement.setString(3, employee.getDept());
            preparedStatement.setInt(4, employee.getEmpId());

            rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated;

        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public int deleteEmployee(int empId) throws SQLException, ConnectionNotFoundException {

        if (!isExist(empId)){
            System.out.println("The record you want to delete("+empId+") doesn't exist.");
            return -1;
        }

        int rowsUpdated;
        try {

            String query = "DELETE FROM HOTELKEY.EMPLOYEES WHERE EMPID = ?;";

            connection = hikariConnection.getPooledConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, empId);

            rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated;

        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public ArrayList<Employee> getEmployee(int empId) throws SQLException, ConnectionNotFoundException {

        // TODO: Remove this check.
        if (!isExist(empId)){
            System.out.println("The record you want to get doesn't exist.");
            return null;
        }

        Employee employee;
        ResultSet resultSet;
        ArrayList<Employee> employees = new ArrayList<>();

        try{

            String query = "SELECT * FROM HOTELKEY.EMPLOYEES WHERE EMPID = ?;";
            connection = hikariConnection.getPooledConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, empId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                employee = new Employee();

                employee.setEmpId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setAge(resultSet.getInt(3));
                employee.setDept(resultSet.getString(4));

                employees.add(employee);
            }
            return employees;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public ArrayList<Employee> getEmployee(String empName) throws SQLException, ConnectionNotFoundException {

        // TODO: Remove this check.
        if (!isExist(empName)){
            System.out.println("The record you want to get doesn't exist.");
            return null;
        }

        Employee employee;
        ResultSet resultSet;
        ArrayList<Employee> employees = new ArrayList<>();

        try{

            String query = "SELECT * FROM HOTELKEY.EMPLOYEES WHERE EMPNAME = ?;";
            connection = hikariConnection.getPooledConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, empName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                employee = new Employee();

                employee.setEmpId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setAge(resultSet.getInt(3));
                employee.setDept(resultSet.getString(4));

                employees.add(employee);
            }
            if (employees.isEmpty())
                System.out.println("No matching records found!");
            return employees;
        } finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }
}