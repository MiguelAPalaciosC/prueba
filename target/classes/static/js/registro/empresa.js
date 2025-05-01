
$(document).ready(function () {
	var toastElements = document.querySelectorAll('.toast');
	toastElements.forEach(function (toastEl) {
		var toast = new bootstrap.Toast(toastEl, {
			delay: 10000 // Tiempo en milisegundos (5000 ms = 5 segundos)
		});
		toast.show();
	});

	// Small using Select2 properties
	$('#departamento').select2({
		theme: "bootstrap-5",
		width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
		placeholder: $(this).data('placeholder'),
		allowClear: true
	});

	$('#ciudad').select2({
		theme: "bootstrap-5",
		width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
		placeholder: $(this).data('placeholder'),
		allowClear: true
	});

	$("#codigo").on("input", function () {
		let valor = $(this).val();

		// Eliminar caracteres no numéricos y limitar a 4 dígitos
		$(this).val(valor.replace(/\D/g, "").substring(0, 4));
	});

	$("#nit").on("input", function () {
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
		$('#nitHidden').val(valor.replace(/\./g, ''));
	});

	$("#dv").on("input", function () {
		let valor = $(this).val();

		// Eliminar caracteres no numéricos y limitar a 4 dígitos
		$(this).val(valor.replace(/\D/g, "").substring(0, 1));
	});

	let contador = 1; // Contador de filas

	// Agregar nueva fila
	$("#addRow").click(function () {
		contador++; // Incrementa el número de fila
		let nuevaFila = `
            <tr>
                <td>${contador}</td>
                <td><input type="text" class="form-control" placeholder="Nombre" id="nombre_persona_${contador}" name="nombre_persona[]" required></td>
                <td><input type="text" class="form-control" placeholder="Apellido" name="apellido_persona[]"></td>
                <td><input type="text" class="form-control" placeholder="Cargo" name="cargo_persona[]"></td>
                <td>
					<div class="row">
						<div class="col-md-12 divInputCorreo">
							<input type="email" class="form-control" placeholder="Correo" id="correo_persona_${contador}" name="correo_persona[]" required>
						</div>
						<div class="col-md-2 divBotonCopiarCorreo ps-0" style="display: none;">
							<button type="button" class="btn btn-primary w-100 btnCopiarCorreo" value="${contador}"><i class="bi bi-copy"></i></button>
						</div>
					</div>
				</td>
                <td><input type="tel" class="form-control" placeholder="Teléfono" name="telefono_persona[]"></td>
                <td><button type="button" class="btn btn-danger btn-sm eliminar-fila"><i class="bi bi-x-lg"></i></button></td>
            </tr>
        `;

		$("#tablaBody").append(nuevaFila); // Agregar fila al tbody

		if (state == 0) {
			$('.divInputCorreo').removeClass('col-md-10').addClass('col-md-12');
			$('.divBotonCopiarCorreo').hide();
			// state = 1;
		} else {
			$('.divInputCorreo').removeClass('col-md-12').addClass('col-md-10');
			$('.divBotonCopiarCorreo').show();
			// state = 0;
		}
	});

	// Eliminar fila
	$(document).on("click", ".eliminar-fila", function () {
		const fila = $(this).closest("tr");
		const inputs = fila.find("input"); // Encuentra todos los inputs en la fila
		let tieneContenido = false;

		// Verificar si hay contenido en los inputs
		inputs.each(function () {
			if ($(this).val().trim() !== "") {
				tieneContenido = true; // Hay contenido en al menos un input
				return false; // Salir del bucle
			}
		});

		// Si hay contenido, preguntar al usuario con SweetAlert2
		if (tieneContenido) {
			Swal.fire({
				title: '¿Estás seguro?',
				text: "Los datos se perderán si eliminas esta fila.",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Sí, eliminar',
				cancelButtonText: 'Cancelar'
			}).then((result) => {
				if (result.isConfirmed) {
					fila.remove(); // Elimina la fila
					actualizarNumeracion(); // Reordenar los números de las filas
				}
			});
		} else {
			fila.remove(); // Elimina la fila si no hay contenido
			actualizarNumeracion(); // Reordenar los números de las filas
		}
	});

	// Función para reordenar los números de las filas y los IDs de los inputs
	function actualizarNumeracion() {
		$("#tablaBody tr").each(function (index) {
			$(this).find("td:first").text(index + 1); // Actualiza el número de fila

			// Actualizar los IDs de los inputs dentro de la fila
			$(this).find("input").each(function () {
				let idOriginal = $(this).attr("id");

				console.log(idOriginal)
				if (idOriginal) {
					let nuevoId = idOriginal.replace(/\d+$/, (index + 1)); // Reemplaza el número al final del ID
					$(this).attr("id", nuevoId);
				}
			});
		});

		contador = $("#tablaBody tr").length; // Actualiza el contador de filas

	}

});

