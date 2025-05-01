package com.aspromedic.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspromedic.dto.CargoRegistroDTO;
import com.aspromedic.model.Cargo;
import com.aspromedic.response.CargoResponseRest;
import com.aspromedic.service.ICargoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private ICargoService cargoService;

    @PostMapping("/save")
    public String guardarCargo(CargoRegistroDTO cargo, HttpSession session) throws IOException {

        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<CargoResponseRest> repetidoResponse = cargoService.buscarPorCodigoCargo(cargo.getCodigo_cargo());
        if (repetidoResponse.getStatusCode() == HttpStatus.OK && repetidoResponse.getBody() != null) {
            List<Cargo> cargos = repetidoResponse.getBody().getCargoResponse().getCargos();
            if (cargos != null && !cargos.isEmpty()) { // Verifica que la lista no esté vacía
                return "redirect:/param/cargos?repetido"; // Redirige si el ID no coincide
            }
        }

        Cargo cargoEntity = new Cargo(cargo.getCodigo_cargo(), cargo.getDescripcion_cargo());

        ResponseEntity<CargoResponseRest> response = cargoService.crear(cargoEntity);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {

            return "redirect:/param/cargos?exito";
        }

        return "redirect:/param/cargos?error";
    }

    @PostMapping("/update")
    public String actualizarCargo(CargoRegistroDTO cargo, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<CargoResponseRest> repetidoResponse = cargoService.buscarPorCodigoCargo(cargo.getCodigo_cargo());
        if (repetidoResponse.getStatusCode() == HttpStatus.OK && repetidoResponse.getBody() != null) {
            List<Cargo> cargos = repetidoResponse.getBody().getCargoResponse().getCargos();
            if (cargos != null && !cargos.isEmpty()) { // Verifica que la lista no esté vacía
                Cargo response = cargos.get(0);
                if (!response.getId_cargo().equals(cargo.getId_cargo())) {
                    return "redirect:/param/cargos?repetido"; // Redirige si el ID no coincide
                }
            }
        }

        Cargo cargoEntity = new Cargo(cargo.getCodigo_cargo(), cargo.getDescripcion_cargo());

        ResponseEntity<CargoResponseRest> response = cargoService.actualizar(cargoEntity, cargo.getId_cargo());

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {

            return "redirect:/param/cargos?exitoUpdate";
        }

        return "redirect:/param/cargos?errorUpdate";
    }

    @PostMapping("/delete")
    public String eliminarCargo(CargoRegistroDTO cargo, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<CargoResponseRest> response = cargoService.eliminar(cargo.getId_cargo());

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {

            return "redirect:/param/cargos?exitoDelete";
        }

        return "redirect:/param/cargos?errorDelete";
    }
}
