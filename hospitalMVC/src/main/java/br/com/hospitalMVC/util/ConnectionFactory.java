package br.com.hospitalMVC.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionFactory {
    public static final String PATH = "jdbc:postgresql://localhost:5432/hospitalMVC";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";

    public static Connection getConnection() throws Exception {
        try{
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(PATH, USER, PASSWORD);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        close(conn, stmt, rs);
    }

    public static void closeConnection(Connection conn, Statement stmt) throws Exception {
        close(conn, stmt, null);
    }

    public static void closeConnection(Connection conn) throws Exception {
        close(conn, null, null);
    }

    private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        try {
            if(conn != null)
                conn.close();
            if(stmt != null)
                stmt.close();
            if(rs != null)
                rs.close();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
