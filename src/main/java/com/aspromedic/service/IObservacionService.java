package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Observacion;
import com.aspromedic.response.ObservacionResponseRest;

public interface IObservacionService {
    
    public ResponseEntity<ObservacionResponseRest> findAll();
    
    public ResponseEntity<ObservacionResponseRest> findByCodigoObservacion(String codigo_observacion);
    
    public ResponseEntity<ObservacionResponseRest> findByDescripcionObservacion(String descripcion_observacion);
    
    public ResponseEntity<ObservacionResponseRest> save(Observacion request);

    public ResponseEntity<ObservacionResponseRest> update(Long id_observacion, Observacion request);
    
    public ResponseEntity<ObservacionResponseRest> delete(Long id_observacion);


}
