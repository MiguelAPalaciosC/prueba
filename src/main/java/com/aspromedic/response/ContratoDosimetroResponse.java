package com.aspromedic.response;

import java.util.List;

import com.aspromedic.model.ContratoDosimetro;

public class ContratoDosimetroResponse {
    private List<ContratoDosimetro> contratoDosimetros;

    public List<ContratoDosimetro> getContratoDosimetros() {
        return contratoDosimetros;
    }

    public void setContratoDosimetros(List<ContratoDosimetro> contratoDosimetros) {
        this.contratoDosimetros = contratoDosimetros;
    }
}
