package com.aspromedic.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.aspromedic.model.Trabajador;

public class TrabajadorRegistroDTO {

    public TrabajadorRegistroDTO() {
    }

    public TrabajadorRegistroDTO(Long cedula_trabajador, String nombre_trabajador, String primer_apellido_trabajador,
            String segundo_apellido_trabajador, LocalDate fecha_inicio_trabajador, String estado_trabajador,
            String observaciones_trabajador, String sexo_trabajador, String mail_trabajador, String celular_trabajador,
            LocalDate fecha_nacimiento_trabajador, String escolaridad_trabajador, String titulo_trabajador,
            Long id_titulo) {
        this.cedula_trabajador = cedula_trabajador;
        this.nombre_trabajador = nombre_trabajador;
        this.primer_apellido_trabajador = primer_apellido_trabajador;
        this.segundo_apellido_trabajador = segundo_apellido_trabajador;
        this.fecha_inicio_trabajador = fecha_inicio_trabajador;
        this.estado_trabajador = estado_trabajador;
        this.observaciones_trabajador = observaciones_trabajador;
        this.sexo_trabajador = sexo_trabajador;
        this.mail_trabajador = mail_trabajador;
        this.celular_trabajador = celular_trabajador;
        this.fecha_nacimiento_trabajador = fecha_nacimiento_trabajador;
        this.escolaridad_trabajador = escolaridad_trabajador;
        this.titulo_trabajador = titulo_trabajador;
        this.id_titulo = id_titulo;
    }

    public TrabajadorRegistroDTO(Long id_trabajador, Long cedula_trabajador, String nombre_trabajador,
            String primer_apellido_trabajador, String segundo_apellido_trabajador, LocalDate fecha_inicio_trabajador,
            String estado_trabajador, String observaciones_trabajador, String sexo_trabajador, String mail_trabajador,
            String celular_trabajador, LocalDate fecha_nacimiento_trabajador, String escolaridad_trabajador,
            String titulo_trabajador, Long id_titulo) {
        this.id_trabajador = id_trabajador;
        this.cedula_trabajador = cedula_trabajador;
        this.nombre_trabajador = nombre_trabajador;
        this.primer_apellido_trabajador = primer_apellido_trabajador;
        this.segundo_apellido_trabajador = segundo_apellido_trabajador;
        this.fecha_inicio_trabajador = fecha_inicio_trabajador;
        this.estado_trabajador = estado_trabajador;
        this.observaciones_trabajador = observaciones_trabajador;
        this.sexo_trabajador = sexo_trabajador;
        this.mail_trabajador = mail_trabajador;
        this.celular_trabajador = celular_trabajador;
        this.fecha_nacimiento_trabajador = fecha_nacimiento_trabajador;
        this.escolaridad_trabajador = escolaridad_trabajador;
        this.titulo_trabajador = titulo_trabajador;
        this.id_titulo = id_titulo;
    }

    private Long id_trabajador;

    private Long cedula_trabajador;

    private String nombre_trabajador;

    private String primer_apellido_trabajador;

    private String segundo_apellido_trabajador;

    private LocalDate fecha_inicio_trabajador;

    private String estado_trabajador;

    private String observaciones_trabajador;

    private String sexo_trabajador;

    private String mail_trabajador;

    private String celular_trabajador;

    private LocalDate fecha_nacimiento_trabajador;

    private String escolaridad_trabajador;

    private String titulo_trabajador;

    private Long id_titulo;

