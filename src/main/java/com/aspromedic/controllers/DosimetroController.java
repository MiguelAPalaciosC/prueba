package com.aspromedic.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspromedic.dto.DosimetroRegistroDTO;
import com.aspromedic.model.Cargo;
import com.aspromedic.model.Contrato;
import com.aspromedic.model.ContratoDosimetro;
import com.aspromedic.model.Dosimetro;
import com.aspromedic.model.Empresa;
import com.aspromedic.model.Ingeominas;
import com.aspromedic.model.Practica;
import com.aspromedic.model.Radiacion;
import com.aspromedic.model.TipoDosimetro;
import com.aspromedic.model.Trabajador;
import com.aspromedic.model.Ubicacion;
import com.aspromedic.response.CargoResponseRest;
import com.aspromedic.response.ContratoDosimetroResponseRest;
import com.aspromedic.response.ContratoResponseRest;
import com.aspromedic.response.DosimetroResponseRest;
import com.aspromedic.response.EmpresaResponseRest;
import com.aspromedic.response.IngeominasResponseRest;
import com.aspromedic.response.PracticaResponseRest;
import com.aspromedic.response.RadiacionResponseRest;
import com.aspromedic.response.TipoDosimetroResponseRest;
import com.aspromedic.response.TrabajadorResponseRest;
import com.aspromedic.response.UbicacionResponseRest;
import com.aspromedic.service.ContratoServiceImpl;
import com.aspromedic.service.ICargoService;
import com.aspromedic.service.IContratoDosimetroService;
import com.aspromedic.service.IContratoService;
import com.aspromedic.service.IDosimetroService;
import com.aspromedic.service.IEmpresaService;
import com.aspromedic.service.IIngeominasService;
import com.aspromedic.service.IPracticaService;
import com.aspromedic.service.IRadiacionService;
import com.aspromedic.service.ITipoDosimetroService;
import com.aspromedic.service.ITrabajadorService;
import com.aspromedic.service.IUbicacionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/gestion3")
public class DosimetroController {

    @Autowired
    private IEmpresaService empresaService;

    @Autowired
    private ITrabajadorService trabajadorService;

    @Autowired
    private ITipoDosimetroService tipoDosimetroService;

    @Autowired
    private IUbicacionService ubicacionService;

    @Autowired
    private ICargoService cargoService;

    @Autowired
    private IPracticaService practicaService;

    @Autowired
    private IIngeominasService ingeominasService;

    @Autowired
    private IRadiacionService radiacionService;

    @Autowired
    private IDosimetroService dosimetrosService;

    @Autowired
    private IContratoDosimetroService contratoDosimetroService;

    @Autowired
    private IContratoService contratoService;

    @GetMapping("/dosimetros")
    public String mostrarDosimetros(Model model, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<EmpresaResponseRest> response = empresaService.findAll();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            model.addAttribute("empresas", response.getBody().getEmpresaResponse().getEmpresas());

            model.addAttribute("id_empresa", "");
        } else {
            model.addAttribute("empresas", Collections.emptyList());

            model.addAttribute("id_empresa", "");
        }

        model.addAttribute("empresa", new Empresa());
        model.addAttribute("dosimetros", null);

