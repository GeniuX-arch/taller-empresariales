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
@RequestMapping(value = "/clubes")
public class ControladorWebClub {

    @Autowired
    private ClubRepositorio clubRepositorio;

    @Autowired
    private EntrenadorRepositorio entrenadorRepositorio;

    @Autowired
    private AsociacionRepositorio asociacionRepositorio;

    @Autowired
    private CompeticionRepositorio competicionRepositorio;

    @Autowired
    private JugadorRepositorio jugadorRepositorio;
    
  
    @GetMapping
    public String clubesListTemplate(Model model) {
        model.addAttribute("listaClub", clubRepositorio.findAll());
        return "list-club";
    }

    @GetMapping("/new")
    public String clubesNewTemplate(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("listaEntrenador", entrenadorRepositorio.findAll());
        model.addAttribute("listaAsociacion", asociacionRepositorio.findAll());
        model.addAttribute("listaCompeticion", competicionRepositorio.findAll());
        model.addAttribute("listaJugador", jugadorRepositorio.findAll());
        return "form-club";
    }

    @GetMapping("/edit/{id}")
    public String clubEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("club", clubRepositorio.findById(id)
            .orElseThrow(() -> new NotFoundException("Club no encontrado")));
        model.addAttribute("listaEntrenador", entrenadorRepositorio.findAll());
        model.addAttribute("listaAsociacion", asociacionRepositorio.findAll());
        model.addAttribute("listaCompeticion", competicionRepositorio.findAll());
        model.addAttribute("listaJugador", jugadorRepositorio.findAll());
        return "form-club";
    }

    @PostMapping("/save")
    public String clubesSaveProcess(@ModelAttribute("club") Club club) {
    	if (club.getId() == null || club.getId().equals("")) {
    	    club.setId(UUID.randomUUID().toString());
    	}
        clubRepositorio.save(club);
        return "redirect:/clubes";
    }

    @GetMapping("/delete/{id}")
    public String clubDeleteProcess(@PathVariable("id") String id) {
        clubRepositorio.deleteById(id);
        return "redirect:/clubes";
    }
}