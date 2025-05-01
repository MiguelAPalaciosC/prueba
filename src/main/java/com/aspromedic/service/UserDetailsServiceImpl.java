package com.aspromedic.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aspromedic.model.Usuario;
import com.aspromedic.model.dao.IUsuarioDao;
import com.aspromedic.response.UsuarioResponseRest;

import jakarta.servlet.http.HttpSession;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Inicio del método loadUserByUsername()");

		try {
			List<Usuario> list = usuarioDao.findByUsername(username);

			if (!list.isEmpty()) {
				Usuario usuario = list.get(0); // Obtener el usuario de la lista

				// Obtener los roles del usuario correctamente
				List<GrantedAuthority> authorities = usuario.getRoles().stream()
						.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNombre())) // Prefijo "ROLE_"
						.collect(Collectors.toList());

				// Log para verificar el usuario y sus roles
				log.info("Usuario autenticado: {} - Roles: {}", usuario.getUsername(), authorities);

				// Guardar el ID del usuario en sesión
				session.setAttribute("user_session_id", usuario.getId());

				return User.builder()
						.username(usuario.getUsername())
						.password(usuario.getPassword())
						.authorities(authorities) // Aquí pasamos los roles corregidos
						.build();
			} else {
				log.error("Usuario no encontrado en la base de datos");
				throw new UsernameNotFoundException("Usuario no encontrado");
			}
		} catch (Exception e) {
			log.error("Error al consultar usuario: {}", e.getMessage());
			throw new RuntimeException("Error en la autenticación", e);
		}
	}

}
