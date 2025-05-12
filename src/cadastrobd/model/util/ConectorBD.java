package cadastrobd.model.util;

import java.sql.*;

public class ConectorBD {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true";
    private static final String USER = "loja";
    private static final String PASSWORD = "loja";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static PreparedStatement getPrepared(Connection con, String sql) throws SQLException {
        return con.prepareStatement(sql);
    }

    public static ResultSet getSelect(PreparedStatement stmt) throws SQLException {
        return stmt.executeQuery();
    }

    public static void close(ResultSet rs) {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
    }

    public static void close(Statement stmt) {
        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
    }

    public static void close(Connection con) {
        try { if (con != null) con.close(); } catch (Exception e) {}
    }
}