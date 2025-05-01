package com.aspromedic.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.aspromedic.model.Contrato;
import com.aspromedic.model.Factura;
import com.aspromedic.model.TipoDosimetro;

public class FacturaRegistroDTO {

    public FacturaRegistroDTO() {
    }

    public FacturaRegistroDTO(Long id_factura, String concepto, Long contrato, String otro_concepto, Long id_empresa,
            String numero_factura,
            LocalDate fecha_factura, Long valor_factura, String observaciones_factura) {
        this.concepto = concepto;
        this.otro_concepto = otro_concepto;
        this.id_factura = id_factura;
        this.contrato = contrato;
        this.id_empresa = id_empresa;
        this.numero_factura = numero_factura;
        this.fecha_factura = fecha_factura;
        this.valor_factura = valor_factura;
        this.observaciones_factura = observaciones_factura;
    }

    public FacturaRegistroDTO(String concepto, Long contrato, String otro_concepto, Long id_empresa,
            String numero_factura,
            LocalDate fecha_factura, Long valor_factura, String observaciones_factura) {
        this.concepto = concepto;
        this.otro_concepto = otro_concepto;
        this.contrato = contrato;
        this.id_empresa = id_empresa;
        this.numero_factura = numero_factura;
        this.fecha_factura = fecha_factura;
        this.valor_factura = valor_factura;
        this.observaciones_factura = observaciones_factura;
    }

    private Long id_factura;

    private String concepto;

    private String otro_concepto;

    private Long contrato;

    private Long id_empresa;

    private String numero_factura;

    private LocalDate fecha_factura;

    private Long valor_contrato;

    private Long valor_factura;

    private String observaciones_factura;

    public Long getId_factura() {
        return id_factura;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }

    public Long getContrato() {
        return contrato;
    }

    public void setContrato(Long contrato) {
        this.contrato = contrato;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(String numero_factura) {
        this.numero_factura = numero_factura;
    }

    public LocalDate getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(LocalDate fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public Long getValor_factura() {
        return valor_factura;
    }

    public void setValor_factura(Long valor_factura) {
        this.valor_factura = valor_factura;
    }

    public String getObservaciones_factura() {
        return observaciones_factura;
    }

    public void setObservaciones_factura(String observaciones_factura) {
        this.observaciones_factura = observaciones_factura;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getOtro_concepto() {
        return otro_concepto;
    }

    public void setOtro_concepto(String otro_concepto) {
        this.otro_concepto = otro_concepto;
    }

    public static List<FacturaRegistroDTO> convertirListaFacturas(List<Factura> facturas) {
        List<FacturaRegistroDTO> listaDTO = new ArrayList<>();

        for (Factura factura : facturas) {
            // Convertir LocalDateTime a LocalDate
            LocalDate fecha = factura.getFecha_factura().toLocalDate();

            // Crear un nuevo objeto FacturaRegistroDTO
            FacturaRegistroDTO dto = new FacturaRegistroDTO(
                    factura.getId_factura(),
                    factura.getConcepto(),
                    factura.getContrato().getId_contrato(),
                    factura.getOtro_concepto(),
                    factura.getId_empresa(),
                    factura.getNumero_factura(),
                    fecha,
                    factura.getValor_factura(),
                    factura.getObservaciones_factura());

            // Agregar el DTO a la lista
            listaDTO.add(dto);
        }

        return listaDTO;
    }

    @Override
    public String toString() {
        return "Factura: [" +
            "id_factura=" + id_factura +
            ", concepto='" + concepto + '\'' +
            ", otro_concepto='" + otro_concepto + '\'' +
            ", contrato=" + contrato +
            ", id_empresa=" + id_empresa +
            ", numero_factura='" + numero_factura + '\'' +
            ", fecha_factura=" + fecha_factura +
            ", valor_factura=" + valor_factura +
            ", observaciones_factura='" + observaciones_factura + '\'' +
        "]";
    }

    public Long getValor_contrato() {
        return valor_contrato;
    }

    public void setValor_contrato(Long valor_contrato) {
        this.valor_contrato = valor_contrato;
    }
}
