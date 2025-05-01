package com.aspromedic.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aspromedic.model.ReciboPago;
import com.aspromedic.model.dao.IReciboPagoDao;
import com.aspromedic.response.ReciboPagoResponseRest;

@Service
public class ReciboPagoServiceImpl implements IReciboPagoService{

    private Logger log = LoggerFactory.getLogger(ReciboPagoServiceImpl.class);

    @Autowired
    private IReciboPagoDao reciboPagoDao;
    
    @Override
    public ResponseEntity<ReciboPagoResponseRest> findByIdFactura(Long id_factura) {
        log.info("Unimplemented method 'findByIdFactura'");

        ReciboPagoResponseRest response = new ReciboPagoResponseRest();

        try {
            List<ReciboPago> list = reciboPagoDao.findByIdFactura(id_factura);

            if (!list.isEmpty()) {
                response.getReciboPagoResponse().setRecibosPagos(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");
                return new ResponseEntity<ReciboPagoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar todos los recibos de pago: {}", e.getMessage());
            return new ResponseEntity<ReciboPagoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ReciboPagoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReciboPagoResponseRest> save(ReciboPago request) {
        log.info("Unimplemented method 'save'");

        ReciboPagoResponseRest response = new ReciboPagoResponseRest();

        List<ReciboPago> list = new ArrayList<>();

        try {
            ReciboPago reciboPago = reciboPagoDao.save(request);

            if (reciboPago != null) {
                list.add(reciboPago);
                response.getReciboPagoResponse().setRecibosPagos(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");
                return new ResponseEntity<ReciboPagoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en guardar el recibo de pago: {}", e.getMessage());
            return new ResponseEntity<ReciboPagoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ReciboPagoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReciboPagoResponseRest> deleteByIdFactura(Long id_factura) {
        log.info("Unimplemented method 'deleteByIdFactura'");

        ReciboPagoResponseRest response = new ReciboPagoResponseRest();

        try {
            reciboPagoDao.deleteByIdFactura(id_factura);
            response.setMetadata("Respuesta ok", "00", "Registro eliminado exitosamente");
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en eliminar el recibo de pago: {}", e.getMessage());
            return new ResponseEntity<ReciboPagoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ReciboPagoResponseRest>(response, HttpStatus.OK);
    }
    
}
