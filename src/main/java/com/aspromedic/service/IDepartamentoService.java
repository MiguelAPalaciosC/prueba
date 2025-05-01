package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Departamento;
import com.aspromedic.response.DepartamentoResponseRest;

public interface IDepartamentoService {

	public ResponseEntity<DepartamentoResponseRest> buscarDepartamento();
	
	public ResponseEntity<DepartamentoResponseRest> buscarDepartamentoPorId(Long id);

	public ResponseEntity<DepartamentoResponseRest> buscarDepartamentoPorNombre(String nombre);

	public ResponseEntity<DepartamentoResponseRest> buscarDepartamentoPorCodigo(String codigo);

	public ResponseEntity<DepartamentoResponseRest> guardarDepartamento(Departamento departamento);

	public ResponseEntity<DepartamentoResponseRest> actualizarDepartamento(Long id, Departamento departamento);

	public ResponseEntity<DepartamentoResponseRest> eliminarDepartamento(Long id);
}
