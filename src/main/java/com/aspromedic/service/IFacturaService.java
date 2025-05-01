package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Factura;
import com.aspromedic.response.FacturaResponseRest;

public interface IFacturaService {
    
    public ResponseEntity<FacturaResponseRest> findAll();

    public ResponseEntity<FacturaResponseRest> findByIdFactura(Long id_factura);

    public ResponseEntity<FacturaResponseRest> findByIdEmpresa(Long id_empresa);

    public ResponseEntity<FacturaResponseRest> findByIdContrato(Long id_contrato);

    public ResponseEntity<FacturaResponseRest> save(Factura request);

    public ResponseEntity<FacturaResponseRest> update(Factura request, Long id_factura);

    public ResponseEntity<FacturaResponseRest> deleteById(Long id_factura);


}
