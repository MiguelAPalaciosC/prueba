package com.aspromedic.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciuda")
public class Ciudad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2519891643722649960L;

	public Ciudad() {
	}

	public Ciudad(Departamento departamento, String nombre_ciudad, String codigo_min_ciudad) {
		this.departamento = departamento;
		this.nombre_ciudad = nombre_ciudad;
		this.codigo_min_ciudad = codigo_min_ciudad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depcod", referencedColumnName = "depcod", nullable = false) // Relacion con la tabla
																					// departamento
	private Departamento departamento;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ciudad;

	@Column(name = "ciucod") // Mapea la columna
	private Long codigo_ciudad;

	@Column(name = "ciunom") // Mapea la columna
	private String nombre_ciudad;

	@Column(name = "ciucodmin") // Mapea la columna
	private String codigo_min_ciudad;


	public Long getId_ciudad() {
		return id_ciudad;
	}

	public void setId_ciudad(Long id_ciudad) {
		this.id_ciudad = id_ciudad;
	}

	public Long getCodigo_ciudad() {
		return codigo_ciudad;
	}

	public void setCodigo_ciudad(Long codigo_ciudad) {
		this.codigo_ciudad = codigo_ciudad;
	}

	public String getNombre_ciudad() {
		return nombre_ciudad;
	}

	public void setNombre_ciudad(String nombre_ciudad) {
		this.nombre_ciudad = nombre_ciudad;
	}

	public String getCodigo_min_ciudad() {
		return codigo_min_ciudad;
	}

	public void setCodigo_min_ciudad(String codigo_min_ciudad) {
		this.codigo_min_ciudad = codigo_min_ciudad;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Ciudad [codigo_ciudad=" + codigo_ciudad + ", nombre_ciudad=" + nombre_ciudad + ", codigo_min_ciudad="
				+ codigo_min_ciudad + ", departamento=" + departamento + "]";
	}
}
