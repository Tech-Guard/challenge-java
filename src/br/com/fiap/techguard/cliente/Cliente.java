package br.com.fiap.techguard.cliente;

import br.com.fiap.techguard.endereco.Endereco;

import java.util.HashSet;
import java.util.Set;

public class Cliente {

    // Atributos
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private Endereco endereco;

    // Método de verificar senha
    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }

    // Método de retornar dados do cliente
    public String retornarDados() {
        return "\nNOME: " + nome + "\nCPF: " + cpf + "\nTELEFONE: " + telefone + "\nEMAIL: " + email + "\nSENHA: " + senha;
    }

    // Construtores
    public Cliente(String nome, String cpf, String telefone, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public Cliente(String nome, String cpf, String telefone, String email, String senha, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public Cliente () {}

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
