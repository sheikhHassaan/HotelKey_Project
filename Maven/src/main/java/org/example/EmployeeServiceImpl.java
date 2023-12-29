package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    public boolean isExist(int empId) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT COUNT(*) AS COUNT FROM HOTELKEY.EMPLOYEES WHERE EMPID = ?;";
            int count;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelKey", "root", "Helloworld");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, empId);
            resultSet = preparedStatement.executeQuery();

            count = resultSet.getInt(1);
            return count > 0;
        } finally {
            connection.close();
            resultSet.close();
        }
    }

    public boolean isExist(String empName) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT COUNT(*) AS COUNT FROM HOTELKEY.EMPLOYEES WHERE EMPNAME = ?;";
            int count;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelKey", "root", "Helloworld");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, empName);
            resultSet = preparedStatement.executeQuery();

            count = resultSet.getInt(1);
            return count > 0;
        } finally {
            connection.close();
            resultSet.close();
        }
    }

    @Override
    public boolean addEmployee(Employee employee) throws SQLException {

        if (!isExist(employee.getEmpId())){
            System.out.println("A record with same the Employee ID already exists.");
            return false;
        }

        Connection connection = null;
        try{
            String query = "INSERT INTO HOTELKEY.EMPLOYEES (EMPID, EMPNAME, EMPAGE, EMPDEPT) VALUES (?,?,?,?);";

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelKey", "root", "Helloworld");
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, employee.getEmpId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getDept());

            preparedStatement.executeUpdate();
            return true;

        } finally {
            connection.close();
        }
    }

    @Override
    public int updateEmployee(Employee employee) throws SQLException {

        if (!isExist(employee.getEmpId())){
            System.out.println("The record you want to update doesn't exist.");
            return -1;
        }

        Connection connection = null;
        int rowsUpdated;
        try {

            String query = "UPDATE HOTELKEY.EMPLOYEES SET EMPNAME = ?, EMPAGE = ?, EMPDEPT = ? WHERE EMPID = ?;";

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelKey", "root", "Helloworld");
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, employee.getEmpId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getDept());

            rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated;

        } finally {
            connection.close();
        }
    }

    @Override
    public int deleteEmployee(int empId) throws SQLException {

        if (!isExist(empId)){
            System.out.println("The record you want to delete doesn't exist.");
            return -1;
        }

        Connection connection = null;
        int rowsUpdated;
        try {

            String query = "DELETE FROM HOTELKEY.EMPLOYEES WHERE EMPID = ?;";

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelKey", "root", "Helloworld");
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, empId);

            rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated;

        } finally {
            connection.close();
        }
    }

    @Override
    public List<Employee> getEmployee(int empId) throws SQLException{

        if (!isExist(empId)){
            System.out.println("The record you want to get doesn't exist.");
            return null;
        }

        List<Employee> employees = new ArrayList<>();
        Employee employee;
        ResultSet resultSet;
        Connection connection = null;
        try{

            String query = "SELECT * FROM HOTELKEY.EMPLOYEES WHERE EMPID = ?;";
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelKey", "root", "Helloworld");

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
            connection.close();
        }
    }

    @Override
    public List<Employee> getEmployee(String empName) throws SQLException{

        if (!isExist(empName)){
            System.out.println("The record you want to get doesn't exist.");
            return null;
        }

        List<Employee> employees = new ArrayList<>();
        Employee employee;
        ResultSet resultSet;
        Connection connection = null;
        try{

            String query = "SELECT * FROM HOTELKEY.EMPLOYEES WHERE EMPNAME = ?;";
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelKey", "root", "Helloworld");

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
            if (employees.size() == 0)
                System.out.println("No matching records found!");
            return employees;
        } finally {
            connection.close();
        }
    }
}
