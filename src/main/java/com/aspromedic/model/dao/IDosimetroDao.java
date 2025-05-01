package com.aspromedic.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aspromedic.model.Dosimetro;
import com.aspromedic.model.Empresa;

public interface IDosimetroDao extends CrudRepository<Dosimetro, Long> {

    @Query("SELECT d FROM Dosimetro d ORDER BY d.id_dosimetro DESC")
    Page<Dosimetro> findAllDosimetro(Pageable pageable);

    @Query("SELECT d FROM Dosimetro d WHERE d.id_dosimetro = :id_dosimetro AND d.estado_dosimetro = 'A'")
    List<Dosimetro> findByDosimetroActivo(Long id_dosimetro);

    @Query("SELECT d FROM Dosimetro d WHERE d.codigo_dosimetro = :codigo_dosimetro")
    List<Dosimetro> findByCodigoDosimetro(String codigo_dosimetro);

    @Query("SELECT d FROM Dosimetro d WHERE d.id_empresa = :id_empresa")
    List<Dosimetro> findByEmpresa(Long id_empresa);

    @Query("SELECT d FROM Dosimetro d WHERE d.id_trabajador.id_trabajador = :id_trabajador")
    List<Dosimetro> findByTrabajadorDosimetro(Long id_trabajador);

    @Query("SELECT d FROM Dosimetro d WHERE d.practica.id_practica = :id_practica")
    List<Dosimetro> findByPracticaDosimetro(Long id_practica);

    @Query("SELECT d FROM Dosimetro d WHERE d.radiacion.id_radiacion = :id_radiacion")
    List<Dosimetro> findByRadiacionDosimetro(Long id_radiacion);

    @Query("SELECT d FROM Dosimetro d WHERE d.ubicacion.id_ubicacion = :id_ubicacion")
    List<Dosimetro> findByUbicacionDosimetro(Long id_ubicacion);

    @Query("SELECT d FROM Dosimetro d WHERE d.cargo.id_cargo = :id_cargo")
    List<Dosimetro> findByCargoDosimetro(Long id_cargo);

    @Query("SELECT d FROM Dosimetro d WHERE d.ingeominas.id_geominas = :id_geominas")
    List<Dosimetro> findByIngeominaDosimetro(Long id_geominas);

    @Query("SELECT d FROM Dosimetro d WHERE d.tipo_dosimetro.id_tipo_dosimetro = :id_tipo_dosimetro")
    List<Dosimetro> findByTipoDosimetroDosimetro(Long id_tipo_dosimetro);

    @Query("SELECT d FROM Dosimetro d WHERE d.periodo_uso = :periodo_uso AND d.id_empresa = :id_empresa AND d.tipo_dosimetro.id_tipo_dosimetro = :tipo_dosimetro")
    List<Dosimetro> findByDosimetrosForContrato(
            @Param("periodo_uso") String periodoUso,
            @Param("id_empresa") Long idEmpresa,
            @Param("tipo_dosimetro") Long tipoDosimetro);

}
