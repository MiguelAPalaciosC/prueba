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

import com.aspromedic.model.TipoDosimetro;
import com.aspromedic.model.dao.ITipoDosimetroDao;
import com.aspromedic.response.TipoDosimetroResponse;
import com.aspromedic.response.TipoDosimetroResponseRest;

@Service
public class TipoDosimetroServiceImpl implements ITipoDosimetroService {

    private Logger log = LoggerFactory.getLogger(TipoDosimetroServiceImpl.class);

    @Autowired
    private ITipoDosimetroDao tipoDosimetroDao;

    @Override
    public ResponseEntity<TipoDosimetroResponseRest> findAll() {
        log.info("findAll tipo dosimetro");

        TipoDosimetroResponseRest response = new TipoDosimetroResponseRest();

        try {
            List<TipoDosimetro> list = tipoDosimetroDao.findAllTipoDosimetro();

            if (!list.isEmpty()) {
                response.getTipoDosimetroResponse().setTipos(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta tipos dosimetro");
                response.setMetadata("Respuesta nok", "-1", "Tipos dosimetro no encontrados");
                return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta tipos dosimetro");
            response.setMetadata("Respuesta nok", "-1", "Tipos dosimetro no encontrados");
            return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoDosimetroResponseRest> findById(Long id_tipoDosimetro) {
        log.info("buscar tipo por id");

        TipoDosimetroResponseRest response = new TipoDosimetroResponseRest();

        List<TipoDosimetro> list = new ArrayList<>();

        try {
            Optional<TipoDosimetro> tipoDosimetro = tipoDosimetroDao.findById(id_tipoDosimetro);

            if (tipoDosimetro.isPresent()) {
                list.add(tipoDosimetro.get());
                response.getTipoDosimetroResponse().setTipos(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta tipo dosimetro por id");
                response.setMetadata("Respuesta nok", "-1", "Tipo dosimetro no encontrado");
                return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta tipo dosimetro por id");
            response.setMetadata("Respuesta nok", "-1", "Tipo dosimetro no encontrado");
            return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoDosimetroResponseRest> save(TipoDosimetro request) {
        log.info("Saving tipo dosimetro");

        TipoDosimetroResponseRest response = new TipoDosimetroResponseRest();

        List<TipoDosimetro> list = new ArrayList<>();

        try {
            TipoDosimetro tipoDosimetro = tipoDosimetroDao.save(request);

            if (tipoDosimetro != null) {
                list.add(tipoDosimetro);
                response.getTipoDosimetroResponse().setTipos(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en guardar tipo dosimetro");
                response.setMetadata("Respuesta nok", "-1", "Tipo dosimetro no guardado");
                return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("Error en guardar tipo dosimetro");
            response.setMetadata("Respuesta nok", "-1", "Tipo dosimetro no guardado");
            return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoDosimetroResponseRest> update(Long id, TipoDosimetro request) {
        log.info("update tipo dosimetro");

        TipoDosimetroResponseRest response = new TipoDosimetroResponseRest();

        List<TipoDosimetro> list = new ArrayList<>();

        try {
            Optional<TipoDosimetro> tipoDosimetro = tipoDosimetroDao.findById(id);

            if (tipoDosimetro.isPresent()) {
                TipoDosimetro tipoDosimetroToUpdate = tipoDosimetro.get();
                tipoDosimetroToUpdate.setNombre(request.getNombre());
                tipoDosimetroToUpdate.setDescripcion(request.getDescripcion());
                tipoDosimetroToUpdate.setOrden(request.getOrden());

                TipoDosimetro tipoGuardado = tipoDosimetroDao.save(tipoDosimetroToUpdate);

                if (tipoGuardado != null) {
                    list.add(tipoGuardado);
                    response.getTipoDosimetroResponse().setTipos(list);
                    response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
                } else {
                    log.error("Error en actualizar tipo dosimetro");
                    response.setMetadata("Respuesta nok", "-1", "Tipo dosimetro no actualizado");
                    return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                log.error("Error en buscar tipo dosimetro por id");
                response.setMetadata("Respuesta nok", "-1", "Tipo dosimetro no encontrado");
                return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en actualizar tipo dosimetro");
            response.setMetadata("Respuesta nok", "-1", "Tipo dosimetro no actualizado");
            return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoDosimetroResponseRest> deleteById(Long id) {
        log.info("eliminar tipo dosimetro");

        TipoDosimetroResponseRest response = new TipoDosimetroResponseRest();

        try {
            tipoDosimetroDao.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error en eliminar tipo dosimetro");
            response.setMetadata("Respuesta nok", "-1", "Tipo dosimetro no eliminado");
            return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoDosimetroResponseRest> findByNombre(String nombre) {
        log.info("buscar tipo de dosimetro por nombre");

        TipoDosimetroResponseRest response = new TipoDosimetroResponseRest();

        try {
            List<TipoDosimetro> list = tipoDosimetroDao.findByNombre(nombre);

            if (!list.isEmpty()) {
                response.getTipoDosimetroResponse().setTipos(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en buscar tipo dosimetro por nombre");
                response.setMetadata("Respuesta nok", "-1", "Tipo dosimetro no encontrado");
                return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en buscar tipo dosimetro por nombre");
            response.setMetadata("Respuesta nok", "-1", "Tipo dosimetro no encontrado");
            return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TipoDosimetroResponseRest>(response, HttpStatus.OK);
    }

}
