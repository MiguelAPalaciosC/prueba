package com.aspromedic.controllers;

import java.io.IOException;
import java.net.ResponseCache;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspromedic.dto.ContratoRegistroDTO;
import com.aspromedic.model.Contacto;
import com.aspromedic.model.Contrato;
import com.aspromedic.model.ContratoDosimetro;
import com.aspromedic.model.Dosimetro;
import com.aspromedic.model.Empresa;
import com.aspromedic.model.TipoDosimetro;
import com.aspromedic.response.ContactoResponseRest;
import com.aspromedic.response.ContratoDosimetroResponse;
import com.aspromedic.response.ContratoDosimetroResponseRest;
import com.aspromedic.response.ContratoResponseRest;
import com.aspromedic.response.DosimetroResponseRest;
import com.aspromedic.response.EmpresaResponseRest;
import com.aspromedic.response.TipoDosimetroResponseRest;
import com.aspromedic.service.IContratoDosimetroService;
import com.aspromedic.service.IContratoService;
import com.aspromedic.service.IDosimetroService;
import com.aspromedic.service.IEmpresaService;
import com.aspromedic.service.ITipoDosimetroService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/gestion4")
public class ContratoController {

    @Autowired
    private IEmpresaService empresaService;

    @Autowired
    private IContratoService contratoService;

    @Autowired
    private IContratoDosimetroService contratoDosimetroService;

    @Autowired
    private ITipoDosimetroService tipoDosimetroService;

    @Autowired
    private IDosimetroService dosimetroService;

    @GetMapping("/contratos")
    public String mostrarContratos(Model model, HttpSession session) throws Exception {
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

        return "admin/contratos";
    }

    @GetMapping("/contratos/{id}")
    public String mostrarContratosPorEmpresa(Model model, HttpSession session, @PathVariable Long id)
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

        ResponseEntity<EmpresaResponseRest> respEmpresa = empresaService.buscarPorId(id);
        if (respEmpresa.getStatusCode() == HttpStatus.OK && respEmpresa.getBody() != null) {
            model.addAttribute("empresa", respEmpresa.getBody().getEmpresaResponse().getEmpresas().get(0));
        } else {
            model.addAttribute("empresa", Collections.emptyList());
        }

        ResponseEntity<ContratoResponseRest> respContrato = contratoService.findByEmpresa(id);

        if (respContrato.getStatusCode() == HttpStatus.OK && respContrato.getBody() != null) {
            List<Contrato> contratos = respContrato.getBody().getContratoResponse().getContratos();

            ContratoRegistroDTO contratoRegistroDTO = new ContratoRegistroDTO();

            List<ContratoRegistroDTO> contratosDTO = contratoRegistroDTO.convertirListaContratos(contratos);

            List<ContratoRegistroDTO> contratosVigentes = new ArrayList<>();

            for (ContratoRegistroDTO contrato : contratosDTO) {
                if (contrato.isFechaFinVencida()) {
                    ResponseEntity<ContratoResponseRest> responseEstado = contratoService
                            .updateEstado(contrato.getId_contrato(), "I");

                    ResponseEntity<ContratoDosimetroResponseRest> responseDosimetro = contratoDosimetroService
                            .updateEstado(contrato.getId_contrato().toString(), "1");

                    contrato.setEstado_contrato("I");
                }

                ResponseEntity<ContratoDosimetroResponseRest> responseContDosimetro = contratoDosimetroService
                        .countDosimetrosActivosContrato(contrato.getId_contrato().toString());

                if (responseContDosimetro.getStatusCode() == HttpStatus.OK) {
                    int size = responseContDosimetro.getBody().getContratoDosimetroResponse().getContratoDosimetros()
                            .size();
                    contrato.setUsuario_asignados(Integer.parseInt(contrato.getNumero_usuarios()) - size);
                }

                contratosVigentes.add(contrato);
            }

            model.addAttribute("contratos", contratosVigentes);
            // model.addAttribute("contratos", contratosDTO);
        } else {
            model.addAttribute("contratos", Collections.emptyList());
        }

