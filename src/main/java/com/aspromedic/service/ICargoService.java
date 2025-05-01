package com.aspromedic.service;

import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Cargo;
import com.aspromedic.response.CargoResponseRest;

public interface ICargoService {

    public ResponseEntity<CargoResponseRest> buscarPorId(Long id);

    public ResponseEntity<CargoResponseRest> buscarCargos();

    public ResponseEntity<CargoResponseRest> buscarPorCodigoCargo(String codigo_cargo);

    public ResponseEntity<CargoResponseRest> buscarPorDescripcionCargo(String descripcion_cargo);

    public ResponseEntity<CargoResponseRest> crear(Cargo request);

    public ResponseEntity<CargoResponseRest> actualizar(Cargo request, Long id);

    public ResponseEntity<CargoResponseRest> eliminar(Long id);
}
