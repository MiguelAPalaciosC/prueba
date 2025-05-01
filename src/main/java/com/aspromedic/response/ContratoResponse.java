package com.aspromedic.response;

import java.util.List;

import com.aspromedic.model.Contrato;

public class ContratoResponse {
    
    private List<Contrato> contratos;

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
}
