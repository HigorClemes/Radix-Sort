package com.mycompany.radix;

import java.util.*;

public class Radix {

    public static void main(String[] args) {
        Arquivo arquivo = new Arquivo();
        Scanner scanner = new Scanner(System.in, "UTF-8");
        List<Item> items = null;
        //Necessário alterar o caminho onde o arquivo entrada.txt será buscado.
        String caminho = "COLOQUE AQUI O SEU CAMINHO";
        int opcaoCampo = 0;
        int opcaoSentido = 0;
        int opcaoValor = 0;
        int tamanho = 0;

        System.out.println("Alunos: Higor José Clemes e Lucas Davi Caitano Ouriques.\n");

        do {
            System.out.println("Deseja gerar valores aleatórios?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            System.out.print("Digite uma das opções acima: ");
            opcaoValor = scanner.nextInt();
        } while (opcaoValor != 1 && opcaoValor != 2);

        if (opcaoValor != 1) {
            items = arquivo.readInputFile(caminho + "ENTRADA.txt");
        } else {
            do {
                System.out.print("Digite o tamanho da lista de dados: ");
                tamanho = scanner.nextInt();
            } while (tamanho == 0);

            arquivo.gerarValoresAleatorios(caminho + "ENTRADA.txt", tamanho);
            items = arquivo.readInputFile(caminho + "ENTRADA.txt");
        }

        if (items != null) {
            do {
                System.out.println("\nEscolha o campo de ordenação:");
                System.out.println("1 - Código");
                System.out.println("2 - Descrição");
                System.out.println("3 - Unidade");
                System.out.println("4 - Valor");

                System.out.print("Digite uma das opções acima: ");
                opcaoCampo = scanner.nextInt();
            } while (opcaoCampo != 1 && opcaoCampo != 2 && opcaoCampo != 3 && opcaoCampo != 4);

            do {
                System.out.println("\nEscolha o sentido de ordenação:");
                System.out.println("1 - Ascendente");
                System.out.println("2 - Descendente");

                System.out.print("Digite uma das opções acima: ");
                opcaoSentido = scanner.nextInt();
            } while (opcaoSentido != 1 && opcaoSentido != 2);

            boolean ascendente = (opcaoSentido == 1);

            switch (opcaoCampo) {
                case 1:
                    RadixClass.radixSortByCodigo(items, ascendente);
                    break;
                case 2:
                    RadixClass.radixSortByDescricao(items, ascendente);
                    break;
                case 3:
                    RadixClass.radixSortByUnidade(items, ascendente);
                    break;
                case 4:
                    RadixClass.radixSortByValor(items, ascendente);
                    break;
                default:
                    System.out.println("Opção de campo inválida");
                    return;
            }

            arquivo.writeOutputFile(items, caminho + "OrdenacaoRadixSort.txt");
        }
    }
}
