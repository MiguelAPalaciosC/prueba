package com.aspromedic.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.aspromedic.model.Contrato;
import com.aspromedic.model.Dosimetro;
import com.aspromedic.model.TipoDosimetro;
import com.aspromedic.model.Trabajador;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class DosimetroRegistroDTO {

    public DosimetroRegistroDTO(Long id_trabajador, String codigo_dosimetro, Long id_empresa, String estado_dosimetro,
            String practica, String radiacion, String ubicacion, String cargo, String ingeominas, Long periodo_uso,
            String tipo_dosimetro, String destino_dosimetro, Long dosimetro_control, Long dosimetro_ambiental) {
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

    public DosimetroRegistroDTO(Long id_trabajador, String codigo_dosimetro, Long id_empresa, String estado_dosimetro,
            String practica, String radiacion, String ubicacion, String cargo, String ingeominas, Long periodo_uso,
            String tipo_dosimetro, Long id_dosimetro, String destino_dosimetro, Long dosimetro_control,
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

    public DosimetroRegistroDTO(Long id_trabajador, String cedula_trabajador, String nombre_trabajador, String codigo_dosimetro, Long id_empresa, String estado_dosimetro,
            String practica, String radiacion, String ubicacion, String cargo, String ingeominas, Long periodo_uso,
            String tipo_dosimetro, Long id_dosimetro, String destino_dosimetro, Long dosimetro_control,
            Long dosimetro_ambiental) {
        this.id_trabajador = id_trabajador;
        this.cedula_trabajador = cedula_trabajador;
        this.nombre_trabajador = nombre_trabajador;
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

    public DosimetroRegistroDTO() {
    }

    private Long id_trabajador;

    private String cedula_trabajador;

    private String nombre_trabajador;

    private String codigo_dosimetro;

    private Long id_empresa;

    private String estado_dosimetro;

    private String practica;

    private String radiacion;

    private String ubicacion;

    private String cargo;

    private String ingeominas;

    private Long periodo_uso;

    private String tipo_dosimetro;

    private Long id_dosimetro;

    private String destino_dosimetro;

    private Long dosimetro_control;

    private Long dosimetro_ambiental;

    private String numero_contrato;

    private LocalDate fecha_inicio;

    private LocalDate fecha_fin;

    public Long getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(Long id_trabajador) {
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

    public String getPractica() {
        return practica;
    }

    public void setPractica(String practica) {
        this.practica = practica;
    }

    public String getRadiacion() {
        return radiacion;
    }

    public void setRadiacion(String radiacion) {
        this.radiacion = radiacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getIngeominas() {
        return ingeominas;
    }

    public void setIngeominas(String ingeominas) {
        this.ingeominas = ingeominas;
    }

    public Long getPeriodo_uso() {
        return periodo_uso;
    }

    public void setPeriodo_uso(Long periodo_uso) {
        this.periodo_uso = periodo_uso;
    }

    public String getTipo_dosimetro() {
        return tipo_dosimetro;
    }

    public void setTipo_dosimetro(String tipo_dosimetro) {
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
    
    public String getNumero_contrato() {
        return numero_contrato;
    }

    public void setNumero_contrato(String numero_contrato) {
        this.numero_contrato = numero_contrato;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getCedula_trabajador() {
        return cedula_trabajador;
    }

    public void setCedula_trabajador(String cedula_trabajador) {
        this.cedula_trabajador = cedula_trabajador;
    }

    public String getNombre_trabajador() {
        return nombre_trabajador;
    }

    public void setNombre_trabajador(String nombre_trabajador) {
        this.nombre_trabajador = nombre_trabajador;
    }


    public static List<DosimetroRegistroDTO> convertirListaDosimetros(List<Dosimetro> dosimetros) {
        List<DosimetroRegistroDTO> listaDTO = new ArrayList<>();

        for (Dosimetro dosimetro : dosimetros) {
            // Convertir LocalDateTime a LocalDate

            // Crear un nuevo objeto ContratoRegistroDTO
            DosimetroRegistroDTO dto = new DosimetroRegistroDTO(
                    dosimetro.getId_trabajador().getId_trabajador(),
                    dosimetro.getId_trabajador().getCedula_trabajador().toString(),
                    dosimetro.getId_trabajador().getNombre_trabajador() + " " + dosimetro.getId_trabajador().getPrimer_apellido_trabajador(),
                    dosimetro.getCodigo_dosimetro(),
                    dosimetro.getId_empresa(),
                    dosimetro.getEstado_dosimetro(),
                    dosimetro.getPractica().getCodigo_practica(),
                    dosimetro.getRadiacion().getCodigo_radiacion(),
                    dosimetro.getUbicacion().getCodigo_ubicacion(),
                    dosimetro.getCargo().getCodigo_cargo(),
                    dosimetro.getIngeominas().getCodigo_geominas(),
                    dosimetro.getPeriodo_uso(),
                    dosimetro.getTipo_dosimetro().getNombre(),
                    dosimetro.getId_dosimetro(),
                    dosimetro.getDestino_dosimetro(),
                    dosimetro.getDosimetro_control(),
                    dosimetro.getDosimetro_ambiental());

            // Agregar el DTO a la lista
            listaDTO.add(dto);
        }

        return listaDTO;
    }

}
