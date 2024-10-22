package com.ejemplo1.jimenez.app.crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ejemplo1.jimenez.app.variables.Competicion;

public interface CrudCompeticion extends MongoRepository<Competicion, String> {
}
