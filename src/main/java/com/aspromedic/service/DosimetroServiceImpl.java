package com.aspromedic.service;

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

import com.aspromedic.model.ContratoDosimetro;
import com.aspromedic.model.Dosimetro;
import com.aspromedic.model.dao.IContratoDosimetroDao;
import com.aspromedic.model.dao.IDosimetroDao;
import com.aspromedic.response.ContratoDosimetroResponseRest;
import com.aspromedic.response.DosimetroResponseRest;

@Service
public class DosimetroServiceImpl implements IDosimetroService {

    private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

    @Autowired
    private IDosimetroDao dosimetroDao;

    @Autowired
    private IContratoDosimetroDao contratoDosimetroDao;

    @Override
    public ResponseEntity<DosimetroResponseRest> findAll(Pageable page) {
        log.info("buscar dosimetros ");

        DosimetroResponseRest response = new DosimetroResponseRest();

        try {
            Page<Dosimetro> dosimetros = dosimetroDao.findAllDosimetro(page);

            if (!dosimetros.isEmpty()) {
                response.getDosimetroResponse().setDosimetros(dosimetros.getContent());
                response.getDosimetroResponse().setTotalElements(dosimetros.getTotalElements());
                response.getDosimetroResponse().setTotalPages(dosimetros.getPageable().getPageNumber());

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontraron resultados");
                response.setMetadata("Respuesta nok", "-1", "No hay dosimetros");

                return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en buscar dosimetros: {}", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> findById(Long id) {
        log.info("buscar dosimetro por id");

        DosimetroResponseRest response = new DosimetroResponseRest();

        List<Dosimetro> list = new ArrayList<>();

        try {
            Optional<Dosimetro> dosimetro = dosimetroDao.findById(id);

            if (dosimetro.isPresent()) {
                list.add(dosimetro.get());
                response.getDosimetroResponse().setDosimetros(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontró el dosimetro con id {}", id);
                response.setMetadata("Respuesta nok", "-1", "No se encontró el dosimetro");

                return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en buscar dosimetro por id: {}", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> findByCodigoDosimetro(String codigo) {
        log.info("buscar dosimetro por codigo");

        DosimetroResponseRest response = new DosimetroResponseRest();

        try {
            List<Dosimetro> dosimetro = dosimetroDao.findByCodigoDosimetro(codigo);

            response.getDosimetroResponse().setDosimetros(dosimetro);

            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        } catch (Exception e) {
            log.error("Error en buscar dosimetro por codigo: {}", e);
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> save(Dosimetro request) {
        log.info("guardar dosimetro");

        DosimetroResponseRest response = new DosimetroResponseRest();

        List<Dosimetro> list = new ArrayList<>();

        try {
            Dosimetro dosimetro = dosimetroDao.save(request);

            if (dosimetro != null) {
                list.add(dosimetro);
                response.getDosimetroResponse().setDosimetros(list);

                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error al guardar el dosimetro");
                response.setMetadata("Respuesta nok", "-1", "Error al guardar");

                return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            log.error("Error en guardar dosimetro: {}", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al guardar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> update(Long id, Dosimetro request) {
        log.info("actualizar dosimetro por id");

        DosimetroResponseRest response = new DosimetroResponseRest();

        List<Dosimetro> list = new ArrayList<>();

        try {
            Optional<Dosimetro> optionalDosimetro = dosimetroDao.findById(id);

            if (optionalDosimetro.isPresent()) {
                Dosimetro dosimetro = optionalDosimetro.get();
                dosimetro.setId_trabajador(request.getId_trabajador());
                dosimetro.setCodigo_dosimetro(request.getCodigo_dosimetro());
                dosimetro.setEstado_dosimetro(request.getEstado_dosimetro());
                dosimetro.setPractica(request.getPractica());
                dosimetro.setRadiacion(request.getRadiacion());
                dosimetro.setUbicacion(request.getUbicacion());
                dosimetro.setCargo(request.getCargo());
                dosimetro.setIngeominas(request.getIngeominas());
                dosimetro.setPeriodo_uso(request.getPeriodo_uso());
                dosimetro.setTipo_dosimetro(request.getTipo_dosimetro());
                dosimetro.setDestino_dosimetro(request.getDestino_dosimetro());
                dosimetro.setDosimetro_control(request.getDosimetro_control());
                dosimetro.setDosimetro_ambiental(request.getDosimetro_ambiental());

                Dosimetro updatedDosimetro = dosimetroDao.save(dosimetro);

                if (updatedDosimetro != null) {
                    list.add(updatedDosimetro);
                    response.getDosimetroResponse().setDosimetros(list);

                    response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
                } else {
                    log.error("Error al actualizar el dosimetro con id {}", id);
                    response.setMetadata("Respuesta nok", "-1", "Error al actualizar");

                    return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                log.error("No se encontró el dosimetro con id {}", id);
                response.setMetadata("Respuesta nok", "-1", "No se encontró el dosimetro");

                return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en actualizar dosimetro por id: {}", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> deleteById(Long id) {
        log.info("eliminar dosimetro");

        DosimetroResponseRest response = new DosimetroResponseRest();

        try {
            dosimetroDao.deleteById(id);

            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("No se encontró el dosimetro con id {}", e);
            response.setMetadata("Respuesta nok", "-1", "No se encontró el dosimetro");

            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> findByEmpresa(Long id_empresa) {
        log.info("Buscar dosimetros por empresa");

        DosimetroResponseRest response = new DosimetroResponseRest();

        try {
            List<Dosimetro> dosimetro = dosimetroDao.findByEmpresa(id_empresa);

            response.getDosimetroResponse().setDosimetros(dosimetro);

            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error en buscar dosimetros por empresa: {}", e);
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> findByTrabajador(Long id_trabajador) {
        log.info("Unimplemented method 'findByTrabajador'");

        DosimetroResponseRest response = new DosimetroResponseRest();

        try {
            List<Dosimetro> dosimetro = dosimetroDao.findByTrabajadorDosimetro(id_trabajador);

            if (!dosimetro.isEmpty()) {
                response.getDosimetroResponse().setDosimetros(dosimetro);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontraron dosimetros asociados al trabajador con id {}", id_trabajador);
                response.setMetadata("Respuesta nok", "-1", "No se encontraron dosimetros");

                return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en buscar dosimetros por trabajador: {}", e);
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> findByPractica(Long id_practica) {
        // TODO Auto-generated method stub
        log.info("Unimplemented method 'findByPractica'");

        DosimetroResponseRest response = new DosimetroResponseRest();

        try {
            List<Dosimetro> dosimetro = dosimetroDao.findByPracticaDosimetro(id_practica);

            if (!dosimetro.isEmpty()) {
                response.getDosimetroResponse().setDosimetros(dosimetro);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontraron dosimetros asociados a la práctica con id {}", id_practica);
                response.setMetadata("Respuesta nok", "-1", "No se encontraron dosimetros");

                return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error en buscar dosimetros por práctica: {}", e);
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> findByRadiacion(Long id_radiacion) {
        // TODO Auto-generated method stub
        log.info("Unimplemented method 'findByRadiacion'");

        DosimetroResponseRest response = new DosimetroResponseRest();

        try {
            List<Dosimetro> dosimetro = dosimetroDao.findByRadiacionDosimetro(id_radiacion);

            if (!dosimetro.isEmpty()) {
                response.getDosimetroResponse().setDosimetros(dosimetro);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontraron dosimetros asociados a la radiación con id {}", id_radiacion);
                response.setMetadata("Respuesta nok", "-1", "No se encontraron dosimetros");

                return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en buscar dosimetros por radiación: {}", e);
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> findByUbicacion(Long id_ubicacion) {
        // TODO Auto-generated method stub
        log.info("Unimplemented method 'findByUbicacion'");

        DosimetroResponseRest response = new DosimetroResponseRest();

        try {
            List<Dosimetro> dosimetro = dosimetroDao.findByUbicacionDosimetro(id_ubicacion);

            if (!dosimetro.isEmpty()) {
                response.getDosimetroResponse().setDosimetros(dosimetro);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontraron dosimetros asociados a la ubicación con id {}", id_ubicacion);
                response.setMetadata("Respuesta nok", "-1", "No se encontraron dosimetros");

                return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error en buscar dosimetros por ubicación: {}", e);
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> findByCargo(Long id_cargo) {
        // TODO Auto-generated method stub
        log.info("Unimplemented method 'findByCargo'");

        DosimetroResponseRest response = new DosimetroResponseRest();

        try {
            List<Dosimetro> dosimetro = dosimetroDao.findByCargoDosimetro(id_cargo);

            if (!dosimetro.isEmpty()) {
                response.getDosimetroResponse().setDosimetros(dosimetro);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontraron dosimetros asociados al cargo con id {}", id_cargo);
                response.setMetadata("Respuesta nok", "-1", "No se encontraron dosimetros");

                return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en buscar dosimetros por cargo: {}", e);
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> findByIngeominas(Long id_ingeominas) {
        // TODO Auto-generated method stub
        log.info("Unimplemented method 'findByIngeominas'");

        DosimetroResponseRest response = new DosimetroResponseRest();

        try {
            List<Dosimetro> dosimetro = dosimetroDao.findByIngeominaDosimetro(id_ingeominas);

            if (!dosimetro.isEmpty()) {
                response.getDosimetroResponse().setDosimetros(dosimetro);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontraron dosimetros asociados a la ingeniera con id {}", id_ingeominas);
                response.setMetadata("Respuesta nok", "-1", "No se encontraron dosimetros");

                return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en buscar dosimetros por ingeniera: {}", e);
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> findByTipo(Long tipo_dosimetro) {
        // TODO Auto-generated method stub
        log.info("Unimplemented method 'findByTipo'");

        DosimetroResponseRest response = new DosimetroResponseRest();

        try {
            List<Dosimetro> dosimetro = dosimetroDao.findByTipoDosimetroDosimetro(tipo_dosimetro);

            if (!dosimetro.isEmpty()) {
                response.getDosimetroResponse().setDosimetros(dosimetro);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("No se encontraron dosimetros asociados al tipo con id {}", tipo_dosimetro);
                response.setMetadata("Respuesta nok", "-1", "No se encontraron dosimetros");

                return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error en buscar dosimetros por tipo: {}", e);
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> findByDosimetrosForContrato(String periodo_uso, Long id_empresa,
            String tipo_dosimetro[]) {
        log.info("Unimplemented method 'findByDosimetrosForContrato'");

        DosimetroResponseRest response = new DosimetroResponseRest();

        List<Dosimetro> dosimetro = new ArrayList<>();

        try {
            for (String tipo : tipo_dosimetro) {
                List<Dosimetro> dosimetroTemp = dosimetroDao.findByDosimetrosForContrato(periodo_uso, id_empresa,
                        Long.parseLong(tipo));
                if (!dosimetroTemp.isEmpty()) {
                    dosimetro.addAll(dosimetroTemp);
                }
            }

            response.getDosimetroResponse().setDosimetros(dosimetro);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        } catch (Exception e) {
            log.error("Error en buscar dosimetros para contrato: {}", e);
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DosimetroResponseRest> findByDosimetrosPorContratoValido(String periodo_uso, Long id_empresa,
            String tipo_dosimetro) {
        log.info("Ejecutando 'findByDosimetrosPorContratoValido'");

        DosimetroResponseRest response = new DosimetroResponseRest();
        List<Dosimetro> dosimetro = new ArrayList<>();

        try {
            String aux[] = tipo_dosimetro.split(",");

            for (String tipo : aux) {
                List<Dosimetro> dosimetroTemp = dosimetroDao.findByDosimetrosForContrato(periodo_uso, id_empresa,
                        Long.parseLong(tipo));

                for (Dosimetro d : dosimetroTemp) {
                    Long id_dosimetro = d.getId_dosimetro();

                    List<ContratoDosimetro> contratos = contratoDosimetroDao.findByIdDosimetroActivo(id_dosimetro);


                    if (contratos.isEmpty()) {
                        dosimetro.add(d);
                        continue;
                    }
                }
            }

            response.getDosimetroResponse().setDosimetros(dosimetro);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        } catch (Exception e) {
            log.error("Error en buscar dosímetros para contrato: {}", e.getMessage(), e);
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
