package com.aspromedic.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspromedic.dto.EmpresaRegistroDTO;
import com.aspromedic.dto.TrabajadorRegistroDTO;
import com.aspromedic.model.Dosimetro;
import com.aspromedic.model.Empresa;
import com.aspromedic.model.Escolaridad;
import com.aspromedic.model.Titulo;
import com.aspromedic.model.Trabajador;
import com.aspromedic.response.DosimetroResponseRest;
import com.aspromedic.response.EmpresaResponseRest;
import com.aspromedic.response.EscolaridadResponseRest;
import com.aspromedic.response.TituloResponseRest;
import com.aspromedic.response.TrabajadorResponseRest;
import com.aspromedic.service.IDosimetroService;
import com.aspromedic.service.IEscolaridadService;
import com.aspromedic.service.ITituloService;
import com.aspromedic.service.ITrabajadorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/gestion2")
public class TrabajadorController {

    @Autowired
    private ITrabajadorService trabajadorService;

    @Autowired
    private ITituloService tituloService;

    @Autowired
    private IEscolaridadService escolaridadService;

    @Autowired
    private IDosimetroService dosimetroService;

    @GetMapping("/trabajador")
    public String irANuevoTrabajador(HttpSession session, Model model) throws Exception {

        // Check if the user is authenticated
        Object userIdAttribute = session.getAttribute("user_session_id");
        if (userIdAttribute == null) {
            return "login"; // Redirect to login if not authenticated
        }

        model.addAttribute("trabajador", new Trabajador());
        model.addAttribute("fecha_nacimiento", "");
        model.addAttribute("fecha_inicio", "");

        ResponseEntity<TituloResponseRest> responseTitulo = tituloService.findAll();

        if (responseTitulo.getStatusCode() == HttpStatus.OK && responseTitulo.getBody() != null
                && responseTitulo.getBody().getTituloResponse() != null) {

            List<Titulo> titulos = responseTitulo.getBody().getTituloResponse().getTitulos();

            model.addAttribute("titulos", titulos);
        } else {
            model.addAttribute("titulos", List.of());
        }

        ResponseEntity<EscolaridadResponseRest> responseEscolaridad = escolaridadService.findAll();

        if (responseEscolaridad.getStatusCode() == HttpStatus.OK && responseEscolaridad.getBody() != null
                && responseEscolaridad.getBody().getEscolaridadResponse() != null) {
            List<Escolaridad> escolaridades = responseEscolaridad.getBody().getEscolaridadResponse().getEscolaridades();

            model.addAttribute("escolaridades", escolaridades);
        } else {
            model.addAttribute("escolaridades", List.of());
        }

        // Return the view name without a leading slash
        return "admin/registroTrabajador"; // Corrected view name
    }

    @GetMapping("/trabajador/{id}")
    public String irEdicionTrabajador(@PathVariable Long id, HttpSession session, Model model) throws Exception {

        // Check if the user is authenticated
        Object userIdAttribute = session.getAttribute("user_session_id");
        if (userIdAttribute == null) {
            return "login"; // Redirect to login if not authenticated
        }

        ResponseEntity<TrabajadorResponseRest> responseTrabajador = trabajadorService.findById(id);
        if (responseTrabajador.getStatusCode() == HttpStatus.OK && responseTrabajador.getBody() != null
                && responseTrabajador.getBody().getTrabajadorResponse() != null) {
            Trabajador trabajador = responseTrabajador.getBody().getTrabajadorResponse().getTrabajadores().get(0);

            model.addAttribute("trabajador", trabajador);

            if (trabajador.getFecha_inicio_trabajador() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fechaVin = trabajador.getFecha_inicio_trabajador().format(formatter); // Formatear a String
                model.addAttribute("fecha_inicio", fechaVin); // Pasar el String al modelo
            } else {
                model.addAttribute("fecha_inicio", "");
            }

            if (trabajador.getFecha_nacimiento_trabajador() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fechaVin = trabajador.getFecha_nacimiento_trabajador().format(formatter); // Formatear a String
                model.addAttribute("fecha_nacimiento", fechaVin); // Pasar el String al modelo
            } else {
                model.addAttribute("fecha_nacimiento", "");
            }

            ResponseEntity<TituloResponseRest> responseTitulo = tituloService.findAll();

            if (responseTitulo.getStatusCode() == HttpStatus.OK && responseTitulo.getBody() != null
                    && responseTitulo.getBody().getTituloResponse() != null) {

                List<Titulo> titulos = responseTitulo.getBody().getTituloResponse().getTitulos();

                model.addAttribute("titulos", titulos);
            } else {
                model.addAttribute("titulos", List.of());
            }

            ResponseEntity<EscolaridadResponseRest> responseEscolaridad = escolaridadService.findAll();

            if (responseEscolaridad.getStatusCode() == HttpStatus.OK && responseEscolaridad.getBody() != null
                    && responseEscolaridad.getBody().getEscolaridadResponse() != null) {
                List<Escolaridad> escolaridades = responseEscolaridad.getBody().getEscolaridadResponse()
                        .getEscolaridades();

                model.addAttribute("escolaridades", escolaridades);
            } else {
                model.addAttribute("escolaridades", List.of());
            }
        }

        return "admin/registroTrabajador"; // Corrected view name
    }

