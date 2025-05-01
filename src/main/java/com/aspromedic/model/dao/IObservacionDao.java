package com.aspromedic.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Observacion;

public interface IObservacionDao extends CrudRepository<Observacion, Long> {
    
    @Query("SELECT o FROM Observacion o WHERE LOWER(o.codigo_observacion) = LOWER(:codigo_observacion)")
    List<Observacion> findByCodigoObservacion(String codigo_observacion);

    @Query("SELECT o FROM Observacion o WHERE LOWER(o.descripcion_observacion) LIKE LOWER(CONCAT('%', :descripcion_observacion, '%'))")
    List<Observacion> findByDescripcionObservacion(String descripcion_observacion);

    Observacion save(Optional<Observacion> observacion);
}
