package com.example.mahoraga.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

            // Extraer la letra de la canción del HTML
            Element lyricsElement = doc.select("div.Lyrics-sc-37019ee2-1.jRTEBZ[data-lyrics-container='true']").first();
            if (lyricsElement == null) {
                throw new RuntimeException("No se pudo encontrar la letra en la página");
            }

            // Devolver la letra de la canción
            return lyricsElement.text();

        } catch (IOException e) {
            throw new RuntimeException("Error al obtener la letra: " + e.getMessage(), e);
        }
    }
}