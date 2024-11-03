package br.com.fiap.techguard.dao;

import br.com.fiap.techguard.exception.ClienteNaoEncontradoException;
import br.com.fiap.techguard.model.Cliente;
import br.com.fiap.techguard.factory.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Método de cadastrar um cliente
    public void cadastrar(Cliente cliente) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getConnection();
            String sql = "INSERT INTO T_CHALLENGE_CLIENTES (NOME, TELEFONE, CPF, EMAIL, SENHA) VALUES (?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getSenha());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setId(generatedKeys.getString(1));
            }

            System.out.println("Cliente cadastrado com sucesso!");

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

    // Método de listar clientes
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM T_CHALLENGE_CLIENTES";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getString("clienteid"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // Método de pesquisar por ID
    public Cliente pesquisarPorId(String id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM T_CHALLENGE_CLIENTES WHERE clienteid = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getString("clienteid"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("senha")
                );
            } else {
                throw new ClienteNaoEncontradoException("Cliente não encontrado com ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao pesquisar cliente", e);
        }

        return cliente;
    }

    // Método de atualizar cliente
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE T_CHALLENGE_CLIENTES SET nome = ?, telefone = ?, cpf = ?, email = ?, senha = ? WHERE clienteid = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getSenha());
            stmt.setString(6, cliente.getId());

            System.out.println("Executando atualização para o cliente: " + cliente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método de remover cliente
    public void remover(String id) {
        String sql = "DELETE FROM T_CHALLENGE_CLIENTES WHERE clienteid = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método de verificar se o cliente já existe
    public boolean clienteExiste(String telefone, String cpf, String email) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionFactory.getConnection();

            String sql = "SELECT COUNT(*) FROM T_CHALLENGE_CLIENTES WHERE TELEFONE = ? OR CPF = ? OR EMAIL = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, telefone);
            stmt.setString(2, cpf);
            stmt.setString(3, email);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            System.out.println("Cliente já cadastrado");

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

    // Método de buscar login cliente
    public Cliente buscarPorLogin(String email, String senha) throws SQLException {
        Cliente cliente = null;
        String sql = "SELECT * FROM T_CHALLENGE_CLIENTES WHERE EMAIL = ? AND SENHA = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getString("NOME"),
                        rs.getString("TELEFONE"),
                        rs.getString("CPF"),
                        rs.getString("EMAIL"),
                        rs.getString("SENHA")
                );
            } else {
                throw new SQLException("Email não encontrado ou senha incorreta.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
            throw e;
        }

        return cliente;
    }

}
