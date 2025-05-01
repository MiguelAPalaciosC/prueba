package com.aspromedic.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aspromedic.model.TipoDosimetro;
import com.aspromedic.response.TipoDosimetroResponseRest;
import com.aspromedic.service.ITipoDosimetroService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/tdosimetro")
public class TipoDosimetroController {

    @Autowired
    private ITipoDosimetroService service;

    @PostMapping("/save")
    public String guardarTipoDosimetro(TipoDosimetro request, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<TipoDosimetroResponseRest> responseDuplicado = service.findByNombre(request.getNombre());

        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody() != null) {
            List<TipoDosimetro> list = responseDuplicado.getBody().getTipoDosimetroResponse().getTipos();
            if (list != null && !list.isEmpty()) {
                return "redirect:/param/tipoDosimetro?repetido";
            }
        }

        ResponseEntity<TipoDosimetroResponseRest> response = service.save(request);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "redirect:/param/tipoDosimetro?exito";
        }
        return "redirect:/param/tipoDosimetro?error";
    }

    @PostMapping("/update")
    public String actualizarTipoDosimetro(TipoDosimetro request, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<TipoDosimetroResponseRest> responseDuplicado = service.findByNombre(request.getNombre());

        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody() != null) {
            List<TipoDosimetro> list = responseDuplicado.getBody().getTipoDosimetroResponse().getTipos();
            if (!list.isEmpty() &&!list.get(0).getId_tipo_dosimetro().equals(request.getId_tipo_dosimetro())) {
                return "redirect:/param/tipoDosimetro?repetido";
            }
        }

        ResponseEntity<TipoDosimetroResponseRest> response = service.update(request.getId_tipo_dosimetro(), request);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "redirect:/param/tipoDosimetro?exitoUpdate";
        }
        return "redirect:/param/tipoDosimetro?errorUpdate";
    }

    @PostMapping("/delete")
    public String eliminarTipoDosimetro(Long id_tipo_dosimetro, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<TipoDosimetroResponseRest> response = service.deleteById(id_tipo_dosimetro);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody()!= null) {
            return "redirect:/param/tipoDosimetro?exitoDelete";
        }
        return "redirect:/param/tipoDosimetro?errorDelete";
    }
}
