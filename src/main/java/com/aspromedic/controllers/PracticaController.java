package com.aspromedic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspromedic.model.Practica;
import com.aspromedic.response.PracticaResponseRest;
import com.aspromedic.service.IPracticaService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/practica")
public class PracticaController {

    @Autowired
    private IPracticaService service;

    @PostMapping("/save")
    public String guardarPractica(Practica request, HttpSession session) throws Exception {

        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<PracticaResponseRest> responseDuplicado = service
                .findByCodigoPractica(request.getCodigo_practica());
        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody() != null) {
            List<Practica> list = responseDuplicado.getBody().getPracticaResponse().getPracticas();
            if (list != null && !list.isEmpty()) {
                return "param/practicas?repetido";
            }
        }

        ResponseEntity<PracticaResponseRest> response = service.save(request);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/practicas?exito";
        } else {
            return "param/practicas?error";
        }
    }

    @PostMapping("/update")
    public String actualizarPractica(Practica request, HttpSession session) throws Exception {

        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<PracticaResponseRest> responseDuplicado = service
                .findByCodigoPractica(request.getCodigo_practica());
        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody() != null) {
            List<Practica> list = responseDuplicado.getBody().getPracticaResponse().getPracticas();
            if (list != null && !list.isEmpty()) {
                Practica pra = list.get(0);
                if (!pra.getId_practica().equals(request.getId_practica())) {
                    return "param/practicas?repetido";
                }
            }
        }

        ResponseEntity<PracticaResponseRest> response = service.update(request.getId_practica(), request);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/practicas?exitoUpdate";
        } else {
            return "param/practicas?errorUpdate";
        }

    }

    @PostMapping("/delete")
    public String eliminarPractica(@RequestParam("id_practica") Long idPractica, HttpSession session) throws Exception {

        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<PracticaResponseRest> response = service.delete(idPractica);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/practicas?exitoDelete";
        } else {
            return "param/practicas?errorDelete";
        }
    }
}
