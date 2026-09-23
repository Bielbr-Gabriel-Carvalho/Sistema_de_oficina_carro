package com.oficina.model;

public class Cliente {
    private String nome;
    private String cpf;
    private boolean cpfValido;
    private String telefone;

    public Cliente(String nome, String cpf, boolean cpfValido, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.cpfValido = cpfValido;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean isCpfValido() {
        return cpfValido;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        String statusCpf = cpfValido ? "Válido" : "Inválido";
        return "Cliente: " + nome
                + " | CPF: " + cpf + " (" + statusCpf + ")"
                + " | Tel: " + telefone;
    }
}
