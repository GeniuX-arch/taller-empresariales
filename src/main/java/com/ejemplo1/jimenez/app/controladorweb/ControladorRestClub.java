package com.ejemplo1.jimenez.app.controladorweb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ejemplo1.jimenez.app.repository.ClubRepositorio;
import com.ejemplo1.jimenez.app.variables.Club;

@RestController
@RequestMapping("/api/clubs")
public class ControladorRestClub {
    
    @Autowired
    private ClubRepositorio clubRepositorio;

    // GET all clubs
    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        List<Club> clubs = clubRepositorio.findAll();
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }

    // GET a single club by ID
    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable("id") String id) {
        return clubRepositorio.findById(id)
                .map(club -> new ResponseEntity<>(club, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST a new club
    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
    	
        Club newClub = clubRepositorio.save(club);
        return new ResponseEntity<>(newClub, HttpStatus.CREATED);
    }

    // PUT to update an existing club
    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable("id") String id, @RequestBody Club club) {
        return clubRepositorio.findById(id)
                .map(existingClub -> {
                    existingClub.setNombre(club.getNombre());
                    existingClub.setEntrenador(club.getEntrenador());
                    existingClub.setJugadores(club.getJugadores());
                    existingClub.setAsociacion(club.getAsociacion());
                    existingClub.setCompeticiones(club.getCompeticiones());
                    Club updatedClub = clubRepositorio.save(existingClub);
                    return new ResponseEntity<>(updatedClub, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE a club
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClub(@PathVariable("id") String id) {
        try {
            clubRepositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}