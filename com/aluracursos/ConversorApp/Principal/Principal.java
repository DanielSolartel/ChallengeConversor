package com.aluracursos.ConversorApp.Principal;

import com.aluracursos.ConversorApp.Modelos.DivisaApi;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.text.DecimalFormat;


public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        String busqueda = "USD";
        double cantidad;
        DecimalFormat df = new DecimalFormat("#.######");

        while (option != 7) {
            System.out.println("**********************************\n" +
                    "Sea bienvenido/a al conversor de monedas\n" +
                    "1. Dolar -> Peso Argentino.\n" +
                    "2. Peso Argentino -> Dolar.\n" +
                    "3. Dolar -> Real Brasilenio.\n" +
                    "4. Real Brasilenio -> Dolar.\n" +
                    "5. Dolar -> Peso Colombiano.\n" +
                    "6. Peso Colombiano -> Dolar.\n" +
                    "7. Salir.\n" +
                    "Elija una opción válida: \n" +
                    "**********************************");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    busqueda = "USD";
                    break;
                case 2:
                    busqueda = "ARS";
                    break;
                case 3:
                    busqueda = "USD";
                    break;
                case 4:
                    busqueda = "BRL";
                    break;
                case 5:
                    busqueda = "USD";
                    break;
                case 6:
                    busqueda = "COP";
                    break;
                case 7:
                    System.out.println("Saliendo del programa..");
                    scanner.close();
                    break;
                default:
                    System.out.println("Numero invalido, intentelo de nuevo");
            }

            if (option != 7) {
                String direccion = "https://v6.exchangerate-api.com/v6/e76124ed456a072e5902443c/latest/" + busqueda;
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                Gson gson = new Gson();
                DivisaApi divisaApi = gson.fromJson(json, DivisaApi.class);
                Double Rate;

                switch (option) {
                    case 1:
                        Rate = divisaApi.conversion_rates().get("ARS");
                        System.out.println("Ingrese la cantidad de Dolares: ");
                        cantidad = scanner.nextDouble();
                        System.out.println("El total son: " + df.format(cantidad * Rate) + " Pesos Argentinos");
                        break;
                    case 2:
                        Rate = divisaApi.conversion_rates().get("USD");
                        System.out.println("Ingrese la cantidad de Pesos Argentinos: ");
                        cantidad = scanner.nextDouble();
                        System.out.println("El total son: " + df.format(cantidad * Rate) + " Dolares");
                        break;
                    case 3:
                        Rate = divisaApi.conversion_rates().get("BRL");
                        System.out.println("Ingrese la cantidad de Dolares: ");
                        cantidad = scanner.nextDouble();
                        System.out.println("El total son: " + df.format(cantidad * Rate) + " Reales Brasilenios");
                        break;
                    case 4:
                        Rate = divisaApi.conversion_rates().get("USD");
                        System.out.println("Ingrese la cantidad de Reales Brasilenios: ");
                        cantidad = scanner.nextDouble();
                        System.out.println("El total son: " + df.format(cantidad * Rate) + " Dolares");
                        break;
                    case 5:
                        Rate = divisaApi.conversion_rates().get("COP");
                        System.out.println("Ingrese la cantidad de Dolares: ");
                        cantidad = scanner.nextDouble();
                        System.out.println("El total son: " + df.format(cantidad * Rate) + " Pesos Colombianos");
                        break;
                    case 6:
                        Rate = divisaApi.conversion_rates().get("USD");
                        System.out.println("Ingrese la cantidad de Pesos Colombianos: ");
                        cantidad = scanner.nextDouble();
                        System.out.println("El total son: " + df.format(cantidad * Rate) + " Dolares");
                        break;
                }
            }
        }
    }
}
