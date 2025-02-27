package com.example.mahoraga.service;

import com.example.mahoraga.model.DatosCancion;
import com.example.mahoraga.model.DatosCancionLetra;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CancionService {
    @Value("${TOKEN}")
    private String TOKEN;
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos convierteDatos = new ConvierteDatos();
    ConsumoLetra consumoLetra = new ConsumoLetra();

    public List<DatosCancion> buscarCanciones(String name) {
        var json = consumoAPI.obtenerDatos(name,TOKEN);
        List<DatosCancion> datos = json.stream()
                .map(j -> convierteDatos.obtenerDatos(j, DatosCancion.class))
                .collect(Collectors.toList());
        return datos;
    }

    public DatosCancionLetra obtenerLetra(DatosCancion datosCancion) {
        return new DatosCancionLetra(datosCancion.title(),datosCancion.artist_names(), consumoLetra.obtenerLetra(datosCancion.path()));
    }

}
