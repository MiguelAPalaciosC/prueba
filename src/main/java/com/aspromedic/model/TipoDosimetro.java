package com.aspromedic.model;

import java.io.Serializable;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_dosimetro")
public class TipoDosimetro implements Serializable {

    private static final long serialVersionUID = 1L;

    public TipoDosimetro() {
    }

    public TipoDosimetro(Long id_tipo_dosimetro, String nombre, String orden) {
        this.id_tipo_dosimetro = id_tipo_dosimetro;
        this.nombre = nombre;
        this.orden = orden;
    }

    public TipoDosimetro(String nombre, String orden) {
        this.nombre = nombre;
        this.orden = orden;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipo_dosimetro;

    private String orden;

    private String nombre;

    private String descripcion;

    public Long getId_tipo_dosimetro() {
        return id_tipo_dosimetro;
    }

    public void setId_tipo_dosimetro(Long id_tipo_dosimetro) {
        this.id_tipo_dosimetro = id_tipo_dosimetro;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
