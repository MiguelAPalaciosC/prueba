package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Radiacion;

public interface IRadiacionDao extends CrudRepository<Radiacion, Long> {
    
    @Query("SELECT r FROM Radiacion r WHERE LOWER(r.codigo_radiacion) = LOWER(:codigo_radiacion)")
    List<Radiacion> findByCodigoRadiacion(String codigo_radiacion);

    @Query("SELECT r FROM Radiacion r WHERE LOWER(r.descripcion_radiacion) LIKE LOWER(CONCAT('%',:descripcion_radiacion,'%'))")
    List<Radiacion> findByDescripcionRadiacion(String descripcion_radiacion);
}
