package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Ubicacion;
import com.aspromedic.response.UbicacionResponseRest;

public interface IUbicacionService {
    

    public ResponseEntity<UbicacionResponseRest> findAll();

    public ResponseEntity<UbicacionResponseRest> findById(Long id_ubicacion);

    public ResponseEntity<UbicacionResponseRest> findByCodigoUbicacion(String codigo_ubicacion);

    public ResponseEntity<UbicacionResponseRest> findByDescripcionUbicacion(String descripcion_ubicacion);

    public ResponseEntity<UbicacionResponseRest> save(Ubicacion request);

    public ResponseEntity<UbicacionResponseRest> update(Long id, Ubicacion request);

    public ResponseEntity<UbicacionResponseRest> delete(Long id);
}
