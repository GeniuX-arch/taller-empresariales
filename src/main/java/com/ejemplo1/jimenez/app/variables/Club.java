package com.ejemplo1.jimenez.app.variables;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Document(collection = "clubes")
public class Club {
    @Id
    private String id;
    private String nombre;
    
    @DBRef
    private Entrenador entrenador;
    
    @DBRef
    private List<Jugador> jugadores;
    
    @DBRef
    private Asociacion asociacion;
    
    @DBRef
    private List<Competicion> competiciones;

    // Constructors
    public Club() {}

    public Club(String nombre, Entrenador entrenador, List<Jugador> jugadores, Asociacion asociacion, List<Competicion> competiciones) {
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.jugadores = jugadores;
        this.asociacion = asociacion;
        this.competiciones = competiciones;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public List<Competicion> getCompeticiones() {
        return competiciones;
    }

    public void setCompeticiones(List<Competicion> competiciones) {
        this.competiciones = competiciones;
    }
}
