package com.aspromedic.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "radiacion")
public class Radiacion implements Serializable{
    
    private static final long serialVersionUID = 1L;

    public Radiacion() {
        super();
    }

    public Radiacion(Long id_radiacion, String codigo_radiacion, String descripcion_radiacion) {
        super();
        this.id_radiacion = id_radiacion;
        this.codigo_radiacion = codigo_radiacion;
        this.descripcion_radiacion = descripcion_radiacion;
    }

    public Radiacion(String codigo_radiacion, String descripcion_radiacion) {
        super();
        this.codigo_radiacion = codigo_radiacion;
        this.descripcion_radiacion = descripcion_radiacion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_radiacion;

    @Column(name = "radcod")
    private String codigo_radiacion;

    @Column(name = "raddes")
    private String descripcion_radiacion;

    public Long getId_radiacion() {
        return id_radiacion;
    }

    public void setId_radiacion(Long id_radiacion) {
        this.id_radiacion = id_radiacion;
    }

    public String getCodigo_radiacion() {
        return codigo_radiacion;
    }

    public void setCodigo_radiacion(String codigo_radiacion) {
        this.codigo_radiacion = codigo_radiacion;
    }

    public String getDescripcion_radiacion() {
        return descripcion_radiacion;
    }

    public void setDescripcion_radiacion(String descripcion_radiacion) {
        this.descripcion_radiacion = descripcion_radiacion;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
