package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Radiacion;
import com.aspromedic.response.RadiacionResponseRest;

public interface IRadiacionService {
    
    public ResponseEntity<RadiacionResponseRest> findAll();

    public ResponseEntity<RadiacionResponseRest> findById(Long id_radiacion);

    public ResponseEntity<RadiacionResponseRest> findByCodigo(String codigo_radiacion);

    public ResponseEntity<RadiacionResponseRest> findByDescripcion(String descripcion_radiacion);

    public ResponseEntity<RadiacionResponseRest> save(Radiacion request);

    public ResponseEntity<RadiacionResponseRest> update(Long id, Radiacion request);

    public ResponseEntity<RadiacionResponseRest> delete(Long id);
}
