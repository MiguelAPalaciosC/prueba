package com.aspromedic.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dosimetro")
public class Dosimetro implements Serializable {

    private static final long serialVersionUID = 1L;

    public Dosimetro() {
    }

    public Dosimetro(Trabajador id_trabajador, String codigo_dosimetro, Long id_empresa, String estado_dosimetro,
            Practica practica, Radiacion radiacion, Ubicacion ubicacion, Cargo cargo, Ingeominas ingeominas, Long periodo_uso,
            TipoDosimetro tipo_dosimetro, String destino_dosimetro, Long dosimetro_control, Long dosimetro_ambiental) {
        this.id_trabajador = id_trabajador;
        this.codigo_dosimetro = codigo_dosimetro;
        this.id_empresa = id_empresa;
        this.estado_dosimetro = estado_dosimetro;
        this.practica = practica;
        this.radiacion = radiacion;
        this.ubicacion = ubicacion;
        this.cargo = cargo;
        this.ingeominas = ingeominas;
        this.periodo_uso = periodo_uso;
        this.tipo_dosimetro = tipo_dosimetro;
        this.destino_dosimetro = destino_dosimetro;
        this.dosimetro_control = dosimetro_control;
        this.dosimetro_ambiental = dosimetro_ambiental;
    }

    public Dosimetro(Trabajador id_trabajador, String codigo_dosimetro, Long id_empresa, String estado_dosimetro,
            Practica practica, Radiacion radiacion, Ubicacion ubicacion, Cargo cargo, Ingeominas ingeominas, Long periodo_uso,
            TipoDosimetro tipo_dosimetro, Long id_dosimetro, String destino_dosimetro, Long dosimetro_control,
            Long dosimetro_ambiental) {
        this.id_trabajador = id_trabajador;
        this.codigo_dosimetro = codigo_dosimetro;
        this.id_empresa = id_empresa;
        this.estado_dosimetro = estado_dosimetro;
        this.practica = practica;
        this.radiacion = radiacion;
        this.ubicacion = ubicacion;
        this.cargo = cargo;
        this.ingeominas = ingeominas;
        this.periodo_uso = periodo_uso;
        this.tipo_dosimetro = tipo_dosimetro;
        this.id_dosimetro = id_dosimetro;
        this.destino_dosimetro = destino_dosimetro;
        this.dosimetro_control = dosimetro_control;
        this.dosimetro_ambiental = dosimetro_ambiental;
    }

    @ManyToOne(fetch = FetchType.EAGER) // Cambiar a EAGER si es necesario
	@JoinColumn(name = "traid", referencedColumnName = "traid", nullable = false)
    private Trabajador id_trabajador;

    @Column(name = "doscod")
    private String codigo_dosimetro;

    @Column(name = "empid")
    private Long id_empresa;

    @Column(name = "dosest")
    private String estado_dosimetro;

    @ManyToOne(fetch = FetchType.EAGER) // Cambiar a EAGER si es necesario
	@JoinColumn(name = "pracod", referencedColumnName = "id_practica", nullable = false)
    private Practica practica;

    @ManyToOne(fetch = FetchType.EAGER) // Cambiar a EAGER si es necesario
	@JoinColumn(name = "radcod", referencedColumnName = "id_radiacion", nullable = false)
    private Radiacion radiacion;

    @ManyToOne(fetch = FetchType.EAGER) // Cambiar a EAGER si es necesario
	@JoinColumn(name = "ubicod", referencedColumnName = "id_ubicacion", nullable = false)
    private Ubicacion ubicacion;

    @ManyToOne(fetch = FetchType.EAGER) // Cambiar a EAGER si es necesario
	@JoinColumn(name = "carcod", referencedColumnName = "id_cargo", nullable = false)
    private Cargo cargo;

    @ManyToOne(fetch = FetchType.EAGER) // Cambiar a EAGER si es necesario
	@JoinColumn(name = "geocod", referencedColumnName = "id_geominas", nullable = false)
    private Ingeominas ingeominas;

    @Column(name = "dosperrec")
    private Long periodo_uso;

    @ManyToOne(fetch = FetchType.EAGER) // Cambiar a EAGER si es necesario
	@JoinColumn(name = "dostipo", referencedColumnName = "id_tipo_dosimetro", nullable = false)
    private TipoDosimetro tipo_dosimetro;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dosid")
    private Long id_dosimetro;

    @Column(name = "destino_dosimetro")
    private String destino_dosimetro;

    @Column(name = "dosctl")
    private Long dosimetro_control;

    @Column(name = "dosamb")
    private Long dosimetro_ambiental;

    public Trabajador getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(Trabajador id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getCodigo_dosimetro() {
        return codigo_dosimetro;
    }

    public void setCodigo_dosimetro(String codigo_dosimetro) {
        this.codigo_dosimetro = codigo_dosimetro;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getEstado_dosimetro() {
        return estado_dosimetro;
    }

    public void setEstado_dosimetro(String estado_dosimetro) {
        this.estado_dosimetro = estado_dosimetro;
    }

    public Practica getPractica() {
        return practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }

    public Radiacion getRadiacion() {
        return radiacion;
    }

    public void setRadiacion(Radiacion radiacion) {
        this.radiacion = radiacion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Ingeominas getIngeominas() {
        return ingeominas;
    }

    public void setIngeominas(Ingeominas ingeominas) {
        this.ingeominas = ingeominas;
    }

    public Long getPeriodo_uso() {
        return periodo_uso;
    }

    public void setPeriodo_uso(Long periodo_uso) {
        this.periodo_uso = periodo_uso;
    }

    public TipoDosimetro getTipo_dosimetro() {
        return tipo_dosimetro;
    }

    public void setTipo_dosimetro(TipoDosimetro tipo_dosimetro) {
        this.tipo_dosimetro = tipo_dosimetro;
    }

    public Long getId_dosimetro() {
        return id_dosimetro;
    }

    public void setId_dosimetro(Long id_dosimetro) {
        this.id_dosimetro = id_dosimetro;
    }

    public String getDestino_dosimetro() {
        return destino_dosimetro;
    }

    public void setDestino_dosimetro(String destino_dosimetro) {
        this.destino_dosimetro = destino_dosimetro;
    }

    public Long getDosimetro_control() {
        return dosimetro_control;
    }

    public void setDosimetro_control(Long dosimetro_control) {
        this.dosimetro_control = dosimetro_control;
    }

    public Long getDosimetro_ambiental() {
        return dosimetro_ambiental;
    }

    public void setDosimetro_ambiental(Long dosimetro_ambiental) {
        this.dosimetro_ambiental = dosimetro_ambiental;
    }

    @Override
    public String toString() {
        return "Dosimetro{" +
                "id_trabajador=" + id_trabajador +
                ", codigo_dosimetro='" + codigo_dosimetro + '\'' +
                ", id_empresa=" + id_empresa +
                ", estado_dosimetro='" + estado_dosimetro + '\'' +
                ", practica='" + practica + '\'' +
                ", radiacion='" + radiacion + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", cargo='" + cargo + '\'' +
                ", ingeominas='" + ingeominas + '\'' +
                ", periodo_uso=" + periodo_uso +
                ", tipo_dosimetro='" + tipo_dosimetro + '\'' +
                ", id_dosimetro=" + id_dosimetro +
                ", destino_dosimetro='" + destino_dosimetro + '\'' +
                ", dosimetro_control=" + dosimetro_control +
                ", dosimetro_ambiental=" + dosimetro_ambiental +
                '}';
    }

}