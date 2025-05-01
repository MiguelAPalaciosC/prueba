package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Contrato;

public interface IContratoDao extends CrudRepository<Contrato, Long> {
    // No se requiere ninguna consulta adicional en este momento

    @Query("SELECT c FROM Contrato c WHERE c.id_empresa = :id_empresa")
    List<Contrato> findByIdEmpresa(Long id_empresa);

    @Query("SELECT c FROM Contrato c WHERE c.id_empresa = :id_empresa ORDER BY c.id_contrato DESC")
    Page<Contrato> findByIdEmpresaUltimo(Long id_empresa, Pageable pageable);

}
