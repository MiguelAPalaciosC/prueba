$(document).ready(function () {
	var toastElements = document.querySelectorAll('.toast');
	toastElements.forEach(function (toastEl) {
		var toast = new bootstrap.Toast(toastEl, {
			delay: 10000 // Tiempo en milisegundos (5000 ms = 5 segundos)
		});
		toast.show();
	});

	$('.select2').select2({
		theme: "bootstrap-5",
		width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
		placeholder: $(this).data('placeholder'),
		allowClear: true
	});

	let fechaRaw1 = $('#fecha_inicio_contrato').val();
	let meses = parseInt($("#duracion_contrato").val());

	if (fechaRaw1 && !isNaN(meses)) {
		$("#fecha_fin_contrato").val(sumarMeses(fechaRaw1, meses));
	}


	if ($('#estado_contrato_select').val()) {
		$('#estado_contrato').val($('#estado_contrato_select').val()).trigger('change');
	}

	$("#fecha_inicio_contrato").change(function () {
		var fecha_inicio = $(this).val();
		var duracion_contrato = parseInt($("#duracion_contrato").val()); // Asegúrate de que sea un número

		if (duracion_contrato) {
			$("#fecha_fin_contrato").val(sumarMeses(fecha_inicio, duracion_contrato)); // Sumar meses a la fecha de inicio
		} else {
			$("#fecha_fin_contrato").val(''); // Limpiar si no hay duración
		}
	});

	if ($("#valor_factura").val()) {
		let input = $("#valor_factura");
		let valor = input.val().replace(/\D/g, ""); // Eliminar caracteres no numéricos

		// Si el input está vacío, evita que se agregue cualquier otro carácter
		if (valor === "") {
			input.val(""); // Mantiene el input vacío sin formato
			return;
		}

		// Formatear con separadores de miles
		let formato = Number(valor).toLocaleString("es-CO");

		// Restaurar el valor formateado
		input.val(formato);
	}

	$("#valor_factura").on("input", function () {
		let input = $(this);
		let valor = input.val().replace(/\D/g, ""); // Eliminar caracteres no numéricos

		// Si el input está vacío, evita que se agregue cualquier otro carácter
		if (valor === "") {
			input.val(""); // Mantiene el input vacío sin formato
			return;
		}

		// Formatear con separadores de miles
		let formato = Number(valor).toLocaleString("es-CO");

		// Restaurar el valor formateado
		input.val(formato);
	});

	$("#valor_factura").change(function () {
		var valor_factura = $(this).val();
		valor_factura = valor_factura.replace(/\./g, ""); // Eliminar puntos

		$('#valor_factura_contrato').val(valor_factura); // Asignar el valor sin puntos al campo oculto

		if (valor_factura < 0) {
			$(this).val(0); // Establecer el valor mínimo en 0
		}
	});

	$("#duracion_contrato").change(function () {
		var duracion_contrato = parseInt($(this).val()); // Asegúrate de que sea un número
		var fecha_inicio = $("#fecha_inicio_contrato").val();

		if (fecha_inicio) {
			$("#fecha_fin_contrato").val(sumarMeses(fecha_inicio, duracion_contrato)); // Sumar meses a la fecha de inicio
		} else {
			$("#fecha_fin_contrato").val(''); // Limpiar si no hay fecha de inicio
		}
	});

	$("#tipo_contratacion_factura").change(function () {
		var tipo_contratacion_factura = $(this).val();
		if (tipo_contratacion_factura == "Beneficiario") {
			$("#divFacturaA").show();
		} else {
			$("#divFacturaA").hide();
		}

		if (tipo_contratacion_factura == "OS" || tipo_contratacion_factura == "OC" || tipo_contratacion_factura == "Contrato") {
			$("#identificacion_contratacion").attr("required", "required");
		} else {
			$("#identificacion_contratacion").removeAttr("required");
		}
	});

	$("#cuotas_contrato").change(function () {
		var cuotas_contrato = $(this).val();
		if (cuotas_contrato < 1) {
			$(this).val(1);
		}
	});

	$("#numero_usuarios").change(function () {
		var numero_usuarios = $(this).val();
		if (numero_usuarios < 1) {
			$(this).val(1);
		}
	});

	if ($('#tipo_dosimetro_select').val()) {
		var tipoDosimetro = $('#tipo_dosimetro_select').val();
		var aux_tipo = tipoDosimetro.split(",");
		for (var i = 0; i < aux_tipo.length; i++) {
			console.log(aux_tipo[i]);
			aux_tipo[i] = aux_tipo[i].trim(); // Eliminar espacios en blanco

			if ($("#tipo_" + aux_tipo[i]).val()) {
				$("#tipo_" + aux_tipo[i]).prop("checked", true);
			}
		}
	}

	validarDosimetrosAgregados();

	function sumarMeses(fechaStr, meses) {
		let [año, mes, dia] = fechaStr.split("-").map(Number);

		console.log("fechaStr: " + fechaStr);
		console.log("meses: " + meses);


		if (dia !== 1 && dia !== 14 && dia !== 15) {
			return "Solo se permiten fechas con día 1 o 15.";
		}

		let fecha = new Date(año, mes - 1, 1); // Normalizamos al día 1

		fecha.setMonth(fecha.getMonth() + meses);

		if (dia === 1) {
			// Obtener último día del mes anterior
			fecha.setDate(0);
		} else {
			// Mantener el día 14 o 15 (14 en tu lógica original)
			fecha.setDate(14);
		}

		let resultado = fecha.toISOString().split("T")[0];


		return resultado;
	}

	function actualizarContador() {
		$('#tbodyDosimetros tr:visible').each(function (index) {
			$(this).find('td').eq(0).text(index + 1);
		});
	}

	function actualizarContador2() {
		$('#tbodyDosimetros tr:visible').each(function (index) {
			$(this).find('td').eq(1).text(index + 1);
		});
	}

	// Filtrar por descripción
	$('#filtro1_1').on('keyup', function () {
		var valorBuscar2 = $(this).val().toLowerCase();
		$('#tbodyDosimetros tr').filter(function () {
			$(this).toggle($(this).find('td').eq(1).text().toLowerCase().indexOf(valorBuscar2) > -1);
		});
		actualizarContador();
	});

	// Filtrar por descripción
	$('#filtro1_2').on('keyup', function () {
		var valorBuscar2 = $(this).val().toLowerCase();
		$('#tbodyDosimetros tr').filter(function () {
			$(this).toggle($(this).find('td').eq(2).text().toLowerCase().indexOf(valorBuscar2) > -1);
		});
		actualizarContador();
	});

	// Filtrar por descripción
	$('#filtro1_3').on('keyup', function () {
		var valorBuscar3 = $(this).val().toLowerCase();
		$('#tbodyDosimetros tr').filter(function () {
			$(this).toggle($(this).find('td').eq(3).text().toLowerCase().indexOf(valorBuscar3) > -1);
		});
		actualizarContador();
	});

	// Filtrar por descripción
	$('#filtro1_4').on('keyup', function () {
		var valorBuscar3 = $(this).val().toLowerCase();
		$('#tbodyDosimetros tr').filter(function () {
			$(this).toggle($(this).find('td').eq(4).text().toLowerCase().indexOf(valorBuscar3) > -1);
		});
		actualizarContador();
	});

	// Filtrar por descripción
	$('#filtro1_5').on('keyup', function () {
		var valorBuscar3 = $(this).val().toLowerCase();
		$('#tbodyDosimetros tr').filter(function () {
			$(this).toggle($(this).find('td').eq(5).text().toLowerCase().indexOf(valorBuscar3) > -1);
		});
		actualizarContador();
	});

	// Filtrar por descripción
	$('#filtro2_1').on('keyup', function () {
		var valorBuscar2 = $(this).val().toLowerCase();
		$('#tablaBodyEmpresaDosimetro tr').filter(function () {
			$(this).toggle($(this).find('td').eq(2).text().toLowerCase().indexOf(valorBuscar2) > -1);
		});
		actualizarContador2();
	});

	// Filtrar por descripción
	$('#filtro2_2').on('keyup', function () {
		var valorBuscar2 = $(this).val().toLowerCase();
		$('#tablaBodyEmpresaDosimetro tr').filter(function () {
			$(this).toggle($(this).find('td').eq(3).text().toLowerCase().indexOf(valorBuscar2) > -1);
		});
		actualizarContador2();
	});

	// Filtrar por descripción
	$('#filtro2_3').on('keyup', function () {
		var valorBuscar3 = $(this).val().toLowerCase();
		$('#tablaBodyEmpresaDosimetro tr').filter(function () {
			$(this).toggle($(this).find('td').eq(4).text().toLowerCase().indexOf(valorBuscar3) > -1);
		});
		actualizarContador2();
	});

	// Filtrar por descripción
	$('#filtro2_4').on('keyup', function () {
		var valorBuscar3 = $(this).val().toLowerCase();
		$('#tablaBodyEmpresaDosimetro tr').filter(function () {
			$(this).toggle($(this).find('td').eq(5).text().toLowerCase().indexOf(valorBuscar3) > -1);
		});
		actualizarContador2();
	});

	// Filtrar por descripción
	$('#filtro2_5').on('keyup', function () {
		var valorBuscar3 = $(this).val().toLowerCase();
		$('#tablaBodyEmpresaDosimetro tr').filter(function () {
			$(this).toggle($(this).find('td').eq(6).text().toLowerCase().indexOf(valorBuscar3) > -1);
		});
		actualizarContador2();
	});

});

