package br.com.fiap.techguard.cliente;

import br.com.fiap.techguard.dao.ClienteDAO;

import java.util.ArrayList;
import java.util.List;

public class SistemaUsuarios {

    private List<Cliente> clientes;
    private ClienteDAO clienteDAO;

    // Construtor
    public SistemaUsuarios() {
        this.clientes = new ArrayList<>();  // Inicializando a lista clientes
        this.clienteDAO = new ClienteDAO(); // Inicializando o DAO
    }

    // Método de cadastrar usuário
    public boolean cadastrarUsuario(String nome, String cpf, String telefone, String email, String senha) {
        cpf = cpf.trim();
        email = email.trim();
        telefone = telefone.trim();
        nome = nome.trim();

        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email) || cliente.getCpf().equals(cpf) || cliente.getTelefone().equals(telefone)) {
                return false;
            }
        }

        if (clienteDAO.clienteExiste(cpf, email, telefone)) {
            return false;
        }
        Cliente novoCliente = new Cliente(nome, cpf, telefone, email, senha);
        clientes.add(novoCliente);
        clienteDAO.insert(novoCliente);

        return true;
    }

    // Método de login usuário
    public Cliente loginUsuario(String email, String senha) {
        email = email.trim();

        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email) && cliente.verificarSenha(senha)) {
                return cliente; // Login bem-sucedido
            }
        }
        // Se não encontrar na lista local, faz a verificação no banco de dados
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente clienteBanco = clienteDAO.buscarPorEmail(email); // Método no DAO que busca pelo email

        if (clienteBanco != null && clienteBanco.verificarSenha(senha)) {
            return clienteBanco; // Login bem-sucedido no banco de dados
        }

        return null;
    }

}
