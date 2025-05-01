package com.aspromedic.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aspromedic.model.Contrato;
import com.aspromedic.model.dao.IContratoDao;
import com.aspromedic.response.ContratoResponseRest;

@Service
public class ContratoServiceImpl implements IContratoService {

    private static final Logger log = LoggerFactory.getLogger(ContratoServiceImpl.class);

    @Autowired
    private IContratoDao contratoDao;

    @Override
    public ResponseEntity<ContratoResponseRest> findAll() {
        log.info("Unimplemented method 'findAll'");

        ContratoResponseRest response = new ContratoResponseRest();

        try {
            List<Contrato> contratos = (List<Contrato>) contratoDao.findAll();
            if (!contratos.isEmpty()) {
                response.getContratoResponse().setContratos(contratos);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta contratos");
                response.setMetadata("Respuesta nok", "-1", "No se encontraron contratos");
                return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta contratos", e);
            response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
            return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoResponseRest> findById(Long id) {
        log.info("Unimplemented method 'findById'");

        ContratoResponseRest response = new ContratoResponseRest();

        List<Contrato> list = new ArrayList<>();

        try {
            Contrato contrato = contratoDao.findById(id).orElse(null);
            if (contrato != null) {
                list.add(contrato);
                response.getContratoResponse().setContratos(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta contrato");
                response.setMetadata("Respuesta nok", "-1", "Contrato no encontrado");
                return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta contrato", e);
            response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
            return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoResponseRest> findByEmpresa(Long id_empresa) {
        log.info("Unimplemented method 'findByEmpresa'");

        ContratoResponseRest response = new ContratoResponseRest();

        try {
            List<Contrato> contratos = contratoDao.findByIdEmpresa(id_empresa);
            if (!contratos.isEmpty()) {
                response.getContratoResponse().setContratos(contratos);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta contrato por empresa");
                response.setMetadata("Respuesta nok", "-1", "No se encontraron contratos para esta empresa");
                return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta contrato por empresa", e);
            response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
            return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoResponseRest> save(Contrato request) {
        log.info("Unimplemented method 'save'");

        ContratoResponseRest response = new ContratoResponseRest();

        List<Contrato> list = new ArrayList<>();

        try {
            Contrato contrato = contratoDao.save(request);
            if (contrato != null) {
                list.add(contrato);
                response.getContratoResponse().setContratos(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta contrato");
                response.setMetadata("Respuesta nok", "-1", "Contrato no guardado");
                return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta contrato", e);
            response.setMetadata("Respuesta nok", "-1", "Error al guardar contrato");
            return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoResponseRest> update(Long id, Contrato request) {
        log.info("Unimplemented method 'update'");

        ContratoResponseRest response = new ContratoResponseRest();

        List<Contrato> list = new ArrayList<>();

        try {
            Contrato contrato = contratoDao.findById(id).orElse(null);
            if (contrato != null) {
                contrato.setFecha_inicio_contrato(request.getFecha_inicio_contrato());
                contrato.setValor_factura(request.getValor_factura());
                contrato.setDuracion_contrato(request.getDuracion_contrato());
                contrato.setFecha_contratacion_factura(request.getFecha_contratacion_factura());
                contrato.setPeriodo_uso_contrato(request.getPeriodo_uso_contrato());
                contrato.setFactura_a(request.getFactura_a());
                contrato.setTipo_contrato(request.getTipo_contrato());
                contrato.setTipo_contratacion_factura(request.getTipo_contratacion_factura());
                contrato.setEstado_contrato(request.getEstado_contrato());
                contrato.setNumero_usuarios(request.getNumero_usuarios());
                contrato.setCuotas_contrato(request.getCuotas_contrato());
                contrato.setObservaciones_contrato(request.getObservaciones_contrato());
                contrato.setIdentificacion_contratacion(request.getIdentificacion_contratacion());

                Contrato updatedContrato = contratoDao.save(contrato);
                if (updatedContrato != null) {
                    list.add(updatedContrato);
                    response.getContratoResponse().setContratos(list);
                    response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
                } else {
                    log.error("Error en consulta contrato");
                    response.setMetadata("Respuesta nok", "-1", "Contrato no actualizado");
                    return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                log.error("Error en consulta contrato");
                response.setMetadata("Respuesta nok", "-1", "Contrato no encontrado");
                return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta contrato", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar contrato");
            return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoResponseRest> deleteById(Long id) {
        log.info("Unimplemented method 'deleteById'");

        ContratoResponseRest response = new ContratoResponseRest();

        try {
            contratoDao.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "Contrato eliminado");
        } catch (Exception e) {
            log.error("Error al eliminar contrato", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar el contrato");
            return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoResponseRest> findByIdEmpresaUltimo(Long id_empresa) {
        log.info("Unimplemented method 'findByIdEmpresaUltimo'");

        ContratoResponseRest response = new ContratoResponseRest();

        try {
            Pageable page = PageRequest.of(0, 1);
            Page<Contrato> contratos = contratoDao.findByIdEmpresaUltimo(id_empresa, page);
            if (!contratos.isEmpty()) {
                response.getContratoResponse().setContratos(contratos.getContent());
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error en consulta contrato por empresa");
                response.setMetadata("Respuesta nok", "-1", "No se encontraron contratos para esta empresa");
                return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta contrato por empresa", e);
            response.setMetadata("Respuesta nok", "-1", "Error en la consulta");
            return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContratoResponseRest> updateEstado(Long id, String estado) {
        log.info("Unimplemented method 'updateEstado'");

        ContratoResponseRest response = new ContratoResponseRest();
        List<Contrato> list = new ArrayList<>();
        
        try {
            Contrato contrato = contratoDao.findById(id).orElse(null);
            if (contrato != null) {
                contrato.setEstado_contrato(estado);
                Contrato updatedContrato = contratoDao.save(contrato);
                if (updatedContrato != null) {
                    list.add(updatedContrato);
                    response.getContratoResponse().setContratos(list);
                    response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
                } else {
                    log.error("Error en consulta contrato");
                    response.setMetadata("Respuesta nok", "-1", "Contrato no actualizado");
                    return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                log.error("Error en consulta contrato");
                response.setMetadata("Respuesta nok", "-1", "Contrato no encontrado");
                return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consulta contrato", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar contrato");
            return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ContratoResponseRest>(response, HttpStatus.OK);
    }

}
