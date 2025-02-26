package com.example.mahoraga.service;

import com.example.mahoraga.model.Cancion;
import com.example.mahoraga.model.DatosCancion;
import com.example.mahoraga.model.DatosCancionLetra;
import com.example.mahoraga.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CancionRepository repository;

    public List<DatosCancion> buscarCanciones(String name) {
        var json = consumoAPI.obtenerDatos(name,TOKEN);
        List<DatosCancion> datos = json.stream()
                .map(j -> convierteDatos.obtenerDatos(j, DatosCancion.class))
                .collect(Collectors.toList());
        return datos;
    }

    public DatosCancionLetra obtenerLetra(DatosCancion datosCancion) {
        Cancion cancion = new Cancion(datosCancion,consumoLetra.obtenerLetra(datosCancion.path()));
        repository.save(cancion);
        return new DatosCancionLetra(cancion.getTilulo(),cancion.getArtista(),cancion.getLetra());
    }

}
