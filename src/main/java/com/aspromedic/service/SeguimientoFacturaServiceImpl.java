package com.aspromedic.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aspromedic.model.SeguimientoFactura;
import com.aspromedic.model.dao.ISeguimientoFacturaDao;
import com.aspromedic.response.SeguimientoFacturaResponseRest;

@Service
public class SeguimientoFacturaServiceImpl implements ISeguimientoFactura{

    private Logger log = LoggerFactory.getLogger(SeguimientoFacturaServiceImpl.class);

    @Autowired
    private ISeguimientoFacturaDao seguimientoFacturaDao;

    @Override
    public ResponseEntity<SeguimientoFacturaResponseRest> findByIdFactura(Long id_factura) {
        log.info("Unimplemented method 'findByIdFactura'");

        SeguimientoFacturaResponseRest response = new SeguimientoFacturaResponseRest();

        try {
            List<SeguimientoFactura> list = seguimientoFacturaDao.findByIdFactura(id_factura);

            if (!list.isEmpty()) {
                response.getSeguimientoFacturaResponse().setSeguimientoFacturas(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");
                return new ResponseEntity<SeguimientoFacturaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en buscar todos los seguimientos de la factura: {}", e.getMessage());
            return new ResponseEntity<SeguimientoFacturaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<SeguimientoFacturaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeguimientoFacturaResponseRest> save(SeguimientoFactura request) {
        log.info("Unimplemented method 'save'");

        SeguimientoFacturaResponseRest response = new SeguimientoFacturaResponseRest();

        try {
            SeguimientoFactura seguimientoFactura = seguimientoFacturaDao.save(request);

            if (seguimientoFactura != null) {
                response.getSeguimientoFacturaResponse().setSeguimientoFacturas(List.of(seguimientoFactura));
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta nok", "-1", "No se encontraron resultados");
                return new ResponseEntity<SeguimientoFacturaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en guardar el seguimiento de la factura: {}", e.getMessage());
            return new ResponseEntity<SeguimientoFacturaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<SeguimientoFacturaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeguimientoFacturaResponseRest> deleteByIdFactura(Long id_factura) {
        log.info("Unimplemented method 'deleteByIdFactura'");

        SeguimientoFacturaResponseRest response = new SeguimientoFacturaResponseRest();

        try {
            seguimientoFacturaDao.deleteByIdFactura(id_factura);
            response.setMetadata("Respuesta ok", "00", "Registro eliminado exitosamente");
        } catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Ocurrio un error inesperado");
            log.error("Error en eliminar el seguimiento de la factura: {}", e.getMessage());
            return new ResponseEntity<SeguimientoFacturaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<SeguimientoFacturaResponseRest>(response, HttpStatus.OK);
    }
    
}
