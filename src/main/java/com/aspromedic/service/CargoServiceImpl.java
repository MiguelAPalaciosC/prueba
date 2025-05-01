package com.aspromedic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspromedic.model.Cargo;
import com.aspromedic.model.dao.ICargoDao;
import com.aspromedic.response.CargoResponseRest;


@Service
public class CargoServiceImpl implements ICargoService {

    private Logger log = LoggerFactory.getLogger(CargoServiceImpl.class);

    @Autowired
    private ICargoDao cargoDao;

    @Override
    @Transactional
    public ResponseEntity<CargoResponseRest> buscarPorId(Long id) {
        log.info("ejecucion del metodo buscar por id");

        CargoResponseRest response = new CargoResponseRest();

        List<Cargo> list = new ArrayList<>();

        try {
            Optional<Cargo> cargo = cargoDao.findById(id);

            if (cargo.isPresent()) {
                list.add(cargo.get());
                response.getCargoResponse().setCargos(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta cargo");
                response.setMetadata("Respuesta nok", "-1", "Cargo no encontrada");

                return new ResponseEntity<CargoResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.error("Error en consulta cargo");
            response.setMetadata("Respuesta nok", "-1", "Cargo no encontrada");

            return new ResponseEntity<CargoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 404
        }

        return new ResponseEntity<CargoResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

    @Override
    @Transactional
    public ResponseEntity<CargoResponseRest> buscarPorCodigoCargo(String codigo_cargo) {
        log.info("ejecucion del metodo buscar por codigo de cargo");

        CargoResponseRest response = new CargoResponseRest();

        try {
            List<Cargo> list = (List<Cargo>) cargoDao.findByCodigoCargo(codigo_cargo);

            response.getCargoResponse().setCargos(list);

            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        } catch (Exception e) {
            log.error("Error en consulta cargo");
            response.setMetadata("Respuesta nok", "-1", "Cargo no encontrada");

            return new ResponseEntity<CargoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 404
        }

        return new ResponseEntity<CargoResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CargoResponseRest> buscarCargos() {
        log.info("inicio del metodo buscarCargo()");

        CargoResponseRest response = new CargoResponseRest();
        try {
            List<Cargo> cargo = (List<Cargo>) cargoDao.findAll();

            response.getCargoResponse().setCargos(cargo);

            response.setMetadata("Respuesta Ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error en la consulta de cargos");
            log.error("Error en la consulta de cargos: ", e);

            return new ResponseEntity<CargoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CargoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CargoResponseRest> crear(Cargo request) {
        log.info("metodo guardar cargos");

        CargoResponseRest response = new CargoResponseRest();

        List<Cargo> list = new ArrayList<>();
        try {
            Cargo cargo = cargoDao.save(request);

            if (cargo != null) {
                list.add(cargo);

                response.getCargoResponse().setCargos(list);

            } else {
                log.error("Error en consulta cargo");
                response.setMetadata("Respuesta nok", "-1", "Cargo no guardado");

                return new ResponseEntity<CargoResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.error("Error en consulta cargo", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al guardar cargo");

            return new ResponseEntity<CargoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
        }

        response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        return new ResponseEntity<CargoResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

    @Override
    @Transactional
    public ResponseEntity<CargoResponseRest> actualizar(Cargo request, Long id) {
        log.info("inicio metodo actualizar()");

        CargoResponseRest response = new CargoResponseRest();

        List<Cargo> list = new ArrayList<>();

        try {
            Optional<Cargo> cargoBuscada = cargoDao.findById(id);

            if (cargoBuscada.isPresent()) {
                cargoBuscada.get().setCodigo_cargo(request.getCodigo_cargo());
                cargoBuscada.get().setDescripcion_cargo(request.getDescripcion_cargo());

                Cargo cargoActualizar = cargoDao.save(cargoBuscada.get());

                if (cargoActualizar != null) {
                    response.setMetadata("Respuesta ok", "00", "Cargo actualizada");

                    list.add(cargoActualizar);
                    response.getCargoResponse().setCargos(list);
                } else {
                    log.error("Error en actualizar cargo");
                    response.setMetadata("Respuesta nok", "-1", "Cargo no actualizada");

                    return new ResponseEntity<CargoResponseRest>(response, HttpStatus.BAD_REQUEST); // error 400
                }
            } else {
                log.error("Error en actualizar cargo");
                response.setMetadata("Respuesta nok", "-1", "Cargo no actualizada");

                return new ResponseEntity<CargoResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.error("Error en actualizar cargo", e.getMessage());

            response.setMetadata("Respuesta nok", "-1", "Error al actualizar la cargo");

            return new ResponseEntity<CargoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
        }
        return new ResponseEntity<CargoResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

    @Override
    @Transactional
    public ResponseEntity<CargoResponseRest> eliminar(Long id) {
        log.info("inicio metodo eliminar cargo");

        CargoResponseRest response = new CargoResponseRest();

        try {
            cargoDao.deleteById(id);

            response.setMetadata("Respuesta ok", "00", "Contactos eliminados");
        } catch (Exception e) {
            log.error("Error en eliminar cargo ", e.getMessage());

            response.setMetadata("Respuesta nok", "-1", "Error al eliminar los cargo");

            return new ResponseEntity<CargoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
        }
        return new ResponseEntity<CargoResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

    @Override
    @Transactional
    public ResponseEntity<CargoResponseRest> buscarPorDescripcionCargo(String descripcion_cargo) {
        log.info("ejecucion del metodo buscar por descripcion de cargo");

        CargoResponseRest response = new CargoResponseRest();

        try {
            List<Cargo> list = (List<Cargo>) cargoDao.findByDescripcionCargo(descripcion_cargo);

            response.getCargoResponse().setCargos(list);

            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        } catch (Exception e) {
            log.error("Error en consulta cargo");
            response.setMetadata("Respuesta nok", "-1", "Cargo no encontrada");

            return new ResponseEntity<CargoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 404
        }

        return new ResponseEntity<CargoResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

}
