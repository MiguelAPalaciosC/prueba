$(document).ready(function () {
	var toastElements = document.querySelectorAll('.toast');
	toastElements.forEach(function (toastEl) {
		var toast = new bootstrap.Toast(toastEl, {
			delay: 10000 // Tiempo en milisegundos (5000 ms = 5 segundos)
		});
		toast.show();
	});

	$('.texto_numero_factura').hide();

	$('#divOtroConcepto').hide();
	$('.divContrato').hide();

	$('.select2').select2({
		theme: "bootstrap-5",
		width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
		placeholder: $(this).data('placeholder'),
		allowClear: true
	});

	var concepto_select = $('#concepto_select').val();
	if (concepto_select) {
		$("#concepto").val(concepto_select).trigger('change');

		$('#concepto').on('select2:opening', function (e) {
			e.preventDefault();
		});

		// Agrega clase que lo simule deshabilitado
		$('#concepto').next('.select2-container').addClass('select2-disabled');
	}
	
	var select_contrato = $('#select_contrato').val();
	if (select_contrato) {
		$("#contrato").val(select_contrato).trigger('change');

		$('#contrato').on('select2:opening', function (e) {
			e.preventDefault();
		});

		// Agrega clase que lo simule deshabilitado
		$('#contrato').next('.select2-container').addClass('select2-disabled');
	}

	var otro_concepto = $("#otro_concepto").val();
	if (otro_concepto) {
		$("#otro_concepto").prop("readonly", true).css({
			"background-color": "#e9ecef", // gris claro similar al disabled
			"pointer-events": "none"       // opcional: evita que se abra teclado en móviles
		});
	}

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

	// Filtrar por código
	$('#filtro2_1').on('keyup', function () {
		var valorBuscar = $(this).val().toLowerCase();
		$('#tbodySeguimiento tr').filter(function () {
			$(this).toggle($(this).find('td').eq(0).text().toLowerCase().indexOf(valorBuscar) > -1);
		});
	});

	// Filtrar por descripción
	$('#filtro2_1').on('keyup', function () {
		var valorBuscar2 = $(this).val().toLowerCase();
		$('#tbodySeguimiento tr').filter(function () {
			$(this).toggle($(this).find('td').eq(0).text().toLowerCase().indexOf(valorBuscar2) > -1);
		});
	});

	// Filtrar por descripción
	$('#filtro2_2').on('keyup', function () {
		var valorBuscar2 = $(this).val().toLowerCase();
		$('#tbodySeguimiento tr').filter(function () {
			$(this).toggle($(this).find('td').eq(1).text().toLowerCase().indexOf(valorBuscar2) > -1);
		});
	});

	// Filtrar por código
	$('#filtro1_1').on('keyup', function () {
		var valorBuscar = $(this).val().toLowerCase();
		$('#tbodyRecibo tr').filter(function () {
			$(this).toggle($(this).find('td').eq(0).text().toLowerCase().indexOf(valorBuscar) > -1);
		});
	});

	// Filtrar por descripción
	$('#filtro1_1').on('keyup', function () {
		var valorBuscar2 = $(this).val().toLowerCase();
		$('#tbodyRecibo tr').filter(function () {
			$(this).toggle($(this).find('td').eq(0).text().toLowerCase().indexOf(valorBuscar2) > -1);
		});
	});

	// Filtrar por descripción
	$('#filtro1_2').on('keyup', function () {
		var valorBuscar2 = $(this).val().toLowerCase();
		$('#tbodyRecibo tr').filter(function () {
			$(this).toggle($(this).find('td').eq(1).text().toLowerCase().indexOf(valorBuscar2) > -1);
		});
	});

	// Filtrar por descripción
	$('#filtro1_3').on('keyup', function () {
		var valorBuscar3 = $(this).val().toLowerCase();
		$('#tbodyRecibo tr').filter(function () {
			$(this).toggle($(this).find('td').eq(2).text().toLowerCase().indexOf(valorBuscar3) > -1);
		});
	});

	// Filtrar por descripción
	$('#filtro1_4').on('keyup', function () {
		var valorBuscar3 = $(this).val().toLowerCase();
		$('#tbodyRecibo tr').filter(function () {
			$(this).toggle($(this).find('td').eq(3).text().toLowerCase().indexOf(valorBuscar3) > -1);
		});
	});
});

