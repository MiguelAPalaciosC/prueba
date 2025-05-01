package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Escolaridad;

public interface IEscolaridadDao extends CrudRepository<Escolaridad, Long>{

    @Query("SELECT e FROM Escolaridad e ORDER BY e.codigo_escolaridad")
    List<Escolaridad> findAllOrderByCodigoEscolaridad();

    @Query("SELECT e FROM Escolaridad e WHERE LOWER(e.nombre_escolaridad) LIKE LOWER(CONCAT('%', :nombre_escolaridad, '%'))")
    List<Escolaridad> findByNombreEscolaridad(String nombre_escolaridad);

    @Query("SELECT e FROM Escolaridad e WHERE LOWER(e.codigo_escolaridad) = LOWER(:codigo_escolaridad)")
    List<Escolaridad> findByCodigoEscolaridad(String codigo_escolaridad);
    
}
