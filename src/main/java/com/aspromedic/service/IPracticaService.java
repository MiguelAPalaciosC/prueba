package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Practica;
import com.aspromedic.response.PracticaResponseRest;

public interface IPracticaService {
    
    public ResponseEntity<PracticaResponseRest> findAll();

    public ResponseEntity<PracticaResponseRest> findById(Long id_practica);
    
    public ResponseEntity<PracticaResponseRest> findByCodigoPractica(String codigo_practica);
    
    public ResponseEntity<PracticaResponseRest> findByDescripcionPractica(String descripcion_practica);
    
    public ResponseEntity<PracticaResponseRest> save(Practica request);

    public ResponseEntity<PracticaResponseRest> update(Long id_practica, Practica request);
    
    public ResponseEntity<PracticaResponseRest> delete(Long id_practica);
}
