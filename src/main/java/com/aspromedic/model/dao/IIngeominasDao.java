package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Ingeominas;

public interface IIngeominasDao extends CrudRepository<Ingeominas, Long>{
    
    @Query("select i from Ingeominas i where LOWER(i.codigo_geominas) = LOWER(:codigo_geominas)")
    List<Ingeominas> findByCodigoGeominas(String codigo_geominas);

    @Query("select i from Ingeominas i where LOWER(i.descripcion_geominas) like LOWER(CONCAT('%', :descripcion_geominas, '%'))")
    List<Ingeominas> findByDescripcionGeominas(String descripcion_geominas);
}
