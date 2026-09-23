# Sistema de Oficina de Carros


## Funcionalidades

- Cadastro de clientes;
- Validação de CPF;
- Cadastro de peças;
- Pesquisa de peças por nome ou código;
- Controle simples do estoque;
- Cadastro de serviços;
- Criação de ordens de serviço;
- Associação do cliente e do veículo à ordem de serviço;
- Adição de serviços e peças à ordem de serviço;
- Cálculo do valor total da ordem de serviço;
- Listagem de clientes, peças e ordens de serviço.

## Estrutura do projeto

```text
src
└── com
    └── oficina
        ├── Main.java
        │
        ├── model
        │   ├── Cliente.java
        │   ├── Servico.java
        │   ├── Peca.java
        │   └── OrdemServico.java
        │
        └── util
            └── ValidadorCPF.java
```

## Classes principais

**Cliente:** representa o cliente da oficina, com informações como nome, CPF e telefone.

**Servico:** representa um serviço realizado pela oficina, contendo descrição e valor.

**Peca:** representa uma peça do estoque, com código, nome, preço e quantidade.

**OrdemServico:** reúne o cliente, veículo, serviços realizados e peças utilizadas em um atendimento.

**ValidadorCPF:** realiza a validação do CPF informado.

**Main:** classe principal do sistema, responsável pelo menu e pela interação com o usuário.

## Conceitos de Java utilizados

- Classes e objetos;
- Encapsulamento;
- Atributos privados;
- Métodos e construtores;
- `ArrayList`;
- Laços de repetição;
- Estruturas condicionais;
- Entrada de dados com `Scanner`;
- Relacionamento entre objetos;
- Organização em pacotes.

## Observação

Este projeto foi desenvolvido com finalidade **acadêmica**, para aplicar os conceitos estudados em Java em um exemplo de sistema de oficina mecânica.

O projeto não utiliza banco de dados. Por isso, os dados cadastrados ficam disponíveis somente durante a execução do programa.