$("#concepto").change(function () {
	if ($(this).val() === "C") {
		$('#divOtroConcepto').hide();
		$('.divContrato').show();

		$("#contrato").attr("required", "required");
		$("#otro_concepto").removeAttr("required");
		$('#otro_concepto').val("");

		$('.texto_numero_factura').show();
	} else {
		$('#divOtroConcepto').show();
		$('.divContrato').hide();

		$('#valor_contrato').val("");
		$('#cuotas_contrato').val("");
		$('#saldo_contrato').val("");

		$('#contrato').val("").trigger('change');
		$("#contrato").removeAttr("required");
		$("#otro_concepto").attr("required", "required");

		$('.texto_numero_factura').text("Factura N° #/# cuotas");
		$('.texto_numero_factura').hide();

	}
});

$('#btnAgregarRecibo').click(function () {
	$('#modalAgregarRecibo').modal('show');
});

$('#btnAgregarSeguimiento').click(function () {
	$('#modalAgregarSeguimiento').modal('show');
});

$('#btnGuardarRecibo').click(function () {
	let cantidadFilas = $('#tbodyRecibo tr').length + 1; // +1 porque se agregará una nueva
	let recibo = cantidadFilas;
	let fechaPago = $('#fecha_recibo').val();
	let valorRecibo = $('#valor_recibo').val();
	let retenciones = $('#retencion').val();

	let fila = `
            <tr>
                <td>${recibo}</td>
                <td><input type="hidden" name="fecha_recibo" value="${fechaPago}">${fechaPago}</td>
                <td><input type="hidden" name="valor_recibo" value="${valorRecibo}">${valorRecibo}</td>
                <td><input type="hidden" name="retencion" value="${retenciones}">${retenciones}</td>
                <td><button type="button" class="btn btn-danger eliminar"><i class="bi bi-trash"></i></button></td>
            </tr>
        `;

	$('#tbodyRecibo').append(fila);

	// Opcional: limpiar inputs después de agregar
	$('#fecha_recibo').val('');
	$('#valor_recibo').val('');
	$('#retencion').val('');

	$('#modalAgregarRecibo').modal('hide'); // Cerrar el modal después de agregar
});

// Delegar evento de eliminar
$(document).on('click', '.eliminar', function () {
	$(this).closest('tr').remove();
});

$('#btnGuardarSeguimiento').click(function () {
	let usuario = $('#usuario').val();
	let fecha_seguimiento = $('#fecha_seguimiento').val();
	let comentario = $('#comentario').val();

	let fila = `
            <tr>
                <td><input type="hidden" name="usuario" value="${usuario}">${usuario}</td>
                <td><input type="hidden" name="fecha_seguimiento" value="${fecha_seguimiento}">${fecha_seguimiento}</td>
                <td><input type="hidden" name="comentario" value="${comentario}">${comentario}</td>
				<td><button type="button" class="btn btn-danger eliminar-seguimiento"><i class="bi bi-trash"></i></button></td>
            </tr>
        `;

	$('#tbodySeguimiento').append(fila);

	// Opcional: limpiar inputs después de agregar
	$('#usuario').val('');
	$('#fecha_seguimiento').val('');
	$('#comentario').val('');

	$('#modalAgregarSeguimiento').modal('hide'); // Cerrar el modal después de agregar
});

$(document).on('click', '.eliminar-seguimiento', function () {
	$(this).closest('tr').remove();
});

$('#contrato').change(function () {
	var id_contrato = $(this).val();

	$.ajax({
		url: '/gestion5/infoContrato',
		method: 'GET',
		data: { id_contrato: id_contrato },
		dataType: 'json',
		success: function (response) {
			console.log("Contrato: ", response.contrato);

			let nitEmpresaStr = String(response.contrato.valor_factura); // Convertir a cadena
            let num = parseFloat(nitEmpresaStr.replace(/\./g, '').replace(/,/g, '.')); // Eliminar puntos y cambiar comas por puntos

            // Formatear el número con el formato de miles
            let formattedNum = num.toLocaleString('es-CO');
			$('#valor_contrato').val(formattedNum);
			$('#cuotas_contrato').val(response.contrato.cuotas_contrato);
			$('#saldo_contrato').val(formattedNum);

			$('.texto_numero_factura').show();
			$('.texto_numero_factura').html("Factura N° <b>" + (parseInt(response.factura_numero) + 1) + "</b>/<b>" + response.contrato.cuotas_contrato + "</b> cuotas");

			// Aquí podrías redirigir, mostrar mensaje, etc.
		},
		error: function (xhr, status, error) {
			console.error("Error al enviar contrato:", error);
			// Mostrar alerta o manejar error según necesidad
		}
	});
});