        return "admin/contratos";
    }

    @GetMapping("/contrato/{id}")
    public String crearContrato(Model model, HttpSession session, @PathVariable Long id) throws IOException {
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

        model.addAttribute("id_contrato", null);
        model.addAttribute("id_empresa", id);

        ResponseEntity<EmpresaResponseRest> empresaResponse = empresaService.buscarPorId(id);
        if (empresaResponse.getStatusCode() == HttpStatus.OK && empresaResponse.getBody() != null) {
            model.addAttribute("empresa", empresaResponse.getBody().getEmpresaResponse().getEmpresas().get(0));
        } else {
            model.addAttribute("empresa", Collections.emptyList());
        }

        ResponseEntity<EmpresaResponseRest> response = empresaService.findAll();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            model.addAttribute("empresas", response.getBody().getEmpresaResponse().getEmpresas());
        } else {
            model.addAttribute("empresas", Collections.emptyList());
        }

        model.addAttribute("contrato", Collections.emptyList());
        model.addAttribute("fecha_inicio", null);
        model.addAttribute("duracion_contrato", null);
        model.addAttribute("fecha_contratacion", null);
        model.addAttribute("valo_contrato", null);
        model.addAttribute("dosimetros_contrato", Collections.emptyList());
        model.addAttribute("estado_contrato", null);

        return "admin/contrato";
    }

    @GetMapping("/contrato/{id}/{id_contrato}")
    public String verContrato(Model model, HttpSession session, @PathVariable Long id,
            @PathVariable Long id_contrato) throws Exception {
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

        model.addAttribute("id_contrato", id_contrato);
        model.addAttribute("id_empresa", id);

        ResponseEntity<EmpresaResponseRest> empresaResponse = empresaService.buscarPorId(id);
        if (empresaResponse.getStatusCode() == HttpStatus.OK && empresaResponse.getBody() != null) {
            model.addAttribute("empresa", empresaResponse.getBody().getEmpresaResponse().getEmpresas().get(0));
        } else {
            model.addAttribute("empresa", Collections.emptyList());
        }

        ResponseEntity<ContratoResponseRest> responseContrato = contratoService.findById(id_contrato);
        if (responseContrato.getStatusCode() == HttpStatus.OK && responseContrato.getBody() != null) {
            Contrato contrato = responseContrato.getBody().getContratoResponse().getContratos().get(0);
            model.addAttribute("contrato", contrato);
            model.addAttribute("estado_contrato", contrato.getEstado_contrato());
            List<TipoDosimetro> tipos = (List<TipoDosimetro>) contrato.getTipo_contrato();

            String tipos_dos = tipos.stream()
                    .map(t -> String.valueOf(t.getId_tipo_dosimetro()))
                    .collect(Collectors.joining(", "));

            model.addAttribute("tipos_dosimetro_select", tipos_dos);
            model.addAttribute("fecha_inicio", contrato.getFecha_inicio_contrato().toLocalDate());
            model.addAttribute("duracion_contrato", contrato.getDuracion_contrato());

            Long valor = ((Number) contrato.getValor_factura()).longValue();
            model.addAttribute("valo_contrato", valor);

            if (contrato.getFecha_contratacion_factura() != null) {
                model.addAttribute("fecha_contratacion", contrato.getFecha_contratacion_factura().toLocalDate());
            } else {
                model.addAttribute("fecha_contratacion", null); // o una fecha por defecto si aplica
            }
            
        } else {
            model.addAttribute("contrato", Collections.emptyList());
        }

        ResponseEntity<EmpresaResponseRest> response = empresaService.findAll();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            model.addAttribute("empresas", response.getBody().getEmpresaResponse().getEmpresas());
        } else {
            model.addAttribute("empresas", Collections.emptyList());
        }

        ResponseEntity<ContratoDosimetroResponseRest> responseDosimetro = contratoDosimetroService
                .findByIdContrato(id_contrato.toString());

        List<Dosimetro> list = new ArrayList<>();

        if (responseDosimetro.getStatusCode() == HttpStatus.OK && responseDosimetro.getBody() != null) {

            List<ContratoDosimetro> dosimetrosContrato = responseDosimetro.getBody()
                    .getContratoDosimetroResponse()
                    .getContratoDosimetros();

            for (ContratoDosimetro contratoDosimetro : dosimetrosContrato) {
                Long idDosimetro = contratoDosimetro.getId_dosimetro();

                if (idDosimetro != null) {
                    ResponseEntity<DosimetroResponseRest> responseDosimetro2 = dosimetroService.findById(idDosimetro);

                    if (responseDosimetro2.getStatusCode() == HttpStatus.OK
                            && responseDosimetro2.getBody() != null
                            && responseDosimetro2.getBody().getDosimetroResponse() != null) {

                        List<Dosimetro> dosimetros = responseDosimetro2.getBody().getDosimetroResponse()
                                .getDosimetros();

                        if (dosimetros != null && !dosimetros.isEmpty()) {
                            list.add(dosimetros.get(0)); // Solo estás tomando el primero
                        }
                    }
                }
            }

            model.addAttribute("dosimetros_contrato", list);

        } else {
            model.addAttribute("dosimetros_contrato", Collections.emptyList());
        }

        return "admin/contrato";
    }

    @PostMapping("/save/{id}")
    public String guardarContrato(Model model, HttpSession session, @PathVariable Long id,
            ContratoRegistroDTO request,
            @RequestParam(value = "tipo_contrato_list", required = false) List<Long> tipo_contrato_list,
            @RequestParam(value = "dosimetros", required = false) List<Long> dosimetros)
            throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        Contrato contrato = new Contrato();

        contrato.setId_empresa(id);
        contrato.setFecha_inicio_contrato(request.getFecha_inicio_contrato().atStartOfDay());
        contrato.setValor_factura(request.getValor_factura());
        contrato.setDuracion_contrato(request.getDuracion_contrato());
        contrato.setFecha_contratacion_factura(request.getFecha_contratacion_factura().atStartOfDay());
        contrato.setIdentificacion_contratacion(request.getIdentificacion_contratacion());
        contrato.setPeriodo_uso_contrato(request.getPeriodo_uso_contrato());
        contrato.setFactura_a(request.getFactura_a());

        List<TipoDosimetro> tipos = new ArrayList<>();
        for (Long tipoDosimetroId : tipo_contrato_list) {
            ResponseEntity<TipoDosimetroResponseRest> responseTipo = tipoDosimetroService
                    .findById(tipoDosimetroId);

            if (responseTipo.getStatusCode() == HttpStatus.OK && responseTipo.getBody() != null) {
                TipoDosimetro tipo = responseTipo.getBody().getTipoDosimetroResponse().getTipos().get(0);
                tipos.add(tipo);
            }
        }
        contrato.setTipo_contrato(tipos);

        contrato.setTipo_contratacion_factura(request.getTipo_contratacion_factura());
        contrato.setEstado_contrato(request.getEstado_contrato());
        contrato.setNumero_usuarios(request.getNumero_usuarios());
        contrato.setCuotas_contrato(request.getCuotas_contrato());
        contrato.setObservaciones_contrato(request.getObservaciones_contrato());

        ResponseEntity<ContratoResponseRest> responseContrato = contratoService.save(contrato);

        if (responseContrato.getStatusCode() == HttpStatus.OK && responseContrato.getBody() != null) {
            return "gestion4/contrato/" + id + "?exito";
        }
        return "gestion4/contrato/" + id + "?error";
    }

    @PostMapping("/renovar/{id}")
    public String renovarContrato(Model model, HttpSession session, @PathVariable Long id,
            @RequestParam("id_contrato") Long id_contrato)
            throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        ResponseEntity<ContratoResponseRest> responseContrato = contratoService.findById(id_contrato);
        if (responseContrato.getStatusCode() == HttpStatus.OK &&
                responseContrato.getBody() != null) {
            Contrato contrato = responseContrato.getBody().getContratoResponse().getContratos().get(0);

            List<TipoDosimetro> tipos = new ArrayList<>();
            for (TipoDosimetro tipoDosimetro : contrato.getTipo_contrato()) {
                ResponseEntity<TipoDosimetroResponseRest> responseTipo = tipoDosimetroService
                        .findById(tipoDosimetro.getId_tipo_dosimetro());

                if (responseTipo.getStatusCode() == HttpStatus.OK && responseTipo.getBody() != null) {
                    TipoDosimetro tipo = responseTipo.getBody().getTipoDosimetroResponse().getTipos().get(0);
                    tipos.add(tipo);
                }
            }

            Contrato nuevoContrato = new Contrato(
                    contrato.getId_empresa(),
                    LocalDate.now().withDayOfMonth(1).atStartOfDay(),
                    contrato.getValor_factura(),
                    contrato.getDuracion_contrato(),
                    contrato.getFecha_contratacion_factura(),
                    contrato.getIdentificacion_contratacion(),
                    contrato.getPeriodo_uso_contrato(),
                    contrato.getFactura_a(),
                    tipos,
                    contrato.getTipo_contratacion_factura(),
                    "A", // valor por defecto
                    contrato.getNumero_usuarios(),
                    contrato.getCuotas_contrato(),
                    contrato.getObservaciones_contrato());

            ResponseEntity<ContratoResponseRest> responseUpdate = contratoService.save(nuevoContrato);

            if (responseUpdate.getStatusCode() == HttpStatus.OK &&
                    responseUpdate.getBody() != null) {

                // Obtener el último contrato de la empresa
                ResponseEntity<ContratoResponseRest> ultimoContrato = contratoService.findByIdEmpresaUltimo(id);

                if (ultimoContrato.getStatusCode() == HttpStatus.OK &&
                        ultimoContrato.getBody() != null) {
                    Contrato ultimoContratoResponse = ultimoContrato.getBody()
                            .getContratoResponse()
                            .getContratos()
                            .get(0);

                    ResponseEntity<ContratoDosimetroResponseRest> responseDosimetrosContrato = contratoDosimetroService
                            .findByIdContrato(id_contrato.toString());
                    List<ContratoDosimetro> dosimetrosContrato = responseDosimetrosContrato.getBody()
                            .getContratoDosimetroResponse()
                            .getContratoDosimetros();

                    List<ContratoDosimetro> nuevoDosimetros = new ArrayList<>();

                    if (dosimetrosContrato != null && !dosimetrosContrato.isEmpty()) {
                        for (ContratoDosimetro contratoDosimetro : dosimetrosContrato) {
                            Long idDosimetro = contratoDosimetro.getId_dosimetro();

                            ContratoDosimetro nuevoContratoDosimetro = new ContratoDosimetro(
                                    idDosimetro,
                                    ultimoContratoResponse.getId_contrato().toString(),
                                    "0" // valor por defecto
                            );

                            if (idDosimetro != null) {
                                nuevoDosimetros.add(nuevoContratoDosimetro);
                            }
                        }

                        List<ResponseEntity<ContratoDosimetroResponseRest>> responses = nuevoDosimetros.stream()
                                .map(contratoDosimetroService::save)
                                .collect(Collectors.toList());

                        return "gestion4/contratos/" + id + "?exitoRenovar";
                    } else {
                        return "gestion4/contratos/" + id + "?exitoRenovar";
                    }
                }

            }
        }

        return "gestion4/contratos/" + id + "?errorRenovar";
    }

    @PostMapping("/update/{id_empresa}")
    public String actulizarContrato(Model model, HttpSession session,
            @PathVariable("id_empresa") Long id,
            @RequestParam("id_contrato") Long id_contrato,
            @RequestParam(value = "tipo_contrato_list", required = false) List<Long> tipo_contrato_list,
            ContratoRegistroDTO request, @RequestParam(value = "dosimetros", required = false) List<Long> dosimetros)
            throws IOException {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "login";
        }

        Contrato contrato = new Contrato();

        contrato.setId_empresa(id);
        contrato.setFecha_inicio_contrato(request.getFecha_inicio_contrato().atStartOfDay());
        contrato.setValor_factura(request.getValor_factura());
        contrato.setDuracion_contrato(request.getDuracion_contrato());
        contrato.setFecha_contratacion_factura(request.getFecha_contratacion_factura().atStartOfDay());
        contrato.setIdentificacion_contratacion(request.getIdentificacion_contratacion());
        contrato.setPeriodo_uso_contrato(request.getPeriodo_uso_contrato());
        contrato.setFactura_a(request.getFactura_a());

        List<TipoDosimetro> tipos = new ArrayList<>();
        for (Long tipoDosimetroId : tipo_contrato_list) {
            ResponseEntity<TipoDosimetroResponseRest> responseTipo = tipoDosimetroService
                    .findById(tipoDosimetroId);

            if (responseTipo.getStatusCode() == HttpStatus.OK && responseTipo.getBody() != null) {
                TipoDosimetro tipo = responseTipo.getBody().getTipoDosimetroResponse().getTipos().get(0);
                tipos.add(tipo);
            }
        }

        contrato.setTipo_contrato(tipos);

        contrato.setTipo_contratacion_factura(request.getTipo_contratacion_factura());
        contrato.setEstado_contrato(request.getEstado_contrato());
        contrato.setNumero_usuarios(request.getNumero_usuarios());
        contrato.setCuotas_contrato(request.getCuotas_contrato());
        contrato.setObservaciones_contrato(request.getObservaciones_contrato());

        ResponseEntity<ContratoResponseRest> responseContrato = contratoService.update(id_contrato, contrato);

        if (responseContrato.getStatusCode() == HttpStatus.OK &&
                responseContrato.getBody() != null) {

            String estado = (request.getEstado_contrato().equals("A")) ? "0" : "1";

            ResponseEntity<ContratoDosimetroResponseRest> responseDosimetro = contratoDosimetroService
                    .updateEstado(id_contrato.toString(), estado);

            return "gestion4/contratos/" + id + "?exitoUpdate";
        }

        return "gestion4/contratos/" + id + "?errorUpdate";
    }

    @GetMapping("/newDosimetro")
    public ResponseEntity<?> nuevoDosimetroContrato(
            @RequestParam Long id_contrato,
            @RequestParam List<String> selectedDosimetros) throws Exception {

        System.out.println("Dosimetros seleccionados: '" + selectedDosimetros + "'");

        // Validamos si la lista no es nula y tiene elementos
        int size = selectedDosimetros != null ? selectedDosimetros.size() : 0;

        if (size == 0) {
            return ResponseEntity.badRequest().body("No se seleccionaron dosímetros");
        }

        // Procesamos la lista de dosímetros seleccionados
        List<ContratoDosimetro> contDosimetro = IntStream.range(0, size)
                .mapToObj(i -> {
                    try {
                        Long dosimetroId = Long.parseLong(selectedDosimetros.get(i));
                        return new ContratoDosimetro(dosimetroId, id_contrato.toString(), "0");
                    } catch (NumberFormatException e) {
                        // En caso de que el valor no sea un número válido
                        System.err.println("Error al parsear el dosímetro: " + selectedDosimetros.get(i));
                        return null; // Retorna null si no es válido
                    }
                })
                .filter(Objects::nonNull) // Elimina valores nulos
                .collect(Collectors.toList());

        // Guardamos los dosímetros de forma segura
        if (contDosimetro.isEmpty()) {
            return ResponseEntity.badRequest().body("No se pudo guardar ningún dosímetro válido.");
        }

        // Guardamos en base de datos
        List<ResponseEntity<ContratoDosimetroResponseRest>> responses = contDosimetro.stream()
                .map(contratoDosimetroService::save)
                .collect(Collectors.toList());

        return ResponseEntity.ok("Dosímetros guardados correctamente: " + contDosimetro.size());
    }

    @GetMapping("/deleteDosimetro")
    public ResponseEntity<?> nuevoDosimetroContrato(
            @RequestParam Long id_contrato,
            @RequestParam Long id_dosimetro) throws Exception {

        ResponseEntity<ContratoDosimetroResponseRest> response = contratoDosimetroService
                .deleteByIdContratoAndIdDosimetro(id_contrato.toString(), id_dosimetro);

        return ResponseEntity.ok("Dosímetros eliminado correctamente " + response.getStatusCode());
    }

    @GetMapping("/filtrar/dosimetros")
    public ResponseEntity<?> mostrarDosimetros(
            @RequestParam String periodo_uso_contrato,
            @RequestParam Long empresa,
            @RequestParam String tipo_dosimetro) throws Exception {

        ResponseEntity<DosimetroResponseRest> response = dosimetroService
                .findByDosimetrosPorContratoValido(periodo_uso_contrato, empresa, tipo_dosimetro);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            List<Dosimetro> dosimetros = response.getBody().getDosimetroResponse().getDosimetros();

            return ResponseEntity.ok(dosimetros);
        }

        return ResponseEntity.ok(Collections.emptyList());
    }

    @PostMapping("/autoSave")
    public ResponseEntity<?> guardarContratoAuto(@RequestBody Map<String, Object> data) {
        try {
            // Extraer los valores del mapa (se pueden castear según tipo esperado)
            Long id_contrato = Long.parseLong(data.get("contrato").toString());
            Long empresa = Long.parseLong(data.get("empresa").toString());
            // String fechaInicio = (String) data.get("fecha_inicio_contrato");

            // Declarar el formateador
            String fechaInicioStr = (String) data.get("fecha_inicio_contrato");
            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);

            String fechaFacturaStr = (String) data.get("fecha_contratacion_factura");
            
            LocalDate fechaFactura = LocalDate.parse(fechaFacturaStr);

            String duracion = (String) data.get("duracion_contrato");
            String periodoUso = (String) data.get("periodo_uso_contrato");
            String tipoContratacion = (String) data.get("tipo_contratacion_factura");
            String facturaA = (String) data.get("factura_a");
            String identificacion = (String) data.get("identificacion_contratacion");
            // String fechaFactura = (String) data.get("fecha_contratacion_factura");

            // LocalDateTime fechaFactura = LocalDateTime.parse((String)
            // data.get("fecha_contratacion_factura"),
            // formatter);

            Long valor = Long.parseLong(data.get("valor_contrato").toString());
            String cuotas = (String) data.get("cuotas_contrato").toString();
            String usuarios = (String) data.get("numero_usuarios").toString();
            String estado = (String) data.get("estado_contrato");
            String tipoDosimetro = (String) data.get("tipo_dosimetro");
            String observaciones = (String) data.get("observaciones_contrato");

            // Aquí podrías instanciar un objeto Contrato y guardarlo con tu servicio

            Contrato contrato = new Contrato();

            contrato.setId_empresa(empresa);

            contrato.setFecha_inicio_contrato(fechaInicio.atStartOfDay());

            contrato.setValor_factura(valor);
            contrato.setDuracion_contrato(Long.parseLong(duracion));

            contrato.setFecha_contratacion_factura(fechaFactura.atStartOfDay());
            
            contrato.setIdentificacion_contratacion(identificacion);
            contrato.setPeriodo_uso_contrato(periodoUso);
            contrato.setFactura_a(facturaA);

            String tipo_contrato_list[] = tipoDosimetro.split(",");

            List<TipoDosimetro> tipos = new ArrayList<>();
            for (String tipoDosimetroId : tipo_contrato_list) {
                ResponseEntity<TipoDosimetroResponseRest> responseTipo = tipoDosimetroService
                        .findById(Long.parseLong(tipoDosimetroId));

                if (responseTipo.getStatusCode() == HttpStatus.OK && responseTipo.getBody() != null) {
                    TipoDosimetro tipo = responseTipo.getBody().getTipoDosimetroResponse().getTipos().get(0);
                    tipos.add(tipo);
                }
            }
            contrato.setTipo_contrato(tipos);

            contrato.setTipo_contratacion_factura(tipoContratacion);
            contrato.setEstado_contrato(estado);
            contrato.setNumero_usuarios(usuarios);
            contrato.setCuotas_contrato(cuotas);
            contrato.setObservaciones_contrato(observaciones);

            ResponseEntity<ContratoResponseRest> responseContrato = contratoService.update(id_contrato, contrato);

            if (responseContrato.getStatusCode() == HttpStatus.OK &&
                    responseContrato.getBody() != null) {

                return ResponseEntity.ok("Contrato guardado correctamente");
            }

            return ResponseEntity.ok("Contrato guardado incorrectamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el contrato");
        }
    }

}
