package be.technifutur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static String URL = "jdbc:postgresql://localhost:6969/northwind";
    private static String USER = "postgres";
    private static String PSWD = "postgres";

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PSWD);
    }
}
