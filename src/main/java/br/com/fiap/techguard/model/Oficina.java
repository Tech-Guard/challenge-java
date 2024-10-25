package br.com.fiap.techguard.model;

public class Oficina {

    // Atributos
    private String id;
    private String nome;
    private Endereco endereco;
    private String telefone;
    private String email;
    private String especialidadesOficina;

    // Construtores
    public Oficina(String id, String nome, Endereco endereco, String telefone, String email, String especialidadesOficina) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.especialidadesOficina = especialidadesOficina;
    }

    public Oficina(String id, String nome, Endereco endereco, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public Oficina(String id, String nome, Endereco endereco, String telefone) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Oficina(String id, String nome, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Oficina(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Oficina(String id) {
        this.id = id;
    }

    public Oficina() {}

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    public String getEspecialidadesOficina() {
        return especialidadesOficina;
    }

    public void setEspecialidadesOficina(String especialidadesOficina) {
        this.especialidadesOficina = especialidadesOficina;
    }
}
