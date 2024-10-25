package br.com.fiap.techguard.model;

public class Diagnostico extends Servico {

    // Atributos
    private String id;
    private String gravidade;
    private String dataHora;
    private boolean diagnosticado;

    // Construtores
    public Diagnostico(String id, String tipoServico, String descricao, String gravidade, String dataHora) {
        super(tipoServico, descricao);
        this.id = id;
        this.gravidade = gravidade;
        this.dataHora = dataHora;

    }
    public Diagnostico() {
        this.diagnosticado = false;
    }

    // Método de processar respostas gravidade
    public void processarRespostas(boolean[] respostas) {
        int score = 0;

        for (boolean resposta : respostas) {
            if (resposta) {
                score++;
            }
        }

        if (score >= 7) {
            gravidade = "Alta";
        } else if (score >= 4) {
            gravidade = "Média";
        } else {
            gravidade = "Baixa";
        }
        this.diagnosticado = true;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isDiagnosticado() {
        return diagnosticado;
    }

    public void setDiagnosticado(boolean diagnosticado) {
        this.diagnosticado = diagnosticado;
    }
}
