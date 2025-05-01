package com.aspromedic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspromedic.model.Cargo;
import com.aspromedic.model.Ciudad;
import com.aspromedic.model.Contacto;
import com.aspromedic.model.dao.ICiudadDao;
import com.aspromedic.response.CargoResponseRest;
import com.aspromedic.response.CiudadResponseRest;

@Service
public class CiudadServiceImpl implements ICiudadService {

	private Logger log = LoggerFactory.getLogger(CiudadServiceImpl.class);

	@Autowired
	private ICiudadDao ciudadDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CiudadResponseRest> buscarCiudadesPorDepartamento(Long id_departamento) {
		log.info("ejecucion del methodo buscar por departamento");

		CiudadResponseRest response = new CiudadResponseRest();

		try {
			List<Ciudad> ciudades = ciudadDao.buscarPorDepartamento(id_departamento);

			if (!ciudades.isEmpty()) {
				response.getCiudadResponse().setCiudades(ciudades);

				response.setMetadata("Respuesta OK", "00", "Ciudades encontradas");
			} else {
				response.setMetadata("Respuesta nok", "-1", "No se encontraron libros para esta categoría");
				log.error("No se encontraron ciudades para la departamento ID: " + id_departamento);
				return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
			log.error("Error al buscar Ciudades por departamento: ", e);
			return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CiudadResponseRest> buscarCiudadPorId(Long id) {
		log.info("ejecucion del metodo buscar por id");

		CiudadResponseRest response = new CiudadResponseRest();

		List<Ciudad> list = new ArrayList<>();

		try {
			Optional<Ciudad> ciudad = ciudadDao.findById(id);

			if (ciudad.isPresent()) {
				list.add(ciudad.get());
				response.getCiudadResponse().setCiudades(list);

				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				log.error("Error en consulta ciudad");
				response.setMetadata("Respuesta nok", "-1", "Ciudad no encontrada");

				return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
			}
		} catch (Exception e) {
			log.error("Error en consulta ciudad");
			response.setMetadata("Respuesta nok", "-1", "Ciudad no encontrada");

			return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 404
		}

		return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	public ResponseEntity<CiudadResponseRest> buscarCiudadPorNombre(String nombre) {
		log.info("ejecucion del methodo buscar por nombre");

		CiudadResponseRest response = new CiudadResponseRest();

		try {
			List<Ciudad> ciudades = ciudadDao.findByNombreCiudad(nombre);

			if (!ciudades.isEmpty()) {
				response.getCiudadResponse().setCiudades(ciudades);

				response.setMetadata("Respuesta OK", "00", "Ciudades encontradas");
			} else {
				response.setMetadata("Respuesta nok", "-1", "No se encontraron ciudades");
				log.error("No se encontraron ciudades para el nombre: " + nombre);
				return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
			log.error("Error al buscar Ciudades por nombre: ", e.getMessage());
			return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CiudadResponseRest> listarCiudades() {
		log.info("ejecucion del methodo buscar todas las ciudades");

		CiudadResponseRest response = new CiudadResponseRest();

		try {
			List<Ciudad> ciudades = (List<Ciudad>) ciudadDao.findAll();

			if (!ciudades.isEmpty()) {
				response.getCiudadResponse().setCiudades(ciudades);

				response.setMetadata("Respuesta OK", "00", "Ciudades encontradas");
			} else {
				response.setMetadata("Respuesta nok", "-1", "No se encontraron ciudades");
				log.error("No se encontraron ciudades ");
				return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
			log.error("Error al buscar Ciudades: ", e);
			return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CiudadResponseRest> buscarCiudadPorCodigo(String codigo) {
		log.info("ejecucion del methodo buscar por codigo");

		CiudadResponseRest response = new CiudadResponseRest();

		try {
			List<Ciudad> ciudades = ciudadDao.findByCodigoMinCiudad(codigo);

			if (!ciudades.isEmpty()) {
				response.getCiudadResponse().setCiudades(ciudades);

				response.setMetadata("Respuesta OK", "00", "Ciudades encontradas");
			} else {
				response.setMetadata("Respuesta nok", "-1", "No se encontraron ciudades");
				log.error("No se encontraron ciudades para el codigo: " + codigo);
				return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
			log.error("Error al buscar Ciudades por codigo: ", e.getMessage());
			return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CiudadResponseRest> guardarCiudad(Ciudad request) {
		log.info("metodo guardar ciudad");

		CiudadResponseRest response = new CiudadResponseRest();

		List<Ciudad> list = new ArrayList<>();
		try {
			Ciudad ciudad = ciudadDao.save(request);

			list.add(ciudad);
			response.getCiudadResponse().setCiudades(list);

			response.setMetadata("Respuesta OK", "00", "Ciudad guardada");

		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
			log.error("Error al guardar la ciudad: ", e);
			return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CiudadResponseRest> actualizarCiudad(Long id, Ciudad request) {
		log.info("inicio metodo actualizar()");

		CiudadResponseRest response = new CiudadResponseRest();

		List<Ciudad> list = new ArrayList<>();

		try {
			Optional<Ciudad> ciudadBuscada = ciudadDao.findById(id);

			if (ciudadBuscada.isPresent()) {
				Ciudad ciudad = ciudadBuscada.get();

				// Validaciones (puedes agregar más según sea necesario)
				if (request.getDepartamento() != null) {
					ciudad.setDepartamento(request.getDepartamento());
				}
				if (request.getNombre_ciudad() != null) {
					ciudad.setNombre_ciudad(request.getNombre_ciudad());
				}
				if (request.getCodigo_min_ciudad() != null) {
					ciudad.setCodigo_min_ciudad(request.getCodigo_min_ciudad());
				}

				// Guardar la ciudad actualizada
				Ciudad ciudadActualizar = ciudadDao.save(ciudad);

				if (ciudadActualizar != null) {
					response.setMetadata("Respuesta ok", "00", "Ciudad actualizada");

					list.add(ciudadActualizar);
					response.getCiudadResponse().setCiudades(list);
				} else {
					log.error("Error en actualizar ciudad");
					response.setMetadata("Respuesta nok", "-1", "Ciudad no actualizada");

					return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.BAD_REQUEST); // error 400
				}
			} else {
				log.error("Error en actualizar ciudad");
				response.setMetadata("Respuesta nok", "-1", "Ciudad no actualizada");

				return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
			}
		} catch (Exception e) {
			log.error("Error en actualizar ciudad", e);

			response.setMetadata("Respuesta nok", "-1", "Error al actualizar la ciudad");

			return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}
		return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	public ResponseEntity<CiudadResponseRest> eliminarCiudad(Long id) {
		log.info("metodo eliminar ciudad");

		CiudadResponseRest response = new CiudadResponseRest();

		try {
			ciudadDao.deleteById(id);

			response.setMetadata("Respuesta ok", "00", "Ciudad eliminada");
		} catch (Exception e) {
			log.error("Error al eliminar ciudad", e.getMessage());
			response.setMetadata("Respuesta nok", "-1", "Error al eliminar la ciudad");

			return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}

		return new ResponseEntity<CiudadResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

}
