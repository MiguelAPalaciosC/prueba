package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Ingeominas;
import com.aspromedic.response.IngeominasResponseRest;

public interface IIngeominasService {
    
    public ResponseEntity<IngeominasResponseRest> buscarIngeominas();

    public ResponseEntity<IngeominasResponseRest> buscarIngeominasPorId(Long id);

    public ResponseEntity<IngeominasResponseRest> buscarIngeominasPorCodigo(String codigo);

    public ResponseEntity<IngeominasResponseRest> buscarIngeominasPorDescripcion(String descripcion);

    public ResponseEntity<IngeominasResponseRest> guardarIngeominas(Ingeominas ingeominas);

    public ResponseEntity<IngeominasResponseRest> actualizarIngeominas(Long id, Ingeominas ingeominas);

    public ResponseEntity<IngeominasResponseRest> eliminarIngeominas(Long id);
}
