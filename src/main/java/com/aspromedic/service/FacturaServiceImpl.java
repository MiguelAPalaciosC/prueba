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

import com.aspromedic.model.Factura;
import com.aspromedic.model.dao.IFacturaDao;
import com.aspromedic.response.FacturaResponseRest;

@Service
public class FacturaServiceImpl implements IFacturaService {

    private Logger log = LoggerFactory.getLogger(FacturaServiceImpl.class);

    @Autowired
    private IFacturaDao facturaDao;

    @Override
    public ResponseEntity<FacturaResponseRest> findAll() {
        log.info("Unimplemented method 'findAll'");

        FacturaResponseRest response = new FacturaResponseRest();

        try {
            List<Factura> list = (List<Factura>) facturaDao.findAll();
            response.getFacturaResponse().setFacturas(list);

            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar todos las facturas: {}", e.getMessage());
            return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FacturaResponseRest> findByIdFactura(Long id_factura) {
        log.info("Unimplemented method 'findByIdFactura'");

        FacturaResponseRest response = new FacturaResponseRest();

        List<Factura> list = new ArrayList<>();

        try {
            Optional<Factura> factura = facturaDao.findById(id_factura);

            if (factura.isPresent()) {
                list.add(factura.get());
                response.getFacturaResponse().setFacturas(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");
                return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar todos las facturas: {}", e.getMessage());
            return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FacturaResponseRest> findByIdEmpresa(Long id_empresa) {
        log.info("Unimplemented method 'findByIdEmpresa'");

        FacturaResponseRest response = new FacturaResponseRest();

        List<Factura> list = new ArrayList<>();

        try {
            list = facturaDao.findByIdEmpresa(id_empresa);

            response.getFacturaResponse().setFacturas(list);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar todos las facturas: {}", e.getMessage());
            return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FacturaResponseRest> save(Factura request) {
        log.info("Unimplemented method 'save'");

        FacturaResponseRest response = new FacturaResponseRest();

        List<Factura> list = new ArrayList<>();

        try {
            Factura factura = facturaDao.save(request);

            if (factura != null) {
                list.add(factura);
                response.getFacturaResponse().setFacturas(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");
                return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar todos las facturas: {}", e.getMessage());
            return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FacturaResponseRest> update(Factura request, Long id_factura) {
        log.info("Unimplemented method 'update'");

        FacturaResponseRest response = new FacturaResponseRest();

        List<Factura> list = new ArrayList<>();

        try {
            Optional<Factura> factura = facturaDao.findById(id_factura);

            if (factura.isPresent()) {
                Factura nuevaFactura = factura.get();
                nuevaFactura.setContrato(request.getContrato());
                nuevaFactura.setConcepto(request.getConcepto());
                nuevaFactura.setOtro_concepto(request.getOtro_concepto());
                nuevaFactura.setId_empresa(request.getId_empresa());
                nuevaFactura.setNumero_factura(request.getNumero_factura());
                nuevaFactura.setFecha_factura(request.getFecha_factura());
                nuevaFactura.setValor_factura(request.getValor_factura());
                nuevaFactura.setObservaciones_factura(request.getObservaciones_factura());

                Factura updatedFactura = facturaDao.save(nuevaFactura);
                list.add(updatedFactura);
                response.getFacturaResponse().setFacturas(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");
                return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar todos las facturas: {}", e.getMessage());
            return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FacturaResponseRest> deleteById(Long id_factura) {
        log.info("Unimplemented method 'deleteById'");

        FacturaResponseRest response = new FacturaResponseRest();

        try {
            facturaDao.deleteById(id_factura);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar todos las facturas: {}", e.getMessage());
            return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FacturaResponseRest> findByIdContrato(Long id_contrato) {
        log.info("Unimplemented method 'findByIdContrato'");

        FacturaResponseRest response = new FacturaResponseRest();

        try {
            List<Factura> list = facturaDao.findByIdContrato(id_contrato);

            response.getFacturaResponse().setFacturas(list);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar las facturas: {}", e);
            return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<FacturaResponseRest>(response, HttpStatus.OK);
    }

}
