package com.example.mahoraga.service;

import com.example.mahoraga.model.DatosCancion;
import com.example.mahoraga.model.GeniusResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsumoAPI {

    private static final String URL_BASE = "https://api.genius.com/search?q=";

    public List<DatosCancion> obtenerDatos(String nombre, String token) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE + nombre.replace(" ", "%20")))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            // Realizar la solicitud HTTP
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Crear un ObjectMapper de Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Deserializar la respuesta JSON en un objeto GeniusResponse
            GeniusResponse geniusResponse = objectMapper.readValue(response.body(), GeniusResponse.class);

            // Verificar si existen hits
            if (geniusResponse.response().hits() == null || geniusResponse.response().hits().isEmpty()) {
                return List.of(); // Retornar lista vacía si no hay resultados
            }

            // Crear una lista para almacenar los resultados
            List<DatosCancion> resultados = new ArrayList<>();

            // Iterar sobre los hits y extraer los datos
            for (var hit : geniusResponse.response().hits()) {
                var result = hit.result();
                resultados.add(new DatosCancion(result.id(), result.title(), result.artistNames(),result.path()));
            }

            // Devolver la lista de resultados
            return resultados;

        } catch (IOException e) {
            throw new RuntimeException("Error de conexión o red: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restablecer el estado de interrupción
            throw new RuntimeException("La solicitud fue interrumpida: " + e.getMessage(), e);
        }
    }
}