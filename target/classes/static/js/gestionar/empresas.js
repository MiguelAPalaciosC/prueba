$(document).ready(function () {
    var toastElements = document.querySelectorAll('.toast');
    toastElements.forEach(function (toastEl) {
        var toast = new bootstrap.Toast(toastEl, {
            delay: 10000 // Tiempo en milisegundos (5000 ms = 5 segundos)
        });
        toast.show();
    });

    $('table tbody tr').each(function () {
        let td7 = $(this).find('td').eq(2);
        let num = parseFloat(td7.text().replace(/\./g, '').replace(/,/g, '.')); // cambia comas por puntos si venía al revés
        if (!isNaN(num)) {
            td7.text(num.toLocaleString('de-DE')); // formato alemán → 1.000,00
        }
    });

    new DataTable('#miTabla', {
        scrollCollapse: true,
        scrollY: '50vh',
        pageLength: 100, // Muestra 100 filas
        searching: false, // Oculta la barra de búsqueda
        lengthChange: false, // Oculta la selección de cantidad de datos a ver
        ordering: true, // Habilita el ordenamiento
        order: [] // Desactiva el ordenamiento automático al cargar
    });

    const filas = document.querySelectorAll("#miTabla tbody tr");
    const valorSeleccionado = document.getElementById("valorSeleccionado");

    filas.forEach(fila => {
        fila.addEventListener("click", function () {
            if (this.classList.contains("selected")) {
                this.classList.remove("selected");
                valorSeleccionado.textContent = "";
            } else {
                filas.forEach(f => f.classList.remove("selected"));
                this.classList.add("selected");

                const valor = this.getAttribute("data-valor");
                console.log("Fila seleccionada:", valor);
                valorSeleccionado.textContent = "Seleccionaste: " + valor;
                $('#btnEditarEmpresa').attr('href', '/gestion/empresa/' + valor);
            }
        });
    });

    filas.forEach(fila => {
        const celda = fila.cells[1]; // segunda columna (índice 1)
        const valorOriginal = celda.textContent.trim();

        // Separar por el guion si es que existe
        const partes = valorOriginal.split('-').map(parte => parte.trim());

        const partesFormateadas = partes.map(parte => {
            const numero = parseFloat(parte.replace(/,/g, ''));
            return !isNaN(numero) ? numero.toLocaleString('es-CO') : parte;
        });

        // Unir nuevamente con ' - '
        celda.textContent = partesFormateadas.join(' - ');
    });


});

$('#btnEliminar').click(function () {
    var filaValida = $('#valorSeleccionado').text();

    console.log(filaValida);

    // Cambiar || por && para la verificación
    if (filaValida !== '' && filaValida !== null && filaValida !== undefined) {
        // Obtener la fila seleccionada
        const filaSeleccionada = $("#miTabla tbody tr.selected"); // Asegúrate de que la fila seleccionada tenga la clase 'selected'
        if (filaSeleccionada.length > 0) { // Verifica que haya una fila seleccionada
            const id_empresa = filaSeleccionada.data("valor"); // Obtener el valor del atributo data-valor
            const codigo = filaSeleccionada.find("td").eq(0).text();
            const rs = filaSeleccionada.find("td").eq(2).text(); // Segunda columna

            console.log("ID de la empresa a eliminar:", id_empresa);

            $('.id_empresa').val(id_empresa);
            $('.codigo').text(codigo);
            $('.rs').text(rs);

            // Mostrar modal
            $('#modalEliminarRegistro').modal('show');
        } else {
            Swal.fire({
                title: 'Seleccione una fila primero antes de proceder.',
                icon: 'warning'
            });
        }
    } else {
        Swal.fire({
            title: 'Seleccione una fila primero antes de proceder.',
            icon: 'warning'
        });
    }

});

