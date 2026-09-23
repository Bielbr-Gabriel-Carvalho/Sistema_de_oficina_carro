package com.oficina.model;

import java.util.ArrayList;
import java.util.List;

public class OrdemServico {
    private static int proximoNumero = 1;

    private int numero;
    private Cliente cliente;
    private String veiculo;
    private String placa;
    private List<Servico> servicos;
    private List<PecaItem> pecas;

    public OrdemServico(Cliente cliente, String veiculo, String placa) {
        this.numero = proximoNumero++;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.placa = placa;
        this.servicos = new ArrayList<>();
        this.pecas = new ArrayList<>();
    }

    public void adicionarServico(Servico servico) {
        servicos.add(servico);
    }

    public void adicionarPeca(Peca peca, int quantidade) {
        pecas.add(new PecaItem(peca, quantidade));
    }

    public double calcularTotal() {
        double total = 0;

        for (Servico servico : servicos) {
            total += servico.getValor();
        }

        for (PecaItem item : pecas) {
            total += item.getSubtotal();
        }

        return total;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();

        texto.append("OS #").append(numero).append("\n");
        texto.append("Cliente: ").append(cliente.getNome()).append("\n");
        texto.append("Telefone: ").append(cliente.getTelefone()).append("\n");
        texto.append("Veículo: ").append(veiculo).append("\n");
        texto.append("Placa: ").append(placa).append("\n");

        texto.append("Serviços:\n");
        if (servicos.isEmpty()) {
            texto.append("  Nenhum serviço.\n");
        } else {
            for (Servico servico : servicos) {
                texto.append("  - ").append(servico).append("\n");
            }
        }

        texto.append("Peças:\n");
        if (pecas.isEmpty()) {
            texto.append("  Nenhuma peça.\n");
        } else {
            for (PecaItem item : pecas) {
                texto.append("  - ").append(item).append("\n");
            }
        }

        texto.append(String.format("TOTAL: R$ %.2f", calcularTotal()));

        return texto.toString();
    }

    private static class PecaItem {
        private Peca peca;
        private int quantidade;

        public PecaItem(Peca peca, int quantidade) {
            this.peca = peca;
            this.quantidade = quantidade;
        }

        public double getSubtotal() {
            return peca.getPreco() * quantidade;
        }

        @Override
        public String toString() {
            return peca.getNome()
                    + " | Qtd: " + quantidade
                    + " | Subtotal: R$ "
                    + String.format("%.2f", getSubtotal());
        }
    }
}