    @GetMapping("/trabajadores")
    public String mostrarTrabajadores(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size, Model model, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        Pageable pageable = PageRequest.of(page, size);
        ResponseEntity<TrabajadorResponseRest> response = trabajadorService.findAll(pageable);

        if (response.getStatusCode() == HttpStatus.OK) {

            List<Trabajador> trabajadores = response.getBody().getTrabajadorResponse().getTrabajadores();
            TrabajadorRegistroDTO converter = new TrabajadorRegistroDTO();
            List<TrabajadorRegistroDTO> list = converter.convertToDTO(trabajadores);

            model.addAttribute("trabajadores", list);

            // Datos de paginación
            long totalElements = response.getBody().getTrabajadorResponse().getTotalElements();
            int totalPages = response.getBody().getTrabajadorResponse().getTotalPages();

            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("totalElements", totalElements);
            model.addAttribute("pageSize", size);
        } else {
            model.addAttribute("trabajadores", List.of());

            model.addAttribute("currentPage", 0);
            model.addAttribute("totalPages", 0);
            model.addAttribute("totalElements", 0);
            model.addAttribute("pageSize", 0);
        }

        return "admin/trabajadores";
    }

    @PostMapping("/save")
    public String guardarTrabajador(@ModelAttribute TrabajadorRegistroDTO request, HttpSession session)
            throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        System.out.println("Trabajador: " + request.toString());

        ResponseEntity<TituloResponseRest> responseTitulo = tituloService.findById(request.getId_titulo());

