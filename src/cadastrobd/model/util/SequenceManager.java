package cadastrobd.model.util;

import java.sql.*;

public class SequenceManager {
    public static int getValue(String nomeSequencia) {
        int id = -1;
        String sql = "SELECT NEXT VALUE FOR " + nomeSequencia;
        try (Connection con = ConectorBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) id = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}