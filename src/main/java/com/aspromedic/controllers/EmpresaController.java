package com.aspromedic.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspromedic.dto.EmpresaRegistroDTO;
import com.aspromedic.model.Ciudad;
import com.aspromedic.model.Contacto;
import com.aspromedic.model.Departamento;
import com.aspromedic.model.Empresa;
import com.aspromedic.model.dao.IDosimetroDao;
import com.aspromedic.response.CiudadResponseRest;
import com.aspromedic.response.ContactoResponse;
import com.aspromedic.response.ContactoResponseRest;
import com.aspromedic.response.DepartamentoResponseRest;
import com.aspromedic.response.DosimetroResponseRest;
import com.aspromedic.response.EmpresaResponseRest;
import com.aspromedic.service.ICiudadService;
import com.aspromedic.service.IContactoService;
import com.aspromedic.service.IDepartamentoService;
import com.aspromedic.service.IDosimetroService;
import com.aspromedic.service.IEmpresaService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/gestion")
public class EmpresaController {

	private Logger log = LoggerFactory.getLogger(EmpresaController.class);

	@Autowired
	private IEmpresaService service;

	@Autowired
	private IContactoService serviceContacto;

	@Autowired
	private ICiudadService ciudadService;

	@Autowired
	private IDepartamentoService departamentoService;

	@Autowired
	private IDosimetroService dosimetroService;

	@GetMapping("/empresa/{id}")
	public String detalle(@PathVariable Long id, Model model, HttpSession session) throws IOException {
		Object userIdAttribute = session.getAttribute("user_session_id");

		if (userIdAttribute == null) {
			return "login";
		}

		ResponseEntity<EmpresaResponseRest> response = service.buscarPorId(id);

		if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
			Empresa empresa = response.getBody().getEmpresaResponse().getEmpresas().get(0);
			// Formatear LocalDateTime a String
			if (empresa.getFecha_vin_empresa() != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String fechaVin = empresa.getFecha_vin_empresa().format(formatter); // Formatear a String
				model.addAttribute("fechaVinEmpresa", fechaVin); // Pasar el String al modelo
			}

			model.addAttribute("empresa", empresa);

			ResponseEntity<ContactoResponseRest> responseContactos = serviceContacto.findByEmpresa(id);

			if (responseContactos.getStatusCode() == HttpStatus.OK && responseContactos.getBody() != null) {
				List<Contacto> contactos = responseContactos.getBody().getContacto().getContactos();

				model.addAttribute("contactos", contactos);
			} else {
				model.addAttribute("contactos", Collections.emptyList());
			}

			ResponseEntity<DepartamentoResponseRest> responseDepartamentos = departamentoService.buscarDepartamento();

			if (responseDepartamentos.getStatusCode() == HttpStatus.OK && responseDepartamentos.getBody() != null) {
				List<Departamento> departamentos = responseDepartamentos.getBody().getDepartamentoResponse()
						.getDepartamentos();
				System.out.println("Departamentos cargados: " + departamentos); // Imprime en consola
				model.addAttribute("departamentos", departamentos);
			} else {
				model.addAttribute("departamentos", Collections.emptyList());
			}

		} else {
			model.addAttribute("empresa", Collections.emptyList());
		}

