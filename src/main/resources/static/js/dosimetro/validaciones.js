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

	if ($('#dosimetro').val()) {
		$('#id_trabajador').on('select2:opening', function (e) {
			e.preventDefault();
		});

		// Agrega clase que lo simule deshabilitado
		$('#id_trabajador').next('.select2-container').addClass('select2-disabled');

		$('#codigo_dosimetro')
			.attr('readonly', true)           // lo hace solo lectura
			.css({ 'background-color': '#e9ecef' }); // fondo gris claro (similar a disabled)

	}

	$("#codigo_dosimetro").on("input", function () {
		let valor = $(this).val();

		// Eliminar caracteres no numéricos y limitar a 5 dígitos
		$(this).val(valor.replace(/\D/g, "").substring(0, 5));
	});

	function cambiarPlaceholder(nuevoPlaceholder) {
		// Destruir la instancia anterior de Select2
		$('#id_trabajador').select2('data', null); // Limpiar la selección
		$('#id_trabajador').select2({
			theme: "bootstrap-5",
			width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
			placeholder: 'Seleccione un nuevo trabajador', // Cambia el texto del placeholder
			allowClear: true
		}).val(null).trigger('change');
		$('#id_trabajador').select2('open'); // Abrir el select para ver el cambio
	}

	$('.form-trabajador').on("keypress", function search(e) {
		if (e.keyCode == 13) {
			findTrabajadores();
		}
	});

	$('#btnSearchTrabajador').click(function () {
		findTrabajadores();
	});

	function findTrabajadores() {
		var cedula = $('#cedula_trabajador').val();
		var nombre = $('#nombre_trabajador').val();

		if (cedula || nombre) {
			$.ajax({
				url: '/gestion3/trabajadores',
				type: 'GET',
				data: { cedula: cedula, nombre: nombre },
				dataType: 'json',
				success: function (data) {
					console.log(data);

					$('#id_trabajador').html('<option value="" hidden>Seleccione un trabajador</option>'); // Limpiar el select

					$.each(data, function (index, trabajador) {
						var fullName = trabajador.nombre_trabajador + ' ' + trabajador.primer_apellido_trabajador + ' ' + trabajador.segundo_apellido_trabajador;
						$('#id_trabajador').append($('<option>', {
							value: trabajador.id_trabajador, // Usar cedula como value
							text: 'CC. ' + trabajador.cedula_trabajador + ' - ' + fullName // Concatenar nombre y apellidos
						}));
					});

					cambiarPlaceholder('Seleccione un trabajador');
				},
				error: function (jqXHR, textStatus, errorThrown) {
					// Imprimir el objeto completo en la consola
					console.error('Error id de la empresa:', jqXHR);

					// O acceder a propiedades específicas del error
					console.error('Error status:', textStatus);
					console.error('Error thrown:', errorThrown);

					// Mostrar un mensaje de error al usuario
					// alert("Ocurrió un error al buscar la empresa. Por favor, inténtalo de nuevo.");
				}
			});
		} else {
			$('.invalid-trabajador').show();
			setTimeout(function () {
				$('.invalid-trabajador').hide();
			}, 3000);
		}
	}

	if ($('#tipo_dosimetro_select').val()) {
		$('#tipo_dosimetro').val($('#tipo_dosimetro_select').val()).trigger('change');
	}

	if ($('#periodo_uso_select').val()) {
		$('#periodo_uso').val($('#periodo_uso_select').val()).trigger('change');
	}

	console.log('periodo_uso_disable: ' + $('#periodo_uso_disable').val());

	if ($('#periodo_uso_disable').val() == 1) {
		$('#periodo_uso').on('select2:opening', function (e) {
			e.preventDefault();
		});

		// Agrega clase que lo simule deshabilitado
		$('#periodo_uso').next('.select2-container').addClass('select2-disabled');


		$('#tipo_dosimetro').on('select2:opening', function (e) {
			e.preventDefault();
		});

		// Agrega clase que lo simule deshabilitado
		$('#tipo_dosimetro').next('.select2-container').addClass('select2-disabled');
	}

	if ($('#ubicacion_select').val()) {
		$('#ubicacion').val($('#ubicacion_select').val()).trigger('change');
	}

	if ($('#cargo_select').val()) {
		$('#cargo').val($('#cargo_select').val()).trigger('change');
	}

	if ($('#practica_select').val()) {
		$('#practica').val($('#practica_select').val()).trigger('change');
	}

	if ($('#radiacion_select').val()) {
		$('#radiacion').val($('#radiacion_select').val()).trigger('change');
	}

	if ($('#ingeominas_select').val()) {
		$('#ingeominas').val($('#ingeominas_select').val()).trigger('change');
	}

	if ($('#estado_dosimetro_select').val()) {
		$('#estado_dosimetro').val($('#estado_dosimetro_select').val()).trigger('change');
	}

	var selectedValue = $('#destino_seleccionado').val();

	// Marcar el checkbox correspondiente
	if (selectedValue) {
		$('.destino_dosimetro[value="' + selectedValue + '"]').prop('checked', true);
	}
});

