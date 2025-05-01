package com.aspromedic.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "titulo")
public class Titulo implements Serializable {

    private static final long serialVersionUID = 1L;

    public Titulo() {
        super();
    }

    public Titulo(Long id_titulo, String codigo_titulo, String descripcion_titulo) {
        super();
        this.id_titulo = id_titulo;
        this.codigo_titulo = codigo_titulo;
        this.descripcion_titulo = descripcion_titulo;
    }

    public Titulo(String codigo_titulo, String descripcion_titulo) {
        super();
        this.codigo_titulo = codigo_titulo;
        this.descripcion_titulo = descripcion_titulo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "titid")
    private Long id_titulo;

    @Column(name = "titcod")
    private String codigo_titulo;

    @Column(name = "titdes")
    private String descripcion_titulo;

    public Long getId_titulo() {
        return id_titulo;
    }

    public void setId_titulo(Long id_titulo) {
        this.id_titulo = id_titulo;
    }

    public String getCodigo_titulo() {
        return codigo_titulo;
    }

    public void setCodigo_titulo(String codigo_titulo) {
        this.codigo_titulo = codigo_titulo;
    }

    public String getDescripcion_titulo() {
        return descripcion_titulo;
    }

    public void setDescripcion_titulo(String descripcion_titulo) {
        this.descripcion_titulo = descripcion_titulo;
    }

    @Override
    public String toString() {
        return "Titulo [id_titulo=" + id_titulo + ", codigo_titulo=" + codigo_titulo + ", descripcion_titulo="
                + descripcion_titulo + "]";
    }
}
