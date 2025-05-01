package com.aspromedic.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ubicacion")
public class Ubicacion implements Serializable {

    private static final long serialVersionUID = 1L;

    public Ubicacion() {
        super();
    }

    public Ubicacion(Long id_ubicacion, String codigo_ubicacion, String descripcion_ubicacion) {
        super();
        this.id_ubicacion = id_ubicacion;
        this.codigo_ubicacion = codigo_ubicacion;
        this.descripcion_ubicacion = descripcion_ubicacion;
    }

    public Ubicacion(String codigo_ubicacion, String descripcion_ubicacion) {
        super();
        this.codigo_ubicacion = codigo_ubicacion;
        this.descripcion_ubicacion = descripcion_ubicacion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ubicacion;

    @Column(name = "ubicod")
    private String codigo_ubicacion;

    @Column(name = "ubides")
    private String descripcion_ubicacion;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(Long id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public String getCodigo_ubicacion() {
        return codigo_ubicacion;
    }

    public void setCodigo_ubicacion(String codigo_ubicacion) {
        this.codigo_ubicacion = codigo_ubicacion;
    }

    public String getDescripcion_ubicacion() {
        return descripcion_ubicacion;
    }

    public void setDescripcion_ubicacion(String descripcion_ubicacion) {
        this.descripcion_ubicacion = descripcion_ubicacion;
    }
}
