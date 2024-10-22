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
@RequestMapping(value = "/competiciones")
public class ControladorWebCompeticion {

    @Autowired
    private CompeticionRepositorio competicionRepositorio;

    @GetMapping
    public String competicionesListTemplate(Model model) {
        model.addAttribute("listaCompeticion", competicionRepositorio.findAll());
        return "list-competicion";
    }

    @GetMapping("/new")
    public String competicionesNewTemplate(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "form-competicion";
    }

    @GetMapping("/edit/{id}")
    public String competicionEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("competicion", competicionRepositorio.findById(id)
            .orElseThrow(() -> new NotFoundException("Competición no encontrada")));
        return "form-competicion";
    }

    @PostMapping("/save")
    public String competicionesSaveProcess(@ModelAttribute("competicion") Competicion competicion) {
    	if (competicion.getId() == null || competicion.getId().equals("")) {
    	    competicion.setId(UUID.randomUUID().toString());
    	}
        competicionRepositorio.save(competicion);
        return "redirect:/competiciones";
    }

    @GetMapping("/delete/{id}")
    public String competicionDeleteProcess(@PathVariable("id") String id) {
        competicionRepositorio.deleteById(id);
        return "redirect:/competiciones";
    }
}