$('#btnContactosEmpresa').click(function () {
    var filaValida = $('#valorSeleccionado').text();

    // Cambiar || por && para la verificación
    if (filaValida !== '' && filaValida !== null && filaValida !== undefined) {
        // Obtener la fila seleccionada
        const filaSeleccionada = $("#miTabla tbody tr.selected"); // Asegúrate de que la fila seleccionada tenga la clase 'selected'
        if (filaSeleccionada.length > 0) { // Verifica que haya una fila seleccionada
            const id_empresa = filaSeleccionada.data("valor"); // Obtener el valor del atributo data-valor
            const codigo = filaSeleccionada.find("td").eq(0).text();
            const rs = filaSeleccionada.find("td").eq(2).text(); // Segunda columna
            const correo = filaSeleccionada.find("td").eq(6).text();


            $('.codigo').text(codigo);
            $('.razon_social').text(rs);
            $('.correo').text(correo);

            $.ajax({
                url: '/gestion/misContactos',
                type: 'GET',
                data: { id_empresa: id_empresa },
                dataType: 'json',
                success: function (data) {

                    $('#tablaBody').empty();

                    if (data.length === 0) {
                        // Si no hay contactos, muestra un mensaje
                        var mensaje = `
                            <tr>
                                <td colspan="7" class="text-center">No hay contactos registrados</td>
                            </tr>
                        `;
                        $('#tablaBody').append(mensaje); // Agrega el mensaje al tbody
                    } else {
                        // Itera sobre los datos y agrega filas a la tabla
                        $.each(data, function (index, contacto) {
                            visible = ((contacto.correo == null) ? 'style="display: none;"' : '');
                            var fila = `
                            <tr>
                                <td>${index + 1}</td>
                                <td>
                                    ${contacto.nombre}
                                </td>
                                <td>
                                    ${contacto.apellido}
                                </td>
                                
                                <td>
                                    ${contacto.celular}
                                </td>
                                <td>
                                    ${contacto.cargo}
                                </td>
                                <td>
                                    <div class="row">
                                        <div class="col-md-10 divInputCorreo pe-0 ps-0">
                                            <input type="hidden" class="form-control" placeholder="Correo" id="correo_persona_${index + 1}" value="${contacto.correo}">
                                            ${contacto.correo}
                                        </div>
                                        <div class="col-md-2 divBotonCopiarCorreo ps-0" ${visible}>
                                            <button type="button" class="btn btn-primary w-100 btnCopiarCorreo" value="${index + 1}">
                                                <i class="bi bi-copy"></i>
                                            </button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        `;
                            $('#tablaBody').append(fila); // Agrega la fila al tbody
                        });
                    }

                    $('#modalVerContactos').modal('show');
                },
                error: function () {
                    console.error('Error al obtener contactos');
                }
            });
        } else {
            Swal.fire({
                title: 'Seleccione una fila primero antes de proceder.',
                icon: 'warning'
            });
        }
    } else {
        Swal.fire({
            title: 'Seleccione una fila primero antes de proceder.',
            icon: 'warning'
        });
    }
});

