package com.aspromedic.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspromedic.dto.ContratoRegistroDTO;
import com.aspromedic.dto.FacturaRegistroDTO;
import com.aspromedic.model.Contrato;
import com.aspromedic.model.Empresa;
import com.aspromedic.model.Factura;
import com.aspromedic.model.ReciboPago;
import com.aspromedic.model.SeguimientoFactura;
import com.aspromedic.model.TipoDosimetro;
import com.aspromedic.response.ContratoResponseRest;
import com.aspromedic.response.EmpresaResponseRest;
import com.aspromedic.response.FacturaResponseRest;
import com.aspromedic.service.IContratoService;
import com.aspromedic.service.IEmpresaService;
import com.aspromedic.service.IFacturaService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/gestion5")
public class FacturaController {

    @Autowired
    private IEmpresaService empresaService;

    @Autowired
    private IContratoService contratoService;

    @Autowired
    private IFacturaService facturaService;

    @GetMapping("/facturas")
    public String mostrarContratos(Model model, HttpSession session) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
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

        model.addAttribute("facturas", Collections.emptyList());

        return "admin/facturas";
    }

    @GetMapping("/facturas/{id}")
    public String mostrarContratosPorEmpresa(Model model, HttpSession session, @PathVariable Long id) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<EmpresaResponseRest> response = empresaService.findAll();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            model.addAttribute("empresas", response.getBody().getEmpresaResponse().getEmpresas());

            model.addAttribute("id_empresa", id);
        } else {
            model.addAttribute("empresas", Collections.emptyList());

            model.addAttribute("id_empresa", "");
        }

        ResponseEntity<EmpresaResponseRest> responseEmpresas = empresaService.buscarPorId(id);

        if (responseEmpresas.getStatusCode() == HttpStatus.OK && responseEmpresas.getBody() != null) {
            model.addAttribute("empresa", responseEmpresas.getBody().getEmpresaResponse().getEmpresas().get(0));
        } else {
            model.addAttribute("empresa", Collections.emptyList());
        }

        ResponseEntity<FacturaResponseRest> responseFactura = facturaService.findByIdEmpresa(id);

        System.out.println("Facturas :" + responseFactura.getStatusCode());
        if (responseFactura.getStatusCode() == HttpStatus.OK && responseFactura.getBody() != null) {
            FacturaRegistroDTO facturaRegistroDTO = new FacturaRegistroDTO();

            List<FacturaRegistroDTO> listFacturas = facturaRegistroDTO
                    .convertirListaFacturas(responseFactura.getBody().getFacturaResponse().getFacturas());
            model.addAttribute("facturas", listFacturas);
        } else {
            model.addAttribute("facturas", Collections.emptyList());
        }

        return "admin/facturas";
    }

    @GetMapping("/factura/{id}")
    public String irFacturaVacia(Model model, HttpSession session, @PathVariable Long id) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<EmpresaResponseRest> response = empresaService.buscarPorId(id);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            model.addAttribute("empresa", response.getBody().getEmpresaResponse().getEmpresas().get(0));
        } else {
            model.addAttribute("empresa", Collections.emptyList());
        }

        ResponseEntity<ContratoResponseRest> responseContratos = contratoService.findByEmpresa(id);

        if (responseContratos.getStatusCode() == HttpStatus.OK && responseContratos.getBody() != null) {
            ContratoRegistroDTO contratoRegistro = new ContratoRegistroDTO();

            List<ContratoRegistroDTO> contratos = contratoRegistro
                    .convertirListaContratos(responseContratos.getBody().getContratoResponse().getContratos());

            model.addAttribute("contratos", contratos);
        } else {
            model.addAttribute("contratos", Collections.emptyList());
        }

        model.addAttribute("id_empresa", id);

        model.addAttribute("id_factura", null);

        return "admin/factura";
    }

    @GetMapping("/factura/{id}/{id_factura}")
    public String verFactura(Model model, HttpSession session, @PathVariable Long id, @PathVariable Long id_factura) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        ResponseEntity<EmpresaResponseRest> response = empresaService.buscarPorId(id);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            model.addAttribute("empresa", response.getBody().getEmpresaResponse().getEmpresas().get(0));
        } else {
            model.addAttribute("empresa", Collections.emptyList());
        }

        ResponseEntity<ContratoResponseRest> responseContratos = contratoService.findByEmpresa(id);

        if (responseContratos.getStatusCode() == HttpStatus.OK && responseContratos.getBody() != null) {
            ContratoRegistroDTO contratoRegistro = new ContratoRegistroDTO();

            List<ContratoRegistroDTO> contratos = contratoRegistro
                    .convertirListaContratos(responseContratos.getBody().getContratoResponse().getContratos());

            model.addAttribute("contratos", contratos);
        } else {
            model.addAttribute("contratos", Collections.emptyList());
        }

        model.addAttribute("id_empresa", id);

        model.addAttribute("id_factura", id_factura);

        ResponseEntity<FacturaResponseRest> responseFactura = facturaService.findByIdFactura(id_factura);

        if (responseFactura.getStatusCode() == HttpStatus.OK && responseFactura.getBody() != null) {
            FacturaRegistroDTO factura = new FacturaRegistroDTO();

            List<FacturaRegistroDTO> list_factura = factura.convertirListaFacturas(responseFactura.getBody().getFacturaResponse().getFacturas());

            model.addAttribute("factura", list_factura.get(0));
        } else {
            model.addAttribute("factura", Collections.emptyList());
        }

        return "admin/factura";
    }

    @PostMapping("/save/{id}")
    public String guardarFactura(Model model, HttpSession session, @PathVariable Long id,
            FacturaRegistroDTO factura,
            @RequestParam(value = "fecha_recibo", required = false) List<String> fecha_recibo,
            @RequestParam(value = "valor_recibo", required = false) List<String> valor_recibo,
            @RequestParam(value = "retencion", required = false) List<String> retencion,
            @RequestParam(value = "usuario", required = false) List<String> usuario,
            @RequestParam(value = "fecha_seguimiento", required = false) List<String> fecha_seguimiento,
            @RequestParam(value = "comentario", required = false) List<String> comentario) throws Exception {
        Object userIdAttribute = session.getAttribute("user_session_id");

        if (userIdAttribute == null) {
            return "redirect:/login";
        }

        System.out.println("Factura: " + factura.toString());

        Factura nuevaFactura = new Factura();

        ResponseEntity<ContratoResponseRest> responseContratos = contratoService.findById(factura.getContrato());

        if (responseContratos.getStatusCode() == HttpStatus.OK && responseContratos.getBody() != null) {
            nuevaFactura.setContrato(
                    responseContratos.getBody().getContratoResponse().getContratos().get(0));
        }

        ResponseEntity<FacturaResponseRest> responseFactura = facturaService.findByIdContrato(factura.getContrato());
        int size = 1;
        if (responseFactura.getStatusCode() == HttpStatus.OK) {
            List<Factura> list_factura = responseFactura.getBody().getFacturaResponse().getFacturas();
            size = list_factura.size();

        }
        
        nuevaFactura.setId_empresa(id);
        nuevaFactura.setConcepto(factura.getConcepto());
        nuevaFactura.setOtro_concepto(factura.getOtro_concepto());

        // nuevaFactura.setNumero_factura(factura.getNumero_factura());
        nuevaFactura.setNumero_factura("" + (size + 1));

        nuevaFactura.setFecha_factura(factura.getFecha_factura().atStartOfDay());
        nuevaFactura.setValor_factura(factura.getValor_factura());
        nuevaFactura.setObservaciones_factura(factura.getObservaciones_factura());

        // List<SeguimientoFactura> seguimientos = new ArrayList<>();

        // if (usuario != null && fecha_seguimiento != null && comentario != null) {
        // for (int i = 0; i < usuario.size(); i++) {
        // SeguimientoFactura seguimiento = new SeguimientoFactura();
        // seguimiento.setUsuario(usuario.get(i));
        // seguimiento.setFecha(LocalDateTime.parse(fecha_seguimiento.get(i)));
        // seguimiento.setComentario(comentario.get(i));

        // // Aquí no asignamos la factura todavía, normalmente se hace después
        // seguimientos.add(seguimiento);
        // }
        // }

        // List<ReciboPago> recibo = new ArrayList<>();

        // if (usuario != null && fecha_seguimiento != null && comentario != null) {
        // for (int i = 0; i < usuario.size(); i++) {
        // // ReciboPago recibo = new SeguimientoFactura();
        // // recibo.setFecha_pago((usuario.get(i));
        // // recibo.setFechaSeguimiento(LocalDateTime.parse(fecha_recibo.get(i)));
        // // recibo.setComentario(comentario.get(i));

        // // // Aquí no asignamos la factura todavía, normalmente se hace después
        // // recibo.add(recibo);
        // }
        // }

        ResponseEntity<FacturaResponseRest> response = facturaService.save(nuevaFactura);

        return "redirect:/gestion5/facturas/" + id + "?exito";
    }

    @GetMapping("/infoContrato")
    public ResponseEntity<?> verInfoContrato(@RequestParam Long id_contrato) throws Exception {

        ResponseEntity<ContratoResponseRest> response = contratoService.findById(id_contrato);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            List<Contrato> contratos = response.getBody().getContratoResponse().getContratos();

            ResponseEntity<FacturaResponseRest> responseFactura = facturaService.findByIdContrato(id_contrato);
            if (responseFactura.getStatusCode() == HttpStatus.OK) {
                if (contratos != null && !contratos.isEmpty()) {
                    List<Factura> list_factura = responseFactura.getBody().getFacturaResponse().getFacturas();
                    int size = list_factura.size();

                    Map<String, Object> respuesta = new HashMap<>();
                    respuesta.put("contrato", contratos.get(0));
                    respuesta.put("factura_numero", size);

                    return ResponseEntity.ok(respuesta);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(Map.of("error", "No se encontró el contrato"));
                }
            }

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No se encontró el contrato"));
    }

}
