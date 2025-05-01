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

import com.aspromedic.model.Titulo;
import com.aspromedic.model.dao.ITituloDao;
import com.aspromedic.response.RadiacionResponseRest;
import com.aspromedic.response.TituloResponseRest;

@Service
public class TituloServiceImpl implements ITituloService{

    private Logger log = LoggerFactory.getLogger(TituloServiceImpl.class);

    @Autowired
    private ITituloDao tituloDao;

    @Override
    public ResponseEntity<TituloResponseRest> findAll() {
        log.info("metodo buscar titulo por id");

        TituloResponseRest response = new TituloResponseRest();

        try {
            List<Titulo> list =  tituloDao.findAllOrderByCodigoTitulo();

            if (!list.isEmpty()) {
                response.getTituloResponse().setTitulos(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta titulos");
                response.setMetadata("Respuesta nok", "-1", "Titulos no encontrados");
                return new ResponseEntity<TituloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("Error en consulta titulos");
            response.setMetadata("Respuesta nok", "-1", "Titulos no encontrados");
            return new ResponseEntity<TituloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TituloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TituloResponseRest> findById(Long id) {
        log.info("buscar por id");

        TituloResponseRest response = new TituloResponseRest();

        List<Titulo> list = new ArrayList<>();

        try {

            Optional<Titulo> titulo = tituloDao.findById(id);

            if (titulo.isPresent()) {
                list.add(titulo.get());

                response.getTituloResponse().setTitulos(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta titulos por id: " + id);
                response.setMetadata("Respuesta nok", "-1", "Titulo no encontrado");
                return new ResponseEntity<TituloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            log.error("Error en consulta titulos por id: " + id);
            response.setMetadata("Respuesta nok", "-1", "Titulo no encontrado");
            return new ResponseEntity<TituloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TituloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TituloResponseRest> findByCodigoTitulo(String codigo) {
        log.info("metodo findByCodigoTitulo");

        TituloResponseRest response = new TituloResponseRest();

        try {
            List<Titulo> titulos = tituloDao.findByCodigoTitulo(codigo);

            if (!titulos.isEmpty()) {
                response.getTituloResponse().setTitulos(titulos);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta titulos por codigo: " + codigo);
                response.setMetadata("Respuesta nok", "-1", "Titulo no encontrado");
                return new ResponseEntity<TituloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("Error en consulta titulos por codigo: " + codigo);
            response.setMetadata("Respuesta nok", "-1", "Titulo no encontrado");
            return new ResponseEntity<TituloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TituloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TituloResponseRest> findByDesccripcionTitulo(String descripcion) {
        log.info("metodo findByDesccripcionTitulo");

        TituloResponseRest response = new TituloResponseRest();

        try {
            List<Titulo> titulos = tituloDao.findByDescripcionTitulo(descripcion);

            if (!titulos.isEmpty()) {
                response.getTituloResponse().setTitulos(titulos);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta titulos por descripcion: " + descripcion);
                response.setMetadata("Respuesta nok", "-1", "Titulo no encontrado");
                return new ResponseEntity<TituloResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta titulos por descripcion: " + descripcion);
            response.setMetadata("Respuesta nok", "-1", "Titulo no encontrado");
            return new ResponseEntity<TituloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TituloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TituloResponseRest> save(Titulo request) {
        log.info("Saving titulo response");

        TituloResponseRest response = new TituloResponseRest();

        List<Titulo> list = new ArrayList<>();

        try {
            Titulo titulo = tituloDao.save(request);

            if (titulo != null) {
                list.add(titulo);

                response.getTituloResponse().setTitulos(list);
                response.setMetadata("Respuesta ok", "00", "Titulo guardado exitosamente");
            } else {
                log.error("Error en guardar titulo");
                response.setMetadata("Respuesta nok", "-1", "Error al guardar el titulo");
                return new ResponseEntity<TituloResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en guardar titulo: ", e);
            response.setMetadata("Respuesta nok", "-1", "Error al guardar el titulo");
            return new ResponseEntity<TituloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    
        }

        return new ResponseEntity<TituloResponseRest>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<TituloResponseRest> deleteById(Long id) {
        log.info("metodo deleteById");

        TituloResponseRest response = new TituloResponseRest();

        try {
            tituloDao.deleteById(id);

            response.setMetadata("Respuesta ok", "00", "Titulo eliminado exitosamente");
        } catch (Exception e) {
            log.error("Error en eliminar titulo por id: " + id);
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar el titulo");
            return new ResponseEntity<TituloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TituloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TituloResponseRest> update(Long id, Titulo request) {
        log.info("method update titulo");

        TituloResponseRest response = new TituloResponseRest();

        List<Titulo> list = new ArrayList<>();

        try {
            Optional<Titulo> titulo = tituloDao.findById(id);

            if (titulo.isPresent()) {
                Titulo tituloToUpdate = titulo.get();
                tituloToUpdate.setCodigo_titulo(request.getCodigo_titulo());
                tituloToUpdate.setDescripcion_titulo(request.getDescripcion_titulo());
                
                Titulo updatedTitulo = tituloDao.save(tituloToUpdate);

                if (updatedTitulo != null) {
                    list.add(updatedTitulo);

                    response.getTituloResponse().setTitulos(list);
                    response.setMetadata("Respuesta ok", "00", "Titulo actualizado exitosamente");
                } else {
                    log.error("Error en actualizar titulo por id: " + id);
                    response.setMetadata("Respuesta nok", "-1", "Error al actualizar el titulo");
                    return new ResponseEntity<TituloResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                log.error("Error en consulta titulo por id: " + id);
                response.setMetadata("Respuesta nok", "-1", "Titulo no encontrado");
                return new ResponseEntity<TituloResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en actualizar titulo por id: " + id);
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar el titulo");
            return new ResponseEntity<TituloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    
        }

        return new ResponseEntity<TituloResponseRest>(response, HttpStatus.OK);
    }
    
}
