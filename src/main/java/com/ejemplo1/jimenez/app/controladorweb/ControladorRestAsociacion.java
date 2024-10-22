package com.ejemplo1.jimenez.app.controladorweb;


import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ejemplo1.jimenez.app.repository.AsociacionRepositorio;
import com.ejemplo1.jimenez.app.variables.Asociacion;



@RestController
@RequestMapping("/api/asociaciones")
public class ControladorRestAsociacion {

    @Autowired
    private AsociacionRepositorio asociacionRepositorio;

    // GET all asociaciones
    @GetMapping
    public ResponseEntity<List<Asociacion>> getAllAsociaciones() {
        List<Asociacion> asociaciones = asociacionRepositorio.findAll();
        return new ResponseEntity<>(asociaciones, HttpStatus.OK);
    }

    // GET a single asociacion by ID
    @GetMapping("/{id}")
    public ResponseEntity<Asociacion> getAsociacionById(@PathVariable("id") String id) {
        return asociacionRepositorio.findById(id)
                .map(asociacion -> new ResponseEntity<>(asociacion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST a new asociacion
    @PostMapping
    public ResponseEntity<Asociacion> createAsociacion(@RequestBody Asociacion asociacion) {
        Asociacion newAsociacion = asociacionRepositorio.save(asociacion);
        return new ResponseEntity<>(newAsociacion, HttpStatus.CREATED);
    }

    // PUT to update an existing asociacion
    @PutMapping("/{id}")
    public ResponseEntity<Asociacion> updateAsociacion(@PathVariable("id") String id, @RequestBody Asociacion asociacion) {
        return asociacionRepositorio.findById(id)
                .map(existingAsociacion -> {
                    existingAsociacion.setNombre(asociacion.getNombre());
                    // Añade aquí otros campos que necesiten ser actualizados
                    Asociacion updatedAsociacion = asociacionRepositorio.save(existingAsociacion);
                    return new ResponseEntity<>(updatedAsociacion, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE an asociacion
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAsociacion(@PathVariable("id") String id) {
        try {
            asociacionRepositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET all paises (for form options)
    @GetMapping("/paises")
    public ResponseEntity<List<String>> getPaises() {
        List<String> listaPaises = Arrays.asList("Colombia", "Bolivia", "España", "Italia", "Inglaterra", "Francia", "Uruguay");
        return new ResponseEntity<>(listaPaises, HttpStatus.OK);
    }
}

