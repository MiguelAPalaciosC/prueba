package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspromedic.model.Empresa;
import com.aspromedic.model.Trabajador;

public interface ITrabajadorDao extends CrudRepository<Trabajador, Long> {

    @Query("SELECT t FROM Trabajador t ORDER BY t.id_trabajador DESC")
    Page<Trabajador> findAllTrabajador(Pageable pageable);

    @Query("SELECT t FROM Trabajador t WHERE (CAST(t.cedula_trabajador AS string) LIKE CONCAT('%', :cedula_trabajador, '%')) AND (LOWER(t.nombre_trabajador) LIKE LOWER(CONCAT('%',:nombre_trabajador, '%')))")
    List<Trabajador> findTrabajadorByCedulaAndNombre(@Param("cedula_trabajador") String cedula_trabajador,
            @Param("nombre_trabajador") String nombre_trabajador);

    @Query("SELECT t FROM Trabajador t WHERE t.id_titulo = :id_titulo")
    List<Trabajador> findTrabajadorByTitulo(@Param("id_titulo") Long id_titulo);

    @Query("SELECT t FROM Trabajador t WHERE t.cedula_trabajador = :cedula_trabajador")
    List<Trabajador> findTrabajadorByCedula(@Param("cedula_trabajador") Long cedula_trabajador);

    @Query("SELECT t FROM Trabajador t WHERE (:cedula_trabajador IS NULL OR :cedula_trabajador = '' OR CAST(t.cedula_trabajador AS string) LIKE CONCAT('%', :cedula_trabajador, '%')) " +
            "AND (:nombre_trabajador IS NULL OR :nombre_trabajador = '' OR LOWER(t.nombre_trabajador) LIKE LOWER(CONCAT('%',:nombre_trabajador, '%'))) " +
            "AND (:primer_apellido_trabajador IS NULL OR :primer_apellido_trabajador = '' OR LOWER(t.primer_apellido_trabajador) LIKE LOWER(CONCAT('%',:primer_apellido_trabajador, '%'))) " +
            "AND (:segundo_apellido_trabajador IS NULL OR :segundo_apellido_trabajador = '' OR LOWER(t.segundo_apellido_trabajador) LIKE LOWER(CONCAT('%',:segundo_apellido_trabajador, '%'))) " +
            "AND (:fecha_inicio_trabajador IS NULL OR :fecha_inicio_trabajador = '' OR CAST(t.fecha_inicio_trabajador AS string) LIKE CONCAT('%', :fecha_inicio_trabajador, '%')) " +
            "AND (:fecha_nacimiento_trabajador IS NULL OR :fecha_nacimiento_trabajador = '' OR CAST(t.fecha_nacimiento_trabajador AS string) LIKE CONCAT('%', :fecha_nacimiento_trabajador, '%')) " +
            "AND (:mail_trabajador IS NULL OR :mail_trabajador = '' OR LOWER(t.mail_trabajador) LIKE LOWER(CONCAT('%',:mail_trabajador, '%'))) " +
            "AND (:celular_trabajador IS NULL OR :celular_trabajador = '' OR LOWER(CAST(t.celular_trabajador AS string)) LIKE LOWER(CONCAT('%',:celular_trabajador, '%'))) " +
            "ORDER BY t.id_trabajador DESC")
    List<Trabajador> findTrabajadorByAll(
            @Param("cedula_trabajador") String cedula_trabajador,
            @Param("nombre_trabajador") String nombre_trabajador,
            @Param("primer_apellido_trabajador") String primer_apellido_trabajador,
            @Param("segundo_apellido_trabajador") String segundo_apellido_trabajador,
            @Param("fecha_inicio_trabajador") String fecha_inicio_trabajador,
            @Param("fecha_nacimiento_trabajador") String fecha_nacimiento_trabajador,
            @Param("mail_trabajador") String mail_trabajador,
            @Param("celular_trabajador") String celular_trabajador);
}
