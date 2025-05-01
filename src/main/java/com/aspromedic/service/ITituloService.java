package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Titulo;
import com.aspromedic.response.TituloResponseRest;

public interface ITituloService {
    
    public ResponseEntity<TituloResponseRest> findAll();

    public ResponseEntity<TituloResponseRest> findById(Long id);

    public ResponseEntity<TituloResponseRest> findByCodigoTitulo(String codigo);

    public ResponseEntity<TituloResponseRest> findByDesccripcionTitulo(String descripcion);

    public ResponseEntity<TituloResponseRest> save(Titulo request);

    public ResponseEntity<TituloResponseRest> deleteById(Long id);

    public ResponseEntity<TituloResponseRest> update(Long id, Titulo response);


}
