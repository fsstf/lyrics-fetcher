package com.example.mahoraga.service;

import com.example.mahoraga.model.DatosCancionLetra;
import com.example.mahoraga.model.GeniusResponseSong;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class ConsumoAPISong {
    ConsumoLetra consumoLetra = new ConsumoLetra();
    private static final String URL_BASE = "https://api.genius.com/songs/";

    public DatosCancionLetra obtenerDatos(Long id, String token) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE + id))
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
            GeniusResponseSong geniusResponseSong = objectMapper.readValue(response.body(), GeniusResponseSong.class);
            if (geniusResponseSong.response().song() == null) {
                return new DatosCancionLetra("null","null","null","null"); // Retornar song vacía si no hay resultados
            }
            GeniusResponseSong.Song song = geniusResponseSong.response().song();

            return new DatosCancionLetra(song.title(),song.artistNames(),consumoLetra.obtenerLetra(song.path()),song.headerImageUrl());


        } catch (IOException e) {
            throw new RuntimeException("Error de conexión o red: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restablecer el estado de interrupción
            throw new RuntimeException("La solicitud fue interrumpida: " + e.getMessage(), e);
        }
    }
}
