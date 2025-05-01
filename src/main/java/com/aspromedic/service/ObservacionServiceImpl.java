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

import com.aspromedic.model.Observacion;
import com.aspromedic.model.dao.IObservacionDao;
import com.aspromedic.response.ObservacionResponseRest;

@Service
public class ObservacionServiceImpl implements IObservacionService {

    private Logger log = LoggerFactory.getLogger(ObservacionServiceImpl.class);

    @Autowired
    private IObservacionDao observacionDao;

    @Override
    public ResponseEntity<ObservacionResponseRest> findAll() {
        log.info("Buscar todas las observaciones");

        ObservacionResponseRest response = new ObservacionResponseRest();

        try {
            List<Observacion> list = (List<Observacion>) observacionDao.findAll();

            if (list.isEmpty()) {
                log.error("Error en consulta observaciones");
                response.setMetadata("Respuesta nok", "-1", "Observaciones no encontradas");
                return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.NOT_FOUND);
            } else {
                response.getObservacionResponse().setObservaciones(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }
        } catch (Exception e) {
            log.error("Error en consulta observaciones");
            response.setMetadata("Respuesta nok", "-1", "Observaciones no encontradas");
            return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ObservacionResponseRest> findByCodigoObservacion(String codigo_observacion) {
        log.info("Buscar observaciones por codigo de observacion");

        ObservacionResponseRest response = new ObservacionResponseRest();

        try {
            List<Observacion> list = observacionDao.findByCodigoObservacion(codigo_observacion);

            if (list.isEmpty()) {
                log.error("Error en consulta observaciones");
                response.setMetadata("Respuesta nok", "-1", "Observaciones no encontradas");
                return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.NOT_FOUND);
            } else {
                response.getObservacionResponse().setObservaciones(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }
        } catch (Exception e) {
            log.error("Error en consulta observaciones");
            response.setMetadata("Respuesta nok", "-1", "Observaciones no encontradas");
            return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ObservacionResponseRest> findByDescripcionObservacion(String descripcion_observacion) {
        log.info("Buscar observaciones por descripcion de observacion");

        ObservacionResponseRest response = new ObservacionResponseRest();

        try {
            List<Observacion> list = observacionDao.findByDescripcionObservacion(descripcion_observacion);

            if (list.isEmpty()) {
                log.error("Error en consulta observaciones");
                response.setMetadata("Respuesta nok", "-1", "Observaciones no encontradas");
                return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.NOT_FOUND);
            } else {
                response.getObservacionResponse().setObservaciones(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }
        } catch (Exception e) {
            log.error("Error en consulta observaciones");
            response.setMetadata("Respuesta nok", "-1", "Observaciones no encontradas");
            return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ObservacionResponseRest> save(Observacion request) {
        log.info("Guardar observacion");

        ObservacionResponseRest response = new ObservacionResponseRest();

        List<Observacion> list = new ArrayList<>();

        try {
            Observacion observacion = observacionDao.save(request);

            if (observacion == null) {
                log.error("Error en guardar observacion");
                response.setMetadata("Respuesta nok", "-1", "Error al guardar observacion");
                return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {

                list.add(observacion);
                response.getObservacionResponse().setObservaciones(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }
        } catch (Exception e) {
            log.error("Error en guardar observacion");
            response.setMetadata("Respuesta nok", "-1", "Error al guardar observacion");
            return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ObservacionResponseRest> update(Long id_observacion, Observacion request) {
        log.info("Actualizar observacion");

        ObservacionResponseRest response = new ObservacionResponseRest();

        List<Observacion> list = new ArrayList<>();

        try {
            Optional<Observacion> observacion = observacionDao.findById(id_observacion);

            if (observacion == null) {
                log.error("Error en actualizar observacion");
                response.setMetadata("Respuesta nok", "-1", "Error al actualizar observacion");
                return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                observacion.get().setCodigo_observacion(request.getCodigo_observacion());
                observacion.get().setDescripcion_observacion(request.getDescripcion_observacion());

                Observacion observacionActualizada = observacionDao.save(observacion.get());

                if (observacionActualizada == null) {

                    log.error("Error en actualizar observacion");
                    response.setMetadata("Respuesta nok", "-1", "Error al actualizar observacion");
                    return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

                } else {

                    list.add(observacionActualizada);

                    response.getObservacionResponse().setObservaciones(list);

                    response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
                }
            }
        } catch (Exception e) {
            log.error("Error en actualizar observacion");
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar observacion");
            return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ObservacionResponseRest> delete(Long id_observacion) {
        log.info("Eliminar observacion");

        ObservacionResponseRest response = new ObservacionResponseRest();

        try {
            observacionDao.deleteById(id_observacion);
            response.setMetadata("Respuesta ok", "00", "Observacion eliminada");
        } catch (Exception e) {
            log.error("Error en eliminar observacion");
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar observacion");
            return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ObservacionResponseRest>(response, HttpStatus.OK);
    }

}
