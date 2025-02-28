package com.example.mahoraga.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class ConsumoLetra {

    public String obtenerLetra(String path) {
        try {
            // Construir la URL completa de la letra
            String lyricsUrl = "https://genius.com" + path;

            // Realizar una solicitud HTTP para obtener el HTML de la página de la letra
            Document doc = Jsoup.connect(lyricsUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .header("Accept-Language", "es-ES,es;q=0.9")
                    .header("Referer", "https://genius.com/")
                    .get();

            // Extraer TODA la letra de la canción
            Elements lyricsElements = doc.select("div[data-lyrics-container='true']");

            // Si no se encuentran los elementos, devolver un mensaje
            if (lyricsElements.isEmpty()) {
                return "Letra no encontrada.";
            }

            // Concatenar todas las partes de la letra
            StringBuilder letraCompleta = new StringBuilder();
            for (Element element : lyricsElements) {
                letraCompleta.append(element.text()).append("\n\n");
            }

            return letraCompleta.toString().trim(); // Devolver la letra completa

        } catch (IOException e) {
            return "Error al obtener la letra: " + e.getMessage();
        }
    }
}