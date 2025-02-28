package com.example.mahoraga.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record DatosCancion(
        @JsonAlias("id")Long id,
        @JsonAlias("title") String title,
        @JsonAlias("artist_names") String artist_names,
        @JsonAlias("path") String path
) {
}
