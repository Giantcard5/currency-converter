import models.API;
import models.Currency;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao Conversor de Moedas!\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha uma Moeda para converter: ");
        String fromCurrency = scanner.nextLine();

        System.out.println("Em qual Moeda você deseja converter: ");
        String toCurrency = scanner.nextLine();

        System.out.println("Escolha um valor de " + fromCurrency + " deseja converter: ");
        double fromCurrencyValue = scanner.nextDouble();

        try {
            API api = new API();

            double currency = api.converterCurrency(fromCurrency, toCurrency);
            double calculateCurrency = fromCurrencyValue * currency;

            System.out.println("\nO valor de " + fromCurrencyValue + " " + fromCurrency + " em " + toCurrency + " é: " + calculateCurrency);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}