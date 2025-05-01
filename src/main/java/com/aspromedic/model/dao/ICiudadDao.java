package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Cargo;
import com.aspromedic.model.Ciudad;

public interface ICiudadDao extends CrudRepository<Ciudad, Long> {

    @Query("SELECT c FROM Ciudad c WHERE CAST(c.departamento.codigo_departamento AS string) = :id_departamento")
    List<Ciudad> buscarPorDepartamento(Long id_departamento);

    @Query("select c from Ciudad c where LOWER(c.nombre_ciudad) like LOWER(CONCAT('%', :nombre_ciudad, '%'))")
    List<Ciudad> findByNombreCiudad(String nombre_ciudad);

    @Query("select c from Ciudad c where LOWER(c.codigo_min_ciudad) = LOWER(:codigo_min_ciudad)")
    List<Ciudad> findByCodigoMinCiudad(String codigo_min_ciudad);
}
