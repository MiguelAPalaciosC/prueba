package com.aspromedic.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "escolaridad")
public class Escolaridad implements Serializable {

    private static final long serialVersionUID = 1L;

    public Escolaridad() {
        super();
    }

    public Escolaridad(Long id, String name, String desc) {
        super();
        this.id_escolaridad = id;
        this.nombre_escolaridad = name;
        this.codigo_escolaridad = desc;
    }

    public Escolaridad(String nombre_escolaridad, String codigo_escolaridad) {
        this.nombre_escolaridad = nombre_escolaridad;
        this.codigo_escolaridad = codigo_escolaridad;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_escolaridad;

    private String nombre_escolaridad;

    private String codigo_escolaridad;

    public Long getId_escolaridad() {
        return id_escolaridad;
    }

    public void setId_escolaridad(Long id_escolaridad) {
        this.id_escolaridad = id_escolaridad;
    }

    public String getNombre_escolaridad() {
        return nombre_escolaridad;
    }

    public void setNombre_escolaridad(String nombre_escolaridad) {
        this.nombre_escolaridad = nombre_escolaridad;
    }

    public String getCodigo_escolaridad() {
        return codigo_escolaridad;
    }

    public void setCodigo_escolaridad(String codigo_escolaridad) {
        this.codigo_escolaridad = codigo_escolaridad;
    }

}
