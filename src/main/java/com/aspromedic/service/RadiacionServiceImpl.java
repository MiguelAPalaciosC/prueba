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

import com.aspromedic.model.Radiacion;
import com.aspromedic.model.dao.IRadiacionDao;
import com.aspromedic.response.RadiacionResponseRest;

@Service
public class RadiacionServiceImpl implements IRadiacionService {

    private Logger log = LoggerFactory.getLogger(RadiacionServiceImpl.class);

    @Autowired
    private IRadiacionDao radiacionDao;

    @Override
    public ResponseEntity<RadiacionResponseRest> findAll() {
        log.info("Buscar todas las radiaciones");

        RadiacionResponseRest response = new RadiacionResponseRest();

        try {
            List<Radiacion> list = (List<Radiacion>) radiacionDao.findAll();

            if (!list.isEmpty()) {
                response.getRadiacionResponse().setRadiaciones(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta radiaciones");
                response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
                return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("Error en consulta radiaciones");
            response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
            return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RadiacionResponseRest> findById(Long id_radiacion) {
        log.info("Buscar radiaciones por id");

        RadiacionResponseRest response = new RadiacionResponseRest();

        List<Radiacion> list = new ArrayList<>();
        try {
            Optional<Radiacion> radiacion =  radiacionDao.findById(id_radiacion);

            if (radiacion.isPresent()) {
                list.add(radiacion.get());
                response.getRadiacionResponse().setRadiaciones(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta radiaciones");
                response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
                return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("Error en consulta radiaciones");
            response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
            return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RadiacionResponseRest> findByCodigo(String codigo_radiacion) {
        log.info("Buscar radiaciones por codigo de radiacion");

        RadiacionResponseRest response = new RadiacionResponseRest();

        try {
            List<Radiacion> list = radiacionDao.findByCodigoRadiacion(codigo_radiacion);

            if (!list.isEmpty()) {
                response.getRadiacionResponse().setRadiaciones(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta radiaciones");
                response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
                return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta radiaciones");
            response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
            return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RadiacionResponseRest> findByDescripcion(String descripcion_radiacion) {
        log.info("Buscar radiaciones por descripcion de radiacion");

        RadiacionResponseRest response = new RadiacionResponseRest();

        try {
            List<Radiacion> list = radiacionDao.findByDescripcionRadiacion(descripcion_radiacion);

            if (!list.isEmpty()) {
                response.getRadiacionResponse().setRadiaciones(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta radiaciones");
                response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
                return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta radiaciones");
            response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
            return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RadiacionResponseRest> save(Radiacion request) {
        log.info("Guardar radiacion");

        RadiacionResponseRest response = new RadiacionResponseRest();

        List<Radiacion> list = new ArrayList<>();

        try {
            Radiacion radiacion = radiacionDao.save(request);

            if (radiacion != null) {
                list.add(radiacion);
                response.getRadiacionResponse().setRadiaciones(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta radiaciones");
                response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
                return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("Error en consulta radiaciones");
            response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
            return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RadiacionResponseRest> update(Long id, Radiacion request) {
        log.info("Actualizar radiacion");

        RadiacionResponseRest response = new RadiacionResponseRest();

        List<Radiacion> list = new ArrayList<>();

        try {
            Optional<Radiacion> radiacion = radiacionDao.findById(id);

            if (radiacion.isPresent()) {
                radiacion.get().setCodigo_radiacion(request.getCodigo_radiacion());
                radiacion.get().setDescripcion_radiacion(request.getDescripcion_radiacion());

                Radiacion radiacionUpdate = radiacionDao.save(radiacion.get());

                if (radiacionUpdate != null) {
                    list.add(radiacionUpdate);
                    response.getRadiacionResponse().setRadiaciones(list);
                    response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
                } else {
                    log.error("Error en consulta radiaciones");
                    response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
                    return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                log.error("Error en consulta radiaciones");
                response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
                return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta radiaciones");
            response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
            return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RadiacionResponseRest> delete(Long id) {
        log.info("Eliminar radiacion");

        RadiacionResponseRest response = new RadiacionResponseRest();

        try {
            radiacionDao.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error en consulta radiaciones");
            response.setMetadata("Respuesta nok", "-1", "Radiaciones no encontradas");
            return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<RadiacionResponseRest>(response, HttpStatus.OK);
    }
    
}
