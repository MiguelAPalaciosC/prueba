package com.aspromedic.response;

import java.util.List;

import com.aspromedic.model.SeguimientoFactura;

public class SeguimientoFacturaResponse {
    
    private List<SeguimientoFactura> seguimientoFacturas;

    public List<SeguimientoFactura> getSeguimientoFacturas() {
        return seguimientoFacturas;
    }

    public void setSeguimientoFacturas(List<SeguimientoFactura> seguimientoFacturas) {
        this.seguimientoFacturas = seguimientoFacturas;
    }
}
