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

import com.aspromedic.model.Ubicacion;
import com.aspromedic.response.UbicacionResponseRest;
import com.aspromedic.service.IUbicacionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ubicacion")
public class UbicacionController {

    @Autowired
    private IUbicacionService service;

    @PostMapping("/save")
    public String guardarUbicacion(Ubicacion request, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<UbicacionResponseRest> responseDuplicado = service.findByCodigoUbicacion(request.getCodigo_ubicacion());

        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody()!= null) {
            List<Ubicacion> list = responseDuplicado.getBody().getUbicacionResponse().getUbicaciones();
            if (list!= null &&!list.isEmpty()) {
                return "param/ubicaciones?repetido";
            }
        }

        ResponseEntity<UbicacionResponseRest> response = service.save(request);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/ubicaciones?exito";
        } else {
            return "param/ubicaciones?error";
        }
    }

    @PostMapping("/update")
    public String actualizarUbicacion(Ubicacion request, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");
        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<UbicacionResponseRest> responseDuplicado = service.findByCodigoUbicacion(request.getCodigo_ubicacion());

        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody()!= null) {
            List<Ubicacion> list = responseDuplicado.getBody().getUbicacionResponse().getUbicaciones();
            if (list!= null &&!list.isEmpty()) {
                Ubicacion ubi = list.get(0);
                if (!ubi.getId_ubicacion().equals(request.getId_ubicacion())) {
                    return "param/ubicaciones?repetido";
                }
            }
        }

        ResponseEntity<UbicacionResponseRest> response = service.update(request.getId_ubicacion(), request);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/ubicaciones?exitoUpdate";
        } else {
            return "param/ubicaciones?errorUpdate";
        }
    }

    @PostMapping("/delete")
    public String eliminarUbicacion(@RequestParam("id_ubicacion") Long id, HttpSession session) throws IOException {

        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<UbicacionResponseRest> response = service.delete(id);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/ubicaciones?exitoDelete";
        } else {
            return "param/ubicaciones?errorDelete";
        }
    }

}
