package com.oficina;

import com.oficina.model.Cliente;
import com.oficina.model.OrdemServico;
import com.oficina.model.Peca;
import com.oficina.model.Servico;
import com.oficina.util.ValidadorCPF;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Peca> pecas = new ArrayList<>();
    private static final List<OrdemServico> ordens = new ArrayList<>();

    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));

        int opcao;
        do {
            System.out.println("\n======================================");
            System.out.println("       SISTEMA DE OFICINA MECÂNICA");
            System.out.println("======================================");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar peça");
            System.out.println("3. Pesquisar peça");
            System.out.println("4. Criar ordem de serviço (cliente + serviços + peças)");
            System.out.println("5. Listar clientes");
            System.out.println("6. Listar peças");
            System.out.println("7. Listar ordens de serviço");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = lerInt();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarPeca();
                    break;
                case 3:
                    pesquisarPeca();
                    break;
                case 4:
                    criarOrdemServico();
                    break;
                case 5:
                    listarClientes();
                    break;
                case 6:
                    listarPecas();
                    break;
                case 7:
                    listarOrdens();
                    break;
                case 0:
                    System.out.println("Sistema encerrado. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrarCliente() {
        System.out.println("\n--- CADASTRO DE CLIENTE ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        boolean cpfValido = ValidadorCPF.validar(cpf);

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        clientes.add(new Cliente(nome, cpf, cpfValido, telefone));

        System.out.println("Cliente cadastrado!");
        System.out.println("CPF: " + (cpfValido ? "VÁLIDO" : "INVÁLIDO"));
    }

    private static void cadastrarPeca() {
        System.out.println("\n--- CADASTRO DE PEÇA ---");

        System.out.print("Código da peça: ");
        String codigo = scanner.nextLine();

        System.out.print("Nome da peça: ");
        String nome = scanner.nextLine();

        System.out.print("Preço (R$): ");
        double preco = lerDouble();

        System.out.print("Quantidade em estoque: ");
        int estoque = lerInt();

        pecas.add(new Peca(codigo, nome, preco, estoque));

        System.out.println("Peça cadastrada com sucesso!");
    }

    private static void pesquisarPeca() {
        System.out.println("\n--- PESQUISA DE PEÇAS ---");
        System.out.print("Digite o nome ou código: ");
        String busca = scanner.nextLine().toLowerCase();

        boolean encontrou = false;

        for (Peca peca : pecas) {
            if (peca.getCodigo().toLowerCase().contains(busca)
                    || peca.getNome().toLowerCase().contains(busca)) {
                System.out.println(peca);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma peça encontrada.");
        }
    }

    private static void criarOrdemServico() {
        if (clientes.isEmpty()) {
            System.out.println("Cadastre pelo menos um cliente antes.");
            return;
        }

        System.out.println("\n--- NOVA ORDEM DE SERVIÇO ---");
        Cliente cliente = escolherCliente();

        System.out.print("Veículo (marca/modelo): ");
        String veiculo = scanner.nextLine();

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        OrdemServico ordem = new OrdemServico(cliente, veiculo, placa);

        System.out.println("\nAdicione os serviços. Digite 0 para terminar.");
        while (true) {
            System.out.print("Descrição do serviço (ou 0): ");
            String descricao = scanner.nextLine();

            if ("0".equals(descricao)) {
                break;
            }

            System.out.print("Valor do serviço (R$): ");
            double valor = lerDouble();

            ordem.adicionarServico(new Servico(descricao, valor));
            System.out.println("Serviço adicionado.");
        }

        if (!pecas.isEmpty()) {
            System.out.println("\nDeseja adicionar peças? 1-Sim / 0-Não");
            int resposta = lerInt();

            if (resposta == 1) {
                while (true) {
                    listarPecas();
                    System.out.print("Digite o código da peça (ou 0 para terminar): ");
                    String codigo = scanner.nextLine();

                    if ("0".equals(codigo)) {
                        break;
                    }

                    Peca peca = buscarPecaPorCodigo(codigo);

                    if (peca == null) {
                        System.out.println("Peça não encontrada.");
                        continue;
                    }

                    System.out.print("Quantidade: ");
                    int quantidade = lerInt();

                    if (quantidade <= 0 || quantidade > peca.getEstoque()) {
                        System.out.println("Quantidade inválida ou estoque insuficiente.");
                        continue;
                    }

                    peca.retirarEstoque(quantidade);
                    ordem.adicionarPeca(peca, quantidade);
                    System.out.println("Peça adicionada.");
                }
            }
        }

        ordens.add(ordem);

        System.out.println("\n======================================");
        System.out.println("ORDEM DE SERVIÇO CRIADA!");
        System.out.println(ordem);
        System.out.println("======================================");
    }

    private static Cliente escolherCliente() {
        listarClientes();

        while (true) {
            System.out.print("Digite o número do cliente: ");
            int indice = lerInt() - 1;

            if (indice >= 0 && indice < clientes.size()) {
                return clientes.get(indice);
            }

            System.out.println("Cliente inválido.");
        }
    }

    private static Peca buscarPecaPorCodigo(String codigo) {
        for (Peca peca : pecas) {
            if (peca.getCodigo().equalsIgnoreCase(codigo)) {
                return peca;
            }
        }
        return null;
    }

    private static void listarClientes() {
        System.out.println("\n--- CLIENTES ---");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i));
        }
    }

    private static void listarPecas() {
        System.out.println("\n--- ESTOQUE DE PEÇAS ---");

        if (pecas.isEmpty()) {
            System.out.println("Nenhuma peça cadastrada.");
            return;
        }

        for (Peca peca : pecas) {
            System.out.println(peca);
        }
    }

    private static void listarOrdens() {
        System.out.println("\n--- ORDENS DE SERVIÇO ---");

        if (ordens.isEmpty()) {
            System.out.println("Nenhuma ordem de serviço cadastrada.");
            return;
        }

        for (OrdemServico ordem : ordens) {
            System.out.println(ordem);
            System.out.println("--------------------------------------");
        }
    }

    private static int lerInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número inteiro: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double lerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Digite um valor válido: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}
