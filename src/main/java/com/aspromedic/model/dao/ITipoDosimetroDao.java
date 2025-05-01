package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.TipoDosimetro;

public interface ITipoDosimetroDao extends CrudRepository<TipoDosimetro, Long>{

    @Query("SELECT td FROM TipoDosimetro td ORDER BY td.orden ASC") 
    List<TipoDosimetro> findAllTipoDosimetro();  // Agregar metodo busqueda de todos los tipos de dosimetro en la entidad TipoDosimetro

    @Query("SELECT td FROM TipoDosimetro td WHERE td.nombre = :nombre")
    List<TipoDosimetro> findByNombre(String nombre);  // Agregar metodo busqueda por nombre en la entidad TipoDosimetro
    
}
