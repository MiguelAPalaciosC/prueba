package com.aspromedic.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aspromedic.model.Ciudad;
import com.aspromedic.model.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	@Query("SELECT u FROM Usuario u WHERE u.username = :username")
	List<Usuario> findByUsername(String username); 
}
