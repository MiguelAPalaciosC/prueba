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

import com.aspromedic.model.Ubicacion;
import com.aspromedic.model.dao.IUbicacionDao;
import com.aspromedic.response.UbicacionResponseRest;

@Service
public class UbicacionServiceImpl implements IUbicacionService {

    private Logger log = LoggerFactory.getLogger(UbicacionServiceImpl.class);

    @Autowired
    private IUbicacionDao ubicacionDao;

    @Override
    public ResponseEntity<UbicacionResponseRest> findAll() {
        log.info("Buscar todas las ubicaciones");

        UbicacionResponseRest response = new UbicacionResponseRest();

        try {
            List<Ubicacion> list =  (List<Ubicacion>) ubicacionDao.findAll();

            response.getUbicacionResponse().setUbicaciones(list);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error en consulta ubicaciones");
            response.setMetadata("Respuesta nok", "-1", "Ubicaciones no encontradas");
            return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UbicacionResponseRest> findById(Long id_ubicacion) {
        log.info("Buscar ubicaciones por id");

        UbicacionResponseRest response = new UbicacionResponseRest();

        List<Ubicacion> list = new ArrayList<>();

        try {
            Optional<Ubicacion> ubicacion = ubicacionDao.findById(id_ubicacion);

            if (ubicacion.isPresent()) {
                list.add(ubicacion.get());
                response.getUbicacionResponse().setUbicaciones(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta ubicaciones");
                response.setMetadata("Respuesta nok", "-1", "Ubicaciones no encontradas");
                return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta ubicaciones");
            response.setMetadata("Respuesta nok", "-1", "Ubicaciones no encontradas");
            return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UbicacionResponseRest> findByCodigoUbicacion(String codigo_ubicacion) {
        log.info("Buscar ubicaciones por codigo de ubicacion");

        UbicacionResponseRest response = new UbicacionResponseRest();

        try {
            List<Ubicacion> list = ubicacionDao.findByCodigoUbicacion(codigo_ubicacion);

            if (list.isEmpty()) {
                log.error("Error en consulta ubicaciones");
                response.setMetadata("Respuesta nok", "-1", "Ubicaciones no encontradas");
                return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.NOT_FOUND);
            } else {
                response.getUbicacionResponse().setUbicaciones(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }
        } catch (Exception e) {
            log.error("Error en consulta ubicaciones");
            response.setMetadata("Respuesta nok", "-1", "Ubicaciones no encontradas");
            return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UbicacionResponseRest> findByDescripcionUbicacion(String descripcion_ubicacion) {
        log.info("Buscar ubicaciones por descripcion de ubicacion");

        UbicacionResponseRest response = new UbicacionResponseRest();

        try {
            List<Ubicacion> list = ubicacionDao.findByDescripcionUbicacion(descripcion_ubicacion);

            if (list.isEmpty()) {
                log.error("Error en consulta ubicaciones");
                response.setMetadata("Respuesta nok", "-1", "Ubicaciones no encontradas");
                return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.NOT_FOUND);
            } else {
                response.getUbicacionResponse().setUbicaciones(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }
        } catch (Exception e) {
            log.error("Error en consulta ubicaciones");
            response.setMetadata("Respuesta nok", "-1", "Ubicaciones no encontradas");
            return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UbicacionResponseRest> save(Ubicacion request) {
        log.info("Guardar ubicacion");

        UbicacionResponseRest response = new UbicacionResponseRest();

        List<Ubicacion> list = new ArrayList<>();

        try {
            Ubicacion ubicacion = ubicacionDao.save(request);

            list.add(ubicacion);
            response.getUbicacionResponse().setUbicaciones(list);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error en guardar ubicacion");
            response.setMetadata("Respuesta nok", "-1", "Ubicaciones no guardadas");
            return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UbicacionResponseRest> update(Long id, Ubicacion request) {
        log.info("Actualizar ubicacion");

        UbicacionResponseRest response = new UbicacionResponseRest();

        List<Ubicacion> list = new ArrayList<>();

        try {
            Optional<Ubicacion> ubicacion = ubicacionDao.findById(id);

            if (ubicacion.isPresent()) {
                ubicacion.get().setCodigo_ubicacion(request.getCodigo_ubicacion());
                ubicacion.get().setDescripcion_ubicacion(request.getDescripcion_ubicacion());

                Ubicacion ubicacionUpdate = ubicacionDao.save(ubicacion.get());

                if (ubicacionUpdate != null) {
                    list.add(ubicacionUpdate);
                    response.getUbicacionResponse().setUbicaciones(list);
                    response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
                } else {
                    log.error("Error en actualizar ubicacion");
                    response.setMetadata("Respuesta nok", "-1", "Ubicaciones no actualizadas");
                    return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.NOT_FOUND);
                }

            } else {
                log.error("Error en actualizar ubicacion");
                response.setMetadata("Respuesta nok", "-1", "Ubicaciones no actualizadas");
                return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en actualizar ubicacion");
            response.setMetadata("Respuesta nok", "-1", "Ubicaciones no actualizadas");
            return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UbicacionResponseRest> delete(Long id) {
        log.info("Eliminar ubicacion");

        UbicacionResponseRest response = new UbicacionResponseRest();

        try {
            ubicacionDao.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error en eliminar ubicacion");
            response.setMetadata("Respuesta nok", "-1", "Ubicaciones no eliminadas");
            return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UbicacionResponseRest>(response, HttpStatus.OK);
    }
    
}
