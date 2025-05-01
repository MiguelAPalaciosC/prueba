package com.aspromedic.model;

import java.io.Serializable;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "recibos_pago")
public class ReciboPago implements Serializable {

    private static final long serialVersionUID = 1L;

    public ReciboPago() {
    }

    public ReciboPago(Long id_recibo, Factura factura, String numero_recibo, LocalDateTime fecha_pago, Long valor_recibo,
            Long retenciones) {
        this.id_recibo = id_recibo;
        this.factura = factura;
        this.numero_recibo = numero_recibo;
        this.fecha_pago = fecha_pago;
        this.valor_recibo = valor_recibo;
        this.retenciones = retenciones;
    }

    public ReciboPago(Factura factura, String numero_recibo, LocalDateTime fecha_pago, Long valor_recibo, Long retenciones) {
        this.factura = factura;
        this.numero_recibo = numero_recibo;
        this.fecha_pago = fecha_pago;
        this.valor_recibo = valor_recibo;
        this.retenciones = retenciones;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recibo")
    private Long id_recibo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura", nullable = false)
    private Factura factura;

    @Column(name = "numero_recibo")
    private String numero_recibo;

    @Column(name = "fecha_pago")
    private LocalDateTime fecha_pago;

    @Column(name = "valor_recibo")
    private Long valor_recibo;

    @Column(name = "retenciones")
    private Long retenciones;

    // --- Getters y Setters ---

    public Long getId_recibo() {
        return id_recibo;
    }

    public void setId_recibo(Long id_recibo) {
        this.id_recibo = id_recibo;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getNumero_recibo() {
        return numero_recibo;
    }

    public void setNumero_recibo(String numero_recibo) {
        this.numero_recibo = numero_recibo;
    }

    public LocalDateTime getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDateTime fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public Long getValor_recibo() {
        return valor_recibo;
    }

    public void setValor_recibo(Long valor_recibo) {
        this.valor_recibo = valor_recibo;
    }

    public Long getRetenciones() {
        return retenciones;
    }

    public void setRetenciones(Long retenciones) {
        this.retenciones = retenciones;
    }

}
