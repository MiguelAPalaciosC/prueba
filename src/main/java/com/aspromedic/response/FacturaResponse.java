package com.aspromedic.response;

import java.util.List;

import com.aspromedic.model.Factura;

public class FacturaResponse {
    
    private List<Factura> facturas;

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
}
