package models;

import com.google.gson.Gson;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    public double converterCurrency(String fromCurrency, String toCurrency) {
        URI address = URI.create("https://v6.exchangerate-api.com/v6/b4ed55bf2e07ce21f1bd4aaa/pair/" + fromCurrency + "/" + toCurrency);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(address)
                .header("Accept", "application/json")
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                throw new RuntimeException("\nErro 404: A conversão de " + fromCurrency + " para " + toCurrency + " não foi encontrada.");
            }

            if (response.statusCode() != 200) {
                throw new RuntimeException("\nErro na API: Código " + response.statusCode() + " - " + response.body());
            }

            Currency currency = new Gson().fromJson(response.body(), Currency.class);

            return currency.conversion_rate();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao acessar a API: " + e.getMessage());
        }
    }
}
