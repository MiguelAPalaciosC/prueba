package com.aspromedic.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aspromedic.model.ContratoDosimetro;
import com.aspromedic.model.Dosimetro;
import com.aspromedic.model.dao.IContratoDosimetroDao;
import com.aspromedic.model.dao.IDosimetroDao;
import com.aspromedic.response.ContratoDosimetroResponseRest;

@Service
public class ContratoDosimetroServiceImpl implements IContratoDosimetroService {

    private static final Logger log = LoggerFactory.getLogger(ContratoDosimetroServiceImpl.class);

    @Autowired
    private IContratoDosimetroDao contratoDosimetroDao;

    @Autowired
    private IDosimetroDao dosimetroDao;

    @Override
    public ResponseEntity<ContratoDosimetroResponseRest> findAll() {
        log.info("Unimplemented method 'findAll'");

        ContratoDosimetroResponseRest response = new ContratoDosimetroResponseRest();

        try {
            List<ContratoDosimetro> contratos = (List<ContratoDosimetro>) contratoDosimetroDao.findAll();
            if (!contratos.isEmpty()) {
                response.getContratoDosimetroResponse().setContratoDosimetros(contratos);
                response.setMetadata("Respuesta OK", "00", "Contratos encontrados");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron contratos");
                return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
            log.error("Error al buscar Contratos: ", e);
            return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoDosimetroResponseRest> findByIdContrato(String id_contrato) {
        log.info("Unimplemented method 'findByIdContrato'");

        ContratoDosimetroResponseRest response = new ContratoDosimetroResponseRest();

        try {
            List<ContratoDosimetro> contratos = contratoDosimetroDao.findByIdContrato(id_contrato);
            if (!contratos.isEmpty()) {
                response.getContratoDosimetroResponse().setContratoDosimetros(contratos);
                response.setMetadata("Respuesta OK", "00", "Contratos encontrados");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron contratos");
                return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
            log.error("Error al buscar Contratos: ", e);
            return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoDosimetroResponseRest> findByIdDosimetro(Long id_dosimetro) {
        log.info("Unimplemented method 'findByIdDosimetro'");

        ContratoDosimetroResponseRest response = new ContratoDosimetroResponseRest();

        try {
            List<ContratoDosimetro> contratos = contratoDosimetroDao.findByIdDosimetro(id_dosimetro);
            if (!contratos.isEmpty()) {
                response.getContratoDosimetroResponse().setContratoDosimetros(contratos);
                response.setMetadata("Respuesta OK", "00", "Contratos encontrados");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron contratos");
                return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
            log.error("Error al buscar Contratos: ", e);
            return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoDosimetroResponseRest> save(ContratoDosimetro request) {
        log.info("Unimplemented method 'save'");

        ContratoDosimetroResponseRest response = new ContratoDosimetroResponseRest();

        List<ContratoDosimetro> list = new ArrayList<>();
        try {
            ContratoDosimetro contrato = contratoDosimetroDao.save(request);

            if (contrato != null) {
                list.add(contrato);
                response.getContratoDosimetroResponse().setContratoDosimetros(list);
            } else {
                log.error("Error en consulta contrato dosimetro");
                response.setMetadata("Respuesta nok", "-1", "Contrato no guardado");
                return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.error("Error en consulta contrato dosimetro", e);
            response.setMetadata("Respuesta nok", "-1", "Error al guardar contrato dosimetro");
            return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error
                                                                                                                  // 500
        }
        return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.OK); // error 200
    }

    @Override
    public ResponseEntity<ContratoDosimetroResponseRest> deleteById(String id_contrato) {
        log.info("Unimplemented method 'deleteById'");

        ContratoDosimetroResponseRest response = new ContratoDosimetroResponseRest();

        try {
            contratoDosimetroDao.deleteByIdContrato(id_contrato);
            response.setMetadata("Respuesta OK", "00", "Contrato eliminado");
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar contrato");
            log.error("Error al eliminar contrato: ", e);
            return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoDosimetroResponseRest> update(String id_contrato) {
        log.info("Unimplemented method 'update'");

        ContratoDosimetroResponseRest response = new ContratoDosimetroResponseRest();

        try {
            List<ContratoDosimetro> contratos = contratoDosimetroDao.findByIdContrato(id_contrato);
            if (!contratos.isEmpty()) {
                for (ContratoDosimetro contrato : contratos) {
                    contrato.setEstado_dosimetro("1"); // Cambiar el estado a "1"
                    contratoDosimetroDao.save(contrato); // Guardar el contrato actualizado
                }

                response.setMetadata("Respuesta OK", "00", "Contrato actualizado");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron contratos");
                return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar contrato dosimetro");
            log.error("Error al actualizar contrato dosimetro: ", e);
            return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoDosimetroResponseRest> updateEstado(String id_contrato, String estado) {
        log.info("Unimplemented method 'updateEstado'");

        ContratoDosimetroResponseRest response = new ContratoDosimetroResponseRest();

        try {
            List<ContratoDosimetro> contratos = contratoDosimetroDao.findByIdContrato(id_contrato);
            if (!contratos.isEmpty()) {
                for (ContratoDosimetro contrato : contratos) {
                    contrato.setEstado_dosimetro(estado); // Cambiar el estado a "1"
                    contratoDosimetroDao.save(contrato); // Guardar el contrato actualizado
                }

                response.setMetadata("Respuesta OK", "00", "Contrato actualizado");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron contratos");
                return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar contrato dosimetro");
            log.error("Error al actualizar contrato dosimetro: ", e);
            return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoDosimetroResponseRest> findByIdDosimetroActivo(Long id_dosimetro) {
        log.info("Unimplemented method 'findByIdDosimetroActivo'");

        ContratoDosimetroResponseRest response = new ContratoDosimetroResponseRest();

        try {
            List<ContratoDosimetro> contratos = contratoDosimetroDao.findByIdDosimetroActivo(id_dosimetro);
            if (!contratos.isEmpty()) {
                response.getContratoDosimetroResponse().setContratoDosimetros(contratos);
                response.setMetadata("Respuesta OK", "00", "Contratos encontrados");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron contratos");
                return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
            log.error("Error al buscar Contratos: ", e);
            return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoDosimetroResponseRest> deleteByIdContratoAndIdDosimetro(String id_contrato,
            Long id_dosimetro) {
        log.info("Unimplemented method 'deleteByIdContratoAndIdDosimetro'");

        ContratoDosimetroResponseRest response = new ContratoDosimetroResponseRest();

        try {
            contratoDosimetroDao.deleteByIdContratoAndIdDosimetro(id_contrato, id_dosimetro);
            response.setMetadata("Respuesta OK", "00", "Dosimetro eliminado");
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar dosimetro");
            log.error("Error al eliminar dosimetro: ", e);
            return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoDosimetroResponseRest> countDosimetrosActivosContrato(String id_contrato) {
        log.info("Unimplemented method 'countDosimetrosActivosContrato'");

        ContratoDosimetroResponseRest response = new ContratoDosimetroResponseRest();

        List<ContratoDosimetro> list = new ArrayList<>();

        try {
            List<ContratoDosimetro> dosimetrosContrato = contratoDosimetroDao.findByIdContrato(id_contrato);

            for (ContratoDosimetro contrato : dosimetrosContrato) {
                Long id_dosimetro = contrato.getId_dosimetro();

                List<Dosimetro> dosimetro = dosimetroDao.findByDosimetroActivo(id_dosimetro);

                if (!dosimetro.isEmpty()) {
                    list.add(contrato);
                }
            }

            response.getContratoDosimetroResponse().setContratoDosimetros(list);
            response.setMetadata("Respuesta OK", "00", "Contratos encontrados");
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al buscar dosimetro");
            log.error("Error al buscar dosimetro: ", e);
            return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContratoDosimetroResponseRest>(response, HttpStatus.OK);
    }

}
