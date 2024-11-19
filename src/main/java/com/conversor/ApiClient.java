package com.conversor;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/a846bd83a804c595338a6f23/latest/";

    public Conversor getExchangeRates(String moedaBase) {
        try {
            URL url = new URL(API_URL + moedaBase);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                return new Gson().fromJson(reader, Conversor.class);
            } else {
                System.out.println("Erro ao conectar à API: " + connection.getResponseMessage());
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
}
