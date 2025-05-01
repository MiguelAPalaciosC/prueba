package com.aspromedic.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Cargo;

public interface ICargoDao extends CrudRepository<Cargo, Long> {

    @Query("select c from Cargo c where LOWER(c.codigo_cargo) = LOWER(:codigo_cargo)")
    List<Cargo> findByCodigoCargo(String codigo_cargo);

    @Query("select c from Cargo c where LOWER(c.descripcion_cargo) like LOWER(CONCAT('%', :descripcion_cargo, '%'))")
    List<Cargo> findByDescripcionCargo(String descripcion_cargo);

}
