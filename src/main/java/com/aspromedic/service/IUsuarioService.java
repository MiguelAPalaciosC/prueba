package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.dto.UsuarioRegistroDTO;
import com.aspromedic.response.UsuarioResponseRest;

public interface IUsuarioService {

	public ResponseEntity<UsuarioResponseRest> findAll();
	
	public ResponseEntity<UsuarioResponseRest> save(UsuarioRegistroDTO usuario, Integer tipo_usuario);

	public ResponseEntity<UsuarioResponseRest> updateUser(UsuarioRegistroDTO usuario, Long id_usuario);
	
	public ResponseEntity<UsuarioResponseRest> getUserById(Long id);
	
	public ResponseEntity<UsuarioResponseRest> getUserByUsername(String username);
}
