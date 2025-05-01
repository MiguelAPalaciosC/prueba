package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Titulo;

public interface ITituloDao extends CrudRepository<Titulo, Long>{

    @Query("select t from Titulo t order by t.codigo_titulo")
    List<Titulo> findAllOrderByCodigoTitulo();

    @Query("select t from Titulo t where LOWER(t.codigo_titulo) = LOWER(:codigo_titulo)")
    List<Titulo> findByCodigoTitulo(String codigo_titulo);

    @Query("select t from Titulo t where LOWER(t.descripcion_titulo) like LOWER(CONCAT('%', :descripcion_titulo, '%'))")
    List<Titulo> findByDescripcionTitulo(String descripcion_titulo);
    
}
