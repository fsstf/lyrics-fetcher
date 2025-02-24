package com.example.mahoraga.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosCancionLetra(
        @JsonAlias("title") String title,
        @JsonAlias("artist_names") String artist_names,
        String letra
) {
}
