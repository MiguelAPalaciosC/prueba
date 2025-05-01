package com.aspromedic.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.aspromedic.model.Contrato;
import com.aspromedic.model.TipoDosimetro;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ContratoRegistroDTO {

    public ContratoRegistroDTO() {
        super();
    }

    public ContratoRegistroDTO(Long id_empresa, LocalDate fecha_inicio_contrato, Long valor_factura,
            Long duracion_contrato, LocalDate fecha_contratacion_factura, String identificacion_contratacion,
            String periodo_uso_contrato, String factura_a, String tipo_contrato, String tipo_contratacion_factura,
            String estado_contrato, String numero_usuarios, String cuotas_contrato, String observaciones_contrato) {
        this.id_empresa = id_empresa;
        this.fecha_inicio_contrato = fecha_inicio_contrato;
        this.valor_factura = valor_factura;
        this.duracion_contrato = duracion_contrato;
        this.fecha_contratacion_factura = fecha_contratacion_factura;
        this.identificacion_contratacion = identificacion_contratacion;
        this.periodo_uso_contrato = periodo_uso_contrato;
        this.factura_a = factura_a;
        this.tipo_contrato = tipo_contrato;
        this.tipo_contratacion_factura = tipo_contratacion_factura;
        this.estado_contrato = estado_contrato;
        this.numero_usuarios = numero_usuarios;
        this.cuotas_contrato = cuotas_contrato;
        this.observaciones_contrato = observaciones_contrato;
    }

    public ContratoRegistroDTO(Long id_contrato, Long id_empresa, LocalDate fecha_inicio_contrato, Long valor_factura,
            Long duracion_contrato, LocalDate fecha_contratacion_factura, String identificacion_contratacion,
            String periodo_uso_contrato, String factura_a, String tipo_contrato, String tipo_contratacion_factura,
            String estado_contrato, String numero_usuarios, String cuotas_contrato, String observaciones_contrato) {
        this.id_contrato = id_contrato;
        this.id_empresa = id_empresa;
        this.fecha_inicio_contrato = fecha_inicio_contrato;
        this.valor_factura = valor_factura;
        this.duracion_contrato = duracion_contrato;
        this.fecha_contratacion_factura = fecha_contratacion_factura;
        this.identificacion_contratacion = identificacion_contratacion;
        this.periodo_uso_contrato = periodo_uso_contrato;
        this.factura_a = factura_a;
        this.tipo_contrato = tipo_contrato;
        this.tipo_contratacion_factura = tipo_contratacion_factura;
        this.estado_contrato = estado_contrato;
        this.numero_usuarios = numero_usuarios;
        this.cuotas_contrato = cuotas_contrato;
        this.observaciones_contrato = observaciones_contrato;
        this.fecha_fin = sumarMeses(fecha_inicio_contrato, duracion_contrato);
    }

    private Long id_contrato;

    private Long id_empresa;

    private LocalDate fecha_inicio_contrato;

    private Long valor_factura;

    private Long duracion_contrato;

    private LocalDate fecha_contratacion_factura;

    private String identificacion_contratacion;

    private String periodo_uso_contrato;

    private String factura_a;

    private String tipo_contrato;

    private String tipo_contratacion_factura;

    private String estado_contrato;

    private String numero_usuarios;

    private Integer usuario_asignados;

    private String cuotas_contrato;

    private String observaciones_contrato;

    private LocalDate fecha_fin;

    public Long getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(Long id_contrato) {
        this.id_contrato = id_contrato;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public LocalDate getFecha_inicio_contrato() {
        return fecha_inicio_contrato;
    }

    public void setFecha_inicio_contrato(LocalDate fecha_inicio_contrato) {
        this.fecha_inicio_contrato = fecha_inicio_contrato;
    }

    public Long getValor_factura() {
        return valor_factura;
    }

    public void setValor_factura(Long valor_factura) {
        this.valor_factura = valor_factura;
    }

    public Long getDuracion_contrato() {
        return duracion_contrato;
    }

    public void setDuracion_contrato(Long duracion_contrato) {
        this.duracion_contrato = duracion_contrato;
    }

    public LocalDate getFecha_contratacion_factura() {
        return fecha_contratacion_factura;
    }

    public void setFecha_contratacion_factura(LocalDate fecha_contratacion_factura) {
        this.fecha_contratacion_factura = fecha_contratacion_factura;
    }

    public String getIdentificacion_contratacion() {
        return identificacion_contratacion;
    }

    public void setIdentificacion_contratacion(String identificacion_contratacion) {
        this.identificacion_contratacion = identificacion_contratacion;
    }

    public String getPeriodo_uso_contrato() {
        return periodo_uso_contrato;
    }

    public void setPeriodo_uso_contrato(String periodo_uso_contrato) {
        this.periodo_uso_contrato = periodo_uso_contrato;
    }

    public String getFactura_a() {
        return factura_a;
    }

    public void setFactura_a(String factura_a) {
        this.factura_a = factura_a;
    }

    public String getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(String tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

    public String getTipo_contratacion_factura() {
        return tipo_contratacion_factura;
    }

    public void setTipo_contratacion_factura(String tipo_contratacion_factura) {
        this.tipo_contratacion_factura = tipo_contratacion_factura;
    }

    public String getEstado_contrato() {
        return estado_contrato;
    }

    public void setEstado_contrato(String estado_contrato) {
        this.estado_contrato = estado_contrato;
    }

    public String getNumero_usuarios() {
        return numero_usuarios;
    }

    public void setNumero_usuarios(String numero_usuarios) {
        this.numero_usuarios = numero_usuarios;
    }

    public String getCuotas_contrato() {
        return cuotas_contrato;
    }

    public void setCuotas_contrato(String cuotas_contrato) {
        this.cuotas_contrato = cuotas_contrato;
    }

    public String getObservaciones_contrato() {
        return observaciones_contrato;
    }

    public void setObservaciones_contrato(String observaciones_contrato) {
        this.observaciones_contrato = observaciones_contrato;
    }

    public Integer getUsuario_asignados() {
        return usuario_asignados;
    }

    public void setUsuario_asignados(Integer usuario_asignados) {
        this.usuario_asignados = usuario_asignados;
    }

    public static LocalDate sumarMeses(LocalDate fecha, double mesesDouble) {
        // Redondear meses
        int meses = (int) Math.round(mesesDouble);
    
        System.out.println("Fecha a sumar: " + fecha);
    
        // Ajustar el día si no es válido (1 o 14)
        int dia = fecha.getDayOfMonth();
        if (dia != 1 && dia != 14) {
            // Redondear al día más cercano permitido
            dia = (dia <= 7) ? 1 : 14;
            fecha = fecha.withDayOfMonth(dia);
        }
    
        // Sumar meses
        LocalDate nuevaFecha = fecha.plusMonths(meses);
    
        // Aplicar reglas según el día original ajustado
        if (fecha.getDayOfMonth() == 1) {
            // Si es 1, mover al último día del mes anterior
            nuevaFecha = nuevaFecha.withDayOfMonth(1).minusDays(1);
        } else if (fecha.getDayOfMonth() == 14) {
            // Si es 14, mantenerlo
            nuevaFecha = nuevaFecha.withDayOfMonth(14);
        }
    
        return nuevaFecha;
    }
    

    public LocalDate getFecha_fin() {
        return fecha_fin = sumarMeses(fecha_inicio_contrato, duracion_contrato > 0 ? duracion_contrato : 0);
    }

    // Método para validar si la fecha_fin está vencida
    public boolean isFechaFinVencida() {
        LocalDate fechaActual = LocalDate.now();
        return fecha_fin.isBefore(fechaActual) || fecha_fin.isEqual(fechaActual);
    }

    public static List<ContratoRegistroDTO> convertirListaContratos(List<Contrato> contratos) {
        List<ContratoRegistroDTO> listaDTO = new ArrayList<>();

        for (Contrato contrato : contratos) {
            // Convertir LocalDateTime a LocalDate
            LocalDate fechaInicio = contrato.getFecha_inicio_contrato().toLocalDate();
            LocalDate fechaContratacion = null;
            if (contrato.getFecha_contratacion_factura() != null) {
                fechaContratacion = contrato.getFecha_contratacion_factura().toLocalDate();
            }
            
            String tipos = contrato.getTipo_contrato().stream()
                    .map(TipoDosimetro::getNombre) // reemplaza 'getNombre' por el método correcto
                    .collect(Collectors.joining(", "));

            // Crear un nuevo objeto ContratoRegistroDTO
            ContratoRegistroDTO dto = new ContratoRegistroDTO(
                    contrato.getId_contrato(),
                    contrato.getId_empresa(),
                    fechaInicio,
                    contrato.getValor_factura(),
                    contrato.getDuracion_contrato(),
                    fechaContratacion,
                    contrato.getIdentificacion_contratacion(),
                    contrato.getPeriodo_uso_contrato(),
                    contrato.getFactura_a(),
                    tipos, // Suponiendo que TipoDosimetro tiene un método toString()
                    contrato.getTipo_contratacion_factura(),
                    contrato.getEstado_contrato(),
                    contrato.getNumero_usuarios(),
                    contrato.getCuotas_contrato(),
                    contrato.getObservaciones_contrato());

            // Agregar el DTO a la lista
            listaDTO.add(dto);
        }

        return listaDTO;
    }

}
