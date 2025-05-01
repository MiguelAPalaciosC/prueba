package com.aspromedic.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aspromedic.model.Contacto;
import com.aspromedic.model.dao.IContactoDao;
import com.aspromedic.response.ContactoResponseRest;

@Service
public class ContactoServiceImpl implements IContactoService {

	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	@Autowired
	private IContactoDao contactoDao;

	@Override
	public ResponseEntity<ContactoResponseRest> findByEmpresa(Long id_empresa) {
		log.info("ingreso al metodo buscar por empresa()");

		ContactoResponseRest response = new ContactoResponseRest();

		try {
			List<Contacto> contactos = contactoDao.findByIdEmpresa(id_empresa);

			log.info("Busco los contactos");

			response.getContacto().setContactos(contactos);

			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			log.error("Error en consulta contactos, ", e.getMessage());

			response.setMetadata("Respuesta nok", "-1", "Error al consultar contactos de empresa");

			return new ResponseEntity<ContactoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}

		return new ResponseEntity<ContactoResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	public ResponseEntity<ContactoResponseRest> save(Contacto request) {
		log.info("metodo guardar contactos");

		ContactoResponseRest response = new ContactoResponseRest();

		List<Contacto> list = new ArrayList<>();
		try {
			Contacto contacto = contactoDao.save(request);

			if (contacto != null) {
				list.add(contacto);

				response.getContacto().setContactos(list);

			} else {
				log.error("Error en consulta contacto");
				response.setMetadata("Respuesta nok", "-1", "Contacto no guardado");

				return new ResponseEntity<ContactoResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
			}
		} catch (Exception e) {
			log.error("Error en consulta contacto", e.getMessage());
			response.setMetadata("Respuesta nok", "-1", "Error al guardar contacto");

			return new ResponseEntity<ContactoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}

		response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<ContactoResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	public ResponseEntity<ContactoResponseRest> delete(Long id_empresa) {
		log.info("inicio metodo eliminar contactos");

		ContactoResponseRest response = new ContactoResponseRest();

		try {
			contactoDao.deleteByEmpresa(id_empresa);

			response.setMetadata("Respuesta ok", "00", "Contactos eliminados");
		} catch (Exception e) {
			log.error("Error en eliminar contactos ", e);

			response.setMetadata("Respuesta nok", "-1", "Error al eliminar los contactos");

			return new ResponseEntity<ContactoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}
		return new ResponseEntity<ContactoResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

}