function validarDosimetrosAgregados() {
	const $tbody = $("#tbodyDosimetros");
	const tiposEnTabla = new Set();

	// Leer todos los tipos en la columna 5 de la tabla (índice 4, ya que empieza desde 0)
	$tbody.find("tr").each(function () {
		const tipo = $(this).find("td").eq(4).text().trim();
		if (tipo) tiposEnTabla.add(tipo);
	});

	// Bloquear checkboxes según los tipos encontrados
	$("input[type='checkbox']").each(function () {
		const $checkbox = $(this);
		const tipo = $checkbox.val();

		if (tiposEnTabla.has(tipo)) {
			$checkbox.prop("checked", true);
			$checkbox.attr("data-fixed", "true");
			$checkbox.on("click", function (e) {
				e.preventDefault(); // Bloquea interacción
			});
		} else if ($checkbox.attr("data-fixed") === "true") {
			// Si ya no está presente en la tabla, quitar restricciones
			$checkbox.removeAttr("data-fixed");
			$checkbox.prop("checked", false);
			$checkbox.off("click");
		}
	});

	// Control visual del select2
	if (tiposEnTabla.size > 0) {
		$('#periodo_uso_contrato').on('select2:opening', function (e) {
			e.preventDefault();
		});
		$('#periodo_uso_contrato').next('.select2-container').addClass('select2-disabled');
	} else {
		// Si no hay tipos, permitir interacción nuevamente
		$('#periodo_uso_contrato').off('select2:opening');
		$('#periodo_uso_contrato').next('.select2-container').removeClass('select2-disabled');
	}
}


