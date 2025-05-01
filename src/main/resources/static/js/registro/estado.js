$('#activo').click(function () {
	$('#divObservaciones').hide();
	$('#observaciones').prop('required', false);
	$('#observaciones').val(''); // Opcional: limpiar el campo cuando se oculta
});

$('#inactivo').click(function () {
	$('#divObservaciones').show();
	$('#observaciones').prop('required', true);
});

var list_ciudades = [];

$('#departamento').change(function () {
	var departamentoId = $(this).val();
	var ciudadSelect = $('#ciudad');

	ciudadSelect.html('<option value="">Cargando...</option>'); // Mensaje mientras carga

	getCodigoMinisterioDepartamento(departamentoId);

	if (departamentoId) {
		$.ajax({
			url: '/registro/empresa/ciudades',
			type: 'GET',
			data: { codigo_departamento: departamentoId },
			dataType: 'json',
			success: function (data) {
				console.log(data);
				list_ciudades = data;
				ciudadSelect.html('<option value="" hidden>Seleccione una opción</option>');
				$.each(data, function (index, ciudad) {
					ciudadSelect.append($('<option>', {
						value: ciudad.id,
						text: ciudad.nombreCiudad
					}));
				});
			},
			error: function () {
				console.error('Error al obtener ciudades');
				ciudadSelect.html('<option value="">Error al cargar ciudades</option>');
			}
		});
	} else {
		ciudadSelect.html('<option value="">Seleccione un departamento primero</option>');
	}
});

function getCodigoMinisterioDepartamento(id_departamento) {
	$.ajax({
		url: '/registro/empresa/departamento',
		type: 'GET',
		data: { id_departamento: id_departamento },
		dataType: 'json',
		success: function (data) {
			console.log(data);

			$('.codigoDepartamento').text(data.codigo_min_departamento);
		},
		error: function () {
			console.error('Error al obtener ciudades');
			ciudadSelect.html('<option value="">Error al cargar ciudades</option>');
		}
	});
}

$('#ciudad').change(function () {
	for (var i = 0; i < list_ciudades.length; i++) {
		if (list_ciudades[i].id == $(this).val()) {
			$('.codigoCiudad').text(list_ciudades[i].codigo_ministerio);
			break;
		}
	}

});
	

$('#codigo').change(function () {
	var codigo = $(this).val();
	var $this = $(this); // Almacena una referencia al elemento

	console.log('change codigo');


	if (codigo || codigo > 0) {
		$.ajax({
			url: '/gestion/codigo',
			type: 'GET',
			data: { codigo: codigo },
			dataType: 'json',
			success: function (data) {
				console.log(data);
				// Aquí puedes manejar la respuesta y actualizar la UI según sea necesario
				if (data.length > 0) {
					if ($('#empresa').val()) {
						if (data.length == 1) {
							if (data[0].id_empresa != $('#empresa').val()) {
								$this.css('background-color', '#ff6688'); // Cambia el color de fondo a rojo
								$('.invalid-id').show(); //
							} else {
								$this.css('background-color', '#40b780b5');
							}
						} else {
							$this.css('background-color', '#ff6688'); // Cambia el color de fondo a rojo
							$('.invalid-id').show(); //
						}
					} else {
						$this.css('background-color', '#ff6688'); // Cambia el color de fondo a rojo
						$('.invalid-id').show(); //
					}
				} else {
					$this.css('background-color', '#40b780b5'); // Cambia el color de fondo a verde
				}

				// Resetear el color de fondo al cambiar de código
				// Si deseas resetear el color después de un tiempo, puedes usar setTimeout
				setTimeout(function () {
					$this.css('background-color', '');
					$('.invalid-id').hide(); // Oculta el mensaje de error al código inválido
					if (data.length > 0) {
                        if ($('#empresa').val()) {
                            if (data.length == 1) {
                                if (data[0].id_empresa != $('#empresa').val()) {
                                    $('#codigo').val('');
                                }
                            } else {
                                $('#codigo').val('');
                            }
                        } else {
                            $('#codigo').val('');
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
		$('#codigo').val('');
		// alert("Por favor, ingresa un código válido.");
	}
});