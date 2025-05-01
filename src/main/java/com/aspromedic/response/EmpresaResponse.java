package com.aspromedic.response;

import java.util.List;

import com.aspromedic.model.Empresa;

public class EmpresaResponse {

	private List<Empresa> empresas;

	private Long totalElements;

	private int totalPages;

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
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
