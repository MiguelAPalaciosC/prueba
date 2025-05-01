package com.aspromedic.dto;

public class CiudadRegistroDTO {

    private Long id;
    private String nombreCiudad;
    private String nombreDepartamento;
    private Long codigoCiudad;
    private String codigo_ministerio;

    // Getters y Setters
    public Long getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(Long codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo_ministerio() {
        return codigo_ministerio;
    }

    public void setCodigo_ministerio(String codigo_ministerio) {
        this.codigo_ministerio = codigo_ministerio;
    }
}
