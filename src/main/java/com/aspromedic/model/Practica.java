package com.aspromedic.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "practica")
public class Practica implements Serializable {

    private static final long serialVersionUID = 1L;

    public Practica() {
        super();
    }

    public Practica(Long id_practica, String codigo_practica, String descripcion_practica) {
        super();
        this.id_practica = id_practica;
        this.codigo_practica = codigo_practica;
        this.descripcion_practica = descripcion_practica;
    }

    public Practica(String codigo_practica, String descripcion_practica) {
        super();
        this.codigo_practica = codigo_practica;
        this.descripcion_practica = descripcion_practica;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_practica;

    @Column(name = "pracod")
    private String codigo_practica;

    @Column(name = "prades")
    private String descripcion_practica;

    public Long getId_practica() {
        return id_practica;
    }

    public void setId_practica(Long id_practica) {
        this.id_practica = id_practica;
    }

    public String getCodigo_practica() {
        return codigo_practica;
    }

    public void setCodigo_practica(String codigo_practica) {
        this.codigo_practica = codigo_practica;
    }

    public String getDescripcion_practica() {
        return descripcion_practica;
    }

    public void setDescripcion_practica(String descripcion_practica) {
        this.descripcion_practica = descripcion_practica;
    }

}
