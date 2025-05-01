package com.aspromedic.dto;

import jakarta.persistence.Column;

public class CargoRegistroDTO {

    public CargoRegistroDTO() {
        super();
    }

    public CargoRegistroDTO(String codigo_cargo, String descripcion_cargo) {
        super();
        this.codigo_cargo = codigo_cargo;
        this.descripcion_cargo = descripcion_cargo;
    }

    public CargoRegistroDTO(Long id_cargo, String codigo_cargo, String descripcion_cargo) {
        super();
        this.id_cargo = id_cargo;
        this.codigo_cargo = codigo_cargo;
        this.descripcion_cargo = descripcion_cargo;
    }
    
    private Long id_cargo;

    private String codigo_cargo;

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