    public Long getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(Long id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public Long getCedula_trabajador() {
        return cedula_trabajador;
    }

    public void setCedula_trabajador(Long cedula_trabajador) {
        this.cedula_trabajador = cedula_trabajador;
    }

    public String getNombre_trabajador() {
        return nombre_trabajador;
    }

    public void setNombre_trabajador(String nombre_trabajador) {
        this.nombre_trabajador = nombre_trabajador;
    }

    public String getPrimer_apellido_trabajador() {
        return primer_apellido_trabajador;
    }

    public void setPrimer_apellido_trabajador(String primer_apellido_trabajador) {
        this.primer_apellido_trabajador = primer_apellido_trabajador;
    }

    public String getSegundo_apellido_trabajador() {
        return segundo_apellido_trabajador;
    }

    public void setSegundo_apellido_trabajador(String segundo_apellido_trabajador) {
        this.segundo_apellido_trabajador = segundo_apellido_trabajador;
    }

    public LocalDate getFecha_inicio_trabajador() {
        return fecha_inicio_trabajador;
    }

    public void setFecha_inicio_trabajador(LocalDate fecha_inicio_trabajador) {
        this.fecha_inicio_trabajador = fecha_inicio_trabajador;
    }

    public String getEstado_trabajador() {
        return estado_trabajador;
    }

    public void setEstado_trabajador(String estado_trabajador) {
        this.estado_trabajador = estado_trabajador;
    }

    public String getObservaciones_trabajador() {
        return observaciones_trabajador;
    }

    public void setObservaciones_trabajador(String observaciones_trabajador) {
        this.observaciones_trabajador = observaciones_trabajador;
    }

    public String getSexo_trabajador() {
        return sexo_trabajador;
    }

    public void setSexo_trabajador(String sexo_trabajador) {
        this.sexo_trabajador = sexo_trabajador;
    }

    public String getMail_trabajador() {
        return mail_trabajador;
    }

    public void setMail_trabajador(String mail_trabajador) {
        this.mail_trabajador = mail_trabajador;
    }

    public String getCelular_trabajador() {
        return celular_trabajador;
    }

    public void setCelular_trabajador(String celular_trabajador) {
        this.celular_trabajador = celular_trabajador;
    }

    public LocalDate getFecha_nacimiento_trabajador() {
        return fecha_nacimiento_trabajador;
    }

    public void setFecha_nacimiento_trabajador(LocalDate fecha_nacimiento_trabajador) {
        this.fecha_nacimiento_trabajador = fecha_nacimiento_trabajador;
    }

    public String getEscolaridad_trabajador() {
        return escolaridad_trabajador;
    }

    public void setEscolaridad_trabajador(String escolaridad_trabajador) {
        this.escolaridad_trabajador = escolaridad_trabajador;
    }

    public String getTitulo_trabajador() {
        return titulo_trabajador;
    }

    public void setTitulo_trabajador(String titulo_trabajador) {
        this.titulo_trabajador = titulo_trabajador;
    }

    public Long getId_titulo() {
        return id_titulo;
    }

    public void setId_titulo(Long id_titulo) {
        this.id_titulo = id_titulo;
    }

    @Override
    public String toString() {
        return "TrabajadorRegistroDTO{" +
                "cedula_trabajador=" + cedula_trabajador +
                ", nombre_trabajador='" + nombre_trabajador + '\'' +
                ", primer_apellido_trabajador='" + primer_apellido_trabajador + '\'' +
                ", segundo_apellido_trabajador='" + segundo_apellido_trabajador + '\'' +
                ", fecha_inicio_trabajador=" + fecha_inicio_trabajador +
                ", estado_trabajador='" + estado_trabajador + '\'' +
                ", observaciones_trabajador='" + observaciones_trabajador + '\'' +
                ", sexo_trabajador='" + sexo_trabajador + '\'' +
                ", mail_trabajador='" + mail_trabajador + '\'' +
                ", celular_trabajador='" + celular_trabajador + '\'' +
                ", fecha_nacimiento_trabajador=" + fecha_nacimiento_trabajador +
                ", escolaridad_trabajador='" + escolaridad_trabajador + '\'' +
                ", titulo_trabajador='" + titulo_trabajador + '\'' +
                ", id_titulo=" + id_titulo +
                "}";
    }

    public List<TrabajadorRegistroDTO> convertToDTO(List<Trabajador> trabajadores) {
        return trabajadores.stream()
                .map(trabajador -> new TrabajadorRegistroDTO(
                        trabajador.getId_trabajador(),
                        trabajador.getCedula_trabajador(),
                        trabajador.getNombre_trabajador(),
                        trabajador.getPrimer_apellido_trabajador(),
                        trabajador.getSegundo_apellido_trabajador(),
                        trabajador.getFecha_inicio_trabajador() != null
                                ? trabajador.getFecha_inicio_trabajador().toLocalDate()
                                : null, // Manejo de nulos
                        trabajador.getEstado_trabajador(),
                        trabajador.getObservaciones_trabajador(),
                        trabajador.getSexo_trabajador(),
                        trabajador.getMail_trabajador(),
                        trabajador.getCelular_trabajador(),
                        trabajador.getFecha_nacimiento_trabajador() != null
                                ? trabajador.getFecha_nacimiento_trabajador().toLocalDate()
                                : null, // Manejo de nulos
                        trabajador.getEscolaridad_trabajador(),
                        trabajador.getTitulo_trabajador(),
                        trabajador.getId_titulo()))
                .collect(Collectors.toList());
    }

}