let lista_dosimetros = [];
$('#btnVerDosimetros').click(function () {
	var periodo_uso_contrato = $("#periodo_uso_contrato").val();
	var empresa = $("#empresa").val();
	var tipo_dosimetro = $(".tipo_contrato:checked").map(function () {
		return $(this).val();
	}).get().join(",");

	var tipo_dosimetro_select = $(".tipo_contrato:checked").map(function () {
		return $(this).val();
	}).get().join(", ");

	$('#tipo_dosimetro_select').val(tipo_dosimetro_select);

	var tipo_dosimetro_texto = $(".tipo_contrato:checked").map(function () {
		const id = $(this).attr("id");
		return $('label[for="' + id + '"]').text().trim();
	}).get().join(", ");

	$('.periodo').text(periodo_uso_contrato);
	$('.tipos_dosimetro').text(tipo_dosimetro_texto);

	console.log('change periodo_uso_contrato: ' + tipo_dosimetro);


	if (periodo_uso_contrato || periodo_uso_contrato > 0) {
		$.ajax({
			url: '/gestion4/filtrar/dosimetros',
			type: 'GET',
			data: { periodo_uso_contrato: periodo_uso_contrato, empresa: empresa, tipo_dosimetro: tipo_dosimetro },
			dataType: 'json',
			success: function (data) {
				console.log(data);
				// Aquí puedes manejar la respuesta y actualizar la UI según sea necesario
				const tableBody = $('#tablaBodyEmpresaDosimetro');
				tableBody.empty(); // Limpiar el contenido previo

				$.each(data, function (index, dosimetro) {
					const row = $('<tr></tr>');

					// Crear la celda del checkbox
					const checkboxCell = $('<td></td>');
					const checkbox = $('<input type="checkbox" class="form-check-input check-agregar-dosimetros" style="font-size: larger;">').val(dosimetro.id_dosimetro); // Valor del checkbox
					checkboxCell.append(checkbox);
					row.append(checkboxCell);

					// Crear las celdas restantes
					row.append($('<td></td>').text(index + 1));
					row.append($('<td></td>').text(dosimetro.codigo_dosimetro));
					row.append($('<td></td>').text(dosimetro.id_trabajador.cedula_trabajador)); // Asumiendo que este es el valor de la cédula
					row.append($('<td></td>').text(`${dosimetro.id_trabajador.nombre_trabajador} ${dosimetro.id_trabajador.primer_apellido_trabajador}`)); // Nombre y apellido del trabajador
					row.append($('<td></td>').text(dosimetro.estado_dosimetro));
					row.append($('<td></td>').text(dosimetro.tipo_dosimetro.nombre)); // Descripción del tipo de dosímetro

					// Agregar la fila al cuerpo de la tabla
					tableBody.append(row);
				});

				if (data.length === 0) {
					tableBody.append('<tr><td colspan="7" class="text-center">No hay dosimetros disponibles para esta empresa/contrato</td></tr>');
				}

				lista_dosimetros = data;

				$("#modalVerDosimetros").modal("show");

			},
			error: function (jqXHR, textStatus, errorThrown) {
				// Imprimir el objeto completo en la consola
				console.error('Error id de la empresa:', jqXHR);

				// O acceder a propiedades específicas del error
				console.error('Error status:', textStatus);
				console.error('Error thrown:', errorThrown);

			}
		});
	} else {
		console.error('Error id de la empresa: código vacío');
	}
});

