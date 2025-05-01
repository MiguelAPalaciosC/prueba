package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.TipoDosimetro;
import com.aspromedic.response.TipoDosimetroResponseRest;

public interface ITipoDosimetroService {
    
    public ResponseEntity<TipoDosimetroResponseRest> findAll();

    public ResponseEntity<TipoDosimetroResponseRest> findById(Long id_tipoDosimetro);

    public ResponseEntity<TipoDosimetroResponseRest> findByNombre(String nombre);

    public ResponseEntity<TipoDosimetroResponseRest> save(TipoDosimetro request);

    public ResponseEntity<TipoDosimetroResponseRest> update(Long id, TipoDosimetro request);

    public ResponseEntity<TipoDosimetroResponseRest> deleteById(Long id);
}
