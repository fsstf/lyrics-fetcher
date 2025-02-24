package com.example.mahoraga.model;

import jakarta.persistence.*;

@Entity(name = "Cancion")
@Table(name = "canciones")
public class Cancion{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tilulo;
    private String artista;
    @Transient
    private String path;
    @Column(length = 2000)
    private String letra;

    public Cancion(DatosCancion datos,String letra) {
        this.tilulo = datos.title();
        this.artista = datos.artist_names();
        this.path = datos.path();
        this.letra = letra;
    }

    public String getLetra() {
        return letra;
    }

    public String getTilulo() {
        return tilulo;
    }

    public String getArtista() {
        return artista;
    }

}
