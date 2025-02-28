package com.example.mahoraga.service;

import com.example.mahoraga.model.DatosCancion;
import com.example.mahoraga.model.DatosCancionLetra;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CancionService {
    @Value("${TOKEN}")
    private String TOKEN;
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConsumoAPISong consumoAPISong = new ConsumoAPISong();

    public List<DatosCancion> buscarCanciones(String name) {
        return consumoAPI.obtenerDatos(name,TOKEN);
    }

    public DatosCancionLetra obtenerLetra(Long id) {
        return consumoAPISong.obtenerDatos(id,TOKEN);
    }

}
