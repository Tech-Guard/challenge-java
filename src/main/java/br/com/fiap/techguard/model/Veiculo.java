package br.com.fiap.techguard.model;

public class Veiculo {

    // Atributos
    private String id;
    private String placa;
    private int quilometragem;
    private boolean motor;
    private int ano;
    private String cor;
    private String modelo;
    private String marca;

    //Construtores
    public Veiculo(String id, String placa, int quilometragem, boolean motor, int ano, String cor, String modelo, String marca) {
        this.id = id;
        this.placa = placa;
        this.quilometragem = quilometragem;
        this.motor = motor;
        this.ano = ano;
        this.cor = cor;
        this.modelo = modelo;
        this.marca = marca;
    }

    public Veiculo() {}

    //Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public boolean isMotor() {
        return motor;
    }

    public void setMotor(boolean motor) {
        this.motor = motor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
