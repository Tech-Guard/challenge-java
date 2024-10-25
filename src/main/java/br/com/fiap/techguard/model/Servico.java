package br.com.fiap.techguard.model;

public class Servico {

    // Atributos
    private String tipoServico;
    private String descricao;

    // Construtores
    public Servico(String tipoServico, String descricao) {
        this.tipoServico = tipoServico;
        this.descricao = descricao;
    }

    public Servico() {}

    // Getters Setters
    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
