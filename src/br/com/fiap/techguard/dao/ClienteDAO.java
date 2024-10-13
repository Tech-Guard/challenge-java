package br.com.fiap.techguard.dao;

import br.com.fiap.techguard.cliente.Cliente;
import br.com.fiap.techguard.database.ConexaoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    // Método de inserir um cliente
    public void insert(Cliente cliente) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoDB.getConnection();

            String sql = "INSERT INTO T_CP_CLIENTE (ID, NOME, TELEFONE, CPF, EMAIL, SENHA) VALUES (DEFAULT, ?, ?, ?, ?, ?)";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getSenha());

            stmt.executeUpdate();

            System.out.println("Cliente inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente no banco de dados.");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método de verificar se o cliente já existe
    public boolean clienteExiste(String cpf, String email, String telefone) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConexaoDB.getConnection();

            String sql = "SELECT COUNT(*) FROM T_CP_CLIENTE WHERE CPF = ? OR EMAIL = ? OR TELEFONE = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setString(2, email);
            stmt.setString(3, telefone);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se existir algum registro
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public Cliente buscarPorEmail(String email) {
        Cliente cliente = null;
        String sql = "SELECT * FROM T_CP_CLIENTE WHERE EMAIL = ?";

        try (Connection conexao = ConexaoDB.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getString("TELEFONE"),
                        rs.getString("EMAIL"),
                        rs.getString("SENHA")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar cliente pelo email: " + email);
        }

        return cliente;
    }

}
