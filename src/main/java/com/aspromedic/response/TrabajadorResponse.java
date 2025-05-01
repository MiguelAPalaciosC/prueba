package com.aspromedic.response;

import java.util.List;

import com.aspromedic.model.Trabajador;

public class TrabajadorResponse {

    private List<Trabajador> trabajadores;

    private Long totalElements;

    private int totalPages;

    public List<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(List<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
