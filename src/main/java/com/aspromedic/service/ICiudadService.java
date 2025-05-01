package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Ciudad;
import com.aspromedic.response.CiudadResponseRest;

public interface ICiudadService {

	public ResponseEntity<CiudadResponseRest> buscarCiudadesPorDepartamento(Long id_departamento);
	
	public ResponseEntity<CiudadResponseRest> buscarCiudadPorId(Long id);

	public ResponseEntity<CiudadResponseRest> buscarCiudadPorNombre(String nombre);

	public ResponseEntity<CiudadResponseRest> listarCiudades();

	public ResponseEntity<CiudadResponseRest> buscarCiudadPorCodigo(String codigo);

	public ResponseEntity<CiudadResponseRest> guardarCiudad(Ciudad ciudad);

	public ResponseEntity<CiudadResponseRest> actualizarCiudad(Long id, Ciudad ciudad);

	public ResponseEntity<CiudadResponseRest> eliminarCiudad(Long id);
}