		return "/admin/empresa";
	}

	@PostMapping("/guardarEmpresa")
	public String crear(@RequestParam Map<String, String> params,
			@RequestParam(name = "nombre_persona[]", required = false) List<String> nombres,
			@RequestParam(name = "apellido_persona[]", required = false) List<String> apellidos,
			@RequestParam(name = "cargo_persona[]", required = false) List<String> cargos,
			@RequestParam(name = "telefono_persona[]", required = false) List<String> celulares,
			@RequestParam(name = "correo_persona[]", required = false) List<String> correos) {
		try {
			log.info("post crear empresa");
			System.out.println("JSON recibido: " + params);
			System.out.println("Nombres: " + nombres);
			System.out.println("Apellidos: " + apellidos);
			System.out.println("Cargos: " + cargos);
			System.out.println("Celulares: " + celulares);
			System.out.println("Correos: " + correos);

			LocalDate fecha = LocalDate.parse(params.get("fecha_vinculacion"), DateTimeFormatter.ISO_LOCAL_DATE);
			LocalDateTime fechaVinculacion = fecha.atStartOfDay();

			Long id_ciudad = Long.parseLong(params.get("ciudad"));

			ResponseEntity<CiudadResponseRest> responseCiudad = ciudadService.buscarCiudadPorId(id_ciudad);

			if (responseCiudad.getStatusCode() == HttpStatus.OK && responseCiudad.getBody() != null) {
				Ciudad ciudad = responseCiudad.getBody().getCiudadResponse().getCiudades().get(0);

				String dvStr = params.get("dv");

				Integer dv = (dvStr != null && !dvStr.trim().isEmpty()) ? Integer.parseInt(dvStr) : null;

				Empresa empresa = new Empresa(
						Long.parseLong(params.get("codigo")),
						Long.parseLong(params.get("nit")),
						dv,
						params.get("razon_social"),
						params.get("direccion"),
						params.get("telefono"),
						null,
						params.get("celular"),
						fechaVinculacion,
						params.get("estado"),
						params.get("email"),
						Long.parseLong(params.get("departamento")),
						params.get("observaciones_empresa"),
						ciudad);

				// Aquí podrías guardar los datos en la base de datos...

				ResponseEntity<EmpresaResponseRest> empresaGuardada = service.crear(empresa);

				// Verificar si la empresa se guardó correctamente
				if (empresaGuardada.getStatusCode() == HttpStatus.OK && empresaGuardada.getBody() != null) {
					ResponseEntity<EmpresaResponseRest> ultimaEmpresa = service.buscarUltimaEmpresa();

					if (ultimaEmpresa.getStatusCode() == HttpStatus.OK &&
							ultimaEmpresa.getBody() != null &&
							ultimaEmpresa.getBody().getEmpresaResponse() != null) { // Verificar que hay contactos

						log.info("Ultima empresa guardada: "
								+ empresaGuardada.getBody().getEmpresaResponse().getEmpresas().get(0).getId_empresa());

						Long idEmpresa = ultimaEmpresa.getBody().getEmpresaResponse().getEmpresas().get(0)
								.getId_empresa();

						// Tomar el tamaño mínimo para evitar IndexOutOfBoundsException
						int size = List.of(nombres, apellidos, cargos, celulares, correos)
								.stream()
								.mapToInt(List::size)
								.max()
								.orElse(0);

						List<Contacto> contactos = IntStream.range(0, size)
								.mapToObj(i -> new Contacto(
										idEmpresa,
										i < nombres.size() ? nombres.get(i) : null,
										i < apellidos.size() && !apellidos.get(i).isEmpty() ? apellidos.get(i) : null,
										i < cargos.size() && !cargos.get(i).isEmpty() ? cargos.get(i) : null,
										i < celulares.size() && !celulares.get(i).isEmpty() ? celulares.get(i) : null,
										i < correos.size() && !correos.get(i).isEmpty() ? correos.get(i) : null))
								.filter(contacto -> contacto.getNombre() != null && !contacto.getNombre().isEmpty()) // ✅
																														// Filtrar
																														// contactos
																														// sin
																														// nombre
								.collect(Collectors.toList());

						// Guardar los contactos en la base de datos si el service lo permite
						List<ResponseEntity<ContactoResponseRest>> responses = contactos.stream()
								.map(serviceContacto::save)
								.collect(Collectors.toList());

						return "gestion/empresas?exito";
					}
				}
			}

			return "gestion/empresas?error";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@PostMapping("/actualizarEmpresa/{id}")
	public String actualizarEmpresa(
			@PathVariable Long id,
			@RequestParam Map<String, String> params,
			@RequestParam(name = "nombre_persona[]", required = false) List<String> nombres,
			@RequestParam(name = "apellido_persona[]", required = false) List<String> apellidos,
			@RequestParam(name = "cargo_persona[]", required = false) List<String> cargos,
			@RequestParam(name = "telefono_persona[]", required = false) List<String> celulares,
			@RequestParam(name = "correo_persona[]", required = false) List<String> correos) {
		try {
			log.info("post actualizar empresa");
			System.out.println("JSON recibido: " + params);
			System.out.println("Nombres: " + nombres);
			System.out.println("Apellidos: " + apellidos);
			System.out.println("Cargos: " + cargos);
			System.out.println("Celulares: " + celulares);
			System.out.println("Correos: " + correos);

			LocalDate fecha = LocalDate.parse(params.get("fecha_vinculacion"), DateTimeFormatter.ISO_LOCAL_DATE);
			LocalDateTime fechaVinculacion = fecha.atStartOfDay();

			Long id_ciudad = Long.parseLong(params.get("ciudad"));

			ResponseEntity<CiudadResponseRest> responseCiudad = ciudadService.buscarCiudadPorId(id_ciudad);

			if (responseCiudad.getStatusCode() == HttpStatus.OK && responseCiudad.getBody() != null) {
				Ciudad ciudad = responseCiudad.getBody().getCiudadResponse().getCiudades().get(0);

				String dvStr = params.get("dv");

				Integer dv = (dvStr != null && !dvStr.trim().isEmpty()) ? Integer.parseInt(dvStr) : null;

				Empresa empresa = new Empresa(
						Long.parseLong(params.get("codigo")),
						Long.parseLong(params.get("nit")),
						dv,
						params.get("razon_social"),
						params.get("direccion"),
						params.get("telefono"),
						null,
						params.get("celular"),
						fechaVinculacion,
						params.get("estado"),
						params.get("email"),
						Long.parseLong(params.get("departamento")),
						params.get("observaciones_empresa"),
						ciudad);

				// Aquí podrías guardar los datos en la base de datos...

				ResponseEntity<EmpresaResponseRest> empresaGuardada = service.actualizar(empresa, id);

				// Verificar si la empresa se guardó correctamente
				if (empresaGuardada.getStatusCode() == HttpStatus.OK && empresaGuardada.getBody() != null) {

					ResponseEntity<ContactoResponseRest> eliminarContacto = serviceContacto.delete(id);

					if (eliminarContacto.getStatusCode() == HttpStatus.OK) {

						// Tomar el tamaño mínimo para evitar IndexOutOfBoundsException
						int size = List.of(nombres, apellidos, cargos, celulares, correos)
								.stream()
								.mapToInt(List::size)
								.max()
								.orElse(0);

						List<Contacto> contactos = IntStream.range(0, size)
								.mapToObj(i -> new Contacto(
										id,
										i < nombres.size() ? nombres.get(i) : null,
										i < apellidos.size() && !apellidos.get(i).isEmpty() ? apellidos.get(i) : null,
										i < cargos.size() && !cargos.get(i).isEmpty() ? cargos.get(i) : null,
										i < celulares.size() && !celulares.get(i).isEmpty() ? celulares.get(i) : null,
										i < correos.size() && !correos.get(i).isEmpty() ? correos.get(i) : null))
								.filter(contacto -> contacto.getNombre() != null && !contacto.getNombre().isEmpty())
								.collect(Collectors.toList());

						// Guardar los contactos en la base de datos si el service lo permite
						List<ResponseEntity<ContactoResponseRest>> responses = contactos.stream()
								.map(serviceContacto::save)
								.collect(Collectors.toList());

						return "gestion/empresas?exitoUpdate";
					}
				}

			}

			return "gestion/empresas?errorUpdate";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@PostMapping("/deleteEmpresa")
	public String eliminarEmpresa(@RequestParam("id_empresa") Long id, HttpSession session) throws IOException {
		Object userIdAttribute = session.getAttribute("user_session_id");

		if (userIdAttribute == null) {
			return "login";
		}

		ResponseEntity<DosimetroResponseRest> respDosimetro = dosimetroService.findByEmpresa(id);
		if (respDosimetro.getStatusCode() == HttpStatus.OK && respDosimetro.getBody() != null
				&& respDosimetro.getBody().getDosimetroResponse() != null
				&& !respDosimetro.getBody().getDosimetroResponse().getDosimetros().isEmpty()) {
			return "gestion/empresa/" + id + "?relacionado";
		}

		ResponseEntity<EmpresaResponseRest> response = service.eliminar(id);

		if (response.getStatusCode() == HttpStatus.OK) {
			return "gestion/empresas?exitoDelete";
		}
		return "gestion/empresas?errorDelete";
	}

	@GetMapping("/empresas")
	public String getListadoEmpresas(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int size, Model model, HttpSession session) {

		Object userIdAttribute = session.getAttribute("user_session_id");

		if (userIdAttribute == null) {
			return "login";
		}

		Pageable pageable = PageRequest.of(page, size);
		ResponseEntity<EmpresaResponseRest> response = service.buscarEmpresas(pageable);

		if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null
				&& response.getBody().getEmpresaResponse() != null) {

			List<Empresa> empresas = response.getBody().getEmpresaResponse().getEmpresas();
			model.addAttribute("empresas", empresas);

			// Datos de paginación
			long totalElements = response.getBody().getEmpresaResponse().getTotalElements();
			int totalPages = response.getBody().getEmpresaResponse().getTotalPages();

			model.addAttribute("currentPage", page);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("totalElements", totalElements);
			model.addAttribute("pageSize", size);

			return "/admin/empresas";
		}

		return "error";
	}

	@GetMapping("/codigo")
	public ResponseEntity<?> buscarPorCodigoEmpresa(@RequestParam String codigo) {

		Long codigo_empresa = Long.parseLong(codigo);
		ResponseEntity<EmpresaResponseRest> response = service.buscarCodigoEmpresaDuplicada(codigo_empresa);

		if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
			return ResponseEntity.ok(response.getBody().getEmpresaResponse().getEmpresas());
		}

		return ResponseEntity.ok(List.of());
	}

	@GetMapping("/misContactos")
	public ResponseEntity<?> misContactos(@RequestParam("id_empresa") Long id_empresa) {
		try {
			ResponseEntity<ContactoResponseRest> response = serviceContacto.findByEmpresa(id_empresa);

			if (response.getStatusCode() == HttpStatus.OK) {
				List<Contacto> contactos = response.getBody() != null ? response.getBody().getContacto().getContactos()
						: Collections.emptyList();
				return ResponseEntity.ok(contactos);
			}

			return ResponseEntity.status(response.getStatusCode())
					.body(Collections.singletonMap("error", "Error al buscar los contactos de la empresa"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("error", "Error interno del servidor: " + e.getMessage()));
		}
	}

	@GetMapping("/filtro/empresas")
	public ResponseEntity<?> buscarEmpresasPorFiltro(@RequestParam Map<String, String> params,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size) {

		Pageable pageable = PageRequest.of(page, size);

		// Verificar si todos los parámetros están vacíos
		if (todosLosParametrosEstanVacios(params)) {
			return buscarTodasLasEmpresas(pageable);
		}

		// Buscar empresas con filtros
		return buscarEmpresasConFiltros(params);
	}

	private boolean todosLosParametrosEstanVacios(Map<String, String> params) {
		// Verificar si todos los valores en el mapa son nulos o vacíos
		return params.values().stream().allMatch(value -> value == null || value.trim().isEmpty());
	}

	private ResponseEntity<?> buscarTodasLasEmpresas(Pageable pageable) {
		ResponseEntity<EmpresaResponseRest> response = service.buscarEmpresas(pageable);
		return procesarRespuesta(response);
	}

	private ResponseEntity<?> buscarEmpresasConFiltros(Map<String, String> params) {
		ResponseEntity<EmpresaResponseRest> response = service.findByAllTabla(
				params.get("codigo_interno_empresa"),
				params.get("nit_empresa"),
				params.get("razon_social_empresa"),
				params.get("nombre_ciudad"),
				params.get("telefono_empresa"),
				params.get("celular_empresa"),
				params.get("mail_empresa"),
				params.get("estado_empresa"));

		return procesarRespuesta(response);
	}

	private ResponseEntity<?> procesarRespuesta(ResponseEntity<EmpresaResponseRest> response) {
		if (response.getStatusCode() == HttpStatus.OK) {
			List<Empresa> empresas = response.getBody().getEmpresaResponse().getEmpresas();
			System.out.println("Empresas cargadas: " + empresas); // Imprime en consola

			// Convertir la lista de Empresa a EmpresaRegistroDTO
			List<EmpresaRegistroDTO> empresaRegistroDTOs = empresas.stream()
					.map(Empresa::toRegistroDTO) // Asumiendo que tienes el método toRegistroDTO en la clase Empresa
					.collect(Collectors.toList());

			return ResponseEntity.ok(empresaRegistroDTOs);
		}
		return ResponseEntity.ok(Collections.emptyList());
	}

}
