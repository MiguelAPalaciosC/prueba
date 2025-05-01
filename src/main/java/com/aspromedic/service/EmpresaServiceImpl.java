package com.aspromedic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspromedic.model.Empresa;
import com.aspromedic.model.dao.IEmpresaDao;
import com.aspromedic.response.EmpresaResponseRest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class EmpresaServiceImpl implements IEmpresaService {

	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	@Autowired
	private IEmpresaDao empresaDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<EmpresaResponseRest> buscarPorId(Long id) {
		log.info("inicio metodo buscarPorId");

		EmpresaResponseRest response = new EmpresaResponseRest();

		List<Empresa> list = new ArrayList<>();

		try {
			Optional<Empresa> empresa = empresaDao.findById(id);

			if (empresa.isPresent()) {
				list.add(empresa.get());

				response.getEmpresaResponse().setEmpresas(list);

				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				log.error("Error en consulta empresa");
				response.setMetadata("Respuesta nok", "-1", "Empresa no encontrada");

				return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
			}
		} catch (Exception e) {
			log.error("Error en consulta empresa");
			response.setMetadata("Respuesta nok", "-1", "Error al consultar empresa");

			return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}
		response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<EmpresaResponseRest> buscarEmpresas(Pageable page) {
		log.info("inicio del metodo buscar empresas");

		EmpresaResponseRest response = new EmpresaResponseRest();

		try {
			// Obtener los resultados paginados
			Page<Empresa> empresaPage = empresaDao.findAllEmpresa(page);

			if (empresaPage == null || empresaPage.isEmpty()) {
				log.warn("No se encontraron empresas");
			}

			response.getEmpresaResponse().setEmpresas(empresaPage.getContent());
			response.getEmpresaResponse().setTotalElements(empresaPage.getTotalElements()); // Aquí obtienes el total
			response.getEmpresaResponse().setTotalPages(empresaPage.getTotalPages());

			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			log.error("Error en consulta empresa: " + e.getMessage(), e);
			response.setMetadata("Respuesta nok", "-1", "Error al consultar empresa");

			return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}
		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<EmpresaResponseRest> crear(Empresa request) {
		log.info("inicio metodo crear()");

		EmpresaResponseRest response = new EmpresaResponseRest();

		List<Empresa> list = new ArrayList<>();

		try {
			Empresa empresaGuardada = empresaDao.save(request);

			if (empresaGuardada != null) {
				list.add(empresaGuardada);

				response.getEmpresaResponse().setEmpresas(list);

				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				log.error("Error en guardar empresa");
				response.setMetadata("Respuesta nok", "-1", "Empresa no guardada");

				return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
			}
		} catch (Exception e) {
			log.error("Error en guardar empresa");
			response.setMetadata("Respuesta nok", "-1", "Error al guardar empresa");

			return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}
		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<EmpresaResponseRest> actualizar(Empresa request, Long id) {
		log.info("inicio metodo actualizar()");

		EmpresaResponseRest response = new EmpresaResponseRest();

		List<Empresa> list = new ArrayList<>();

		try {
			Optional<Empresa> empresaBuscada = empresaDao.findById(id);

			if (empresaBuscada.isPresent()) {
				empresaBuscada.get().setCodigo_interno_empresa(request.getCodigo_interno_empresa());
				empresaBuscada.get().setNit_empresa(request.getNit_empresa());
				empresaBuscada.get().setDigito_verificacion(request.getDigito_verificacion());
				empresaBuscada.get().setRazon_social_empresa(request.getRazon_social_empresa());
				empresaBuscada.get().setDireccion_empresa(request.getDireccion_empresa());
				empresaBuscada.get().setTelefono_empresa(request.getTelefono_empresa());
				empresaBuscada.get().setFax_empresa(request.getFax_empresa());
				empresaBuscada.get().setCelular_empresa(request.getCelular_empresa());
				empresaBuscada.get().setFecha_vin_empresa(request.getFecha_vin_empresa());
				empresaBuscada.get().setEstado_empresa(request.getEstado_empresa());
				empresaBuscada.get().setMail_empresa(request.getMail_empresa());
				empresaBuscada.get().setDepartamento(request.getDepartamento());
				empresaBuscada.get().setObservaciones_empresa(request.getObservaciones_empresa());
				empresaBuscada.get().setCiudad(request.getCiudad());

				Empresa empresaActualizar = empresaDao.save(empresaBuscada.get());

				if (empresaActualizar != null) {
					response.setMetadata("Respuesta ok", "00", "Empresa actualizada");

					list.add(empresaActualizar);
					response.getEmpresaResponse().setEmpresas(list);
				} else {
					log.error("Error en actualizar empresa");
					response.setMetadata("Respuesta nok", "-1", "Empresa no actualizada");

					return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.BAD_REQUEST); // error 400
				}
			} else {
				log.error("Error en actualizar empresa");
				response.setMetadata("Respuesta nok", "-1", "Empresa no actualizada");

				return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
			}
		} catch (Exception e) {
			log.error("Error en actualizar empresa", e.getMessage());

			response.setMetadata("Respuesta nok", "-1", "Error al actualizar la empresa");

			return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}
		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<EmpresaResponseRest> eliminar(Long id) {
		log.info("Inicio metodo eliminar empresa");

		EmpresaResponseRest response = new EmpresaResponseRest();
		try {

			empresaDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Empresa eliminada");
		} catch (Exception e) {
			log.error("Error en eliminar empresa", e.getMessage());

			response.setMetadata("Respuesta nok", "-1", "Error al eliminar la empresa");

			return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}
		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	public ResponseEntity<EmpresaResponseRest> buscarUltimaEmpresa() {
		log.info("inicio del metodo buscar empresas");

		EmpresaResponseRest response = new EmpresaResponseRest();

		try {
			// Obtener los resultados paginados
			Pageable page = PageRequest.of(0, 1);
			Page<Empresa> empresaPage = empresaDao.findAllEmpresa(page);

			if (empresaPage == null || empresaPage.isEmpty()) {
				log.warn("No se encontraron empresas");
			}

			response.getEmpresaResponse().setEmpresas(empresaPage.getContent());
			response.getEmpresaResponse().setTotalElements(empresaPage.getTotalElements()); // Aquí obtienes el total
			response.getEmpresaResponse().setTotalPages(empresaPage.getTotalPages());

			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			log.error("Error en consulta empresa: " + e.getMessage(), e);
			response.setMetadata("Respuesta nok", "-1", "Error al consultar empresa");

			return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}
		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EmpresaResponseRest> buscarCodigoEmpresaDuplicada(Long codigo) {
		log.info("Buscar emporesa por razon social");

		EmpresaResponseRest response = new EmpresaResponseRest();

		try {
			List<Empresa> empresas = empresaDao.findByCodigoDuplicate(codigo);

			response.getEmpresaResponse().setEmpresas(empresas);
			response.setMetadata("Respuesta ok", "00", "Empresa encontrada");
		} catch (Exception e) {
			log.error("Error en busqueda de empresas por razon social: " + e.getMessage(), e);
			response.setMetadata("Respuesta nok", "-1", "Error al buscar empresa");
		}

		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EmpresaResponseRest> findAll() {
		log.info("inicio del metodo buscar empresas");

		EmpresaResponseRest response = new EmpresaResponseRest();

		try {
			// Obtener los resultados paginados
			List<Empresa> empresaPage = (List<Empresa>) empresaDao.findAll();

			if (empresaPage == null || empresaPage.isEmpty()) {
				log.warn("No se encontraron empresas");
			}

			response.getEmpresaResponse().setEmpresas(empresaPage);

			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			log.error("Error en consulta empresa: " + e.getMessage(), e);
			response.setMetadata("Respuesta nok", "-1", "Error al consultar empresa");

			return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}
		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EmpresaResponseRest> buscarPorDepartamento(Long departamento) {
		log.info("buscar empresa por departamento");

		EmpresaResponseRest response = new EmpresaResponseRest();

		try {
			List<Empresa> empresas = empresaDao.findByDepartamentoDuplicate(departamento);

			if (empresas != null && !empresas.isEmpty()) {
				response.getEmpresaResponse().setEmpresas(empresas);
				response.setMetadata("Respuesta ok", "00", "Empresa encontrada");
			} else {
				response.setMetadata("Respuesta nok", "-1", "Empresa no encontrada");

				return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
			}

		} catch (Exception e) {
			log.error("Error en busqueda de empresas por departamento: " + e.getMessage(), e);
			response.setMetadata("Respuesta nok", "-1", "Error al buscar empresa");
			return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}

		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EmpresaResponseRest> buscarPorCiudad(Long ciudad) {
		log.info("buscar empresa por ciudad");

		EmpresaResponseRest response = new EmpresaResponseRest();

		try {
			List<Empresa> empresas = empresaDao.findByCiudadDuplicate(ciudad);

			if (empresas != null && !empresas.isEmpty()) {
				response.getEmpresaResponse().setEmpresas(empresas);
				response.setMetadata("Respuesta ok", "00", "Empresa encontrada");
			} else {
				response.setMetadata("Respuesta nok", "-1", "Empresa no encontrada");

				return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
			}

		} catch (Exception e) {
			log.error("Error en busqueda de empresas por ciudad: " + e.getMessage(), e);
			response.setMetadata("Respuesta nok", "-1", "Error al buscar empresa");
			return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}

		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EmpresaResponseRest> findByAllTabla(String codigo_interno_empresa, String nit_empresa,
			String razon_social_empresa, String nombre_ciudad, String telefono_empresa, String celular_empresa,
			String mail_empresa, String estado_empresa) {
		log.info("Unimplemented method 'findByAllTabla'");

		EmpresaResponseRest response = new EmpresaResponseRest();

		try {
			List<Empresa> empresas = empresaDao.findByAllTabla(codigo_interno_empresa, nit_empresa,
					razon_social_empresa, nombre_ciudad, telefono_empresa, celular_empresa, mail_empresa,
					estado_empresa);

			response.getEmpresaResponse().setEmpresas(empresas);
			response.setMetadata("Respuesta ok", "00", "Empresa encontrada");

		} catch (Exception e) {
			log.error("Error en busqueda de empresas por ciudad: " + e.getMessage(), e);
			response.setMetadata("Respuesta nok", "-1", "Error al buscar empresa");
			return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}

		return new ResponseEntity<EmpresaResponseRest>(response, HttpStatus.OK);
	}

}
