package com.aspromedic.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contratos")
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;

    public Contrato() {
        super();
    }

    public Contrato(Long id_empresa, LocalDateTime fecha_inicio_contrato, Long valor_factura,
            Long duracion_contrato, LocalDateTime fecha_contratacion_factura, String identificacion_contratacion,
            String periodo_uso_contrato, String factura_a, Collection<TipoDosimetro> tipo_contrato,
            String tipo_contratacion_factura, String estado_contrato, String numero_usuarios, String cuotas_contrato,
            String observaciones_contrato) {
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

    public Contrato(Long id_contrato, Long id_empresa, LocalDateTime fecha_inicio_contrato, Long valor_factura,
            Long duracion_contrato, LocalDateTime fecha_contratacion_factura, String identificacion_contratacion,
            String periodo_uso_contrato, String factura_a, Collection<TipoDosimetro> tipo_contrato,
            String tipo_contratacion_factura, String estado_contrato, String numero_usuarios, String cuotas_contrato,
            String observaciones_contrato) {
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
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "connro")
    private Long id_contrato;

    @Column(name = "empid")
    private Long id_empresa;

    @Column(name = "confec")
    private LocalDateTime fecha_inicio_contrato;

    @Column(name = "conval")
    private Long valor_factura;

    @Column(name = "condura")
    private Long duracion_contrato;

    @Column(name = "confecfac")
    private LocalDateTime fecha_contratacion_factura;

    @Column(name = "connrofac")
    private String identificacion_contratacion;

    @Column(name = "periodo_uso")
    private String periodo_uso_contrato;

    @Column(name = "factura_a")
    private String factura_a;

    // private TipoDosimetro tipo_contrato;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tipo_dosimetro_contrato", joinColumns = @JoinColumn(name = "id_contrato", referencedColumnName = "connro"), inverseJoinColumns = @JoinColumn(name = "id_tipo_dosimetro", referencedColumnName = "id_tipo_dosimetro") // Ajusta                                                                                                                                                                                                                     // TipoDosimetro
    )
    private Collection<TipoDosimetro> tipo_contrato;

    @Column(name = "contipofac")
    private String tipo_contratacion_factura;

    @Column(name = "conest")
    private String estado_contrato;

    @Column(name = "numero_usuarios")
    private String numero_usuarios;

    @Column(name = "cuotas_contrato")
    private String cuotas_contrato;

    @Column(name = "conobser")
    private String observaciones_contrato;

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

    public LocalDateTime getFecha_inicio_contrato() {
        return fecha_inicio_contrato;
    }

    public void setFecha_inicio_contrato(LocalDateTime fecha_inicio_contrato) {
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

    public LocalDateTime getFecha_contratacion_factura() {
        return fecha_contratacion_factura;
    }

    public void setFecha_contratacion_factura(LocalDateTime fecha_contratacion_factura) {
        this.fecha_contratacion_factura = fecha_contratacion_factura;
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

    public String getIdentificacion_contratacion() {
        return identificacion_contratacion;
    }

    public void setIdentificacion_contratacion(String identificacion_contratacion) {
        this.identificacion_contratacion = identificacion_contratacion;
    }

    public Collection<TipoDosimetro> getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(Collection<TipoDosimetro> tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "id_contrato=" + id_contrato +
                ", id_empresa=" + id_empresa +
                ", fecha_inicio_contrato=" + fecha_inicio_contrato +
                ", valor_factura=" + valor_factura +
                ", duracion_contrato=" + duracion_contrato +
                ", fecha_contratacion_factura=" + fecha_contratacion_factura +
                ", identificacion_contratacion='" + identificacion_contratacion + '\'' +
                ", periodo_uso_contrato='" + periodo_uso_contrato + '\'' +
                ", factura_a='" + factura_a + '\'' +
                ", tipo_contratacion_factura='" + tipo_contratacion_factura + '\'' +
                ", estado_contrato='" + estado_contrato + '\'' +
                ", numero_usuarios='" + numero_usuarios + '\'' +
                ", cuotas_contrato='" + cuotas_contrato + '\'' +
                ", observaciones_contrato='" + observaciones_contrato + '\'' +
                '}';
    }

    public String generarNumeroContratoDesdeId(Long ultimoId) {
        String anio = String.valueOf(LocalDate.now().getYear());

        // Extraer últimos 4 dígitos del ID
        long base = ultimoId % 10000; // 5573 en este caso
        long siguiente = base + 1;

        return anio + "-" + String.format("%04d", siguiente);
    }

    public static LocalDate sumarMeses(LocalDate fecha, double mesesDouble) {
        // Sumar meses
        int meses = (int) Math.round(mesesDouble);

        System.out.println("Fecha a sumar: " + fecha);

        LocalDate nuevaFecha = fecha.plusMonths(meses);

        // Verificar el día
        if (fecha.getDayOfMonth() == 1) {
            // Si el día es 1, obtener el último día del mes anterior
            nuevaFecha = nuevaFecha.withDayOfMonth(1).minusDays(1); // Último día del mes anterior
        } else if (fecha.getDayOfMonth() == 15) {
            // Si el día es 14, mantenerlo
            nuevaFecha = nuevaFecha.withDayOfMonth(14);
        } else {
            throw new IllegalArgumentException("Solo se permiten fechas con día 1 o 14.");
        }

        return nuevaFecha;
    }

    public LocalDate getFecha_fin() {
        return sumarMeses(fecha_inicio_contrato.toLocalDate(), duracion_contrato > 0 ? duracion_contrato : 0);
    }

}
