package com.ejemplo1.jimenez.app.controladorweb;

import com.ejemplo1.jimenez.app.variables.*;
import com.ejemplo1.jimenez.app.repository.*;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ejemplo1.jimenez.app.tools.*;


@Controller
@RequestMapping(value = "/jugadores")
public class ControladorWebJugador {

    @Autowired
    private JugadorRepositorio jugadorRepositorio;

    @GetMapping
    public String jugadoresListTemplate(Model model) {
        model.addAttribute("listaJugador", jugadorRepositorio.findAll());
        return "list-jugador";
    }

    @GetMapping("/new")
    public String jugadoresNewTemplate(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "form-jugador";
    }

    @GetMapping("/edit/{id}")
    public String jugadorEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("jugador", jugadorRepositorio.findById(id)
            .orElseThrow(() -> new NotFoundException("Jugador no encontrado")));
        return "form-jugador";
    }

    @PostMapping("/save")
    public String jugadoresSaveProcess(@ModelAttribute("jugador") Jugador jugador) {

        if (jugador.getId() == null || jugador.getId().equals("")) {
        	   jugador.setId(UUID.randomUUID().toString());
        }

        jugadorRepositorio.save(jugador);
        return "redirect:/jugadores";
    }

    @GetMapping("/delete/{id}")
    public String jugadorDeleteProcess(@PathVariable("id") String id) {
        jugadorRepositorio.deleteById(id);
        return "redirect:/jugadores";
    }
}