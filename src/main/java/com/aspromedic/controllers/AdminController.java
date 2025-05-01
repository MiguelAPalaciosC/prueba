package com.aspromedic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	

	@GetMapping("/registroUsuario")
	public String mostrarFormulario() {
		return "admin/registroUsuario";
	}
	
	@GetMapping("/index")
	public String irAlInicio() {
		return "admin/index";
	}
}
