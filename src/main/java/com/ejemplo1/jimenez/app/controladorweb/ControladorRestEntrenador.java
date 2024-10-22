package com.ejemplo1.jimenez.app.controladorweb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ejemplo1.jimenez.app.repository.EntrenadorRepositorio;
import com.ejemplo1.jimenez.app.variables.Entrenador;

@RestController
@RequestMapping("/api/entrenadores")
public class ControladorRestEntrenador {

    @Autowired
    private EntrenadorRepositorio entrenadorRepositorio;

    // GET all entrenadores
    @GetMapping
    public ResponseEntity<List<Entrenador>> getAllEntrenadores() {
        List<Entrenador> entrenadores = entrenadorRepositorio.findAll();
        return new ResponseEntity<>(entrenadores, HttpStatus.OK);
    }

    // GET a single entrenador by ID
    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> getEntrenadorById(@PathVariable("id") String id) {
        return entrenadorRepositorio.findById(id)
                .map(entrenador -> new ResponseEntity<>(entrenador, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST a new entrenador
    @PostMapping
    public ResponseEntity<Entrenador> createEntrenador(@RequestBody Entrenador entrenador) {
        Entrenador newEntrenador = entrenadorRepositorio.save(entrenador);
        return new ResponseEntity<>(newEntrenador, HttpStatus.CREATED);
    }

    // PUT to update an existing entrenador
    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> updateEntrenador(@PathVariable("id") String id, @RequestBody Entrenador entrenador) {
        return entrenadorRepositorio.findById(id)
                .map(existingEntrenador -> {
                    existingEntrenador.setNombre(entrenador.getNombre());
                    
                    // Actualiza otros campos que necesiten ser actualizados aquí
                    Entrenador updatedEntrenador = entrenadorRepositorio.save(existingEntrenador);
                    return new ResponseEntity<>(updatedEntrenador, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE a entrenador
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEntrenador(@PathVariable("id") String id) {
        try {
            entrenadorRepositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
