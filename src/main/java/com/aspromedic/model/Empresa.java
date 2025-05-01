package com.aspromedic.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.aspromedic.dto.EmpresaRegistroDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 286415799503809894L;

	public Empresa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Empresa(Long nit_empresa, Integer digito_verificacion, String direccion_empresa, String telefono_empresa,
			String fax_empresa,
			String celular_empresa, LocalDateTime fecha_vin_empresa, String estado_empresa, String mail_empresa,
			Long departamento, String observaciones_empresa, Ciudad ciudad) {
		super();
		this.nit_empresa = nit_empresa;
		this.digito_verificacion = digito_verificacion;
		this.direccion_empresa = direccion_empresa;
		this.telefono_empresa = telefono_empresa;
		this.fax_empresa = fax_empresa;
		this.celular_empresa = celular_empresa;
		this.fecha_vin_empresa = fecha_vin_empresa;
		this.estado_empresa = estado_empresa;
		this.mail_empresa = mail_empresa;
		this.departamento = departamento;
		this.observaciones_empresa = observaciones_empresa;
		this.ciudad = ciudad;
	}

	public Empresa(Long codigo_interno_empresa, Long nit_empresa, Integer digito_verificacion,
			String razon_social_empresa, String direccion_empresa, String telefono_empresa,
			String fax_empresa, String celular_empresa, LocalDateTime fecha_vin_empresa, String estado_empresa,
			String mail_empresa, Long departamento, String observaciones_empresa, Ciudad ciudad) {
		super();
		this.codigo_interno_empresa = codigo_interno_empresa;
		this.nit_empresa = nit_empresa;
		this.digito_verificacion = digito_verificacion;
		this.razon_social_empresa = razon_social_empresa;
		this.direccion_empresa = direccion_empresa;
		this.telefono_empresa = telefono_empresa;
		this.fax_empresa = fax_empresa;
		this.celular_empresa = celular_empresa;
		this.fecha_vin_empresa = fecha_vin_empresa;
		this.estado_empresa = estado_empresa;
		this.mail_empresa = mail_empresa;
		this.departamento = departamento;
		this.observaciones_empresa = observaciones_empresa;
		this.ciudad = ciudad;
	}

	public Empresa(Long codigo_interno_empresa, Long nit_empresa, String razon_social_empresa, String direccion_empresa,
			String telefono_empresa, String fax_empresa, String celular_empresa, LocalDateTime fecha_vin_empresa,
			String estado_empresa, String mail_empresa, Long departamento, String observaciones_empresa, Ciudad ciudad,
			Long id_empresa, Integer digito_verificacion) {
		super();
		this.codigo_interno_empresa = codigo_interno_empresa;
		this.nit_empresa = nit_empresa;
		this.razon_social_empresa = razon_social_empresa;
		this.direccion_empresa = direccion_empresa;
		this.telefono_empresa = telefono_empresa;
		this.fax_empresa = fax_empresa;
		this.celular_empresa = celular_empresa;
		this.fecha_vin_empresa = fecha_vin_empresa;
		this.estado_empresa = estado_empresa;
		this.mail_empresa = mail_empresa;
		this.departamento = departamento;
		this.observaciones_empresa = observaciones_empresa;
		this.ciudad = ciudad;
		this.id_empresa = id_empresa;
		this.digito_verificacion = digito_verificacion;
	}

	@Column(name = "empcod") // Mapea la columna
	private Long codigo_interno_empresa;

	@Column(name = "empnit") // Mapea la columna
	private Long nit_empresa;

	@Column(name = "emprazsoc") // Mapea la columna
	private String razon_social_empresa;

	@Column(name = "empdir") // Mapea la columna
	private String direccion_empresa;

	@Column(name = "emptel") // Mapea la columna
	private String telefono_empresa;

	@Column(name = "empfax") // Mapea la columna
	private String fax_empresa;

	@Column(name = "empcel") // Mapea la columna
	private String celular_empresa;

	@Column(name = "empfecvin") // Mapea la columna
	@JsonIgnore
	private LocalDateTime fecha_vin_empresa;

	@Column(name = "empest") // Mapea la columna
	private String estado_empresa;

	@Column(name = "empmail") // Mapea la columna
	private String mail_empresa;

	@Column(name = "depcod")
	private Long departamento;

	@Column(name = "empobs") // Mapea la columna
	private String observaciones_empresa;

	@ManyToOne(fetch = FetchType.EAGER) // Cambiar a EAGER si es necesario
	@JoinColumn(name = "ciucod", referencedColumnName = "id_ciudad", nullable = false)
	@JsonIgnore
	private Ciudad ciudad;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_seq")
	@SequenceGenerator(name = "empresa_seq", sequenceName = "empresas_empid_seq", allocationSize = 1)
	@Column(name = "empid")
	private Long id_empresa;

	@Column(name = "dv")
	private Integer digito_verificacion;

	public Long getCodigo_interno_empresa() {
		return codigo_interno_empresa;
	}

	public void setCodigo_interno_empresa(Long codigo_interno_empresa) {
		this.codigo_interno_empresa = codigo_interno_empresa;
	}

	public Long getNit_empresa() {
		return nit_empresa;
	}

	public void setNit_empresa(Long nit_empresa) {
		this.nit_empresa = nit_empresa;
	}

	public String getRazon_social_empresa() {
		return razon_social_empresa;
	}

	public void setRazon_social_empresa(String razon_social_empresa) {
		this.razon_social_empresa = razon_social_empresa;
	}

	public String getDireccion_empresa() {
		return direccion_empresa;
	}

	public void setDireccion_empresa(String direccion_empresa) {
		this.direccion_empresa = direccion_empresa;
	}

	public String getTelefono_empresa() {
		return telefono_empresa;
	}

	public void setTelefono_empresa(String telefono_empresa) {
		this.telefono_empresa = telefono_empresa;
	}

	public String getFax_empresa() {
		return fax_empresa;
	}

	public void setFax_empresa(String fax_empresa) {
		this.fax_empresa = fax_empresa;
	}

	public String getCelular_empresa() {
		return celular_empresa;
	}

	public void setCelular_empresa(String celular_empresa) {
		this.celular_empresa = celular_empresa;
	}

	public LocalDateTime getFecha_vin_empresa() {
		return fecha_vin_empresa;
	}

	public void setFecha_vin_empresa(LocalDateTime fecha_vin_empresa) {
		this.fecha_vin_empresa = fecha_vin_empresa;
	}

	public String getEstado_empresa() {
		return estado_empresa;
	}

	public void setEstado_empresa(String estado_empresa) {
		this.estado_empresa = estado_empresa;
	}

	public String getMail_empresa() {
		return mail_empresa;
	}

	public void setMail_empresa(String mail_empresa) {
		this.mail_empresa = mail_empresa;
	}

	public Long getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Long departamento) {
		this.departamento = departamento;
	}

	public String getObservaciones_empresa() {
		return observaciones_empresa;
	}

	public void setObservaciones_empresa(String observaciones_empresa) {
		this.observaciones_empresa = observaciones_empresa;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public long getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(long id_empresa) {
		this.id_empresa = id_empresa;
	}

	public Integer getDigito_verificacion() {
		return digito_verificacion;
	}

	public void setDigito_verificacion(Integer digito_verificacion) {
		this.digito_verificacion = digito_verificacion;
	}

	public EmpresaRegistroDTO toRegistroDTO() {
		return new EmpresaRegistroDTO(
				this.codigo_interno_empresa,
				this.nit_empresa,
				this.razon_social_empresa,
				this.direccion_empresa,
				this.telefono_empresa,
				this.fax_empresa,
				this.celular_empresa,
				this.fecha_vin_empresa,
				this.estado_empresa,
				this.mail_empresa,
				this.departamento,
				this.observaciones_empresa,
				this.ciudad != null ? this.ciudad.getNombre_ciudad() : null,
				this.id_empresa,
				this.digito_verificacion);
	}
}
