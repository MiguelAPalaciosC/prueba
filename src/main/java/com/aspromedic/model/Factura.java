package com.aspromedic.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "factura")
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    public Factura(String concepto, Contrato contrato, String otro_concepto, Long id_empresa, String numero_factura,
            LocalDateTime fecha_factura,
            Long valor_factura, String observaciones_factura) {
        this.concepto = concepto;
        this.otro_concepto = otro_concepto;
        this.contrato = contrato;
        this.id_empresa = id_empresa;
        this.numero_factura = numero_factura;
        this.fecha_factura = fecha_factura;
        this.valor_factura = valor_factura;
        this.observaciones_factura = observaciones_factura;
    }

    public Factura(Long id_factura, String concepto, Contrato contrato, String otro_concepto, Long id_empresa,
            String numero_factura,
            LocalDateTime fecha_factura, Long valor_factura, String observaciones_factura) {
        this.id_factura = id_factura;
        this.concepto = concepto;
        this.otro_concepto = otro_concepto;
        this.contrato = contrato;
        this.id_empresa = id_empresa;
        this.numero_factura = numero_factura;
        this.fecha_factura = fecha_factura;
        this.valor_factura = valor_factura;
        this.observaciones_factura = observaciones_factura;
    }

    public Factura() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long id_factura;

    private String concepto;

    @ManyToOne(fetch = FetchType.EAGER) // Cambiar a EAGER si es necesario
    @JoinColumn(name = "connro", referencedColumnName = "connro", nullable = false)
    private Contrato contrato;

    private String otro_concepto;

    @Column(name = "empcod")
    private Long id_empresa;

    @Column(name = "facnro")
    private String numero_factura;

    @Column(name = "facfech")
    private LocalDateTime fecha_factura;

    @Column(name = "facval")
    private Long valor_factura;

    private String observaciones_factura;

    // @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<SeguimientoFactura> seguimientos = new ArrayList<>();

    // @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<ReciboPago> recibos = new ArrayList<>();

    public Long getId_factura() {
        return id_factura;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
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

    public LocalDateTime getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(LocalDateTime fecha_factura) {
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

    // public List<SeguimientoFactura> getSeguimientos() {
    //     return seguimientos;
    // }

    // public void setSeguimientos(List<SeguimientoFactura> seguimientos) {
    //     this.seguimientos = seguimientos;
    // }

    // public List<ReciboPago> getRecibos() {
    //     return recibos;
    // }

    // public void setRecibos(List<ReciboPago> recibos) {
    //     this.recibos = recibos;
    // }

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

}
