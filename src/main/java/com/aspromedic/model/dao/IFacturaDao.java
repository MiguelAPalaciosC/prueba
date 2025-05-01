package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long> {
    // No se requiere ninguna consulta adicional en este momento
    
    @Query("SELECT f FROM Factura f WHERE f.id_empresa = :id_empresa")
    List<Factura> findByIdEmpresa(Long id_empresa);
    
    @Query("SELECT f FROM Factura f WHERE f.contrato.id_contrato = :id_contrato")
    List<Factura> findByIdContrato(Long id_contrato);
}
