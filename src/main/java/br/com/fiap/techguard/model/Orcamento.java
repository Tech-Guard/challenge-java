package br.com.fiap.techguard.model;

public class Orcamento extends Servico {

    // Atributos
    private String id;
    private double custoTotal;
    private String status;
    private String dataExpiracao;

    // Construtores
    public Orcamento(String id, String tipoServico, String descricao, double custoTotal) {
        super(tipoServico, descricao);
        this.id = id;
        this.custoTotal = custoTotal;
    }

    public Orcamento( String id, String tipoServico, String descricao, double custoTotal, String status, String dataExpiracao) {
        super(tipoServico, descricao);
        this.id = id;
        this.custoTotal = custoTotal;
        this.status = status;
        this.dataExpiracao = dataExpiracao;
    }

    public Orcamento() {}

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCustoTotal() { return custoTotal; }

    public void setCustoTotal(double custoTotal) { this.custoTotal = custoTotal; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(String dataExpiracao) { this.dataExpiracao = dataExpiracao; }
}