$(document).on("click", ".btnCopiarRS", function () {
    var id = $(this).val();

    var razon_social = $('.razon_social').text();

    // Copiar el valor sin puntos al portapapeles
    navigator.clipboard.writeText(razon_social).then(function () {
        console.log('Razon social copiado al portapapeles: ' + razon_social);
        // Aquí puedes mostrar un mensaje de éxito si lo deseas
    }, function (err) {
        console.error('Error al copiar el Correo: ', err);
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

$(document).on("click", ".btnCopiarCorreoEmpresa", function () {
    var correo = $('.correo').text();

    // Copiar el valor sin puntos al portapapeles
    navigator.clipboard.writeText(correo).then(function () {
        console.log('Correo copiado al portapapeles: ' + correo);
        // Aquí puedes mostrar un mensaje de éxito si lo deseas
    }, function (err) {
        console.error('Error al copiar el Correo: ', err);
    });
});
let ignoreChange = false;

$(".input_filtro")
    .keydown(function (e) {
        if (e.key === "Tab") {
            ignoreChange = true;
        }

        if (e.key === "Enter") {
            e.preventDefault(); // Evita el submit

            function isValidInput(selector, minLength) {
                return $(selector).val().trim().length > minLength;
            }

            if (
                isValidInput('#buscar1', 0) ||
                isValidInput('#buscar2', 2) ||
                isValidInput('#buscar3', 2) ||
                isValidInput('#buscar4', 2) ||
                isValidInput('#buscar5', 2) ||
                isValidInput('#buscar6', 2) ||
                isValidInput('#buscar7', 2) ||
                isValidInput('#buscar8', 0)
            ) {
                getEmpresasFiltro();
            } else if (
                !isValidInput('#buscar1', 0) &&
                !isValidInput('#buscar2', 0) &&
                !isValidInput('#buscar3', 0) &&
                !isValidInput('#buscar4', 0) &&
                !isValidInput('#buscar5', 0) &&
                !isValidInput('#buscar6', 0) &&
                !isValidInput('#buscar7', 0) &&
                !isValidInput('#buscar8', 0)
            ) {
                getEmpresasFiltro();
            }
        }
    })
    .keyup(function (e) {
        // Resetear bandera cuando se suelta cualquier tecla
        if (e.key === "Tab") {
            ignoreChange = false;
        }
    });

$(".input_filtro").change(function () {
    if (ignoreChange) return; // Ignora cambios hechos por Tab

    function isValidInput(selector, minLength) {
        return $(selector).val().trim().length > minLength;
    }

    if (
        isValidInput('#buscar1', 0) ||
        isValidInput('#buscar2', 3) ||
        isValidInput('#buscar3', 3) ||
        isValidInput('#buscar4', 3) ||
        isValidInput('#buscar5', 3) ||
        isValidInput('#buscar6', 3) ||
        isValidInput('#buscar7', 3) ||
        isValidInput('#buscar8', 0)
    ) {
        getEmpresasFiltro();
    } else if (
        !isValidInput('#buscar1', 0) &&
        !isValidInput('#buscar2', 0) &&
        !isValidInput('#buscar3', 0) &&
        !isValidInput('#buscar4', 0) &&
        !isValidInput('#buscar5', 0) &&
        !isValidInput('#buscar6', 0) &&
        !isValidInput('#buscar7', 0) &&
        !isValidInput('#buscar8', 0)
    ) {
        getEmpresasFiltro();
    }
});


function getEmpresasFiltro() {
    const params = {
        codigo_interno_empresa: $('#buscar1').val(),
        nit_empresa: $('#buscar2').val(),
        razon_social_empresa: $('#buscar3').val(),
        nombre_ciudad: $('#buscar4').val(),
        telefono_empresa: $('#buscar5').val(),
        celular_empresa: $('#buscar6').val(),
        mail_empresa: $('#buscar7').val(),
        estado_empresa: $('#buscar8').val()
    };

    // Realizar la solicitud AJAX
    $.ajax({
        url: '/gestion/filtro/empresas', // URL del endpoint
        type: 'GET',
        data: params,
        success: function (response) {
            console.log("Respuesta del servidor:", response);
            manejarRespuesta(response);
        },
        error: function (xhr, status, error) {
            // Manejar errores
            $('#resultado').empty();
            $('#resultado').append('<p>Error al buscar empresas: ' + error + '</p>');
        }
    });
}

// Función para manejar la respuesta del servidor
function manejarRespuesta(response) {
    $('#tablaBodyEmpresas').empty();

    $('.numero_resultado').text(response.length); // Actualiza el número de resultados

    // Verificar si hay resultados
    if (response.length > 0) {
        response.forEach(function (empresa) {
            // Crear una nueva fila para cada empresa
            let nitEmpresaStr = String(empresa.nit_empresa); // Convertir a cadena
            let num = parseFloat(nitEmpresaStr.replace(/\./g, '').replace(/,/g, '.')); // Eliminar puntos y cambiar comas por puntos

            // Formatear el número con el formato de miles
            let formattedNum = num.toLocaleString('es-CO');

            if (empresa.digito_verificacion != null) {
                num = formattedNum + ' - ' + empresa.digito_verificacion;
            } else {
                num = formattedNum;
            }

            const row = `
                <tr data-valor="${empresa.id_empresa}">
                    <td style="text-align: center;">${empresa.codigo_interno_empresa}</td>
                    <td style="text-align: right;">${num}</td>
                    <td style="text-align: left;">${empresa.razon_social_empresa}</td>
                    <td style="text-align: left;">${empresa.ciudad}</td>
                    <td style="text-align: right;">${empresa.telefono_empresa}</td>
                    <td style="text-align: right;">${empresa.celular_empresa}</td>
                    <td style="text-align: left;">${empresa.mail_empresa}</td>
                    <td>${empresa.estado_empresa}</td>
                </tr>`;
            // Agregar la fila al tbody
            $('#tablaBodyEmpresas').append(row);
        });

        // Agregar eventos a las filas después de que se hayan creado
        agregarEventosFilas();
    } else {
        // Si no hay resultados, mostrar un mensaje
        $('#tablaBodyEmpresas').append('<tr><td colspan="8" style="text-align: center;">No se encontraron empresas.</td></tr>');
    }
}

// Función para agregar eventos a las filas
function agregarEventosFilas() {
    const filas = document.querySelectorAll("#tablaBodyEmpresas tr"); // Asegúrate de que el selector sea correcto
    const valorSeleccionado = document.getElementById("valorSeleccionado");

    filas.forEach(fila => {
        fila.addEventListener("click", function () {
            if (this.classList.contains("selected")) {
                this.classList.remove("selected");
                valorSeleccionado.textContent = "";
            } else {
                filas.forEach(f => f.classList.remove("selected"));
                this.classList.add("selected");

                const valor = this.getAttribute("data-valor");
                console.log("Fila seleccionada:", valor);
                valorSeleccionado.textContent = "Seleccionaste: " + valor;
                $('#btnEditarEmpresa').attr('href', '/gestion/empresa/' + valor);
            }
        });
    });
}