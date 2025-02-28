package com.example.mahoraga.controller;

import com.example.mahoraga.model.DatosCancion;
import com.example.mahoraga.model.DatosCancionLetra;
import com.example.mahoraga.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CancionController {

    @Autowired
    CancionService cancionService;

    @GetMapping("/song")
    public ResponseEntity<List<DatosCancion>> buscarCancion(@RequestParam String name){
        List<DatosCancion> canciones = cancionService.buscarCanciones(name);
        return ResponseEntity.ok(canciones);
    }

    @GetMapping("/song/{id}")
    public DatosCancionLetra obternerLetra(@PathVariable Long id){
        return cancionService.obtenerLetra(id);
    }
}
