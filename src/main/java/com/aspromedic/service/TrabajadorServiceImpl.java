package com.aspromedic.service;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aspromedic.model.Trabajador;
import com.aspromedic.model.dao.ITrabajadorDao;
import com.aspromedic.response.TrabajadorResponseRest;

@Service
public class TrabajadorServiceImpl implements ITrabajadorService {

    private Logger log = LoggerFactory.getLogger(TrabajadorServiceImpl.class);

    @Autowired
    private ITrabajadorDao trabajadorDao;

    @Override
    public ResponseEntity<TrabajadorResponseRest> findAll(Pageable page) {
        log.info("metodo buscar trabajadores");

        TrabajadorResponseRest response = new TrabajadorResponseRest();

        try {
            Page<Trabajador> list = trabajadorDao.findAllTrabajador(page);

            if (!list.isEmpty()) {
                response.getTrabajadorResponse().setTrabajadores(list.getContent());
                response.getTrabajadorResponse().setTotalElements(list.getTotalElements()); // Aquí obtienes el total
                response.getTrabajadorResponse().setTotalPages(list.getTotalPages());

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

            } else {
                log.error("No se encontraron resultados");
                response.setMetadata("Respuesta nok", "-1", "No hay trabajadores");

                return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en buscar trabajadores: {}", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TrabajadorResponseRest> findById(Long id) {
        log.info("buscar trabajador por id");

        TrabajadorResponseRest response = new TrabajadorResponseRest();

        List<Trabajador> list = new ArrayList<>();

        try {
            Optional<Trabajador> trabajador = trabajadorDao.findById(id);

            if (trabajador.isPresent()) {
                list.add(trabajador.get());

                response.getTrabajadorResponse().setTrabajadores(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontró el trabajador con id: {}", id);
                response.setMetadata("Respuesta nok", "-1", "No se encontró el trabajador");

                return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error en buscar trabajador por id: {}", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<TrabajadorResponseRest> save(Trabajador request) {
        log.info("guardar trabajador ");

        TrabajadorResponseRest response = new TrabajadorResponseRest();

        List<Trabajador> list = new ArrayList<>();

        try {
            Trabajador trabajador = trabajadorDao.save(request);

            if (trabajador != null) {
                list.add(trabajador);
                response.getTrabajadorResponse().setTrabajadores(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error al guardar trabajador");
                response.setMetadata("Respuesta nok", "-1", "Error al guardar");

                return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("Error en guardar trabajador: {}", e);
            response.setMetadata("Respuesta nok", "-1", "Error al guardar");
            return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TrabajadorResponseRest> update(Long id, Trabajador request) {
        log.info("actualizar trabajador");

        TrabajadorResponseRest response = new TrabajadorResponseRest();

        List<Trabajador> list = new ArrayList<>();

        try {

            Optional<Trabajador> trabajador = trabajadorDao.findById(id);

            if (trabajador.isPresent()) {
                Trabajador updated = trabajador.get();
                updated.setCedula_trabajador(request.getCedula_trabajador());
                updated.setNombre_trabajador(request.getNombre_trabajador());
                updated.setPrimer_apellido_trabajador(request.getPrimer_apellido_trabajador());
                updated.setSegundo_apellido_trabajador(request.getSegundo_apellido_trabajador());
                updated.setFecha_inicio_trabajador(request.getFecha_inicio_trabajador());
                updated.setEstado_trabajador(request.getEstado_trabajador());
                updated.setObservaciones_trabajador(request.getObservaciones_trabajador());
                updated.setSexo_trabajador(request.getSexo_trabajador());
                updated.setMail_trabajador(request.getMail_trabajador());
                updated.setCelular_trabajador(request.getCelular_trabajador());
                updated.setFecha_nacimiento_trabajador(request.getFecha_nacimiento_trabajador());
                updated.setEscolaridad_trabajador(request.getEscolaridad_trabajador());
                updated.setTitulo_trabajador(request.getTitulo_trabajador());
                updated.setId_titulo(request.getId_titulo());

                Trabajador saved = trabajadorDao.save(updated);

                if (saved != null) {
                    list.add(saved);
                    response.getTrabajadorResponse().setTrabajadores(list);

                    response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
                } else {
                    log.error("Error al actualizar trabajador con id: {}", id);
                    response.setMetadata("Respuesta nok", "-1", "Error al actualizar");
                    return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                log.error("No se encontró el trabajador con id: {}", id);
                response.setMetadata("Respuesta nok", "-1", "No se encontró el trabajador");

                return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en actualizar trabajador: {}", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar");
            return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TrabajadorResponseRest> deleteById(Long id) {
        log.info("eliminar trabajador");

        TrabajadorResponseRest response = new TrabajadorResponseRest();

        try {
            trabajadorDao.deleteById(id);

            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        } catch (Exception e) {
            log.error("Error en eliminar trabajador con id: {}", id);
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar");
            return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TrabajadorResponseRest> findByCodigoYNombre(String codigo, String nombre) {
        log.info("buscar trabajadores por id y nombre");

        TrabajadorResponseRest response = new TrabajadorResponseRest();

        try {
            List<Trabajador> trabajadores = trabajadorDao.findTrabajadorByCedulaAndNombre(codigo, nombre);

            if (!trabajadores.isEmpty()) {
                response.getTrabajadorResponse().setTrabajadores(trabajadores);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontraron trabajadores con el código {} y nombre {}", codigo, nombre);
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");

                return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en buscar trabajadores por código y nombre: {}", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TrabajadorResponseRest> findByTitulo(Long id_titulo) {
        log.info("buscar por titulo");

        TrabajadorResponseRest response = new TrabajadorResponseRest();

        try {
            List<Trabajador> trabajadores = trabajadorDao.findTrabajadorByTitulo(id_titulo);

            if (!trabajadores.isEmpty()) {
                response.getTrabajadorResponse().setTrabajadores(trabajadores);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontraron trabajadores con el título {}", id_titulo);
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");

                return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en buscar trabajadores por título: {}", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<TrabajadorResponseRest> findByCedula(Long cedula_trabajador) {
        log.info("Unimplemented method 'findByCedula'");

        TrabajadorResponseRest response = new TrabajadorResponseRest();

        try {
            List<Trabajador> trabajadores = trabajadorDao.findTrabajadorByCedula(cedula_trabajador);

            if (!trabajadores.isEmpty()) {
                response.getTrabajadorResponse().setTrabajadores(trabajadores);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontraron trabajadores con la cédula {}", cedula_trabajador);
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");
                return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en buscar trabajadores por cédula: {}", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TrabajadorResponseRest> findByAll(String cedula_trabajador, String nombre_trabajador,
            String primer_apellido_trabajador, String segundo_apellido_trabajador, String fecha_inicio_trabajador,
            String fecha_nacimiento_trabajador, String mail_trabajador, String celular_trabajador) {
        log.info("Unimplemented method 'findByAll'");

        TrabajadorResponseRest response = new TrabajadorResponseRest();

        try {
            List<Trabajador> trabajadores = trabajadorDao.findTrabajadorByAll(cedula_trabajador, nombre_trabajador,
                    primer_apellido_trabajador, segundo_apellido_trabajador, fecha_inicio_trabajador,
                    fecha_nacimiento_trabajador, mail_trabajador, celular_trabajador);

            response.getTrabajadorResponse().setTrabajadores(trabajadores);

            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        } catch (Exception e) {
            log.error("Error en buscar trabajadores por todos los criterios: {}", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TrabajadorResponseRest>(response, HttpStatus.OK);
    }

}
