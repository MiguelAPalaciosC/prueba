package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.aspromedic.model.Contacto;

public interface IContactoDao extends CrudRepository<Contacto, Long> {

    // Alternativa con @Query si la relación no está bien definida
    @Query("SELECT c FROM Contacto c WHERE c.id_empresa = :id_empresa")
    List<Contacto> findByIdEmpresa(Long id_empresa);

    @Modifying
    @Transactional // Asegúrate de que la operación se ejecute dentro de una transacción
    @Query("DELETE FROM Contacto c WHERE c.id_empresa = :id_empresa")
    void  deleteByEmpresa(Long id_empresa);
}
