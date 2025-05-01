package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Contacto;
import com.aspromedic.response.ContactoResponseRest;

public interface IContactoService {

	public ResponseEntity<ContactoResponseRest> findByEmpresa(Long id_empresa);
	
	public ResponseEntity<ContactoResponseRest> save(Contacto request);
	
	public ResponseEntity<ContactoResponseRest> delete(Long id_empresa);
}
