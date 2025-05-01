package com.aspromedic.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aspromedic.model.Cargo;
import com.aspromedic.model.Ciudad;
import com.aspromedic.model.Departamento;
import com.aspromedic.model.Escolaridad;
import com.aspromedic.model.Ingeominas;
import com.aspromedic.model.Observacion;
import com.aspromedic.model.Practica;
import com.aspromedic.model.Radiacion;
import com.aspromedic.model.TipoDosimetro;
import com.aspromedic.model.Titulo;
import com.aspromedic.model.Ubicacion;

import com.aspromedic.response.CargoResponseRest;
import com.aspromedic.response.CiudadResponseRest;
import com.aspromedic.response.DepartamentoResponseRest;
import com.aspromedic.response.EscolaridadResponseRest;
import com.aspromedic.response.IngeominasResponseRest;
import com.aspromedic.response.ObservacionResponseRest;
import com.aspromedic.response.PracticaResponseRest;
import com.aspromedic.response.RadiacionResponseRest;
import com.aspromedic.response.TipoDosimetroResponseRest;
import com.aspromedic.response.TituloResponseRest;
import com.aspromedic.response.UbicacionResponseRest;
import com.aspromedic.service.ICargoService;
import com.aspromedic.service.ICiudadService;
import com.aspromedic.service.IDepartamentoService;
import com.aspromedic.service.IEscolaridadService;
import com.aspromedic.service.IIngeominasService;
import com.aspromedic.service.IObservacionService;
import com.aspromedic.service.IPracticaService;
import com.aspromedic.service.IRadiacionService;
import com.aspromedic.service.ITipoDosimetroService;
import com.aspromedic.service.ITituloService;
import com.aspromedic.service.IUbicacionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/param")
public class ParametrizacionController {

    @Autowired
    private ICargoService cargoService;

    @Autowired
    private ICiudadService ciudadService;

    @Autowired
    private IDepartamentoService departamentoService;

    @Autowired
    private IIngeominasService geominasService;

    @Autowired
    private IObservacionService observacionService;

    @Autowired
    private IPracticaService practicaService;

    @Autowired
    private IRadiacionService radiacionService;

    @Autowired
    private ITituloService tituloService;

    @Autowired
    private IUbicacionService ubicacionService;

    @Autowired
    private IEscolaridadService escolaridadService;

    @Autowired
    private ITipoDosimetroService tipoDosimetroService;

    @GetMapping("/cargos")
    public String verCargos(Model model, HttpSession session) {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<CargoResponseRest> response = cargoService.buscarCargos();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
                && response.getBody().getCargoResponse() != null) {

            List<Cargo> cargos = response.getBody().getCargoResponse().getCargos();

            model.addAttribute("cargos", cargos);

            return "/admin/cargo";
        }

