package com.aspromedic.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspromedic.model.Observacion;
import com.aspromedic.response.ObservacionResponseRest;
import com.aspromedic.service.IObservacionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/observacion")
public class ObservacionController {

    @Autowired
    private IObservacionService observacionService;

    @PostMapping("/save")
    public String guardarObservacion(Observacion request, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<ObservacionResponseRest> responseDuplicados = observacionService
                .findByCodigoObservacion(request.getCodigo_observacion());
        if (responseDuplicados.getStatusCode() == HttpStatus.OK && responseDuplicados.getBody() != null) {
            List<Observacion> list = responseDuplicados.getBody().getObservacionResponse().getObservaciones();
            if (list != null && !list.isEmpty()) {
                return "param/observaciones?repetido";
            }
        }

        ResponseEntity<ObservacionResponseRest> response = observacionService.save(request);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/observaciones?exito";
        } else {
            return "param/observaciones?error";
        }
    }

    @PostMapping("/update")
    public String actualizarObservacion(Observacion request, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<ObservacionResponseRest> responseDuplicados = observacionService
                .findByCodigoObservacion(request.getCodigo_observacion());
        if (responseDuplicados.getStatusCode() == HttpStatus.OK && responseDuplicados.getBody() != null) {
            List<Observacion> list = responseDuplicados.getBody().getObservacionResponse().getObservaciones();
            if (!list.isEmpty() && !list.get(0).getId_observacion().equals(request.getId_observacion())) {
                return "param/observaciones?repetido";
            }
        }

        ResponseEntity<ObservacionResponseRest> response = observacionService.update(request.getId_observacion(),
                request);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/observaciones?exitoUpdate";
        } else {
            return "param/observaciones?errorUpdate";
        }
    }

    @PostMapping("/delete")
    public String eliminarObservacion(@RequestParam("id_observacion") Long id, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<ObservacionResponseRest> response = observacionService.delete(id);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/observaciones?exitoDelete";
        } else {
            return "param/observaciones?errorDelete";
        }
    }
}
