package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {

    public PessoaJuridica getPessoa(int id) {
        String sql = "SELECT p.id, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pj.cnpj " +
                     "FROM Pessoa p INNER JOIN PessoaJuridica pj ON p.id = pj.id WHERE p.id = ?";
        try (Connection con = ConectorBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PessoaJuridica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> lista = new ArrayList<>();
        String sql = "SELECT p.id, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pj.cnpj " +
                     "FROM Pessoa p INNER JOIN PessoaJuridica pj ON p.id = pj.id";
        try (Connection con = ConectorBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new PessoaJuridica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void incluir(PessoaJuridica pj) {
        int id = SequenceManager.getValue("seq_pessoa");
        pj.id = id;

        String sql1 = "INSERT INTO Pessoa (id, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO PessoaJuridica (id, cnpj) VALUES (?, ?)";

        try (Connection con = ConectorBD.getConnection();
             PreparedStatement stmt1 = con.prepareStatement(sql1);
             PreparedStatement stmt2 = con.prepareStatement(sql2)) {

            stmt1.setInt(1, pj.id);
            stmt1.setString(2, pj.nome);
            stmt1.setString(3, pj.logradouro);
            stmt1.setString(4, pj.cidade);
            stmt1.setString(5, pj.estado);
            stmt1.setString(6, pj.telefone);
            stmt1.setString(7, pj.email);
            stmt1.executeUpdate();

            stmt2.setInt(1, pj.id);
            stmt2.setString(2, pj.getCnpj());
            stmt2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaJuridica pj) {
        String sql1 = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
        String sql2 = "UPDATE PessoaJuridica SET cnpj = ? WHERE id = ?";

        try (Connection con = ConectorBD.getConnection();
             PreparedStatement stmt1 = con.prepareStatement(sql1);
             PreparedStatement stmt2 = con.prepareStatement(sql2)) {

            stmt1.setString(1, pj.nome);
            stmt1.setString(2, pj.logradouro);
            stmt1.setString(3, pj.cidade);
            stmt1.setString(4, pj.estado);
            stmt1.setString(5, pj.telefone);
            stmt1.setString(6, pj.email);
            stmt1.setInt(7, pj.id);
            stmt1.executeUpdate();

            stmt2.setString(1, pj.getCnpj());
            stmt2.setInt(2, pj.id);
            stmt2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql1 = "DELETE FROM PessoaJuridica WHERE id = ?";
        String sql2 = "DELETE FROM Pessoa WHERE id = ?";

        try (Connection con = ConectorBD.getConnection();
             PreparedStatement stmt1 = con.prepareStatement(sql1);
             PreparedStatement stmt2 = con.prepareStatement(sql2)) {

            stmt1.setInt(1, id);
            stmt1.executeUpdate();

            stmt2.setInt(1, id);
            stmt2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}