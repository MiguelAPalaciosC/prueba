package com.aspromedic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspromedic.model.Ingeominas;
import com.aspromedic.response.IngeominasResponseRest;
import com.aspromedic.service.IIngeominasService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/geomina")
public class IngeominasController {

    @Autowired
    private IIngeominasService geominaService;

    @PostMapping("/save")
    public String guardarIngeominas(Ingeominas request, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<IngeominasResponseRest> responseDuplicado = geominaService
                .buscarIngeominasPorCodigo(request.getCodigo_geominas());
        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody() != null) {
            List<Ingeominas> list = responseDuplicado.getBody().getIngeominasResponse().getIngeominas();
            if (list != null && !list.isEmpty()) {
                return "param/geominas?repetido";
            }
        }

        ResponseEntity<IngeominasResponseRest> response = geominaService.guardarIngeominas(request);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "param/geominas?exito";
        } else {
            return "param/geominas?error";
        }
    }

    @PostMapping("/update")
    public String actualizarIngeominas(Ingeominas request, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<IngeominasResponseRest> responseDuplicado = geominaService
                .buscarIngeominasPorCodigo(request.getCodigo_geominas());
        if (responseDuplicado.getStatusCode() == HttpStatus.OK && responseDuplicado.getBody() != null) {
            List<Ingeominas> list = responseDuplicado.getBody().getIngeominasResponse().getIngeominas();
            if (list != null && !list.isEmpty()) {
                Ingeominas ing = list.get(0);
                if (!ing.getId_geominas().equals(request.getId_geominas())) {
                    return "param/geominas?repetido";
                }
            }
        }

        ResponseEntity<IngeominasResponseRest> response = geominaService.actualizarIngeominas(request.getId_geominas(),
                request);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/geominas?exitoUpdate";
        } else {
            return "param/geominas?errorUpdate";
        }
    }

    @PostMapping("/delete")
    public String eliminarIngeominas(@RequestParam("id_geominas") Long id_geominas, HttpSession session)
            throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<IngeominasResponseRest> response = geominaService.eliminarIngeominas(id_geominas);
        if (response.getStatusCode() == HttpStatus.OK) {
            return "param/geominas?exitoDelete";
        } else {
            return "param/geominas?errorDelete";
        }
    }

}
