package com.aspromedic.model;

import java.io.Serializable;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "observa")
public class Observacion implements Serializable {

    private static final long serialVersionUID = 1L;

    public Observacion() {
        super();
    }

    public Observacion(Long id_observacion, String codigo_observacion, String descripcion_observacion) {
        super();
        this.id_observacion = id_observacion;
        this.codigo_observacion = codigo_observacion;
        this.descripcion_observacion = descripcion_observacion;
    }

    public Observacion(String codigo_observacion, String descripcion_observacion) {
        super();
        this.codigo_observacion = codigo_observacion;
        this.descripcion_observacion = descripcion_observacion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_observacion;

    @Column(name = "obscod")
    private String codigo_observacion;

    @Column(name = "obsdes")
    private String descripcion_observacion;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId_observacion() {
        return id_observacion;
    }

    public void setId_observacion(Long id_observacion) {
        this.id_observacion = id_observacion;
    }

    public String getCodigo_observacion() {
        return codigo_observacion;
    }

    public void setCodigo_observacion(String codigo_observacion) {
        this.codigo_observacion = codigo_observacion;
    }

    public String getDescripcion_observacion() {
        return descripcion_observacion;
    }

    public void setDescripcion_observacion(String descripcion_observacion) {
        this.descripcion_observacion = descripcion_observacion;
    }

}
