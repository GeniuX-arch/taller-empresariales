package com.ejemplo1.jimenez.app.crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ejemplo1.jimenez.app.variables.Asociacion;

public interface CrudAsociacion extends MongoRepository<Asociacion, String> {
}
