package br.com.fiap.techguard.cliente;

import java.util.ArrayList;
import java.util.List;

public class SistemaUsuarios {

    private List<Cliente> clientes;

    // Construtores
    public SistemaUsuarios() {
        this.clientes = new ArrayList<>();
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
        clientes.add(new Cliente(nome, cpf, telefone, email, senha));
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
        return null;
    }

}