$('#btnGuardarDosimetroContrato').click(function () {
	var selectedDosimetrosIds = [];
	var numero_usuarios = $("#numero_usuarios").val();
	let count = 0;

	$('table tbody tr').each(function () {
		const cellText = $(this).find('td:eq(3)').text(); // eq(3) es la cuarta columna (índice 0-based)
		if (cellText.includes('A')) {
			count++;
		}
	});

	// Obtener los IDs seleccionados
	$('.check-agregar-dosimetros:checked').each(function () {
		selectedDosimetrosIds.push(parseInt($(this).val()));
	});

	var selectedDosimetros = [];

	if (selectedDosimetrosIds.length > 0) {
		console.log('IDs de dosímetros seleccionados:', selectedDosimetrosIds);

		// Verificar si hay fila con clase .sinFilas
		const tieneSinFilas = $('#tbodyDosimetros .sinFilas').length > 0;

		// Si existe .sinFilas, limpiar la tabla
		if (tieneSinFilas) {
			$('#tbodyDosimetros').empty();
		}

		var count_filas = $('#tbodyDosimetros tr').length + 1;

		lista_dosimetros.forEach(function (dosimetro) {
			if (selectedDosimetrosIds.includes(dosimetro.id_dosimetro)) {
				if (dosimetro.estado_dosimetro == 'A' && numero_usuarios <= count) {
					swal.fire({
						title: '¡Atención!',
						text: "Ya cuenta con el numero maximo de trabajadores activos que puede asignar a este contrato.",
						icon: 'warning',
						confirmButtonText: 'Aceptar'
					});
				} else {
					selectedDosimetros.push(dosimetro.id_dosimetro);
					const fila = `
						<tr>
							<td>${count_filas}</td>
							<td><input type="hidden" value="${dosimetro.id_dosimetro}" name="dosimetros">${dosimetro.codigo_dosimetro}</td>
							<td>${dosimetro.id_trabajador.cedula_trabajador}</td>
							<td>${dosimetro.id_trabajador.nombre_trabajador} ${dosimetro.id_trabajador.primer_apellido_trabajador}</td>
							<td>${dosimetro.estado_dosimetro}</td>
							<td>${dosimetro.tipo_dosimetro.nombre}</td>
							<td>
								<button class="btn btn-sm btn-danger btn-eliminar-dosimetro" value="${dosimetro.id_dosimetro}">
									<i class="bi bi-trash"></i>
								</button>
							</td>
						</tr>
					`;
					count_filas++;
					$('#tbodyDosimetros').append(fila);
				}
			}
		});

		validarDosimetrosAgregados();
		newDosimetro(selectedDosimetros);
		autoSaveContrato();

	} else {
		console.log('No se seleccionaron dosímetros.');
		$('#tbodyDosimetros').empty().append('<tr class="sinFilas"><td colspan="7" class="text-center">No se seleccionaron dosímetros</td></tr>');
	}

	$("#modalVerDosimetros").modal("hide");
});


