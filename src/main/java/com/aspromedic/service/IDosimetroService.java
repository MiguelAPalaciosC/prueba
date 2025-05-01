package com.aspromedic.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Dosimetro;
import com.aspromedic.response.DosimetroResponseRest;

public interface IDosimetroService {
    
    public ResponseEntity<DosimetroResponseRest> findAll(Pageable page);

    public ResponseEntity<DosimetroResponseRest> findById(Long id);

    public ResponseEntity<DosimetroResponseRest> findByEmpresa(Long id_empresa);

    public ResponseEntity<DosimetroResponseRest> findByCodigoDosimetro(String codigo);

    public ResponseEntity<DosimetroResponseRest> findByTrabajador(Long id_trabajador);

    public ResponseEntity<DosimetroResponseRest> findByPractica(Long id_practica);

    public ResponseEntity<DosimetroResponseRest> findByRadiacion(Long id_radiacion);

    public ResponseEntity<DosimetroResponseRest> findByUbicacion(Long id_ubicacion);

    public ResponseEntity<DosimetroResponseRest> findByCargo(Long id_cargo);

    public ResponseEntity<DosimetroResponseRest> findByIngeominas(Long id_ingeominas);

    public ResponseEntity<DosimetroResponseRest> findByTipo(Long tipo_dosimetro);

    public ResponseEntity<DosimetroResponseRest> findByDosimetrosForContrato(String periodo_uso, Long id_empresa, String tipo_dosimetro[]);

    public ResponseEntity<DosimetroResponseRest> findByDosimetrosPorContratoValido(String periodo_uso, Long id_empresa, String tipo_dosimetro);

    public ResponseEntity<DosimetroResponseRest> save(Dosimetro request);

    public ResponseEntity<DosimetroResponseRest> update(Long id, Dosimetro request);

    public ResponseEntity<DosimetroResponseRest> deleteById(Long id);
}
