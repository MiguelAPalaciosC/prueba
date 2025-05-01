package com.aspromedic.response;

import java.util.List;

import com.aspromedic.model.TipoDosimetro;

public class TipoDosimetroResponse {
    
    private List<TipoDosimetro> tipos;

    public List<TipoDosimetro> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoDosimetro> tipos) {
        this.tipos = tipos;
    }
}
