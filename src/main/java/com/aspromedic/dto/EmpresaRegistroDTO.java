package com.aspromedic.dto;

import java.time.LocalDateTime;

public class EmpresaRegistroDTO {

    public EmpresaRegistroDTO(Long codigo_interno_empresa, Long nit_empresa, String razon_social_empresa,
            String direccion_empresa, String telefono_empresa, String fax_empresa, String celular_empresa,
            LocalDateTime fecha_vin_empresa, String estado_empresa, String mail_empresa, Long departamento,
            String observaciones_empresa, String ciudad, Long id_empresa, Integer digito_verificacion) {
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

    public EmpresaRegistroDTO() {
    }

    private Long codigo_interno_empresa;

    private Long nit_empresa;

    private String razon_social_empresa;

    private String direccion_empresa;

    private String telefono_empresa;

    private String fax_empresa;

    private String celular_empresa;

    private LocalDateTime fecha_vin_empresa;

    private String estado_empresa;

    private String mail_empresa;

    private Long departamento;

    private String observaciones_empresa;

    private String ciudad;

    private Long id_empresa;

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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public Integer getDigito_verificacion() {
        return digito_verificacion;
    }

    public void setDigito_verificacion(Integer digito_verificacion) {
        this.digito_verificacion = digito_verificacion;
    }

}
