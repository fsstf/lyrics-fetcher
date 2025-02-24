package com.example.mahoraga.repository;

import com.example.mahoraga.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepository extends JpaRepository <Cancion,Long>{
}