$('#codigo_dosimetro').change(function () {
	var codigo_dosimetro = $(this).val();
	var $this = $(this); // Almacena una referencia al elemento

	console.log('change codigo_dosimetro');


	if (codigo_dosimetro || codigo_dosimetro > 0) {
		$.ajax({
			url: '/gestion3/codigo',
			type: 'GET',
			data: { codigo_dosimetro: codigo_dosimetro },
			dataType: 'json',
			success: function (data) {
				console.log(data);
				// Aquí puedes manejar la respuesta y actualizar la UI según sea necesario
				if (data) {

					if (data.length > 0) {
						if ($('#dosimetro').val()) {

							if (data.length == 1) {
								console.log('data[0].id_dosimetro: ' + data[0].id_dosimetro);
								if (data[0].id_dosimetro != $('#dosimetro').val()) {
									$this.css('background-color', '#ff6688'); // Cambia el color de fondo a rojo
									$('.invalid-codigo').show(); //
								} else {
									$this.css('background-color', '#40b780b5');
								}
							} else {
								$this.css('background-color', '#ff6688'); // Cambia el color de fondo a rojo
								$('.invalid-codigo').show(); //
							}
						} else {
							$this.css('background-color', '#ff6688'); // Cambia el color de fondo a rojo
							$('.invalid-codigo').show(); //
						}
					} else {
						$this.css('background-color', '#40b780b5'); // Cambia el color de fondo a verde
					}
				} else {
					// alert("No se encontraron empresas para el código proporcionado.");
					$this.css('background-color', '#40b780b5'); // Cambia el color de fondo a rojo
				}

				// Resetear el color de fondo al cambiar de código
				// Si deseas resetear el color después de un tiempo, puedes usar setTimeout
				setTimeout(function () {
					$this.css('background-color', '');
					$('.invalid-codigo').hide(); // Oculta el mensaje de error al código inválido

					if (data.length > 0) {
						if ($('#dosimetro').val()) {
							if (data.length == 1) {
								if (data[0].id_dosimetro != $('#dosimetro').val()) {
									$('#codigo_dosimetro').val('');
								}
							} else {
								$('#codigo_dosimetro').val('');
							}
						} else {
							$('#codigo_dosimetro').val('');
						}
					}
				}, 3000); // Resetea el color después de 2 segundos
			},
			error: function (jqXHR, textStatus, errorThrown) {
				// Imprimir el objeto completo en la consola
				console.error('Error id de la empresa:', jqXHR);

				// O acceder a propiedades específicas del error
				console.error('Error status:', textStatus);
				console.error('Error thrown:', errorThrown);

				// Mostrar un mensaje de error al usuario
				// alert("Ocurrió un error al buscar la empresa. Por favor, inténtalo de nuevo.");
			}
		});
	} else {
		console.error('Error id de la empresa: código vacío');
		$('#codigo_dosimetro').val('');
		// alert("Por favor, ingresa un código válido.");
	}
});