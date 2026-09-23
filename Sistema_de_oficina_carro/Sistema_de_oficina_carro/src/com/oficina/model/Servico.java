package com.oficina.model;

public class Servico {
    private String descricao;
    private double valor;

    public Servico(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Serviço: " + descricao
                + " | Valor: R$ " + String.format("%.2f", valor);
    }
}
