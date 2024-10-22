package com.ejemplo1.jimenez.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ejemplo1.jimenez.app.variables.Entrenador;

public interface EntrenadorRepositorio extends MongoRepository<Entrenador, String> {
}