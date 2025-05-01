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

import com.aspromedic.model.Radiacion;
import com.aspromedic.response.RadiacionResponseRest;
import com.aspromedic.service.IRadiacionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/radiacion")
public class RadiacionController {

    @Autowired
    private IRadiacionService service;

    @PostMapping("/save")
    public String guardarRadiacion(Radiacion request, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<RadiacionResponseRest> responseDuplicado = service.findByCodigo(request.getCodigo_radiacion());
        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody() != null) {
            List<Radiacion> list = responseDuplicado.getBody().getRadiacionResponse().getRadiaciones();
            if (list != null && !list.isEmpty()) {
                return "param/radiaciones?repetido";
            }
        }

        ResponseEntity<RadiacionResponseRest> response = service.save(request);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/radiaciones?exito";
        } else {
            return "param/radiaciones?error";
        }
    }

    @PostMapping("/update")
    public String actualizarRadiacion(Radiacion request, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<RadiacionResponseRest> responseDuplicado = service.findByCodigo(request.getCodigo_radiacion());
        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody() != null) {
            List<Radiacion> list = responseDuplicado.getBody().getRadiacionResponse().getRadiaciones();
            if (list != null && !list.isEmpty()) {
                Radiacion rad = list.get(0);
                if (!rad.getId_radiacion().equals(request.getId_radiacion())) {
                    return "param/radiaciones?repetido";
                }
            }
        }

        ResponseEntity<RadiacionResponseRest> response = service.update(request.getId_radiacion(), request);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/radiaciones?exitoUpdate";
        } else {
            return "param/radiaciones?errorUpdate";
        }
    }

    @PostMapping("/delete")
    public String eliminarRadiacion(@RequestParam("id_radiacion") Long idRadiacion, HttpSession session)
            throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<RadiacionResponseRest> response = service.delete(idRadiacion);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/radiaciones?exitoDelete";
        } else {
            return "param/radiaciones?errorDelete";
        }

    }
}
