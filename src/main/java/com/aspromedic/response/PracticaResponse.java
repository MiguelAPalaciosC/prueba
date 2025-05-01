package com.aspromedic.response;

import java.util.List;

import com.aspromedic.model.Practica;

public class PracticaResponse {
    
    private List<Practica> practicas;

    public List<Practica> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<Practica> practicas) {
        this.practicas = practicas;
    }
}
