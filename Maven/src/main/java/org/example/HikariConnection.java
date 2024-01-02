package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnection {
    static Connection connection;
    static HikariDataSource dataSource;
    static HikariConfig config;

    public static Connection getPooledConnection() throws SQLException, ConnectionNotFoundException {

        config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/HotelKey");
        config.setUsername("root");
        config.setPassword("Helloworld");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);

        if (dataSource == null){
            dataSource = new HikariDataSource(config);
        }
        connection = dataSource.getConnection();

        if (connection == null){
            throw new ConnectionNotFoundException();
        }
        return connection;

/*
        finally {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (dataSource != null && dataSource.isRunning()){
                dataSource.close();
            }
        }
*/
    }
    public static void close() throws SQLException {
        if (connection != null && !connection.isClosed()){
            connection.close();
        }
        if (dataSource != null && !dataSource.isClosed()){
            dataSource.close();
        }
    }
}
