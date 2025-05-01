package com.aspromedic.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aspromedic.model.ReciboPago;
import com.aspromedic.response.ReciboPagoResponseRest;

public interface IReciboPagoService{

    public ResponseEntity<ReciboPagoResponseRest> findByIdFactura(Long id_factura);
    
    public ResponseEntity<ReciboPagoResponseRest> save(ReciboPago request);
        
    public ResponseEntity<ReciboPagoResponseRest> deleteByIdFactura(Long id_factura);
    
}
