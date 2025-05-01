package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspromedic.model.Ubicacion;

public interface IUbicacionDao extends CrudRepository<Ubicacion, Long> {

    @Query("SELECT u FROM Ubicacion u WHERE LOWER(u.codigo_ubicacion) = LOWER(:codigo_ubicacion)")
    List<Ubicacion> findByCodigoUbicacion(@Param("codigo_ubicacion") String codigo_ubicacion);

    @Query("select u from Ubicacion u where LOWER(u.descripcion_ubicacion) like LOWER(CONCAT('%', :descripcion_ubicacion, '%'))")
    List<Ubicacion> findByDescripcionUbicacion(@Param("descripcion_ubicacion") String descripcionUbicacion);

}
