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
@RequestMapping(value = "/entrenadores")
public class ControladorWebEntrenador {

    @Autowired
    private EntrenadorRepositorio entrenadoresRepositorio;

    @GetMapping
    public String entrenadoresListTemplate(Model model) {
        model.addAttribute("listaEntrenador", entrenadoresRepositorio.findAll());
        return "list-entrenador";
    }

    @GetMapping("/new")
    public String entrenadoresNewTemplate(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "form-entrenador";
    }

    @GetMapping("/edit/{id}")
    public String entrenadorEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("entrenador", entrenadoresRepositorio.findById(id) // error
                .orElseThrow(() -> new NotFoundException("Entrenador no encontrado")));
        return "form-entrenador";
    }

    @PostMapping("/save")
    public String entrenadoresSaveProcess(@ModelAttribute("entrenador") Entrenador entrenador) {
        if (entrenador.getId() == null || entrenador.getId().equals("")) {
        	entrenador.setId(UUID.randomUUID().toString());
        }
        entrenadoresRepositorio.save(entrenador);
        return "redirect:/entrenadores";
    }

    @GetMapping("/delete/{id}")
    public String entrenadorDeleteProcess(@PathVariable("id") String id) {
        entrenadoresRepositorio.deleteById(id); //error
        return "redirect:/entrenadores";
    }
}
