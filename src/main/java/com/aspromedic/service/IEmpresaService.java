package com.aspromedic.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Empresa;
import com.aspromedic.response.EmpresaResponseRest;

public interface IEmpresaService {

	public ResponseEntity<EmpresaResponseRest> buscarPorId(Long id);

	public ResponseEntity<EmpresaResponseRest> buscarEmpresas(Pageable page);

	public ResponseEntity<EmpresaResponseRest> findAll();

	public ResponseEntity<EmpresaResponseRest> findByAllTabla(String codigo_interno_empresa, String nit_empresa, String razon_social_empresa, String nombre_ciudad, String telefono_empresa, String celular_empresa, String mail_empresa, String estado_empresa);

	public ResponseEntity<EmpresaResponseRest> buscarPorDepartamento(Long departamento);

	public ResponseEntity<EmpresaResponseRest> buscarPorCiudad(Long ciudad);

	public ResponseEntity<EmpresaResponseRest> buscarCodigoEmpresaDuplicada(Long codigo);
	
	public ResponseEntity<EmpresaResponseRest> buscarUltimaEmpresa();

	public ResponseEntity<EmpresaResponseRest> crear(Empresa request);

	public ResponseEntity<EmpresaResponseRest> actualizar(Empresa request, Long id);

	public ResponseEntity<EmpresaResponseRest> eliminar(Long id);

}
