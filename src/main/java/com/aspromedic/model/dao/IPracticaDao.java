package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Practica;

public interface IPracticaDao extends CrudRepository<Practica, Long> {

    @Query("select p from Practica p where LOWER(p.codigo_practica) = LOWER(:codigo_practica)")
    List<Practica> findByCodigoPractica(String codigo_practica);

    @Query("select p from Practica p where LOWER(p.descripcion_practica) like LOWER(CONCAT('%', :descripcion_practica, '%'))")
    List<Practica> findByDescripcionPractica(String descripcion_practica);
    
}
