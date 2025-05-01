package com.aspromedic.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Empresa;

public interface IEmpresaDao extends CrudRepository<Empresa, Long>{

	
	@Query("SELECT e FROM Empresa e ORDER BY e.id_empresa DESC")
	Page<Empresa> findAllEmpresa(Pageable pageable);

	@Query("SELECT e FROM Empresa e where LOWER(CAST(e.codigo_interno_empresa AS string)) like LOWER(CONCAT('%', :codigo_interno_empresa, '%'))")
	List<Empresa> findByCodigoEmpresa(String codigo_interno_empresa);

	@Query("SELECT e FROM Empresa e where LOWER(CAST(e.nit_empresa AS string)) like LOWER(CONCAT('%', :nit_empresa, '%'))")
	List<Empresa> findByNitEmpresa(String nit_empresa);

	@Query("SELECT e FROM Empresa e where LOWER(CAST(e.razon_social_empresa AS string)) like LOWER(CONCAT('%', :razon_social_empresa, '%'))")
	List<Empresa> findByRazonSocial(String razon_social_empresa);

	@Query("SELECT e FROM Empresa e where LOWER(CAST(e.telefono_empresa AS string)) like LOWER(CONCAT('%', :telefono_empresa, '%'))")
	List<Empresa> findByTelefonoEmpresa(String telefono_empresa);

	@Query("SELECT e FROM Empresa e where LOWER(CAST(e.celular_empresa AS string)) like LOWER(CONCAT('%', :celular_empresa, '%'))")
	List<Empresa> findByCelularEmpresa(String celular_empresa);

	@Query("SELECT e FROM Empresa e where LOWER(CAST(e.estado_empresa AS string)) like LOWER(CONCAT('%', :estado_empresa, '%'))")
	List<Empresa> findByEstadoEmpresa(String estado_empresa);

	@Query("SELECT e FROM Empresa e where LOWER(CAST(e.mail_empresa AS string)) like LOWER(CONCAT('%', :mail_empresa, '%'))")
	List<Empresa> findByMailEmpresa(String mail_empresa);

	@Query("SELECT e FROM Empresa e WHERE e.codigo_interno_empresa = :codigo_interno_empresa")
	List<Empresa> findByCodigoDuplicate(Long codigo_interno_empresa);

	@Query("SELECT e FROM Empresa e WHERE e.departamento = :departamento")
    List<Empresa> findByDepartamentoDuplicate(Long departamento);

	@Query("SELECT e FROM Empresa e WHERE e.ciudad.id_ciudad = :ciudad")
    List<Empresa> findByCiudadDuplicate(Long ciudad);

	@Query("SELECT e FROM Empresa e WHERE LOWER(CAST(e.codigo_interno_empresa AS string)) like LOWER(CONCAT('%', :codigo_interno_empresa, '%'))" +
	      								"AND LOWER(CAST(e.nit_empresa AS string)) like LOWER(CONCAT('%', :nit_empresa, '%'))" +
										"AND LOWER(CAST(e.razon_social_empresa AS string)) like LOWER(CONCAT('%', :razon_social_empresa, '%'))"+
										"AND LOWER(CAST(e.ciudad.nombre_ciudad AS string)) like LOWER(CONCAT('%', :nombre_ciudad, '%'))" +
										"AND LOWER(CAST(e.telefono_empresa AS string)) like LOWER(CONCAT('%', :telefono_empresa, '%'))" +
										"AND LOWER(CAST(e.celular_empresa AS string)) like LOWER(CONCAT('%', :celular_empresa, '%'))" +
										"AND LOWER(CAST(e.mail_empresa AS string)) like LOWER(CONCAT('%', :mail_empresa, '%'))" +
										"AND LOWER(CAST(e.estado_empresa AS string)) like LOWER(CONCAT('%', :estado_empresa, '%'))")
	List<Empresa> findByAllTabla(String codigo_interno_empresa, String nit_empresa, String razon_social_empresa, String nombre_ciudad, String telefono_empresa, String celular_empresa, String mail_empresa, String estado_empresa);



	
}
