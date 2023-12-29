package org.example;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {

    public boolean isExist(int empId) throws SQLException;
    public boolean isExist(String empName) throws SQLException;


    boolean addEmployee(Employee employee) throws SQLException;  // returns true/false, based on whether the operation was successful or not?
    int updateEmployee(Employee employee) throws SQLException;   // returns number of rows that were changed as a result of insertion.
    int deleteEmployee(int empId) throws SQLException;   // returns number of rows that were changed as a result of deletion.
    List<Employee> getEmployee(int empId) throws SQLException;    // Get employee object based on employeeId
    List<Employee> getEmployee(String empName) throws SQLException;   // Get employee object based on employeeName
}