package br.com.gomide.hashing.model;

import java.util.Scanner;

import br.com.gomide.hashing.service.HashList;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashList<Produto> hashList = new HashList<>();
        HashTable<Produto> hashTable = hashList.createHashTable(4);

        while (true) {
            System.out.println("##########################################################");
            System.out.println("                         Menu");
            System.out.println("##########################################################");
            System.out.println("1 - Inserir produto");
            System.out.println("2 - Consultar todos os produtos cadastrados de um tipo");
            System.out.println("3 - Contar quantos produtos estão cadastrados em cada tipo");
            System.out.println("4 - Sair");
            
            System.out.print("\nInforme uma opção: ");

            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    System.out.print("Descrição do produto: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Tipo (A - Alimentação, H - Higiene, L - Limpeza, V - Vestuário): ");
                    String tipo = scanner.nextLine().toUpperCase();

                    if (!tipo.matches("[AHLV]")) {
                        System.out.println("Tipo inválido!");
                        break;
                    }

                    Produto produto = new Produto(tipo, descricao);
                    hashList.insert(hashTable, produto);
                    System.out.println("Produto inserido com sucesso!");
                    break;

                case 2:
                    System.out.print("Informe o tipo (A, H, L, V): ");
                    String tipoConsulta = scanner.nextLine().toUpperCase();

                    if (!tipoConsulta.matches("[AHLV]")) {
                        System.out.println("Tipo inválido!");
                        break;
                    }

                    boolean encontrou = false;

                    System.out.println(" ");

                    for (Node<Produto> bucket : hashTable.getItems()) {
                        Node<Produto> node = bucket;
                        while (node != null) {
                            if (node.getStatus() == NodeStatus.BUSY &&
                                node.getValue().getTipo().equals(tipoConsulta)) {
                                System.out.println("- " + node.getValue().getDescricao());
                                encontrou = true;
                            }
                            node = node.getNext();
                        }
                    }

                    if (!encontrou) {
                        System.out.println("Nenhum produto encontrado para o tipo informado.");
                    }

                    System.out.println(" ");

                    break;

                case 3:
                    String[] tipos = {"A", "H", "L", "V"};
                    String[] nomes = {"Alimentação", "Higiene", "Limpeza", "Vestuário"};
                
                    for (int i = 0; i < tipos.length; i++) {
                        int count = 0;
                        int index = getIndexByTipo(tipos[i]);
                        Node<Produto> current = hashTable.getItems().get(index);
                        
                        while (current != null && current.getValue() != null) {
                            if (current.getValue().getTipo().equals(tipos[i])) {
                                count++;
                            }
                            current = current.getNext();
                        }
                        System.out.printf("%s - %d produto%s%n", nomes[i], count, count == 1 ? "" : "s");
                    }
                    break;
                
                case 4:
                    System.out.println("##########################################################");
                    System.out.println("                   Programa encerrado!");
                    System.out.println("##########################################################");

                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static int getIndexByTipo(String tipo) {
        switch (tipo) {
            case "A": return 0;
            case "H": return 1;
            case "L": return 2;
            case "V": return 3;
            default: throw new IllegalArgumentException("Tipo inválido: " + tipo);
        }
    }
}
