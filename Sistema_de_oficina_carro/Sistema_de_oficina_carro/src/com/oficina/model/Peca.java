package com.oficina.model;

public class Peca {
    private String codigo;
    private String nome;
    private double preco;
    private int estoque;

    public Peca(String codigo, String nome, double preco, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void retirarEstoque(int quantidade) {
        if (quantidade > 0 && quantidade <= estoque) {
            estoque -= quantidade;
        }
    }

    @Override
    public String toString() {
        return "Código: " + codigo
                + " | Peça: " + nome
                + " | Preço: R$ " + String.format("%.2f", preco)
                + " | Estoque: " + estoque;
    }
}
