package com.ejemplo1.jimenez.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ejemplo1.jimenez.app.variables.Club;

public interface ClubRepositorio extends MongoRepository<Club, String> {
}
