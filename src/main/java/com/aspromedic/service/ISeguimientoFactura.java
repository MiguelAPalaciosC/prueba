package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.SeguimientoFactura;
import com.aspromedic.response.SeguimientoFacturaResponseRest;

public interface ISeguimientoFactura {

    public ResponseEntity<SeguimientoFacturaResponseRest> findByIdFactura(Long id_factura);
    
    public ResponseEntity<SeguimientoFacturaResponseRest> save(SeguimientoFactura request);
        
    public ResponseEntity<SeguimientoFacturaResponseRest> deleteByIdFactura(Long id_factura);
}
