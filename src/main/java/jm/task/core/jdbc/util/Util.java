package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/tipost";
    private static final String NAME = "root";
    private static final String PASSWORD = "Ramazanchik$232004";

    public static Connection getConnection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("This project has been connected to database" + URL);
            }
        } catch(java.sql.SQLException e) {
            System.out.println("There is no connection(\n");
            e.printStackTrace();
        }
        return connection;
    }
}
