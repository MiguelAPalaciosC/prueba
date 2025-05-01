package com.aspromedic.model;

import java.io.Serializable;
import java.util.Objects;

public class ContratoDosimetroId implements Serializable {
    private Long id_contrato_dosimetro;
    private Long id_dosimetro;

    public ContratoDosimetroId() {}

    public ContratoDosimetroId(Long id_contrato_dosimetro, Long id_dosimetro) {
        this.id_contrato_dosimetro = id_contrato_dosimetro;
        this.id_dosimetro = id_dosimetro;
    }

    // Getters y Setters

    public Long getId_contrato_dosimetro() {
        return id_contrato_dosimetro;
    }

    public void setId_contrato_dosimetro(Long id_contrato_dosimetro) {
        this.id_contrato_dosimetro = id_contrato_dosimetro;
    }

    public Long getId_dosimetro() {
        return id_dosimetro;
    }

    public void setId_dosimetro(Long id_dosimetro) {
        this.id_dosimetro = id_dosimetro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContratoDosimetroId)) return false;
        ContratoDosimetroId that = (ContratoDosimetroId) o;
        return Objects.equals(id_contrato_dosimetro, that.id_contrato_dosimetro) &&
               Objects.equals(id_dosimetro, that.id_dosimetro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_contrato_dosimetro, id_dosimetro);
    }
}
