package org.example;

import java.sql.*;

public class MySQLConnection{
    public static Connection loadConnection(String dataBase){

        try{
            // Loading the JDBC Driver class (Optional)
//            Class.forName("com.mysql.cj.jdbc.Driver");

            // Declaring the URL, User, Password strings to pass while establishing the connection.
            String url = "jdbc:mysql://localhost:3306/"+dataBase;
            String user = "root";
            String password = "Helloworld";

            // Establishing the connection to MySQL database
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connection established successfully!");
                return connection;
            }
            else {
                System.out.println("Failed to connect!");
                return null;
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
//        catch (ClassNotFoundException e){
//            System.out.println(e.getMessage());
//            return null;
//        }
    }

    public static void close(Connection connection){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void close(Statement statement){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void close(ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}