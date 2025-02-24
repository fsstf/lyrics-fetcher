package com.example.mahoraga.controller;

import com.example.mahoraga.model.DatosCancion;
import com.example.mahoraga.model.DatosCancionLetra;
import com.example.mahoraga.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CancionController {

    @Autowired
    CancionService cancionService;

    @GetMapping("/songs")
    public List<DatosCancion> bucarCancion(@RequestParam String name){
        return cancionService.buscarCanciones(name);
    }

    @PostMapping("/song")
    public DatosCancionLetra obternerLetra(@RequestBody DatosCancion datosCancion){
        return cancionService.obtenerLetra(datosCancion);
    }
}
