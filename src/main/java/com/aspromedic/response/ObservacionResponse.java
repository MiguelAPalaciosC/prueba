package com.aspromedic.response;

import java.util.List;

import com.aspromedic.model.Observacion;

public class ObservacionResponse {
    
    private List<Observacion> observaciones;

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }
}
