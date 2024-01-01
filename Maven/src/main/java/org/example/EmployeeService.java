package org.example;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeService {
    boolean isExist(int empId) throws SQLException, ConnectionNotFoundException;
    boolean isExist(String empName) throws SQLException, ConnectionNotFoundException;
    boolean addEmployee(Employee employee) throws SQLException, ConnectionNotFoundException;  // returns true/false, based on whether the operation was successful or not?
    int updateEmployee(Employee employee) throws SQLException, ConnectionNotFoundException;   // returns number of rows that were changed as a result of insertion.
    int deleteEmployee(int empId) throws SQLException, ConnectionNotFoundException;   // returns number of rows that were changed as a result of deletion.
    ArrayList<Employee> getEmployee(int empId) throws SQLException, ConnectionNotFoundException;    // Get employee object based on employeeId
    ArrayList<Employee> getEmployee(String empName) throws SQLException, ConnectionNotFoundException;   // Get employee object based on employeeName
}