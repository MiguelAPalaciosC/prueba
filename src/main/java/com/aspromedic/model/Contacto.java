package com.aspromedic.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contacto")
public class Contacto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3036159824792897099L;

	public Contacto(Long id_empresa, String nombre, String apellido, String cargo, String celular, String correo) {
		super();
		this.id_empresa = id_empresa;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cargo = cargo;
		this.celular = celular;
		this.correo = correo;
	}

	public Contacto(Long id_contacto, Long id_empresa, String nombre, String apellido, String cargo, String celular,
			String correo) {
		super();
		this.id_contacto = id_contacto;
		this.id_empresa = id_empresa;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cargo = cargo;
		this.celular = celular;
		this.correo = correo;
	}

	public Contacto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_contacto;

	@Column(name = "empid")
	private Long id_empresa;

	private String nombre;

	private String apellido;

	private String cargo;

	private String celular;

	private String correo;

	public Long getId_contacto() {
		return id_contacto;
	}

	public void setId_contacto(Long id_contacto) {
		this.id_contacto = id_contacto;
	}

	public Long getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Long id_empresa) {
		this.id_empresa = id_empresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
