package com.ejemplo1.jimenez.app.controladorweb;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ejemplo1.jimenez.app.repository.CompeticionRepositorio;
import com.ejemplo1.jimenez.app.variables.Competicion;

@RestController
@RequestMapping("/api/competiciones")
public class ControladorRestCompeticion {

    @Autowired
    private CompeticionRepositorio competicionRepositorio;

    // GET all competiciones
    @GetMapping
    public ResponseEntity<List<Competicion>> getAllCompeticiones() {
        List<Competicion> competiciones = competicionRepositorio.findAll();
        return new ResponseEntity<>(competiciones, HttpStatus.OK);
    }

    // GET a single competicion by ID
    @GetMapping("/{id}")
    public ResponseEntity<Competicion> getCompeticionById(@PathVariable("id") String id) {
        return competicionRepositorio.findById(id)
                .map(competicion -> new ResponseEntity<>(competicion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST a new competicion
    @PostMapping
    public ResponseEntity<Competicion> createCompeticion(@RequestBody Competicion competicion) {
        competicion.setId(UUID.randomUUID().toString());
        Competicion newCompeticion = competicionRepositorio.save(competicion);
        return new ResponseEntity<>(newCompeticion, HttpStatus.CREATED);
    }

    // PUT to update an existing competicion
    @PutMapping("/{id}")
    public ResponseEntity<Competicion> updateCompeticion(@PathVariable("id") String id, @RequestBody Competicion competicion) {
        return competicionRepositorio.findById(id)
                .map(existingCompeticion -> {
                    existingCompeticion.setNombre(competicion.getNombre());
                    // Actualiza otros campos que necesiten ser actualizados aquí
                    Competicion updatedCompeticion = competicionRepositorio.save(existingCompeticion);
                    return new ResponseEntity<>(updatedCompeticion, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE a competicion
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCompeticion(@PathVariable("id") String id) {
        try {
            competicionRepositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

