package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Ciudad;
import com.aspromedic.model.Departamento;

public interface IDepartamentoDao extends CrudRepository<Departamento, Long>{

    @Query("SELECT d FROM Departamento d ORDER BY d.nombre_departamento ASC")
    List<Departamento> findAllDepartamentos();

    @Query("select d from Departamento d where LOWER(d.nombre_departamento) like LOWER(CONCAT('%', :nombre_departamento, '%'))")
    List<Departamento> findByNombreDepartamento(String nombre_departamento);

    @Query("select d from Departamento d where LOWER(d.codigo_min_departamento) = LOWER(:codigo_min_departamento)")
    List<Departamento> findByCodigoMinDepartamento(String codigo_min_departamento);
}
