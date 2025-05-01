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

import com.aspromedic.model.Departamento;
import com.aspromedic.response.CiudadResponseRest;
import com.aspromedic.response.DepartamentoResponseRest;
import com.aspromedic.response.EmpresaResponseRest;
import com.aspromedic.service.ICiudadService;
import com.aspromedic.service.IDepartamentoService;
import com.aspromedic.service.IEmpresaService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    private IDepartamentoService service;

    @Autowired
    private ICiudadService ciudadService;

    @Autowired
    private IEmpresaService empresaService;

    @PostMapping("/save")
    public String guardarDepartamento(Departamento dep, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<DepartamentoResponseRest> resp = service
                .buscarDepartamentoPorCodigo(dep.getCodigo_min_departamento());
        if (resp.getStatusCode() == HttpStatus.OK && resp.getBody() != null) {
            List<Departamento> departamentos = resp.getBody().getDepartamentoResponse().getDepartamentos();
            if (departamentos != null && !departamentos.isEmpty()) {
                    return "redirect:/param/departamentos?repetido";
            }
        }

        Departamento departamento = new Departamento(dep.getNombre_departamento(), dep.getCodigo_min_departamento());

        System.out.println("NOMBRE Departamento: " + departamento.getNombre_departamento());
        System.out.println("Codigo min Departamento: " + departamento.getCodigo_min_departamento());
        ResponseEntity<DepartamentoResponseRest> response = service.guardarDepartamento(departamento);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "redirect:/param/departamentos?exito";
        }

        return "redirect:/param/departamentos?error";
    }

    @PostMapping("/update")
    public String actualizarDepartamento(Departamento dep, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<DepartamentoResponseRest> resp = service
                .buscarDepartamentoPorCodigo(dep.getCodigo_min_departamento());
        if (resp.getStatusCode() == HttpStatus.OK && resp.getBody() != null) {
            List<Departamento> departamentos = resp.getBody().getDepartamentoResponse().getDepartamentos();
            if (departamentos != null && !departamentos.isEmpty()) {
                Departamento depar = departamentos.get(0);
                if (!depar.getCodigo_departamento().equals(dep.getCodigo_departamento())) {
                    return "redirect:/param/departamentos?repetido";
                }
            }
        }

        Departamento departamento = new Departamento(dep.getCodigo_departamento(), dep.getNombre_departamento(),
                dep.getCodigo_min_departamento());

        ResponseEntity<DepartamentoResponseRest> response = service.actualizarDepartamento(dep.getCodigo_departamento(),
                departamento);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "redirect:/param/departamentos?exitoUpdate";
        }

        return "redirect:/param/departamentos?errorUpdate";
    }

    @PostMapping("/delete")
    public String eliminarDepartamento(@RequestParam("codigo_departamento") Long id) {
        ResponseEntity<CiudadResponseRest> repCiudad = ciudadService.buscarCiudadesPorDepartamento(id);
        if (repCiudad.getStatusCode() == HttpStatus.OK
                && !repCiudad.getBody().getCiudadResponse().getCiudades().isEmpty()) {
            return "redirect:/param/departamentos?relacionado";
        }

        ResponseEntity<EmpresaResponseRest> repEmpresa = empresaService.buscarPorDepartamento(id);
        if (repEmpresa.getStatusCode() == HttpStatus.OK
                && !repEmpresa.getBody().getEmpresaResponse().getEmpresas().isEmpty()) {
            return "redirect:/param/departamentos?relacionado";
        }

        ResponseEntity<DepartamentoResponseRest> response = service.eliminarDepartamento(id);
        if (response.getStatusCode() == HttpStatus.OK) {
            return "redirect:/param/departamentos?exitoDelete";
        }

        return "redirect:/param/departamentos?errorDelete";
    }
}
