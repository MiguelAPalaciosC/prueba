package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.aspromedic.model.ReciboPago;

public interface IReciboPagoDao extends CrudRepository<ReciboPago, Long> {

    @Query("SELECT rp FROM ReciboPago rp WHERE rp.factura.id_factura = :id_factura")
    List<ReciboPago> findByIdFactura(Long id_factura);
    
    @Transactional
    @Modifying // Indica que es una operación de modificación (INSERT, UPDATE, DELETE)
    @Query("DELETE FROM ReciboPago rp WHERE rp.factura.id_factura = :id_factura")
    void deleteByIdFactura(@Param("id_factura") Long id_factura);
}
