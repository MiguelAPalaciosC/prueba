package com.aspromedic.response;

import java.util.List;

import com.aspromedic.model.Ubicacion;

public class UbicacionResponse {
    
    private List<Ubicacion> ubicaciones;

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }
}
