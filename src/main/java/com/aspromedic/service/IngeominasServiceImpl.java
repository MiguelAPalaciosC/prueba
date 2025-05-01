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

import com.aspromedic.model.Ingeominas;
import com.aspromedic.model.dao.IIngeominasDao;
import com.aspromedic.response.IngeominasResponseRest;

@Service
public class IngeominasServiceImpl implements IIngeominasService {

    private Logger log = LoggerFactory.getLogger(IngeominasServiceImpl.class);

    @Autowired
    private IIngeominasDao ingeominasDao;

    @Override
    public ResponseEntity<IngeominasResponseRest> buscarIngeominas() {
        log.info("ejecucion del metodo buscar todos");

        IngeominasResponseRest response = new IngeominasResponseRest();

        
        try {
            List<Ingeominas> list = (List<Ingeominas>) ingeominasDao.findAll();

            if (!list.isEmpty()) {
                response.getIngeominasResponse().setIngeominas(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta ingeominas");
                response.setMetadata("Respuesta nok", "-1", "Ingeominas no encontrada");

                return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.error("Error en consulta ingeominas");
            response.setMetadata("Respuesta nok", "-1", "Ingeominas no encontrada");

            return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 404
        }

        return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

    @Override
    public ResponseEntity<IngeominasResponseRest> buscarIngeominasPorId(Long id) {
        log.info("ejecucion del metodo buscar por id");

        IngeominasResponseRest response = new IngeominasResponseRest();

        List<Ingeominas> list = new ArrayList<>();

        try {
            Optional<Ingeominas> ingeominas = ingeominasDao.findById(id);

            if (ingeominas.isPresent()) {
                list.add(ingeominas.get());
                response.getIngeominasResponse().setIngeominas(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta ingeominas por id");
                response.setMetadata("Respuesta nok", "-1", "Ingeominas no encontrada");

                return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.error("Error en consulta ingeominas por id");
            response.setMetadata("Respuesta nok", "-1", "Ingeominas no encontrada");

            return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 404
        }

        return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

    @Override
    public ResponseEntity<IngeominasResponseRest> buscarIngeominasPorCodigo(String codigo) {
        log.info("ejecucion del metodo buscar por codigo");

        IngeominasResponseRest response = new IngeominasResponseRest();

        try {
            List<Ingeominas> list = ingeominasDao.findByCodigoGeominas(codigo);

            if (!list.isEmpty()) {
                response.getIngeominasResponse().setIngeominas(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta ingeominas por codigo");
                response.setMetadata("Respuesta nok", "-1", "Ingeominas no encontrada");

                return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.error("Error en consulta ingeominas por codigo");
            response.setMetadata("Respuesta nok", "-1", "Ingeominas no encontrada");

            return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 404
        }

        return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

    @Override
    public ResponseEntity<IngeominasResponseRest> buscarIngeominasPorDescripcion(String descripcion) {
        log.info("ejecucion del metodo buscar por descripcion");

        IngeominasResponseRest response = new IngeominasResponseRest();

        try {
            List<Ingeominas> list = ingeominasDao.findByDescripcionGeominas(descripcion);

            if (!list.isEmpty()) {
                response.getIngeominasResponse().setIngeominas(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta ingeominas por descripcion");
                response.setMetadata("Respuesta nok", "-1", "Ingeominas no encontrada");

                return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.error("Error en consulta ingeominas por descripcion");
            response.setMetadata("Respuesta nok", "-1", "Ingeominas no encontrada");

            return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 404
        }

        return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

    @Override
    public ResponseEntity<IngeominasResponseRest> guardarIngeominas(Ingeominas request) {
        log.info("metodo guardar ingeominas");

        IngeominasResponseRest response = new IngeominasResponseRest();

        List<Ingeominas> list = new ArrayList<>();
        try {
            Ingeominas ingeominas = ingeominasDao.save(request);

            if (ingeominas != null) {
                list.add(ingeominas);

                response.getIngeominasResponse().setIngeominas(list);

            } else {
                log.error("Error en consulta ingeominas");
                response.setMetadata("Respuesta nok", "-1", "Ingeominas no guardado");

                return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.error("Error en consulta ingeominas", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al guardar ingeominas");

            return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
        }

        response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

    @Override
    public ResponseEntity<IngeominasResponseRest> actualizarIngeominas(Long id, Ingeominas request) {
        log.info("inicio metodo actualizar()");

        IngeominasResponseRest response = new IngeominasResponseRest();

        List<Ingeominas> list = new ArrayList<>();

        try {
            Optional<Ingeominas> ingeominasBuscada = ingeominasDao.findById(id);

            if (ingeominasBuscada.isPresent()) {
                ingeominasBuscada.get().setCodigo_geominas(request.getCodigo_geominas());
                ingeominasBuscada.get().setDescripcion_geominas(request.getDescripcion_geominas());

                Ingeominas ingeominasActualizar = ingeominasDao.save(ingeominasBuscada.get());

                if (ingeominasActualizar != null) {
                    response.setMetadata("Respuesta ok", "00", "Ingeominas actualizada");

                    list.add(ingeominasActualizar);
                    response.getIngeominasResponse().setIngeominas(list);
                } else {
                    log.error("Error en actualizar ingeominas");
                    response.setMetadata("Respuesta nok", "-1", "Ingeominas no actualizada");

                    return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.BAD_REQUEST); // error 400
                }
            } else {
                log.error("Error en actualizar ingeominas");
                response.setMetadata("Respuesta nok", "-1", "Ingeominas no actualizada");

                return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.error("Error en actualizar ingeominas", e.getMessage());

            response.setMetadata("Respuesta nok", "-1", "Error al actualizar la ingeominas");

            return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
        }
        return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

    @Override
    public ResponseEntity<IngeominasResponseRest> eliminarIngeominas(Long id) {
        log.info("inicio metodo eliminar()");
        IngeominasResponseRest response = new IngeominasResponseRest();

        try {
            ingeominasDao.deleteById(id);

            response.setMetadata("Respuesta ok", "00", "Ingeominas eliminada");

        } catch (Exception e) {
            log.error("Error en eliminar ingeominas", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar la ingeominas");

            return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
        }

        return new ResponseEntity<IngeominasResponseRest>(response, HttpStatus.OK); // devuelve 200
    }

}
