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

import com.aspromedic.model.Ciudad;
import com.aspromedic.model.Departamento;
import com.aspromedic.model.dao.IDepartamentoDao;
import com.aspromedic.response.CiudadResponseRest;
import com.aspromedic.response.DepartamentoResponseRest;


@Service
public class DepartamentoServiceImpl implements IDepartamentoService{

	private static final Logger log = LoggerFactory.getLogger(DepartamentoServiceImpl.class);
	
	@Autowired
	private IDepartamentoDao departamentoDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<DepartamentoResponseRest> buscarDepartamento() {
		log.info("inicio del metodo buscarDepartamento()");
		
		DepartamentoResponseRest response = new DepartamentoResponseRest();
		try {
			List<Departamento> departamento = departamentoDao.findAllDepartamentos();
			
			response.getDepartamentoResponse().setDepartamentos(departamento);
			
			response.setMetadata("Respuesta Ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error en la consulta de departamentos");
			log.error("Error en la consulta de departamentos: ", e.getMessage());
			
			return new ResponseEntity<DepartamentoResponseRest> (response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepartamentoResponseRest> (response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly =  true)
	public ResponseEntity<DepartamentoResponseRest> buscarDepartamentoPorId(Long id) {
		log.info("inicio del metodo buscar departamento por id");
		
		DepartamentoResponseRest response = new DepartamentoResponseRest();
		List<Departamento> list = new ArrayList<>();
		
		try {
			Optional<Departamento> departamento = departamentoDao.findById(id);
			
			if (departamento.isPresent()) {
				list.add(departamento.get());
				
				response.getDepartamentoResponse().setDepartamentos(list);
				
				response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
			} else {
				response.setMetadata("Respuesta nok", "-1", "Libro no encontrado");
				log.error("Error al buscar libro");
				
				return new ResponseEntity<DepartamentoResponseRest> (response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
			log.error("Error al buscar libro: ", e.getMessage());
			
			return new ResponseEntity<DepartamentoResponseRest> (response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepartamentoResponseRest> (response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DepartamentoResponseRest> buscarDepartamentoPorNombre(String nombre) {
		log.info("ejecucion del methodo buscar por nombre");

		DepartamentoResponseRest response = new DepartamentoResponseRest();

		try {
			List<Departamento> departamentos = departamentoDao.findByNombreDepartamento(nombre);

			if (!departamentos.isEmpty()) {
				response.getDepartamentoResponse().setDepartamentos(departamentos);

				response.setMetadata("Respuesta OK", "00", "Departamentos encontradas");
			} else {
				response.setMetadata("Respuesta nok", "-1", "No se encontraron departamentos");
				log.error("No se encontraron departamentos para el nombre: " + nombre);
				return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
			log.error("Error al buscar Departamentos por nombre: ", e.getMessage());
			return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DepartamentoResponseRest> buscarDepartamentoPorCodigo(String codigo) {
		log.info("ejecucion del methodo buscar por codigo");

		DepartamentoResponseRest response = new DepartamentoResponseRest();

		try {
			List<Departamento> departamentos = departamentoDao.findByCodigoMinDepartamento(codigo);

			if (!departamentos.isEmpty()) {
				response.getDepartamentoResponse().setDepartamentos(departamentos);

				response.setMetadata("Respuesta OK", "00", "Departamentos encontradas");
			} else {
				response.setMetadata("Respuesta nok", "-1", "No se encontraron departamentos");
				log.error("No se encontraron departamentos para el codigo: " + codigo);
				return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
			log.error("Error al buscar Departamentos por codigo: ", e.getMessage());
			return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DepartamentoResponseRest> guardarDepartamento(Departamento request) {
		log.info("metodo guardar departamento");

		DepartamentoResponseRest response = new DepartamentoResponseRest();

		List<Departamento> list = new ArrayList<>();
		try {
			Departamento departamento = departamentoDao.save(request);

			list.add(departamento);
			response.getDepartamentoResponse().setDepartamentos(list);

			response.setMetadata("Respuesta OK", "00", "Departamento guardada");

		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
			log.error("Error al guardar departamento: ", e);
			return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DepartamentoResponseRest> actualizarDepartamento(Long id, Departamento request) {
		log.info("inicio metodo actualizar()");

        DepartamentoResponseRest response = new DepartamentoResponseRest();

        List<Departamento> list = new ArrayList<>();

        try {
            Optional<Departamento> departamentoBuscada = departamentoDao.findById(id);

            if (departamentoBuscada.isPresent()) {
                departamentoBuscada.get().setNombre_departamento(request.getNombre_departamento());
				departamentoBuscada.get().setCodigo_min_departamento(request.getCodigo_min_departamento());

                Departamento departamentoActualizar = departamentoDao.save(departamentoBuscada.get());

                if (departamentoActualizar != null) {
                    response.setMetadata("Respuesta ok", "00", "Departamento actualizada");

                    list.add(departamentoActualizar);
                    response.getDepartamentoResponse().setDepartamentos(list);
                } else {
                    log.error("Error en actualizar departamento");
                    response.setMetadata("Respuesta nok", "-1", "Departamento no actualizada");

                    return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.BAD_REQUEST); // error 400
                }
            } else {
                log.error("Error en actualizar departamento");
                response.setMetadata("Respuesta nok", "-1", "Departamento no actualizada");

                return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.error("Error en actualizar departamento", e.getMessage());

            response.setMetadata("Respuesta nok", "-1", "Error al actualizar la departamento");

            return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
        }
        return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	public ResponseEntity<DepartamentoResponseRest> eliminarDepartamento(Long id) {
		log.info("inicio metodo eliminar()");

		DepartamentoResponseRest response = new DepartamentoResponseRest();

		try {
			departamentoDao.deleteById(id);

			response.setMetadata("Respuesta ok", "00", "Departamento eliminada");
		} catch (Exception e) {
			log.error("Error en eliminar departamento", e.getMessage());

			response.setMetadata("Respuesta nok", "-1", "Error al eliminar la departamento");

			return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}
		return new ResponseEntity<DepartamentoResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

}
