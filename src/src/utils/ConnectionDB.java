package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/player_management_db?createDatabaseIfNotExist=true";
    private static final String user = "root";
    private static final String pass = "10122001@";

    public static Connection getConnection(){
        try{

            Class.forName(Driver);

            return DriverManager.getConnection(url,user,pass);

        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    public static void closeConnection(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
