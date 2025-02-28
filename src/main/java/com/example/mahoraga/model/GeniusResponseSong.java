package com.example.mahoraga.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeniusResponseSong (Response response) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(Song song) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Song(Long id, String title, @JsonAlias("artist_names") String artistNames, @JsonAlias("path") String path, @JsonAlias("header_image_url") String headerImageUrl) {}
}

