package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.aspromedic.model.ContratoDosimetro;

public interface IContratoDosimetroDao extends CrudRepository<ContratoDosimetro, Long> {
    // No se requiere ninguna consulta adicional en este momento

    @Query("SELECT cd FROM ContratoDosimetro cd WHERE cd.codigo_contrato = :codigo_contrato AND cd.id_dosimetro = :id_dosimetro")
    List<ContratoDosimetro> findByIdContratoAndIdDosimetro(String codigo_contrato, Long id_dosimetro);

    @Query("SELECT cd FROM ContratoDosimetro cd WHERE cd.codigo_contrato = :codigo_contrato")
    List<ContratoDosimetro> findByIdContrato(String codigo_contrato);

    @Query("SELECT cd FROM ContratoDosimetro cd WHERE cd.id_dosimetro = :id_dosimetro")
    List<ContratoDosimetro> findByIdDosimetro(Long id_dosimetro);

    @Query("SELECT cd FROM ContratoDosimetro cd WHERE cd.id_dosimetro = :id_dosimetro AND cd.estado_dosimetro = '0'")
    List<ContratoDosimetro> findByIdDosimetroActivo(Long id_dosimetro);

    @Transactional
    @Modifying // Indica que es una operación de modificación (INSERT, UPDATE, DELETE)
    @Query("DELETE FROM ContratoDosimetro cd WHERE cd.codigo_contrato = :codigo_contrato AND cd.id_dosimetro = :id_dosimetro")
    void deleteByIdContratoAndIdDosimetro(@Param("codigo_contrato") String codigo_contrato, Long id_dosimetro);

    @Transactional
    @Modifying // Indica que es una operación de modificación (INSERT, UPDATE, DELETE)
    @Query("DELETE FROM ContratoDosimetro cd WHERE cd.codigo_contrato = :codigo_contrato")
    void deleteByIdContrato(@Param("codigo_contrato") String codigo_contrato);

}
