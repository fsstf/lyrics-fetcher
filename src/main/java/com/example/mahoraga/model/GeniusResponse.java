package com.example.mahoraga.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record GeniusResponse(Response response) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Response(List<Hit> hits) {}

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Hit(Result result) {}

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Result(Long id, String title, @JsonAlias("artist_names") String artistNames, @JsonAlias("path") String path) {}

    }
