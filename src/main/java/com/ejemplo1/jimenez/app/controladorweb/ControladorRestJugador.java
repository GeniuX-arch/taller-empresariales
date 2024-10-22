package com.ejemplo1.jimenez.app.controladorweb;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ejemplo1.jimenez.app.repository.JugadorRepositorio;
import com.ejemplo1.jimenez.app.variables.Jugador;

@RestController
@RequestMapping("/api/jugadores")
public class ControladorRestJugador {
    @Autowired
    private JugadorRepositorio jugadorRepositorio;

    @GetMapping
    public ResponseEntity<List<Jugador>> listarJugadores() {
        List<Jugador> listaJugadores = jugadorRepositorio.findAll();
        return new ResponseEntity<>(listaJugadores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> obtenerJugador(@PathVariable("id") String id) {
        Optional<Jugador> jugador = jugadorRepositorio.findById(id);
        return jugador.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Jugador> guardarJugador(@RequestBody Jugador jugador) {
    	
        Jugador nuevoJugador = jugadorRepositorio.save(jugador);
        return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> modificarJugador(@PathVariable("id") String id, @RequestBody Jugador jugador) {
        Optional<Jugador> jugadorExistente = jugadorRepositorio.findById(id);
        if (jugadorExistente.isPresent()) {
            jugador.setId(id);
            Jugador jugadorActualizado = jugadorRepositorio.save(jugador);
            return new ResponseEntity<>(jugadorActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarJugador(@PathVariable("id") String id) {
        try {
            jugadorRepositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}