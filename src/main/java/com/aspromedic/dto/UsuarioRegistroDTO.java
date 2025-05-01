package com.aspromedic.dto;

public class UsuarioRegistroDTO {

	public UsuarioRegistroDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioRegistroDTO(String nombre, String username, String cargo, String password) {
		super();
		this.nombre = nombre;
		this.username = username;
		this.cargo = cargo;
		this.password = password;
	}

	public UsuarioRegistroDTO(Long id, String nombre, String username, String cargo, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.cargo = cargo;
		this.password = password;
	}

	public UsuarioRegistroDTO(String nombre, String username, String cargo, String password, Integer estado) {
		this.nombre = nombre;
		this.username = username;
		this.cargo = cargo;
		this.password = password;
		this.estado = estado;
	}

	private Long id;
	private String nombre;
	private String username;
	private String cargo;
	private String password;
	private Integer estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEstado() {
        return estado;
    }

	public void setEstado(Integer estado) {
        this.estado = estado;
    }

}
