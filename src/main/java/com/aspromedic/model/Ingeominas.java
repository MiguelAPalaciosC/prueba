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
@Table(name = "geominas")
public class Ingeominas implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 286415799503809894L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_geominas;

    @Column(name = "geocod")
    private String codigo_geominas;

    @Column(name = "geodes")
    private String descripcion_geominas;

    public Ingeominas() {
        super();
    }

    public Ingeominas(Long id_geominas, String codigo_geominas, String descripcion_geominas) {
        super();
        this.id_geominas = id_geominas;
        this.codigo_geominas = codigo_geominas;
        this.descripcion_geominas = descripcion_geominas;
    }

    public Ingeominas(String codigo_geominas, String descripcion_geominas) {
        super();
        this.codigo_geominas = codigo_geominas;
        this.descripcion_geominas = descripcion_geominas;
    }

    public Long getId_geominas() {
        return id_geominas;
    }

    public void setId_geominas(Long id_geominas) {
        this.id_geominas = id_geominas;
    }

    public String getCodigo_geominas() {
        return codigo_geominas;
    }

    public void setCodigo_geominas(String codigo_geominas) {
        this.codigo_geominas = codigo_geominas;
    }

    public String getDescripcion_geominas() {
        return descripcion_geominas;
    }

    public void setDescripcion_geominas(String descripcion_geominas) {
        this.descripcion_geominas = descripcion_geominas;
    }

}
