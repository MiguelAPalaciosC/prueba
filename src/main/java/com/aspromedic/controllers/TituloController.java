package com.aspromedic.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspromedic.model.Titulo;
import com.aspromedic.response.ResponseRest;
import com.aspromedic.response.TituloResponseRest;
import com.aspromedic.response.TrabajadorResponseRest;
import com.aspromedic.service.ITituloService;
import com.aspromedic.service.ITrabajadorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/titulo")
public class TituloController {

    @Autowired
    private ITituloService tituloService;

    @Autowired
    private ITrabajadorService trabajadorService;

    @PostMapping("/save")
    public String saveTitulo(Titulo request, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }
        System.out.println("Titulo: " + request.toString());

        ResponseEntity<TituloResponseRest> responseDuplicado = tituloService
                .findByCodigoTitulo(request.getCodigo_titulo());

        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody() != null) {
            List<Titulo> list = responseDuplicado.getBody().getTituloResponse().getTitulos();
            if (list != null && !list.isEmpty()) {
                return "redirect:/param/titulos?repetido";
            }
        }

        ResponseEntity<TituloResponseRest> response = tituloService
                .save(new Titulo(request.getCodigo_titulo(), request.getDescripcion_titulo()));

        System.out.println("response:   " + response);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "redirect:/param/titulos?exito";
        } else {
            return "redirect:/param/titulos?error";
        }
    }

    @PostMapping("/update")
    public String updateTitulo(Titulo request, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<TituloResponseRest> responseDuplicado = tituloService
                .findByCodigoTitulo(request.getCodigo_titulo());

        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody() != null) {
            List<Titulo> list = responseDuplicado.getBody().getTituloResponse().getTitulos();
            if (!list.isEmpty() && list != null) {
                Titulo titulo = list.get(0);
                if (!request.getId_titulo().equals(titulo.getId_titulo())) {
                    return "redirect:/param/titulos?repetido";
                }
            }
        }

        ResponseEntity<TituloResponseRest> response = tituloService.update(request.getId_titulo(), request);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "redirect:/param/titulos?exitoUpdate";
        } else {
            return "redirect:/param/titulos?errorUpdate";
        }

    }

    @PostMapping("/delete")
    public String eliminarTitulo(@RequestParam("id_titulo") Long id_titulo, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");
        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<TrabajadorResponseRest> responseTrabajador = trabajadorService.findByTitulo(id_titulo);
        if (responseTrabajador.getStatusCode() == HttpStatus.OK && responseTrabajador.getBody()!= null
                &&!responseTrabajador.getBody().getTrabajadorResponse().getTrabajadores().isEmpty()) {
            return "redirect:/param/titulos?relacionado";
        }

        ResponseEntity<TituloResponseRest> response = tituloService.deleteById(id_titulo);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "redirect:/param/titulos?exitoDelete";
        } else {
            return "redirect:/param/titulos?errorDelete";
        }
    }
}