        if (responseTitulo.getStatusCode() == HttpStatus.OK && responseTitulo.getBody() != null
                && responseTitulo.getBody().getTituloResponse() != null) {
            Titulo titulo = responseTitulo.getBody().getTituloResponse().getTitulos().get(0);

            request.setTitulo_trabajador(titulo.getDescripcion_titulo());

            ResponseEntity<EscolaridadResponseRest> responseEscolaridad = escolaridadService
                    .findById(Long.parseLong(request.getEscolaridad_trabajador()));

            System.out.println("response Escolaridad: " + responseEscolaridad);

            if (responseEscolaridad.getStatusCode() == HttpStatus.OK && responseEscolaridad.getBody() != null
                    && responseEscolaridad.getBody().getEscolaridadResponse() != null) {

                Escolaridad escolaridad = responseEscolaridad.getBody().getEscolaridadResponse()
                        .getEscolaridades().get(0);

                request.setEscolaridad_trabajador(escolaridad.getNombre_escolaridad());

                if (request.getFecha_nacimiento_trabajador() == null) {
                    request.setFecha_nacimiento_trabajador(LocalDate.of(1900, 1, 1)); // 1 de enero de 1900
                }

                Trabajador trabajador = new Trabajador(request);

                System.out.println("Trabajador guardar: " + trabajador);
                ResponseEntity<TrabajadorResponseRest> response = trabajadorService.save(trabajador);

                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    return "gestion2/trabajadores?exito";
                }
            }
        }
        return "gestion2/trabajadores?error";
    }

    @PostMapping("/update/{id}")
    public String actualizarTrabajador(@PathVariable Long id, @ModelAttribute TrabajadorRegistroDTO request,
            HttpSession session)
            throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        System.out.println("Trabajador: " + request.toString());

        ResponseEntity<TituloResponseRest> responseTitulo = tituloService.findById(request.getId_titulo());

        if (responseTitulo.getStatusCode() == HttpStatus.OK && responseTitulo.getBody() != null
                && responseTitulo.getBody().getTituloResponse() != null) {
            Titulo titulo = responseTitulo.getBody().getTituloResponse().getTitulos().get(0);

            request.setTitulo_trabajador(titulo.getDescripcion_titulo());

            ResponseEntity<EscolaridadResponseRest> responseEscolaridad = escolaridadService
                    .findById(Long.parseLong(request.getEscolaridad_trabajador()));

            System.out.println("response Escolaridad: " + responseEscolaridad);

            if (responseEscolaridad.getStatusCode() == HttpStatus.OK && responseEscolaridad.getBody() != null
                    && responseEscolaridad.getBody().getEscolaridadResponse() != null) {

                Escolaridad escolaridad = responseEscolaridad.getBody().getEscolaridadResponse()
                        .getEscolaridades().get(0);

                request.setEscolaridad_trabajador(escolaridad.getNombre_escolaridad());

                if (request.getFecha_nacimiento_trabajador() == null) {
                    request.setFecha_nacimiento_trabajador(LocalDate.of(1900, 1, 1)); // 1 de enero de 1900
                }

                Trabajador trabajador = new Trabajador(request);

                System.out.println("Trabajador guardar: " + trabajador);
                ResponseEntity<TrabajadorResponseRest> response = trabajadorService.update(id, trabajador);

                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    return "gestion2/trabajadores?exitoUpdate";
                }
            }
        }
        return "gestion2/trabajadores?errorUpdate";
    }

    @PostMapping("/delete")
    public String eliminarTrabajador(@RequestParam("id_trabajador") Long id_trabajador, HttpSession session)
            throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<DosimetroResponseRest> responseDosimetro = dosimetroService.findByTrabajador(id_trabajador);

        if (responseDosimetro.getStatusCode() == HttpStatus.OK && responseDosimetro.getBody() != null
                && responseDosimetro.getBody().getDosimetroResponse() != null) {
            List<Dosimetro> dosimetros = responseDosimetro.getBody().getDosimetroResponse()
                    .getDosimetros();

            if (dosimetros != null && !dosimetros.isEmpty()) {
                return "gestion2/trabajadores?relacionado";
            }
        }

        ResponseEntity<TrabajadorResponseRest> response = trabajadorService.deleteById(id_trabajador);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "gestion2/trabajadores?exito";
        } else {
            return "gestion2/trabajadores?error";
        }
    }

    @GetMapping("/cedula")
    public ResponseEntity<?> buscarPorCedula(@RequestParam Long cedula_trabajador) {
        ResponseEntity<TrabajadorResponseRest> response = trabajadorService.findByCedula(cedula_trabajador);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return ResponseEntity.ok(response.getBody().getTrabajadorResponse().getTrabajadores());
        }

        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/filtro/trabajadores")
    public ResponseEntity<?> buscartrabajadaresFiltro(@RequestParam Map<String, String> params,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size) {

        Pageable pageable = PageRequest.of(page, size);

        // Verificar si todos los parámetros están vacíos
        if (todosLosParametrosEstanVacios(params)) {
            return buscarTodosLosTrabajadores(pageable);
        }

        // Buscar trabajadores con filtros
        return buscarTrabajadoresConFiltros(params);
    }

    private boolean todosLosParametrosEstanVacios(Map<String, String> params) {
        // Verificar si todos los valores en el mapa son nulos o vacíos
        return params.values().stream().allMatch(value -> value == null || value.trim().isEmpty());
    }

    private ResponseEntity<?> buscarTodosLosTrabajadores(Pageable pageable) {
        ResponseEntity<TrabajadorResponseRest> response = trabajadorService.findAll(pageable);
        return procesarRespuesta(response);
    }

    private ResponseEntity<?> buscarTrabajadoresConFiltros(Map<String, String> params) {

        String[] apellidos_aux = params.get("apellidos").trim().split("\\s+");

        String apellido_1 = apellidos_aux.length > 0 ? apellidos_aux[0] : "";
        String apellido_2 = apellidos_aux.length > 1 ? apellidos_aux[1] : "";
        ResponseEntity<TrabajadorResponseRest> response = trabajadorService.findByAll(
                params.get("cedula"),
                params.get("nombres"),
                apellido_1,
                apellido_2,
                params.get("registro"),
                params.get("nacido"),
                params.get("correo"),
                params.get("celular"));

        return procesarRespuesta(response);
    }

    private ResponseEntity<?> procesarRespuesta(ResponseEntity<TrabajadorResponseRest> response) {
        if (response.getStatusCode() == HttpStatus.OK) {
            List<Trabajador> trabajadores = response.getBody().getTrabajadorResponse().getTrabajadores();
            System.out.println("Trabajadores cargados: " + trabajadores); // Imprime en consola

            // Convertir la lista de Empresa a EmpresaRegistroDTO
            // TrabajadorRegistroDTO converter = new TrabajadorRegistroDTO();
            // List<TrabajadorRegistroDTO> list = converter.convertToDTO(trabajadores);

            return ResponseEntity.ok(trabajadores);
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}
