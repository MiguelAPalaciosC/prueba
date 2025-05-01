package com.aspromedic.response;

import java.util.List;

import com.aspromedic.model.ReciboPago;

public class ReciboPagoResponse {
    
    private List<ReciboPago> recibosPagos;

    public List<ReciboPago> getRecibosPagos() {
        return recibosPagos;
    }

    public void setRecibosPagos(List<ReciboPago> recibosPagos) {
        this.recibosPagos = recibosPagos;
    }
}
