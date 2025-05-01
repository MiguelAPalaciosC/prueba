package com.aspromedic.response;

import java.util.List;

import com.aspromedic.model.Dosimetro;

public class DosimetroResponse {
    
    private List<Dosimetro> dosimetros;

    private Long totalElements;

	private int totalPages;

    public List<Dosimetro> getDosimetros() {
        return dosimetros;
    }

    public void setDosimetros(List<Dosimetro> dosimetros) {
        this.dosimetros = dosimetros;
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
