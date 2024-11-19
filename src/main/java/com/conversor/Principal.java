package com.conversor;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Menu menu = new Menu();
        ApiClient apiClient = new ApiClient();
        Scanner scanner = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 7) {
            menu.exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            String moedaBase = null;
            String moedaAlvo = null;

            switch (opcao) {
                case 1 -> {
                    moedaBase = "USD";
                    moedaAlvo = "EUR";
                }
                case 2 -> {
                    moedaBase = "EUR";
                    moedaAlvo = "USD";
                }
                case 3 -> {
                    moedaBase = "USD";
                    moedaAlvo = "BRL";
                }
                case 4 -> {
                    moedaBase = "BRL";
                    moedaAlvo = "USD";
                }
                case 5 -> {
                    moedaBase = "BRL";
                    moedaAlvo = "EUR";
                }
                case 6 -> {
                    moedaBase = "EUR";
                    moedaAlvo = "BRL";
                }
                case 7 -> System.out.println("Saindo... Até logo!");
                default -> System.out.println("Opção inválida!");
            }

            if (moedaBase != null && moedaAlvo != null) {
                Conversor conversor = apiClient.getExchangeRates(moedaBase);
                if (conversor != null) {
                    Double taxa = conversor.getConversionRate(moedaAlvo);
                    if (taxa != null) {
                        System.out.print("Digite o valor a ser convertido: ");
                        double valor = scanner.nextDouble();
                        System.out.println("Resultado: " + String.format("%.2f", valor) + " " + moedaBase + " " +
                                "equivale a " + String.format("%.2f", valor * taxa) + " " + moedaAlvo);
                    } else {
                        System.out.println("Taxa de conversão não encontrada para " + moedaAlvo);
                    }
                } else {
                    System.out.println("Erro ao obter os dados de conversão.");
                }
            }
        }

        scanner.close();
    }
}
