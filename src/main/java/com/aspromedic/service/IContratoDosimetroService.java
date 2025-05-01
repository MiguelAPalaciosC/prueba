package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.ContratoDosimetro;
import com.aspromedic.response.ContratoDosimetroResponseRest;

public interface IContratoDosimetroService {
    
    public ResponseEntity<ContratoDosimetroResponseRest> findAll();

    public ResponseEntity<ContratoDosimetroResponseRest> findByIdContrato(String id_contrato);

    public ResponseEntity<ContratoDosimetroResponseRest> findByIdDosimetro(Long id_dosimetro);

    public ResponseEntity<ContratoDosimetroResponseRest> countDosimetrosActivosContrato(String id_contrato);

    public ResponseEntity<ContratoDosimetroResponseRest> findByIdDosimetroActivo(Long id_dosimetro);

    public ResponseEntity<ContratoDosimetroResponseRest> save(ContratoDosimetro request);

    public ResponseEntity<ContratoDosimetroResponseRest> update(String id_contrato);

    public ResponseEntity<ContratoDosimetroResponseRest> updateEstado(String id_contrato, String estado);

    public ResponseEntity<ContratoDosimetroResponseRest> deleteById(String id_contrato);

    public ResponseEntity<ContratoDosimetroResponseRest> deleteByIdContratoAndIdDosimetro(String id_contrato, Long id_dosimetro);

}
