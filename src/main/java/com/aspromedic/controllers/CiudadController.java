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

import com.aspromedic.model.Ciudad;
import com.aspromedic.model.Departamento;
import com.aspromedic.response.CiudadResponseRest;
import com.aspromedic.response.DepartamentoResponseRest;
import com.aspromedic.response.EmpresaResponseRest;
import com.aspromedic.service.ICiudadService;
import com.aspromedic.service.IDepartamentoService;
import com.aspromedic.service.IEmpresaService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    private ICiudadService service;

    @Autowired
    private IDepartamentoService departamentoService;

    @Autowired
    private IEmpresaService empresaService;

    @PostMapping("/save")
    public String guardarCiudad(@RequestParam("departamento") Long departamentoId,
            @RequestParam("nombre_ciudad") String nombreCiudad,
            @RequestParam("codigo_min_ciudad") String codigoMinCiudad, HttpSession session) throws Exception {

        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        System.out.println("Ciudad: " + departamentoId);

        ResponseEntity<CiudadResponseRest> reponseRepetidos = service.buscarCiudadPorCodigo(codigoMinCiudad);

        if (reponseRepetidos.getStatusCode() == HttpStatus.OK && reponseRepetidos.getBody() != null) {
            List<Ciudad> ciudades = reponseRepetidos.getBody().getCiudadResponse().getCiudades();
            if (ciudades != null && ciudades.size() > 0) {
                return "param/ciudades?repetido";
            }
        }

        ResponseEntity<DepartamentoResponseRest> lis_departamentos = departamentoService
                .buscarDepartamentoPorId(departamentoId);

        if (lis_departamentos.getStatusCode() == HttpStatus.OK && lis_departamentos.getBody() != null) {
            Departamento departamento = lis_departamentos.getBody().getDepartamentoResponse().getDepartamentos().get(0);
            System.out.println("Departamento: " + departamento.toString());
            Ciudad ciudad = new Ciudad(departamento, nombreCiudad, codigoMinCiudad);
            ResponseEntity<CiudadResponseRest> response = service.guardarCiudad(ciudad);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return "param/ciudades?exito";
            }
        }

        return "param/ciudades?error";
    }

    @PostMapping("/update")
    public String actualizarCiudad(@RequestParam("codigo_ciudad") Long idCiudad,
            @RequestParam("departamento") Long departamentoId,
            @RequestParam("nombre_ciudad") String nombreCiudad,
            @RequestParam("codigo_min_ciudad") String codigoMinCiudad, HttpSession session) throws IOException {

        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<CiudadResponseRest> reponseRepetidos = service.buscarCiudadPorCodigo(codigoMinCiudad);

        if (reponseRepetidos.getStatusCode() == HttpStatus.OK && reponseRepetidos.getBody() != null) {
            List<Ciudad> ciudades = reponseRepetidos.getBody().getCiudadResponse().getCiudades();
            if (ciudades != null && ciudades.size() > 0) {
                Ciudad ciudad = ciudades.get(0);
                if (!ciudad.getId_ciudad().equals(idCiudad)) {
                    return "param/ciudades?repetido";
                }
            }
        }

        ResponseEntity<DepartamentoResponseRest> lis_departamentos = departamentoService
                .buscarDepartamentoPorId(departamentoId);

        if (lis_departamentos.getStatusCode() == HttpStatus.OK && lis_departamentos.getBody() != null) {
            Departamento departamento = lis_departamentos.getBody().getDepartamentoResponse().getDepartamentos().get(0);
            System.out.println("Departamento: " + departamento.toString());
            Ciudad ciudad = new Ciudad(departamento, nombreCiudad, codigoMinCiudad);
            ResponseEntity<CiudadResponseRest> response = service.actualizarCiudad(idCiudad, ciudad);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return "param/ciudades?exitoUpdate";
            }
        }

        return "param/ciudades?errorUpdate";
    }

    @PostMapping("/delete")
    public String eliminarCiudad(@RequestParam("codigo_ciudad") Long idCiudad) {

        ResponseEntity<EmpresaResponseRest> responseEmpresa = empresaService.buscarPorCiudad(idCiudad);

        if (responseEmpresa.getStatusCode() == HttpStatus.OK && responseEmpresa.getBody() != null
                && responseEmpresa.getBody().getEmpresaResponse().getEmpresas().size() > 0) {
            return "param/ciudades?relacionado";
        }

        ResponseEntity<CiudadResponseRest> response = service.eliminarCiudad(idCiudad);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "param/ciudades?exitoDelete";
        }

        return "param/ciudades?errorDelete";
    }

}
