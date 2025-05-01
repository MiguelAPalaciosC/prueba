package com.aspromedic.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "depar")
public class Departamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 168814762920339781L;

	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Departamento(String nombre_departamento, String codigo_min_departamento) {
		super();
		this.nombre_departamento = nombre_departamento;
		this.codigo_min_departamento = codigo_min_departamento;
	}

	public Departamento(Long codigo_departamento, String nombre_departamento, String codigo_min_departamento) {
		super();
		this.codigo_departamento = codigo_departamento;
		this.nombre_departamento = nombre_departamento;
		this.codigo_min_departamento = codigo_min_departamento;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "depcod") // Mapea la columna
	private Long codigo_departamento;

	@Column(name = "depnom") // Mapea la columna
	private String nombre_departamento;

	@Column(name = "depcodmin") // Mapea la columna
	private String codigo_min_departamento;

	public Long getCodigo_departamento() {
		return codigo_departamento;
	}

	public void setCodigo_departamento(Long codigo_departamento) {
		this.codigo_departamento = codigo_departamento;
	}

	public String getNombre_departamento() {
		return nombre_departamento;
	}

	public void setNombre_departamento(String nombre_departamento) {
		this.nombre_departamento = nombre_departamento;
	}

	public String getCodigo_min_departamento() {
		return codigo_min_departamento;
	}

	public void setCodigo_min_departamento(String codigo_min_departamento) {
		this.codigo_min_departamento = codigo_min_departamento;
	}

	@Override
	public String toString() {
		return "Departamento [codigo_departamento=" + codigo_departamento + ", nombre_departamento="
				+ nombre_departamento
				+ ", codigo_min_departamento=" + codigo_min_departamento + "]";
	}
}
