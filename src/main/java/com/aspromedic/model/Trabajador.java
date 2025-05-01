package com.aspromedic.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.aspromedic.dto.TrabajadorRegistroDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "traba")
public class Trabajador implements Serializable {

    private static final long serialVersionUID = 1L;

    public Trabajador() {
    }

    public Trabajador(Long cedula_trabajador, String nombre_trabajador, String primer_apellido_trabajador,
            String segundo_apellido_trabajador, LocalDateTime fecha_inicio_trabajador, String estado_trabajador,
            String observaciones_trabajador, String sexo_trabajador, Long id_trabajador, String mail_trabajador,
            String celular_trabajador, LocalDateTime fecha_nacimiento_trabajador, String escolaridad_trabajador,
            String titulo_trabajador, Long id_titulo) {
        this.cedula_trabajador = cedula_trabajador;
        this.nombre_trabajador = nombre_trabajador;
        this.primer_apellido_trabajador = primer_apellido_trabajador;
        this.segundo_apellido_trabajador = segundo_apellido_trabajador;
        this.fecha_inicio_trabajador = fecha_inicio_trabajador;
        this.estado_trabajador = estado_trabajador;
        this.observaciones_trabajador = observaciones_trabajador;
        this.sexo_trabajador = sexo_trabajador;
        this.id_trabajador = id_trabajador;
        this.mail_trabajador = mail_trabajador;
        this.celular_trabajador = celular_trabajador;
        this.fecha_nacimiento_trabajador = fecha_nacimiento_trabajador;
        this.escolaridad_trabajador = escolaridad_trabajador;
        this.titulo_trabajador = titulo_trabajador;
        this.id_titulo = id_titulo;
    }

    public Trabajador(Long cedula_trabajador, String nombre_trabajador, String primer_apellido_trabajador,
            String segundo_apellido_trabajador, LocalDateTime fecha_inicio_trabajador, String estado_trabajador,
            String observaciones_trabajador, String sexo_trabajador, String mail_trabajador, String celular_trabajador,
            LocalDateTime fecha_nacimiento_trabajador, String escolaridad_trabajador, String titulo_trabajador,
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

    public Trabajador(TrabajadorRegistroDTO request) {
        this.cedula_trabajador = request.getCedula_trabajador();
        this.nombre_trabajador = request.getNombre_trabajador();
        this.primer_apellido_trabajador = request.getPrimer_apellido_trabajador();
        this.segundo_apellido_trabajador = request.getSegundo_apellido_trabajador();
        
        // Convertir las fechas de String a LocalDateTime si es necesario
        this.fecha_inicio_trabajador = (request.getFecha_inicio_trabajador()).atStartOfDay();
        this.estado_trabajador = request.getEstado_trabajador();
        this.observaciones_trabajador = request.getObservaciones_trabajador();
        this.sexo_trabajador = request.getSexo_trabajador();
        this.mail_trabajador = request.getMail_trabajador();
        this.celular_trabajador = request.getCelular_trabajador();
        
        // Convertir las fechas de String a LocalDateTime si es necesario
        this.fecha_nacimiento_trabajador = (request.getFecha_nacimiento_trabajador()).atStartOfDay();
        this.escolaridad_trabajador = request.getEscolaridad_trabajador();
        this.titulo_trabajador = request.getTitulo_trabajador();
        this.id_titulo = request.getId_titulo();
    }

    @Column(name = "traced")
    private Long cedula_trabajador;

    @Column(name = "tranom")
    private String nombre_trabajador;

    @Column(name = "traape1")
    private String primer_apellido_trabajador;

    @Column(name = "traape2")
    private String segundo_apellido_trabajador;

    @Column(name = "trafecini")
    private LocalDateTime fecha_inicio_trabajador;

    @Column(name = "traest")
    private String estado_trabajador;

    @Column(name = "traobs")
    private String observaciones_trabajador;

    @Column(name = "trasex")
    private String sexo_trabajador;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "traid")
    private Long id_trabajador;

    @Column(name = "tramail")
    private String mail_trabajador;

    @Column(name = "tracel")
    private String celular_trabajador;

    @Column(name = "trafecnac")
    private LocalDateTime fecha_nacimiento_trabajador;

    @Column(name = "traesc")
    private String escolaridad_trabajador;

    @Column(name = "tratit")
    private String titulo_trabajador;

    @Column(name = "titid")
    private Long id_titulo;

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

    public LocalDateTime getFecha_inicio_trabajador() {
        return fecha_inicio_trabajador;
    }

    public void setFecha_inicio_trabajador(LocalDateTime fecha_inicio_trabajador) {
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

    public Long getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(Long id_trabajador) {
        this.id_trabajador = id_trabajador;
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

    public LocalDateTime getFecha_nacimiento_trabajador() {
        return fecha_nacimiento_trabajador;
    }

    public void setFecha_nacimiento_trabajador(LocalDateTime fecha_nacimiento_trabajador) {
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
        return "Trabajador{" +
                "cedula_trabajador=" + cedula_trabajador +
                ", nombre_trabajador='" + nombre_trabajador + '\'' +
                ", primer_apellido_trabajador='" + primer_apellido_trabajador + '\'' +
                ", segundo_apellido_trabajador='" + segundo_apellido_trabajador + '\'' +
                ", fecha_inicio_trabajador=" + fecha_inicio_trabajador +
                ", estado_trabajador='" + estado_trabajador + '\'' +
                ", observaciones_trabajador='" + observaciones_trabajador + '\'' +
                ", sexo_trabajador='" + sexo_trabajador + '\'' +
                ", id_trabajador=" + id_trabajador +
                ", mail_trabajador='" + mail_trabajador + '\'' +
                ", celular_trabajador='" + celular_trabajador + '\'' +
                ", fecha_nacimiento_trabajador=" + fecha_nacimiento_trabajador +
                ", escolaridad_trabajador='" + escolaridad_trabajador + '\'' +
                ", titulo_trabajador='" + titulo_trabajador + '\'' +
                ", id_titulo=" + id_titulo +
                "}";
    }
}
