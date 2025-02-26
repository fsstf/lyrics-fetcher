package com.example.mahoraga.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsumoAPI {

    private String URL_BASE = "https://api.genius.com/search?q=";

    public List<String> obtenerDatos(String nombre,String TOKEN){
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE + nombre.replace(" ","")))
                .header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            // Realizar la solicitud HTTP
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Crear un ObjectMapper de Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Parsear el JSON de la respuesta
            JsonNode jsonNode = objectMapper.readTree(response.body());

            // Extraer el array "hits" dentro de "response"
            JsonNode hitsNode = jsonNode.path("response").path("hits");

            // Verificar si existe la estructura esperada
            if (hitsNode.isMissingNode() || !hitsNode.isArray() || hitsNode.size() == 0) {
                throw new RuntimeException("El JSON no contiene la estructura esperada (response -> hits) o está vacío");
            }

            // Crear una lista para almacenar los resultados
            List<String> resultados = new ArrayList<>();

            // Iterar sobre todos los elementos del array "hits"
            for (JsonNode hit : hitsNode) {
                // Extraer el objeto "result" de cada elemento
                JsonNode resultNode = hit.path("result");

                // Verificar si "result" existe
                if (!resultNode.isMissingNode()) {
                    // Agregar el objeto "result" como una cadena JSON a la lista
                    resultados.add(resultNode.toPrettyString());
                }
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