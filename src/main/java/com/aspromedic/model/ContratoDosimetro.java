package com.aspromedic.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "contratodosimetro")
public class ContratoDosimetro implements Serializable {

    private static final long serialVersionUID = 1L;

    public ContratoDosimetro() {
        super();
    }

    public ContratoDosimetro(Long id_contrato_dosimetro, Long id_dosimetro, String codigo_contrato, String estado_dosimetro) {
        super();
        this.id_contrato_dosimetro = id_contrato_dosimetro;
        this.id_dosimetro = id_dosimetro;
        this.codigo_contrato = codigo_contrato;
        this.estado_dosimetro = estado_dosimetro;
    }

    public ContratoDosimetro(Long id_dosimetro, String codigo_contrato, String estado_dosimetro) {
        super();
        this.id_dosimetro = id_dosimetro;
        this.codigo_contrato = codigo_contrato;
        this.estado_dosimetro = estado_dosimetro;
    }

    @Id
    @Column(name = "condosi")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_contrato_dosimetro;

    
    @Column(name = "dosid")
    private Long id_dosimetro;

    @Column(name = "connro")
    private String codigo_contrato;

    private String estado_dosimetro;

    public Long getId_contrato_dosimetro() {
        return id_contrato_dosimetro;
    }

    public void setId_contrato_dosimetro(Long id_contrato_dosimetro) {
        this.id_contrato_dosimetro = id_contrato_dosimetro;
    }

    public Long getId_dosimetro() {
        return id_dosimetro;
    }

    public void setId_dosimetro(Long id_dosimetro) {
        this.id_dosimetro = id_dosimetro;
    }

    public String getCodigo_contrato() {
        return codigo_contrato;
    }

    public void setCodigo_contrato(String codigo_contrato) {
        this.codigo_contrato = codigo_contrato;
    }

    public String getEstado_dosimetro() {
        return estado_dosimetro;
    }

    public void setEstado_dosimetro(String estado_dosimetro) {
        this.estado_dosimetro = estado_dosimetro;
    }

}
