package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {

    public PessoaFisica getPessoa(int id) {
        String sql = "SELECT p.id, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cpf " +
                     "FROM Pessoa p INNER JOIN PessoaFisica pf ON p.id = pf.id WHERE p.id = ?";
        try (Connection con = ConectorBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PessoaFisica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> lista = new ArrayList<>();
        String sql = "SELECT p.id, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cpf " +
                     "FROM Pessoa p INNER JOIN PessoaFisica pf ON p.id = pf.id";
        try (Connection con = ConectorBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new PessoaFisica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void incluir(PessoaFisica pf) {
        int id = SequenceManager.getValue("seq_pessoa");
        pf.id = id;

        String sql1 = "INSERT INTO Pessoa (id, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO PessoaFisica (id, cpf) VALUES (?, ?)";

        try (Connection con = ConectorBD.getConnection();
             PreparedStatement stmt1 = con.prepareStatement(sql1);
             PreparedStatement stmt2 = con.prepareStatement(sql2)) {

            stmt1.setInt(1, pf.id);
            stmt1.setString(2, pf.nome);
            stmt1.setString(3, pf.logradouro);
            stmt1.setString(4, pf.cidade);
            stmt1.setString(5, pf.estado);
            stmt1.setString(6, pf.telefone);
            stmt1.setString(7, pf.email);
            stmt1.executeUpdate();

            stmt2.setInt(1, pf.id);
            stmt2.setString(2, pf.getCpf());
            stmt2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaFisica pf) {
        String sql1 = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
        String sql2 = "UPDATE PessoaFisica SET cpf = ? WHERE id = ?";

        try (Connection con = ConectorBD.getConnection();
             PreparedStatement stmt1 = con.prepareStatement(sql1);
             PreparedStatement stmt2 = con.prepareStatement(sql2)) {

            stmt1.setString(1, pf.nome);
            stmt1.setString(2, pf.logradouro);
            stmt1.setString(3, pf.cidade);
            stmt1.setString(4, pf.estado);
            stmt1.setString(5, pf.telefone);
            stmt1.setString(6, pf.email);
            stmt1.setInt(7, pf.id);
            stmt1.executeUpdate();

            stmt2.setString(1, pf.getCpf());
            stmt2.setInt(2, pf.id);
            stmt2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql1 = "DELETE FROM PessoaFisica WHERE id = ?";
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