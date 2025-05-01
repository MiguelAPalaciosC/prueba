package com.aspromedic.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargo")
public class Cargo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 286415799503809894L;

    public Cargo() {
        super();
    }

    public Cargo(String codigo_cargo, String descripcion_cargo) {
        super();
        this.codigo_cargo = codigo_cargo;
        this.descripcion_cargo = descripcion_cargo;
    }

    public Cargo(Long id_cargo, String codigo_cargo, String descripcion_cargo) {
        super();
        this.id_cargo = id_cargo;
        this.codigo_cargo = codigo_cargo;
        this.descripcion_cargo = descripcion_cargo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cargo;

    @Column(name = "carcod")
    private String codigo_cargo;

    @Column(name = "cardes")
    private String descripcion_cargo;

    public Long getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(Long id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getCodigo_cargo() {
        return codigo_cargo;
    }

    public void setCodigo_cargo(String codigo_cargo) {
        this.codigo_cargo = codigo_cargo;
    }

    public String getDescripcion_cargo() {
        return descripcion_cargo;
    }

    public void setDescripcion_cargo(String descripcion_cargo) {
        this.descripcion_cargo = descripcion_cargo;
    }

}
