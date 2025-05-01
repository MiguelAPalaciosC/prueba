package com.aspromedic.controllers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspromedic.dto.CiudadRegistroDTO;
import com.aspromedic.model.Ciudad;
import com.aspromedic.model.Departamento;
import com.aspromedic.response.CiudadResponseRest;
import com.aspromedic.response.DepartamentoResponseRest;
import com.aspromedic.service.CiudadServiceImpl;
import com.aspromedic.service.ICiudadService;
import com.aspromedic.service.IDepartamentoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/registro/empresa")
public class LocationController {

    private Logger log = LoggerFactory.getLogger(CiudadServiceImpl.class);

    @Autowired
    private IDepartamentoService departamentoService; // Inyectamos el servicio

    @Autowired
    private ICiudadService ciudadDao;

    @GetMapping("/")
    public String irAListaDeEmpresas(HttpSession session) {

        // Check if the user is authenticated
        Object userIdAttribute = session.getAttribute("user_session_id");
        if (userIdAttribute == null) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }

        // Return the view name without a leading slash
        return "admin/registro"; // Corrected view name
    }

    @GetMapping
    public String mostrarDepartamentos(Model model, HttpSession session, HttpServletRequest request) {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        // Add the CSRF token to the model
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);

        ResponseEntity<DepartamentoResponseRest> response = departamentoService.buscarDepartamento();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            List<Departamento> departamentos = response.getBody().getDepartamentoResponse().getDepartamentos();
            System.out.println("Departamentos cargados: " + departamentos); // Imprime en consola
            model.addAttribute("departamentos", departamentos);
        } else {
            model.addAttribute("departamentos", Collections.emptyList());
        }

        return "admin/registro";
    }

    @GetMapping("/departamento")
    public ResponseEntity<?> obtenerDepartamentos(@RequestParam Long id_departamento) {
        ResponseEntity<DepartamentoResponseRest> response = departamentoService.buscarDepartamentoPorId(id_departamento);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            Departamento departamentos = response.getBody().getDepartamentoResponse().getDepartamentos().get(0);
            System.out.println("Departamentos cargados: " + departamentos); // Imprime en consola
            return ResponseEntity.ok(departamentos);
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/ciudades")
    public ResponseEntity<List<CiudadRegistroDTO>> obtenerCiudades(@RequestParam Long codigo_departamento) {
        ResponseEntity<CiudadResponseRest> response = ciudadDao.buscarCiudadesPorDepartamento(codigo_departamento);
        System.out.println("Response: " + response); // Corrige la impresión

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            List<Ciudad> ciudades = response.getBody().getCiudadResponse().getCiudades();

            // Inicializa la relación para cada ciudad
            for (Ciudad ciudad : ciudades) {
                Hibernate.initialize(ciudad.getDepartamento()); // Inicializa la relación
            }

            // Convertir a DTOs
            List<CiudadRegistroDTO> ciudadDTOs = ciudades.stream().map(ciudad -> {
                CiudadRegistroDTO dto = new CiudadRegistroDTO();
                dto.setId(ciudad.getId_ciudad());
                dto.setCodigoCiudad(ciudad.getCodigo_ciudad());
                dto.setNombreCiudad(ciudad.getNombre_ciudad());
                dto.setCodigo_ministerio(ciudad.getCodigo_min_ciudad());
                dto.setNombreDepartamento(ciudad.getDepartamento().getNombre_departamento()); // Asegúrate de que el nombre del
                                                                                 // departamento esté disponible
                return dto;
            }).collect(Collectors.toList());

            System.out.println("Ciudades cargados: " + ciudadDTOs); // Imprime en consola
            return ResponseEntity.ok(ciudadDTOs);
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}
