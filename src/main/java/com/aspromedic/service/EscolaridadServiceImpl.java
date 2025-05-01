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

import com.aspromedic.model.Escolaridad;
import com.aspromedic.model.dao.IEscolaridadDao;
import com.aspromedic.response.EscolaridadResponseRest;
import com.aspromedic.response.ResponseRest;

@Service
public class EscolaridadServiceImpl implements IEscolaridadService{

    private Logger log = LoggerFactory.getLogger(EscolaridadServiceImpl.class);

    @Autowired
    private IEscolaridadDao escolaridadDao;

    @Override
    public ResponseEntity<EscolaridadResponseRest> findAll() {
        log.info("metodo buscar todos escolaridad");

        EscolaridadResponseRest response = new EscolaridadResponseRest();

        try {
            List<Escolaridad> list = escolaridadDao.findAllOrderByCodigoEscolaridad();

            if (!list.isEmpty())  {
                response.getEscolaridadResponse().setEscolaridades(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");

                return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar todos escolaridad: {}", e.getMessage());
            return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EscolaridadResponseRest> findById(Long id) {
        log.info("metodo buscar escolaridad por id");

        EscolaridadResponseRest response = new EscolaridadResponseRest();

        List<Escolaridad> list = new ArrayList<>();

        try {
            Optional<Escolaridad> escolaridad = escolaridadDao.findById(id);

            if (escolaridad.isPresent()) {
                list.add(escolaridad.get());

                response.getEscolaridadResponse().setEscolaridades(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontró el resultado");
                return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar escolaridad por id: {}", e);
            return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EscolaridadResponseRest> findByNombreEscolaridad(String nombre) {
        log.info("metodo buscar escolaridad por nombre");

        EscolaridadResponseRest response = new EscolaridadResponseRest();

        try {
            List<Escolaridad> list = escolaridadDao.findByNombreEscolaridad(nombre);

            if (!list.isEmpty())  {
                response.getEscolaridadResponse().setEscolaridades(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");
                return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar escolaridad por nombre: {}", e.getMessage());
            return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EscolaridadResponseRest> findByCodigoEscolaridad(String codigo) {
        log.info("metodo buscar escolaridad por codigo");

        EscolaridadResponseRest response = new EscolaridadResponseRest();

        try {
            List<Escolaridad> list = escolaridadDao.findByCodigoEscolaridad(codigo);

            if (!list.isEmpty())  {
                response.getEscolaridadResponse().setEscolaridades(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");
                return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar escolaridad por codigo: {}", e.getMessage());
            return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EscolaridadResponseRest> save(Escolaridad request) {
        log.info("guardar escolaridad");

        EscolaridadResponseRest response = new EscolaridadResponseRest();

        List<Escolaridad> list = new ArrayList<Escolaridad>();

        try {
            Escolaridad escolaridad = escolaridadDao.save(request);

            if (escolaridad != null) {
                list.add(escolaridad);

                response.getEscolaridadResponse().setEscolaridades(list);
                response.setMetadata("Respuesta ok", "00", "Registro guardado exitosamente");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se pudo guardar el registro");
                return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en guardar escolaridad: {}", e.getMessage());
            return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EscolaridadResponseRest> update(Long id, Escolaridad request) {
        log.info("Updating Escolaridad");

        EscolaridadResponseRest response = new EscolaridadResponseRest();

        List<Escolaridad> list = new ArrayList<>();

        try {
            Optional<Escolaridad> escolaridad = escolaridadDao.findById(id);

            if (escolaridad.isPresent()) {
                escolaridad.get().setNombre_escolaridad(request.getNombre_escolaridad());
                escolaridad.get().setCodigo_escolaridad(request.getCodigo_escolaridad());

                Escolaridad updatedEscolaridad = escolaridadDao.save(escolaridad.get());

                if (updatedEscolaridad!= null) {
                    list.add(updatedEscolaridad);

                    response.getEscolaridadResponse().setEscolaridades(list);
                    response.setMetadata("Respuesta ok", "00", "Registro actualizado exitosamente");
                } else {
                    response.setMetadata("Respuesta nok", "-1", "No se pudo actualizar el registro");
                    return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontró el registro");
                return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en actualizar escolaridad: {}", e.getMessage());
            return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EscolaridadResponseRest> deleteById(Long id) {
        log.info("Delete escolaridad");

        EscolaridadResponseRest response = new EscolaridadResponseRest();

        try {
            escolaridadDao.deleteById(id);

            response.setMetadata("Respuesta ok", "00", "Registro eliminado exitosamente");
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "No se pudo eliminar el registro");
            log.error("Error en eliminar escolaridad: {}", e.getMessage());
            return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<EscolaridadResponseRest>(response, HttpStatus.OK);
    }
    
}