        return "error";
    }

    @GetMapping("/ciudades")
    public String verListaCiudades(Model model, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<CiudadResponseRest> response = ciudadService.listarCiudades();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
                && response.getBody().getCiudadResponse() != null) {
            List<Ciudad> list = response.getBody().getCiudadResponse().getCiudades();

            model.addAttribute("ciudades", list);

            ResponseEntity<DepartamentoResponseRest> responseDepart = departamentoService.buscarDepartamento();

            if (responseDepart.getStatusCode() == HttpStatus.OK && responseDepart.getBody() != null) {
                List<Departamento> departamentos = responseDepart.getBody().getDepartamentoResponse()
                        .getDepartamentos();

                model.addAttribute("departamentos", departamentos);
            } else {
                model.addAttribute("departamentos", Collections.emptyList());
            }

            return "/admin/ciudad";

        }

        return "error";
    }

    @GetMapping("/departamentos")
    public String verListadoDepartamentos(Model model, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<DepartamentoResponseRest> response = departamentoService.buscarDepartamento();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
                && response.getBody().getDepartamentoResponse() != null) {

            List<Departamento> departamentos = response.getBody().getDepartamentoResponse().getDepartamentos();

            model.addAttribute("departamentos", departamentos);
        } else {
            model.addAttribute("departamentos", Collections.emptyList());
        }

        return "/admin/departamento";

    }

    @GetMapping("/geominas")
    public String verGeomasinas(Model model, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<IngeominasResponseRest> response = geominasService.buscarIngeominas();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
                && response.getBody().getIngeominasResponse() != null) {

            List<Ingeominas> geominas = response.getBody().getIngeominasResponse().getIngeominas();

            model.addAttribute("geominas", geominas);

        } else {
            model.addAttribute("geomina", Collections.emptyList());
        }

        return "/admin/ingeominas";
    }

    @GetMapping("/observaciones")
    public String verObservaciones(Model model, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<ObservacionResponseRest> response = observacionService.findAll();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
                && response.getBody().getObservacionResponse() != null) {

            List<Observacion> observaciones = response.getBody().getObservacionResponse().getObservaciones();

            model.addAttribute("observaciones", observaciones);
        } else {
            model.addAttribute("observaciones", Collections.emptyList());
        }
        return "/admin/observacion";
    }

    @GetMapping("/practicas")
    public String verPracticas(Model model, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<PracticaResponseRest> response = practicaService.findAll();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
                && response.getBody().getPracticaResponse() != null) {

            List<Practica> practicas = response.getBody().getPracticaResponse().getPracticas();

            model.addAttribute("practicas", practicas);
        } else {
            model.addAttribute("practicas", Collections.emptyList());
        }
        return "/admin/practica";
    }

    @GetMapping("/radiaciones")
    public String verRadiaciones(Model model, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<RadiacionResponseRest> response = radiacionService.findAll();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
                && response.getBody().getRadiacionResponse() != null) {

            List<Radiacion> radiaciones = response.getBody().getRadiacionResponse().getRadiaciones();

            model.addAttribute("radiaciones", radiaciones);
        } else {
            model.addAttribute("radiaciones", Collections.emptyList());
        }
        return "/admin/radiacion";
    }

    @GetMapping("/titulos")
    public String verTitulos(Model model, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<TituloResponseRest> response = tituloService.findAll();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
                && response.getBody().getTituloResponse() != null) {

            List<Titulo> titulos = response.getBody().getTituloResponse().getTitulos();

            model.addAttribute("titulos", titulos);
        } else {
            model.addAttribute("titulos", Collections.emptyList());
        }
        return "/admin/titulo";
    }

    @GetMapping("/ubicaciones")
    public String verUbicaciones(Model model, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<UbicacionResponseRest> response = ubicacionService.findAll();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
                && response.getBody().getUbicacionResponse() != null) {

            List<Ubicacion> ubicaciones = response.getBody().getUbicacionResponse().getUbicaciones();

            model.addAttribute("ubicaciones", ubicaciones);
        } else {
            model.addAttribute("ubicaciones", Collections.emptyList());
        }

        return "/admin/ubicacion";
    }

    @GetMapping("/escolaridades")
    public String verEscolaridades(Model model, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<EscolaridadResponseRest> response = escolaridadService.findAll();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
                && response.getBody().getEscolaridadResponse() != null) {

            List<Escolaridad> escolaridades = response.getBody().getEscolaridadResponse().getEscolaridades();

            model.addAttribute("escolaridades", escolaridades);
        } else {
            model.addAttribute("escolaridades", Collections.emptyList());
        }
        return "/admin/escolaridad";
    }

    @GetMapping("tipoDosimetro")
    public String verTipoDosimetro(Model model, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<TipoDosimetroResponseRest> response = tipoDosimetroService.findAll();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody()!= null
                && response.getBody().getTipoDosimetroResponse()!= null) {
                    List<TipoDosimetro> tipoDosimetro = response.getBody().getTipoDosimetroResponse().getTipos();

                    model.addAttribute("tipoDosimetro", tipoDosimetro);
        } else {
            model.addAttribute("tipoDosimetro", Collections.emptyList());
        }

        return "/admin/tipoDosimetro";
    }

}