$('#email').change(function (e) {
	var emailRegex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var EmailId = this.value;
	if (emailRegex.test(EmailId)) {
		this.style.backgroundColor = "";

		$('#correo_persona_1').val($(this).val());
	} else {
		$('.xmail').hide().removeClass('hide').slideDown('fast');
		this.style.backgroundColor = "LightPink";
	}
});

let state = 0;

$('#btnAjustarTabla').click(function () {
	if (state == 0) {
		$('.div-contactos').removeClass('col-md-6').addClass('col-md-12');
		$('.div-formulario').hide();

		$('#btnAjustarTabla').html('<i class="bi bi-arrows-angle-contract"></i>');


		$('.divInputCorreo').removeClass('col-md-12').addClass('col-md-10');
		$('.divBotonCopiarCorreo').show();
		state = 1;
	} else {
		$('.div-contactos').removeClass('col-md-12').addClass('col-md-6');
		$('.div-formulario').show();

		$('#btnAjustarTabla').html('<i class="bi bi-arrows-angle-expand"></i>');

		$('.divInputCorreo').removeClass('col-md-10').addClass('col-md-12');
		$('.divBotonCopiarCorreo').hide();
		state = 0;
	}
});

$('#btnCopiarNIT').click(function () {
	// Obtener el valor del input con id "nit"
	var nit = $('#nit').val();

	// Eliminar los puntos del NIT
	var nitSinPuntos = nit.replace(/\./g, '');

	// Copiar el valor sin puntos al portapapeles
	navigator.clipboard.writeText(nitSinPuntos).then(function () {
		console.log('NIT copiado al portapapeles: ' + nitSinPuntos);
		// Aquí puedes mostrar un mensaje de éxito si lo deseas
	}, function (err) {
		console.error('Error al copiar el NIT: ', err);
	});
});

$(document).on("click", ".btnCopiarCorreo", function () {
	var id = $(this).val();

	var correo_persona = $('#correo_persona_' + id).val();

	// Copiar el valor sin puntos al portapapeles
	navigator.clipboard.writeText(correo_persona).then(function () {
		console.log('Correo copiado al portapapeles: ' + correo_persona);
		// Aquí puedes mostrar un mensaje de éxito si lo deseas
	}, function (err) {
		console.error('Error al copiar el Correo: ', err);
	});
});

function verificarContacto() {
	let tieneContenido = false;
	$("#tablaBody tr").each(function () {
		const inputs = $(this).find("input"); // Encuentra todos los inputs en la fila

		// Verificar si hay contenido en los inputs
		inputs.each(function () {
			if ($(this).val().trim() !== "") {
				tieneContenido = true; // Hay contenido en al menos un input
				return false; // Salir del bucle
			}
		});
	});

	if (!tieneContenido) {
		swal.fire({
			title: '¡Atención!',
			text: "No hay contactos registrados, necesita por lo menos uno para proceder.",
			icon: 'warning',
			confirmButtonText: 'Aceptar'
		});
		return false; // Evita el envío del formulario si no hay contactos
	}
	return true; // Permite el envío del formulario si hay contactos
}