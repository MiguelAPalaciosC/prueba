package com.aspromedic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspromedic.dto.UsuarioRegistroDTO;
import com.aspromedic.model.Ciudad;
import com.aspromedic.model.Rol;
import com.aspromedic.model.Usuario;
import com.aspromedic.model.dao.IUsuarioDao;
import com.aspromedic.response.UsuarioResponseRest;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired(required = true)
	private IUsuarioDao usuarioDao;

	@Override
	public ResponseEntity<UsuarioResponseRest> save(UsuarioRegistroDTO usuarioDTO, Integer tipo_usuario) {
		log.info("inicio metodo crear()");

		UsuarioResponseRest response = new UsuarioResponseRest();

		List<Usuario> list = new ArrayList<>();

		try {
			String rol = "";
			if (tipo_usuario == 1) {
				rol = "ADMIN";
			} else {
				rol = "USER";
			}
			Usuario usuarioGuardado = usuarioDao.save(new Usuario(usuarioDTO.getNombre(), usuarioDTO.getUsername(),
					usuarioDTO.getCargo(), 1, usuarioDTO.getPassword(), Arrays.asList(new Rol(rol))));

			if (usuarioGuardado != null) {
				list.add(usuarioGuardado);
				response.getUsuarioResponse().setUsuario(list);
			} else {
				log.error("Error en guardar usuario");
				response.setMetadata("Respuesta nok", "-1", "Usuario no guardada");

				return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.BAD_REQUEST); // error 400
			}
		} catch (Exception e) {
			log.error("Error en crear usuario");
			response.setMetadata("Respuesta nok", "-1", "Error al crear usuario");

			return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}

		response.setMetadata("Respuesta ok", "00", "Usuario creado");
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<UsuarioResponseRest> getUserById(Long id) {
		log.info("inicio metodo buscarPorId()");

		UsuarioResponseRest response = new UsuarioResponseRest();

		List<Usuario> list = new ArrayList<>();

		try {

			Optional<Usuario> usuario = usuarioDao.findById(id);

			if (usuario.isPresent()) {
				list.add(usuario.get());
				response.getUsuarioResponse().setUsuario(list);

			} else {
				log.error("Error en consulta usuario");
				response.setMetadata("Respuesta nok", "-1", "Usuario no encontrada");

				return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
			}
		} catch (Exception e) {
			log.error("Error en consulta categoria");
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");

			return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}
		response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<UsuarioResponseRest> getUserByUsername(String username) {
		log.info("inicio metodo buscarPorId()");

		UsuarioResponseRest response = new UsuarioResponseRest();

		try {

			List<Usuario> usuario = usuarioDao.findByUsername(username);

			if (!usuario.isEmpty()) {
				response.getUsuarioResponse().setUsuario(usuario);

			} else {
				log.error("Error en consulta usuario");
				response.setMetadata("Respuesta nok", "-1", "Usuario no encontrada");

				return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
			}
		} catch (Exception e) {
			log.error("Error en consulta categoria");
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");

			return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
		}
		response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	public ResponseEntity<UsuarioResponseRest> findAll() {
		log.info("findAll usuarios");

		UsuarioResponseRest response = new UsuarioResponseRest();

		try {
			List<Usuario> list = (List<Usuario>) usuarioDao.findAll();

			if (!list.isEmpty()) {
				response.getUsuarioResponse().setUsuario(list);

				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

			} else {
				log.error("Error en consulta usuarios");
				response.setMetadata("Respuesta nok", "-1", "No hay usuarios");
			}
		} catch (Exception e) {
			log.error("Error en consulta usuarios");
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
		}

		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	public ResponseEntity<UsuarioResponseRest> updateUser(UsuarioRegistroDTO usuario, Long id_usuario) {
		log.info("metodo actualizar usuario");

		UsuarioResponseRest response = new UsuarioResponseRest();

		List<Usuario> list = new ArrayList<>();

		try {
			Optional<Usuario> usuarioOptional = usuarioDao.findById(id_usuario);

			if (usuarioOptional.isPresent()) {
				usuarioOptional.get().setNombre(usuario.getNombre());
				usuarioOptional.get().setUsername(usuario.getUsername());
				usuarioOptional.get().setCargo(usuario.getCargo());

				if (!usuario.getPassword().isEmpty()) {
					usuarioOptional.get().setPassword(usuario.getPassword());
				}

				usuarioOptional.get().setEstado(usuario.getEstado());

				Usuario usuarioActualizado = usuarioDao.save(usuarioOptional.get());

				if (usuarioActualizado != null) {
					list.add(usuarioActualizado);
					response.getUsuarioResponse().setUsuario(list);

					response.setMetadata("Respuesta ok", "00", "Usuario actualizado");
				} else {
					log.error("Error en actualizar usuario");
					response.setMetadata("Respuesta nok", "-1", "Usuario no actualizado");
					return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.BAD_REQUEST); // error 400
				}
			} else {
				log.error("Error en consulta usuario");
				response.setMetadata("Respuesta nok", "-1", "Usuario no encontrado");
				return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
			}
		} catch (Exception e) {
			log.error("Error en actualizar usuario");
			response.setMetadata("Respuesta nok", "-1", "Error al actualizar usuario");
			return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 400
		}

		return new ResponseEntity<UsuarioResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

}
