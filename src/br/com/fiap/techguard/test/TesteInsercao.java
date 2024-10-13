package br.com.fiap.techguard.test;

import br.com.fiap.techguard.cliente.Cliente;
import br.com.fiap.techguard.dao.ClienteDAO;

public class TesteInsercao {

    public static void main(String[] args) {
        // Criação de um novo cliente
        Cliente cliente = new Cliente("Maria", "98765432100", "(11) 91234-5678", "maria@gmail.com", "321");

        // Instancia o DAO e insere o cliente no banco
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.insert(cliente);
    }
}
