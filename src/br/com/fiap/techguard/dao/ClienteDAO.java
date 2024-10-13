package br.com.fiap.techguard.dao;

import br.com.fiap.techguard.cliente.Cliente;
import br.com.fiap.techguard.database.ConexaoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    // Método para inserir um cliente no banco de dados
    public void insert(Cliente cliente) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            // Obtém a conexão
            conexao = ConexaoDB.getConnection();

            // SQL de inserção
            String sql = "INSERT INTO T_CP_CLIENTE (ID, NOME, TELEFONE, CPF, EMAIL, SENHA) VALUES (DEFAULT, ?, ?, ?, ?, ?)";

            // Prepara a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getSenha());

            // Executa a instrução
            stmt.executeUpdate();

            System.out.println("Cliente inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente no banco de dados.");
            e.printStackTrace();
        } finally {
            // Fecha a conexão e o PreparedStatement
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
