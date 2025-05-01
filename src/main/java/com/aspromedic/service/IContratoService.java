package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Contrato;
import com.aspromedic.response.ContratoResponseRest;

public interface IContratoService {
    
    public ResponseEntity<ContratoResponseRest> findAll();

    public ResponseEntity<ContratoResponseRest> findById(Long id);

    public ResponseEntity<ContratoResponseRest> findByIdEmpresaUltimo(Long id_empresa);

    public ResponseEntity<ContratoResponseRest> findByEmpresa(Long id_empresa);

    public ResponseEntity<ContratoResponseRest> save(Contrato request);

    public ResponseEntity<ContratoResponseRest> update(Long id, Contrato request);

    public ResponseEntity<ContratoResponseRest> updateEstado(Long id, String estado);

    public ResponseEntity<ContratoResponseRest> deleteById(Long id);

}
