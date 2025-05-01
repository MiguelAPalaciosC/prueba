package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Escolaridad;
import com.aspromedic.response.EscolaridadResponseRest;
import com.aspromedic.response.ResponseRest;

public interface IEscolaridadService {
    
    public ResponseEntity<EscolaridadResponseRest> findAll();

    public ResponseEntity<EscolaridadResponseRest> findById(Long id);

    public ResponseEntity<EscolaridadResponseRest> findByNombreEscolaridad(String nombre);

    public ResponseEntity<EscolaridadResponseRest> findByCodigoEscolaridad(String codigo);

    public ResponseEntity<EscolaridadResponseRest> save(Escolaridad request);

    public ResponseEntity<EscolaridadResponseRest> update(Long id, Escolaridad request);

    public ResponseEntity<EscolaridadResponseRest> deleteById(Long id);

}