        return "admin/dosimetros";
    }

    @GetMapping("/dosimetros/{id}")
    public String mostrarDosimetrosPorIdEmpresa(Model model, HttpSession session, @PathVariable Long id)
            throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<EmpresaResponseRest> response = empresaService.findAll();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            model.addAttribute("empresas", response.getBody().getEmpresaResponse().getEmpresas());

            model.addAttribute("id_empresa", id);
        } else {
            model.addAttribute("empresas", Collections.emptyList());

            model.addAttribute("id_empresa", id);
        }

        ResponseEntity<EmpresaResponseRest> empresaResponse = empresaService.buscarPorId(id);

        if (empresaResponse.getStatusCode() == HttpStatus.OK && empresaResponse.getBody() != null) {
            model.addAttribute("empresa", empresaResponse.getBody().getEmpresaResponse().getEmpresas().get(0));
        } else {
            model.addAttribute("empresa", Collections.emptyList());
        }

        ResponseEntity<DosimetroResponseRest> responseDosimetro = dosimetrosService.findByEmpresa(id);

        if (responseDosimetro.getStatusCode() == HttpStatus.OK && responseDosimetro.getBody() != null) {
            DosimetroRegistroDTO dosimetro = new DosimetroRegistroDTO();
            List<DosimetroRegistroDTO> list_dosimetro = dosimetro
                    .convertirListaDosimetros(responseDosimetro.getBody().getDosimetroResponse().getDosimetros());

            for (DosimetroRegistroDTO dosimetroRegistro : list_dosimetro) {

                Long id_dosimetro = dosimetroRegistro.getId_dosimetro();
                ResponseEntity<ContratoDosimetroResponseRest> responseContrato = contratoDosimetroService
                        .findByIdDosimetroActivo(id_dosimetro);
                if (responseContrato.getStatusCode() == HttpStatus.OK && responseContrato.getBody() != null) {
                    ContratoDosimetro contratoDosimetro = responseContrato.getBody()
                            .getContratoDosimetroResponse().getContratoDosimetros().get(0);
                    ResponseEntity<ContratoResponseRest> responseContratoDosimetro = contratoService
                            .findById(Long.parseLong(contratoDosimetro.getCodigo_contrato()));

                    if (responseContratoDosimetro.getStatusCode() == HttpStatus.OK
                            && responseContratoDosimetro.getBody() != null) {
                        Contrato contratoResponseRest = responseContratoDosimetro.getBody()
                                .getContratoResponse().getContratos().get(0);

                        dosimetroRegistro.setNumero_contrato(contratoDosimetro.getCodigo_contrato());
                        dosimetroRegistro
                                .setFecha_inicio(contratoResponseRest.getFecha_inicio_contrato().toLocalDate());
                        dosimetroRegistro.setFecha_fin(contratoResponseRest.getFecha_fin());

                    }
                }

                model.addAttribute("dosimetros", list_dosimetro);
            }

        } else {
            model.addAttribute("dosimetros", Collections.emptyList());
        }

        return "admin/dosimetros";
    }

    @GetMapping("/dosimetro/{id}")
    public String mosatrarRegistroDosimetro(Model model, HttpSession session, @PathVariable Long id) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<TipoDosimetroResponseRest> responseTipo = tipoDosimetroService.findAll();

        if (responseTipo.getStatusCode() == HttpStatus.OK && responseTipo.getBody() != null) {
            model.addAttribute("tiposDosimetro", responseTipo.getBody().getTipoDosimetroResponse().getTipos());
        } else {
            model.addAttribute("tiposDosimetro", Collections.emptyList());
        }

        ResponseEntity<UbicacionResponseRest> responseUbicacion = ubicacionService.findAll();

        if (responseUbicacion.getStatusCode() == HttpStatus.OK && responseUbicacion.getBody() != null) {
            model.addAttribute("ubicaciones", responseUbicacion.getBody().getUbicacionResponse().getUbicaciones());
        } else {
            model.addAttribute("ubicaciones", Collections.emptyList());
        }

        ResponseEntity<CargoResponseRest> responseCargo = cargoService.buscarCargos();

        if (responseCargo.getStatusCode() == HttpStatus.OK && responseCargo.getBody() != null) {
            model.addAttribute("cargos", responseCargo.getBody().getCargoResponse().getCargos());
        } else {
            model.addAttribute("cargos", Collections.emptyList());
        }

        ResponseEntity<PracticaResponseRest> responsePractica = practicaService.findAll();

        if (responsePractica.getStatusCode() == HttpStatus.OK && responsePractica.getBody() != null) {
            model.addAttribute("practicas", responsePractica.getBody().getPracticaResponse().getPracticas());
        } else {
            model.addAttribute("practicas", Collections.emptyList());
        }

        ResponseEntity<RadiacionResponseRest> responseRadiacion = radiacionService.findAll();

        if (responseRadiacion.getStatusCode() == HttpStatus.OK && responseRadiacion.getBody() != null) {
            model.addAttribute("radiaciones", responseRadiacion.getBody().getRadiacionResponse().getRadiaciones());
        } else {
            model.addAttribute("radiaciones", Collections.emptyList());
        }

        ResponseEntity<IngeominasResponseRest> responseIngeominas = ingeominasService.buscarIngeominas();

        if (responseIngeominas.getStatusCode() == HttpStatus.OK && responseIngeominas.getBody() != null) {
            model.addAttribute("ingeominas", responseIngeominas.getBody().getIngeominasResponse().getIngeominas());
        } else {
            model.addAttribute("ingeominas", Collections.emptyList());
        }

        ResponseEntity<EmpresaResponseRest> empresaResponse = empresaService.buscarPorId(id);
        if (empresaResponse.getStatusCode() == HttpStatus.OK && empresaResponse.getBody() != null) {
            model.addAttribute("empresa", empresaResponse.getBody().getEmpresaResponse().getEmpresas().get(0));
        } else {
            model.addAttribute("empresa", Collections.emptyList());
        }

        model.addAttribute("id_dosimetro", null);
        model.addAttribute("id_empresa", id);
        model.addAttribute("trabajador", null);
        model.addAttribute("dosimetro", null);
        model.addAttribute("contratosDosimetro", 0);

        return "admin/dosimetro";
    }

    @GetMapping("/dosimetro/{id}/{id_dosimetro}")
    public String mosatrarInfoDosimetro(Model model, HttpSession session, @PathVariable Long id,
            @PathVariable Long id_dosimetro) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<TipoDosimetroResponseRest> responseTipo = tipoDosimetroService.findAll();

        if (responseTipo.getStatusCode() == HttpStatus.OK && responseTipo.getBody() != null) {
            model.addAttribute("tiposDosimetro", responseTipo.getBody().getTipoDosimetroResponse().getTipos());
        } else {
            model.addAttribute("tiposDosimetro", Collections.emptyList());
        }

        ResponseEntity<UbicacionResponseRest> responseUbicacion = ubicacionService.findAll();

        if (responseUbicacion.getStatusCode() == HttpStatus.OK && responseUbicacion.getBody() != null) {
            model.addAttribute("ubicaciones", responseUbicacion.getBody().getUbicacionResponse().getUbicaciones());
        } else {
            model.addAttribute("ubicaciones", Collections.emptyList());
        }

        ResponseEntity<CargoResponseRest> responseCargo = cargoService.buscarCargos();

        if (responseCargo.getStatusCode() == HttpStatus.OK && responseCargo.getBody() != null) {
            model.addAttribute("cargos", responseCargo.getBody().getCargoResponse().getCargos());
        } else {
            model.addAttribute("cargos", Collections.emptyList());
        }

        ResponseEntity<PracticaResponseRest> responsePractica = practicaService.findAll();

        if (responsePractica.getStatusCode() == HttpStatus.OK && responsePractica.getBody() != null) {
            model.addAttribute("practicas", responsePractica.getBody().getPracticaResponse().getPracticas());
        } else {
            model.addAttribute("practicas", Collections.emptyList());
        }

        ResponseEntity<RadiacionResponseRest> responseRadiacion = radiacionService.findAll();

        if (responseRadiacion.getStatusCode() == HttpStatus.OK && responseRadiacion.getBody() != null) {
            model.addAttribute("radiaciones", responseRadiacion.getBody().getRadiacionResponse().getRadiaciones());
        } else {
            model.addAttribute("radiaciones", Collections.emptyList());
        }

        ResponseEntity<IngeominasResponseRest> responseIngeominas = ingeominasService.buscarIngeominas();

        if (responseIngeominas.getStatusCode() == HttpStatus.OK && responseIngeominas.getBody() != null) {
            model.addAttribute("ingeominas", responseIngeominas.getBody().getIngeominasResponse().getIngeominas());
        } else {
            model.addAttribute("ingeominas", Collections.emptyList());
        }

        model.addAttribute("id_dosimetro", id_dosimetro);
        model.addAttribute("id_empresa", id);

        ResponseEntity<EmpresaResponseRest> empresaResponse = empresaService.buscarPorId(id);
        if (empresaResponse.getStatusCode() == HttpStatus.OK && empresaResponse.getBody() != null) {
            model.addAttribute("empresa", empresaResponse.getBody().getEmpresaResponse().getEmpresas().get(0));
        } else {
            model.addAttribute("empresa", Collections.emptyList());
        }

        ResponseEntity<DosimetroResponseRest> responseDosimetro = dosimetrosService.findById(id_dosimetro);
        if (responseDosimetro.getStatusCode() == HttpStatus.OK && responseDosimetro.getBody() != null) {
            model.addAttribute("dosimetro", responseDosimetro.getBody().getDosimetroResponse().getDosimetros().get(0));

            ResponseEntity<TrabajadorResponseRest> responseTrabajador = trabajadorService.findById(responseDosimetro
                    .getBody().getDosimetroResponse().getDosimetros().get(0).getId_trabajador().getId_trabajador());

            if (responseTrabajador.getStatusCode() == HttpStatus.OK && responseTrabajador.getBody() != null) {
                model.addAttribute("trabajador",
                        responseTrabajador.getBody().getTrabajadorResponse().getTrabajadores().get(0));
            } else {
                model.addAttribute("trabajador", List.of());
            }
        } else {
            model.addAttribute("dosimetro", List.of());
        }

        ResponseEntity<ContratoDosimetroResponseRest> dosimetroContratoResponse = contratoDosimetroService
                .findByIdDosimetro(id_dosimetro);
        if (dosimetroContratoResponse.getStatusCode() == HttpStatus.OK && dosimetroContratoResponse.getBody() != null) {
            model.addAttribute("contratosDosimetro", 1);
        } else {
            model.addAttribute("contratosDosimetro", 0);
        }

        ResponseEntity<ContratoDosimetroResponseRest> responseContrato = contratoDosimetroService
                .findByIdDosimetro(id_dosimetro);
        if (responseContrato.getStatusCode() == HttpStatus.OK && responseContrato.getBody() != null) {

            List<ContratoDosimetro> contratos = responseContrato.getBody().getContratoDosimetroResponse()
                    .getContratoDosimetros();

            // Verificamos si alguno tiene estado_dosimetro == "0"
            boolean tieneEstadoInvalido = contratos.stream()
                    .anyMatch(c -> "0".equals(c.getEstado_dosimetro()));

            // Si tiene estado inválido, lo eliminamos de la lista
            if (tieneEstadoInvalido) {
                model.addAttribute("contratosDosimetro", 1);
            } else {
                model.addAttribute("contratosDosimetro", 0);
            }
        } else {
            model.addAttribute("contratosDosimetro", 0);
        }

        return "admin/dosimetro";
    }

    @GetMapping("/trabajadores")
    public ResponseEntity<?> mostrarTrabajadores(@RequestParam String cedula, @RequestParam String nombre,
            Model model) {

        ResponseEntity<TrabajadorResponseRest> response = trabajadorService.findByCodigoYNombre(cedula, nombre);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return ResponseEntity.ok(response.getBody().getTrabajadorResponse().getTrabajadores());
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/codigo")
    public ResponseEntity<?> validarCodigo(@RequestParam("codigo_dosimetro") String codigo_dosimetro) {
        ResponseEntity<DosimetroResponseRest> response = dosimetrosService.findByCodigoDosimetro(codigo_dosimetro);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return ResponseEntity.ok(response.getBody().getDosimetroResponse().getDosimetros());
        }

        return ResponseEntity.ok("0");

    }

    @PostMapping("/save/{id}")
    public String save(@PathVariable Long id, DosimetroRegistroDTO request, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        System.out.println("Dosimetro: " + request.toString());

        request.setId_empresa(id);

        if ("0".equals(request.getDestino_dosimetro())) {
            request.setDosimetro_control(Long.parseLong("0"));
            request.setDosimetro_ambiental(Long.parseLong("0"));
        } else if ("1".equals(request.getDestino_dosimetro())) {
            request.setDosimetro_control(Long.parseLong("1"));
            request.setDosimetro_ambiental(Long.parseLong("0"));
        } else {
            request.setDosimetro_control(Long.parseLong("0"));
            request.setDosimetro_ambiental(Long.parseLong("1"));
        }

        Dosimetro dosimetro = new Dosimetro();

        dosimetro.setCodigo_dosimetro(request.getCodigo_dosimetro());
        dosimetro.setId_empresa(request.getId_empresa());
        dosimetro.setEstado_dosimetro(request.getEstado_dosimetro());
        dosimetro.setPeriodo_uso(request.getPeriodo_uso());
        dosimetro.setDestino_dosimetro(request.getDestino_dosimetro());
        dosimetro.setDosimetro_control(request.getDosimetro_control());
        dosimetro.setDosimetro_ambiental(request.getDosimetro_ambiental());

        ResponseEntity<TrabajadorResponseRest> responseTrabajador = trabajadorService
                .findById(request.getId_trabajador());

        if (responseTrabajador.getStatusCode() == HttpStatus.OK && responseTrabajador.getBody() != null) {
            Trabajador trabajadorDosimetro = responseTrabajador.getBody().getTrabajadorResponse().getTrabajadores()
                    .get(0);

            dosimetro.setId_trabajador(trabajadorDosimetro);
        }

        ResponseEntity<PracticaResponseRest> responsePractica = practicaService
                .findById(Long.parseLong(request.getPractica()));

        if (responsePractica.getStatusCode() == HttpStatus.OK && responsePractica.getBody() != null) {
            Practica practicaDosimetro = responsePractica.getBody().getPracticaResponse().getPracticas()
                    .get(0);

            dosimetro.setPractica(practicaDosimetro);

        }

        ResponseEntity<RadiacionResponseRest> responseRadiacion = radiacionService
                .findById(Long.parseLong(request.getRadiacion()));

        if (responseRadiacion.getStatusCode() == HttpStatus.OK && responseRadiacion.getBody() != null) {
            Radiacion radiacionDosimetro = responseRadiacion.getBody().getRadiacionResponse().getRadiaciones()
                    .get(0);

            dosimetro.setRadiacion(radiacionDosimetro);
        }

        ResponseEntity<UbicacionResponseRest> responseUbicacion = ubicacionService
                .findById(Long.parseLong(request.getUbicacion()));

        if (responseUbicacion.getStatusCode() == HttpStatus.OK && responseUbicacion.getBody() != null) {
            Ubicacion ubicacionDosimetro = responseUbicacion.getBody().getUbicacionResponse().getUbicaciones()
                    .get(0);

            dosimetro.setUbicacion(ubicacionDosimetro);
        }

        ResponseEntity<CargoResponseRest> responseCargo = cargoService.buscarPorId(Long.parseLong(request.getCargo()));

        if (responseCargo.getStatusCode() == HttpStatus.OK && responseCargo.getBody() != null) {
            Cargo cargoDosimetro = responseCargo.getBody().getCargoResponse().getCargos()
                    .get(0);

            dosimetro.setCargo(cargoDosimetro);
        }

        ResponseEntity<IngeominasResponseRest> responseIngestos = ingeominasService
                .buscarIngeominasPorId(Long.parseLong(request.getIngeominas()));

        if (responseIngestos.getStatusCode() == HttpStatus.OK && responseIngestos.getBody() != null) {
            Ingeominas ingeominasDosimetro = responseIngestos.getBody().getIngeominasResponse().getIngeominas()
                    .get(0);

            dosimetro.setIngeominas(ingeominasDosimetro);
        }

        ResponseEntity<TipoDosimetroResponseRest> responseTipos = tipoDosimetroService
                .findById(Long.parseLong(request.getTipo_dosimetro()));

        if (responseTipos.getStatusCode() == HttpStatus.OK && responseTipos.getBody() != null) {
            TipoDosimetro tipoDosimetroDosimetro = responseTipos.getBody().getTipoDosimetroResponse().getTipos()
                    .get(0);

            dosimetro.setTipo_dosimetro(tipoDosimetroDosimetro);
        }

        ResponseEntity<DosimetroResponseRest> response = dosimetrosService.save(dosimetro);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "gestion3/dosimetros/" + id + "?exito";
        } else {
            return "gestion3/dosimetros/" + id + "/?error";
        }

    }

    @PostMapping("/update/{id_empresa}")
    public String updateDosimetro(@PathVariable("id_empresa") Long id_empresa,
            @RequestParam("id_dosimetro") Long id_dosimetro,
            DosimetroRegistroDTO request, HttpSession session) throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        System.out.println("Dosimetro: " + request.toString());

        request.setId_empresa(id_empresa);

        if ("0".equals(request.getDestino_dosimetro())) {
            request.setDosimetro_control(Long.parseLong("0"));
            request.setDosimetro_ambiental(Long.parseLong("0"));
        } else if ("1".equals(request.getDestino_dosimetro())) {
            request.setDosimetro_control(Long.parseLong("1"));
            request.setDosimetro_ambiental(Long.parseLong("0"));
        } else {
            request.setDosimetro_control(Long.parseLong("0"));
            request.setDosimetro_ambiental(Long.parseLong("1"));
        }

        Dosimetro dosimetro = new Dosimetro();

        dosimetro.setCodigo_dosimetro(request.getCodigo_dosimetro());
        dosimetro.setId_empresa(request.getId_empresa());
        dosimetro.setEstado_dosimetro(request.getEstado_dosimetro());
        dosimetro.setPeriodo_uso(request.getPeriodo_uso());
        dosimetro.setDestino_dosimetro(request.getDestino_dosimetro());
        dosimetro.setDosimetro_control(request.getDosimetro_control());
        dosimetro.setDosimetro_ambiental(request.getDosimetro_ambiental());

        ResponseEntity<TrabajadorResponseRest> responseTrabajador = trabajadorService
                .findById(request.getId_trabajador());

        if (responseTrabajador.getStatusCode() == HttpStatus.OK && responseTrabajador.getBody() != null) {
            Trabajador trabajadorDosimetro = responseTrabajador.getBody().getTrabajadorResponse().getTrabajadores()
                    .get(0);

            dosimetro.setId_trabajador(trabajadorDosimetro);
        }

        ResponseEntity<PracticaResponseRest> responsePractica = practicaService
                .findById(Long.parseLong(request.getPractica()));

        if (responsePractica.getStatusCode() == HttpStatus.OK && responsePractica.getBody() != null) {
            Practica practicaDosimetro = responsePractica.getBody().getPracticaResponse().getPracticas()
                    .get(0);

            dosimetro.setPractica(practicaDosimetro);

        }

        ResponseEntity<RadiacionResponseRest> responseRadiacion = radiacionService
                .findById(Long.parseLong(request.getRadiacion()));

        if (responseRadiacion.getStatusCode() == HttpStatus.OK && responseRadiacion.getBody() != null) {
            Radiacion radiacionDosimetro = responseRadiacion.getBody().getRadiacionResponse().getRadiaciones()
                    .get(0);

            dosimetro.setRadiacion(radiacionDosimetro);
        }

        ResponseEntity<UbicacionResponseRest> responseUbicacion = ubicacionService
                .findById(Long.parseLong(request.getUbicacion()));

        if (responseUbicacion.getStatusCode() == HttpStatus.OK && responseUbicacion.getBody() != null) {
            Ubicacion ubicacionDosimetro = responseUbicacion.getBody().getUbicacionResponse().getUbicaciones()
                    .get(0);

            dosimetro.setUbicacion(ubicacionDosimetro);
        }

        ResponseEntity<CargoResponseRest> responseCargo = cargoService.buscarPorId(Long.parseLong(request.getCargo()));

        if (responseCargo.getStatusCode() == HttpStatus.OK && responseCargo.getBody() != null) {
            Cargo cargoDosimetro = responseCargo.getBody().getCargoResponse().getCargos()
                    .get(0);

            dosimetro.setCargo(cargoDosimetro);
        }

        ResponseEntity<IngeominasResponseRest> responseIngestos = ingeominasService
                .buscarIngeominasPorId(Long.parseLong(request.getIngeominas()));

        if (responseIngestos.getStatusCode() == HttpStatus.OK && responseIngestos.getBody() != null) {
            Ingeominas ingeominasDosimetro = responseIngestos.getBody().getIngeominasResponse().getIngeominas()
                    .get(0);

            dosimetro.setIngeominas(ingeominasDosimetro);
        }

        ResponseEntity<TipoDosimetroResponseRest> responseTipos = tipoDosimetroService
                .findById(Long.parseLong(request.getTipo_dosimetro()));

        if (responseTipos.getStatusCode() == HttpStatus.OK && responseTipos.getBody() != null) {
            TipoDosimetro tipoDosimetroDosimetro = responseTipos.getBody().getTipoDosimetroResponse().getTipos()
                    .get(0);

            dosimetro.setTipo_dosimetro(tipoDosimetroDosimetro);
        }

        ResponseEntity<DosimetroResponseRest> response = dosimetrosService.update(id_dosimetro, dosimetro);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return "gestion3/dosimetros/" + id_empresa + "?exitoUpdate";
        } else {
            return "gestion3/dosimetros/" + id_empresa + "?errorUpdate";
        }

    }

}
