package com.aspromedic.service;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aspromedic.model.Practica;
import com.aspromedic.model.dao.IPracticaDao;
import com.aspromedic.response.PracticaResponseRest;

@Service
public class PracticaServiceImpl implements IPracticaService {

    private Logger log = LoggerFactory.getLogger(PracticaServiceImpl.class);

    @Autowired
    private IPracticaDao practicaDao;

    @Override
    public ResponseEntity<PracticaResponseRest> findAll() {
        log.info("Buscar todas las practicas");

        PracticaResponseRest response = new PracticaResponseRest();

        try {
            List<Practica> list = (List<Practica>) practicaDao.findAll();

            response.getPracticaResponse().setPracticas(list);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error en consulta practicas");
            response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");
            return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PracticaResponseRest> findById(Long id_practica) {
        log.info("Buscar practicas por id");

        PracticaResponseRest response = new PracticaResponseRest();

        List<Practica> list = new ArrayList<>();

        try {
            Practica practica = practicaDao.findById(id_practica).get();

            if (practica != null) {
                list.add(practica);
                response.getPracticaResponse().setPracticas(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta practicas");
                response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");

                return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta practicas");
            response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");
            return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PracticaResponseRest> findByCodigoPractica(String codigo_practica) {
        log.info("Buscar practicas por codigo de practica");

        PracticaResponseRest response = new PracticaResponseRest();

        try {
            List<Practica> list = (List<Practica>) practicaDao.findByCodigoPractica(codigo_practica);

            if (list.isEmpty()) {
                log.error("Error en consulta practicas");
                response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");
                return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.NOT_FOUND);
            } else {
                response.getPracticaResponse().setPracticas(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }

        } catch (Exception e) {
            log.error("Error en consulta practicas");
            response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");
            return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PracticaResponseRest> findByDescripcionPractica(String descripcion_practica) {
        log.info("Buscar practicas por descripcion de practica");

        PracticaResponseRest response = new PracticaResponseRest();

        try {
            List<Practica> list = (List<Practica>) practicaDao.findByDescripcionPractica(descripcion_practica);

            if (list.isEmpty()) {
                log.error("Error en consulta practicas");
                response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");
                return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.NOT_FOUND);
            } else {
                response.getPracticaResponse().setPracticas(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }
        } catch (Exception e) {
            log.error("Error en consulta practicas");
            response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");
            return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PracticaResponseRest> save(Practica request) {
        log.info("Guardar practica");

        PracticaResponseRest response = new PracticaResponseRest();
        List<Practica> list = new ArrayList<>();

        try {
            Practica practica = practicaDao.save(request);

            if (practica != null) {
                list.add(practica);
                response.getPracticaResponse().setPracticas(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta practicas");
                response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");
                return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta practicas");
            response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");
            return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PracticaResponseRest> update(Long id_practica, Practica request) {
        log.info("Actualizar practica");

        PracticaResponseRest response = new PracticaResponseRest();
        List<Practica> list = new ArrayList<>();

        try {
            Optional<Practica> practica = practicaDao.findById(id_practica);

            if (practica.isPresent()) {
                practica.get().setCodigo_practica(request.getCodigo_practica());
                practica.get().setDescripcion_practica(request.getDescripcion_practica());

                Practica practicaActualizada = practicaDao.save(practica.get());

                if (practicaActualizada != null) {
                    list.add(practicaActualizada);
                    response.getPracticaResponse().setPracticas(list);
                    response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
                } else {
                    log.error("Error en consulta practicas");
                    response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");
                    return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.NOT_FOUND);
                }
            } else {
                log.error("Error en consulta practicas");
                response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");
                return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta practicas");
            response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");
            return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PracticaResponseRest> delete(Long id_practica) {
        log.info("Eliminar practica");

        PracticaResponseRest response = new PracticaResponseRest();

        try {
            practicaDao.deleteById(id_practica);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error en consulta practicas");
            response.setMetadata("Respuesta nok", "-1", "Practicas no encontradas");
            return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PracticaResponseRest>(response, HttpStatus.OK);
    }

}
