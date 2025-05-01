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

import com.aspromedic.model.Escolaridad;
import com.aspromedic.response.EscolaridadResponseRest;
import com.aspromedic.service.IEscolaridadService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/escolaridad")
public class EscolaridadController {

    @Autowired
    private IEscolaridadService escolaridadService;

    @PostMapping("/save")
    public String guardarEscolaridad(Escolaridad request, HttpSession session) throws IOException {

        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<EscolaridadResponseRest> responseDuplicado = escolaridadService
                .findByCodigoEscolaridad(request.getCodigo_escolaridad());
        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody() != null) {
            List<Escolaridad> list = responseDuplicado.getBody().getEscolaridadResponse().getEscolaridades();
            if (list != null && !list.isEmpty()) {
                return "param/escolaridades?repetido";
            }
        }

        ResponseEntity<EscolaridadResponseRest> response = escolaridadService.save(request);

        System.out.println("response: " + response);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/escolaridades?exito";
        } else {
            return "param/escolaridades?error";
        }

    }

    @PostMapping("/update")
    public String actualizarEscolaridad(Escolaridad request, HttpSession session) throws IOException {

        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<EscolaridadResponseRest> responseDuplicado = escolaridadService
                .findByCodigoEscolaridad(request.getCodigo_escolaridad());
        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody() != null) {
            List<Escolaridad> list = responseDuplicado.getBody().getEscolaridadResponse().getEscolaridades();
            if (list != null && !list.isEmpty()) {
                Escolaridad esc = list.get(0);
                if (!esc.getId_escolaridad().equals(request.getId_escolaridad())) {
                    return "param/escolaridades?repetido";
                }
            }
        }

        ResponseEntity<EscolaridadResponseRest> response = escolaridadService.update(request.getId_escolaridad(),
                request);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/escolaridades?exitoUpdate";
        } else {
            return "param/escolaridades?errorUpdate";
        }
    }

    @PostMapping("/delete")
    public String eliminarEscolaridad(@RequestParam("id_escolaridad") Long id, HttpSession session) throws IOException {

        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<EscolaridadResponseRest> response = escolaridadService.deleteById(id);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/escolaridades?exitoDelete";
        } else {
            return "param/escolaridades?errorDelete";
        }
    }

}
