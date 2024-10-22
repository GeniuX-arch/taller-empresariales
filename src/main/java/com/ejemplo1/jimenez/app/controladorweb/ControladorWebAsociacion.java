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
@RequestMapping(value = "/asociaciones")
public class ControladorWebAsociacion {

    @Autowired
    private AsociacionRepositorio asociacionRepositorio;

    @GetMapping
    public String asociacionesListTemplate(Model model) {
        model.addAttribute("listaAsociacion", asociacionRepositorio.findAll());
        return "list-asociacion";
    }

    @GetMapping("/new")
    public String asociacionesNewTemplate(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "form-asociacion";
    }

    @GetMapping("/edit/{id}")
    public String asociacionEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("asociacion", asociacionRepositorio.findById(id)
            .orElseThrow(() -> new NotFoundException("Asociación no encontrada")));
        return "form-asociacion";
    }

    @PostMapping("/save")
    public String asociacionesSaveProcess(@ModelAttribute("asociacion") Asociacion asociacion) {
    	if (asociacion.getId() == null || asociacion.getId().equals("")) {
    	    asociacion.setId(UUID.randomUUID().toString());
    	}
        asociacionRepositorio.save(asociacion);
        return "redirect:/asociaciones";
    }

    @GetMapping("/delete/{id}")
    public String asociacionDeleteProcess(@PathVariable("id") String id) {
        asociacionRepositorio.deleteById(id);
        return "redirect:/asociaciones";
    }
}