package com.aspromedic.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.aspromedic.model.Trabajador;
import com.aspromedic.response.TrabajadorResponseRest;

public interface ITrabajadorService {
    

    public ResponseEntity<TrabajadorResponseRest> findAll(Pageable page);

    public ResponseEntity<TrabajadorResponseRest> findByAll(String cedula_trabajador, String nombre_trabajador,
            String primer_apellido_trabajador, String segundo_apellido_trabajador, String fecha_inicio_trabajador,
            String fecha_nacimiento_trabajador, String mail_trabajador, String celular_trabajador);

    public ResponseEntity<TrabajadorResponseRest> findById(Long id);

    public ResponseEntity<TrabajadorResponseRest> findByCodigoYNombre(String codigo, String nombre);

    public ResponseEntity<TrabajadorResponseRest> findByTitulo(Long id_titulo);

    public ResponseEntity<TrabajadorResponseRest> findByCedula(Long cedula_trabajador);

    public ResponseEntity<TrabajadorResponseRest> save(Trabajador request);

    public ResponseEntity<TrabajadorResponseRest> update(Long id, Trabajador request);

    public ResponseEntity<TrabajadorResponseRest> deleteById(Long id);
}
