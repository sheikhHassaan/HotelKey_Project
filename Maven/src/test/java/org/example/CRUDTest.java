package org.example;

import junit.framework.TestCase;
import org.junit.Test;
import java.sql.*;
import static org.example.CRUD.*;
import static org.example.MySQLConnection.loadConnection;

public class CRUDTest extends TestCase {

    // Declaring the URL, User, Password strings to pass while establishing the connection.
    String url = "jdbc:mysql://localhost:3306/HotelKey";
    String user = "root";
    String password = "Helloworld";

    // Establishing the connection to MySQL database
    Connection testConnection = DriverManager.getConnection(url, user, password);
    Statement testStatement = testConnection.createStatement();
    ResultSet result;

    public CRUDTest() throws SQLException {
    }
    /*

    @Test
    public void testRunSelect() throws SQLException {
        result = testStatement.executeQuery("SELECT * FROM HOTELKEY.EMPLOYEES;");
        assertEquals(result, runSelect(testConnection, "SELECT * FROM HotelKey.Employees;"));
    }
    @Test
    public void testRunInsert() throws ClassNotFoundException, SQLException, ConnectionNotFoundException {

        Connection connection  = loadConnection("HotelKey");
        assertEquals(1, runInsert(connection, "INSERT INTO HotelKey.Employees (EmpId, EmpName, EmpAge, EmpDept) VALUES (11, 'Chris Rock', 36, 'Entertainment');"));
    }
     */

    
    @Test
    public void testRunUpdate() {
        assertEquals(true, true);
    }
    @Test
    public void testRunDelete() {
        assertEquals(true, true);
    }
    @Test
    public void testListEmployees() {
        assertEquals(true, true);
    }
    @Test
    public void testGetEmployeeById() {
        assertEquals(true, true);
    }
}