function newDosimetro(selectedDosimetros) {
	var id_contrato = $("#contrato").val();

	$.ajax({
		url: '/gestion4/newDosimetro',
		type: 'GET',
		data: {
			id_contrato: id_contrato,
			selectedDosimetros: selectedDosimetros
		},
		dataType: 'json',
		success: function (data) {
			console.log(data);
			// Aquí puedes manejar la respuesta y actualizar la UI según sea necesario
		}
	});
}


// Delegación de eventos para que funcione con elementos agregados dinámicamente
$(document).on('click', '.btn-eliminar-dosimetro', function () {
	$(this).closest('tr').remove();
	console.log($(this).val());

	deleteDosimetro($(this).val());
	validarDosimetrosAgregados();
});


function deleteDosimetro(id_dosimetro) {
	var id_contrato = $("#contrato").val();

	$.ajax({
		url: '/gestion4/deleteDosimetro',
		type: 'GET',
		data: {
			id_contrato: id_contrato,
			id_dosimetro: id_dosimetro
		},
		dataType: 'json',
		success: function (data) {
			console.log(data);
			// Aquí puedes manejar la respuesta y actualizar la UI según sea necesario
		}
	});
}


function autoSaveContrato() {
	var tipo_dosimetro = $(".tipo_contrato:checked").map(function () {
		return $(this).val();
	}).get().join(",");

	var tipo_dosimetro_select = $(".tipo_contrato:checked").map(function () {
		return $(this).val();
	}).get().join(", ");

	$('#tipo_dosimetro_select').val(tipo_dosimetro_select);

	var data = {
        contrato: $("#contrato").val(),
        empresa: $("#empresa").val(),
        fecha_inicio_contrato: $("#fecha_inicio_contrato").val(),
        duracion_contrato: $("#duracion_contrato").val(),
        periodo_uso_contrato: $("#periodo_uso_contrato").val(),
        tipo_contratacion_factura: $("#tipo_contratacion_factura").val(),
        factura_a: $("#factura_a").val(),
        identificacion_contratacion: $("#identificacion_contratacion").val(),
        fecha_contratacion_factura: $("#fecha_contratacion_factura").val(),
        valor_contrato: $("#valor_factura_contrato").val(),
        cuotas_contrato: $("#cuotas_contrato").val(),
        numero_usuarios: $("#numero_usuarios").val(),
        estado_contrato: $("#estado_contrato").val(),
        tipo_dosimetro: tipo_dosimetro,
        observaciones_contrato: $("#observaciones_contrato").val()
    };

	console.log(data);
	

    $.ajax({
        url: '/gestion4/autoSave', // <-- Cambia esta URL a la que maneja el POST en tu backend
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            console.log("Contrato enviado correctamente:", response);
            // Aquí podrías redirigir, mostrar mensaje, etc.
        },
        error: function (xhr, status, error) {
            console.error("Error al enviar contrato:", error);
            // Mostrar alerta o manejar error según necesidad
        }
    });
}