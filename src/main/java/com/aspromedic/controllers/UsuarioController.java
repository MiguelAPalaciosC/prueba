package com.aspromedic.controllers;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspromedic.dto.UsuarioRegistroDTO;
import com.aspromedic.response.UsuarioResponseRest;
import com.aspromedic.service.IUsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class UsuarioController {

	@Autowired(required = true)
	private IUsuarioService service;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevousuarioDTO() {
		return new UsuarioRegistroDTO();
	}

	@GetMapping("/admin/usuarios")
	public String listarUsuarios(HttpServletRequest request, Model model) {
		Object userIdAttribute = request.getSession().getAttribute("user_session_id");

		if (userIdAttribute == null) {
			return "redirect:/login";
		}

		ResponseEntity<UsuarioResponseRest> response = service.findAll();

		// Agregar log para depuración
		System.out.println("Response: {}" + response);

		if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
				&& response.getBody().getUsuarioResponse() != null) {

			model.addAttribute("usuarios", response.getBody().getUsuarioResponse().getUsuario());
		} else {
			model.addAttribute("usuarios", Collections.emptyList());
		}

		return "admin/usuarios"; // Renderiza listarUsuarios.html en /templates/
	}

	@PostMapping("/admin/registroUsuario")
	public String registrarCuentaDeUsuario(UsuarioRegistroDTO registro,
			@RequestParam("tipo_usuario") Integer tipo_usuario) {

		registro.setPassword(passwordEncoder.encode(registro.getPassword()));

		ResponseEntity<UsuarioResponseRest> response = service.save(registro, tipo_usuario);

		if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
			return "redirect:/admin/registroUsuario?exito";
		}

		return "redirect:/admin/registroUsuario?error";
	}

	@PostMapping("/admin/actualizarUsuario/{id}")
	public String actualizarUsuario(@PathVariable Long id, UsuarioRegistroDTO usuario, HttpSession session) {
		Object userIdAttribute = session.getAttribute("user_session_id");

		if (userIdAttribute == null) {
			return "redirect:/login";
		}

		ResponseEntity<UsuarioResponseRest> response = service.updateUser(usuario, id);

		if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
			return "redirect:/admin/usuario/" + id + "?exito";
		}
		return "redirect:/admin/usuario/" + id + "?error";
	}

	@GetMapping("/admin/usuario/{id}")
	public String editarUsuario(@PathVariable Long id, Model model, HttpSession session) throws IOException {
		Object userIdAttribute = session.getAttribute("user_session_id");

		if (userIdAttribute == null) {
			return "redirect:/login";
		}

		ResponseEntity<UsuarioResponseRest> response = service.getUserById(id);

		if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
				&& response.getBody().getUsuarioResponse() != null
				&& response.getBody().getUsuarioResponse().getUsuario() != null
				&& !response.getBody().getUsuarioResponse().getUsuario().isEmpty()) {

			model.addAttribute("usuario", response.getBody().getUsuarioResponse().getUsuario().get(0));
		} else {
			model.addAttribute("usuario", new UsuarioRegistroDTO());
		}

		return "/admin/usuario";

	}

	@GetMapping(value = { "/login", "/" })
	public String home() {
		return "login"; // Renderiza index.html en /templates/
	}

	@GetMapping("/access")
	public String access(HttpSession session) {
		Object userIdAttribute = session.getAttribute("user_session_id");

		if (userIdAttribute == null) {
			return "redirect:/login";
		}

		try {
			Long userId = Long.parseLong(userIdAttribute.toString());
			ResponseEntity<UsuarioResponseRest> response = service.getUserById(userId);

			if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
					&& response.getBody().getUsuarioResponse() != null
					&& response.getBody().getUsuarioResponse().getUsuario() != null
					&& !response.getBody().getUsuarioResponse().getUsuario().isEmpty()) {

				session.setAttribute("user_session_id",
						response.getBody().getUsuarioResponse().getUsuario().get(0).getId());

				return "redirect:/admin/index";
			}
		} catch (NumberFormatException e) {
			// Si el valor en sesión no es un número válido, lo redirigimos al login
			return "redirect:/login";
		} catch (Exception e) {
			// Captura cualquier otro error inesperado
			e.printStackTrace(); // O log.error("Error obteniendo usuario", e);
		}

		return "redirect:/login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}
		return "redirect:/login";
	}

}
