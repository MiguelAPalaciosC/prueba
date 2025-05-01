$(document).ready(function () {
    var toastElements = document.querySelectorAll('.toast');
    toastElements.forEach(function (toastEl) {
        var toast = new bootstrap.Toast(toastEl, {
            delay: 10000 // Tiempo en milisegundos (5000 ms = 5 segundos)
        });
        toast.show();
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
                $('#btnEditar').attr('href', '/gestion2/trabajador/' + valor);
            }
        });
    });

    filas.forEach(fila => {
        const celda = fila.cells[0]; // segunda columna (índice 1)
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
            const rs = filaSeleccionada.find("td").eq(1).text(); // Primera columna

            console.log("ID de la empresa a eliminar:", id_empresa);

            $('.id_trabajador').val(id_empresa);
            $('.cedula').text(codigo);
            $('.nombre').text(rs);

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

let ignoreChange = false;

$(".input_filtro")
    .keydown(function (e) {
        if (e.key === "Tab") {
            ignoreChange = true;
        }

        if (e.key === "Enter") {
            e.preventDefault(); // evita que se haga submit si está dentro de un formulario

            function isValidInput(selector, minLength) {
                return $(selector).val().trim().length > minLength;
            }

            if (
                isValidInput('#buscar1', 2) ||
                isValidInput('#buscar2', 0) ||
                isValidInput('#buscar3', 0) ||
                isValidInput('#buscar4', 3) ||
                isValidInput('#buscar5', 3) ||
                isValidInput('#buscar6', 3) ||
                isValidInput('#buscar7', 3)
            ) {
                getTrabajadoresFiltro();
            } else if (
                !isValidInput('#buscar1', 0) &&
                !isValidInput('#buscar2', 0) &&
                !isValidInput('#buscar3', 0) &&
                !isValidInput('#buscar4', 0) &&
                !isValidInput('#buscar5', 0) &&
                !isValidInput('#buscar6', 0) &&
                !isValidInput('#buscar7', 0)
            ) {
                getTrabajadoresFiltro();
            }
        }
    })
    .keyup(function (e) {
        if (e.key === "Tab") {
            ignoreChange = false;
        }
    });

$(".input_filtro").change(function () {
    if (ignoreChange) return;

    function isValidInput(selector, minLength) {
        return $(selector).val().trim().length > minLength;
    }

    if (
        isValidInput('#buscar1', 2) ||
        isValidInput('#buscar2', 1) ||
        isValidInput('#buscar3', 1) ||
        isValidInput('#buscar4', 2) ||
        isValidInput('#buscar5', 2) ||
        isValidInput('#buscar6', 2) ||
        isValidInput('#buscar7', 2)
    ) {
        getTrabajadoresFiltro();
    } else if (
        !isValidInput('#buscar1', 0) &&
        !isValidInput('#buscar2', 0) &&
        !isValidInput('#buscar3', 0) &&
        !isValidInput('#buscar4', 0) &&
        !isValidInput('#buscar5', 0) &&
        !isValidInput('#buscar6', 0) &&
        !isValidInput('#buscar7', 0)
    ) {
        getTrabajadoresFiltro();
    }
});


function getTrabajadoresFiltro() {
    const params = {
        cedula: $('#buscar1').val(),
        nombres: $('#buscar2').val(),
        apellidos: $('#buscar3').val(),
        registro: $('#buscar4').val(),
        nacido: $('#buscar5').val(),
        correo: $('#buscar6').val(),
        celular: $('#buscar7').val()
    };

    // Realizar la solicitud AJAX
    $.ajax({
        url: '/gestion2/filtro/trabajadores', // URL del endpoint
        type: 'GET',
        data: params,
        success: function (response) {
            manejarRespuestaTrabajador(response);
        },
        error: function (xhr, status, error) {
            // Manejar errores
            $('#resultado').empty();
            $('#resultado').append('<p>Error al buscar empresas: ' + error + '</p>');
        }
    });
}

// Función para manejar la respuesta del servidor
function manejarRespuestaTrabajador(response) {
    console.log("Respuesta del servidor:", response); // Verificar la respuesta del servidor
    $('.numero_resultado').text(response.length); // Actualiza el número de resultados

    // console.log("Antes de vaciar:", $('#tablaBodyTrabajadores').html());
    $('#tablaBodyTrabajadores').empty();
    // console.log("Después de vaciar:", $('#tablaBodyTrabajadores').html());

    // Verificar si hay resultados
    if (response.length > 0) {
        response.forEach(function (trabajador) {
            // Manejar el campo fecha_inicio_trabajador
            const fechaInicio = trabajador.fecha_inicio_trabajador.length > 0 ? convertToDate(trabajador.fecha_inicio_trabajador) : 'N/A';

            const fechaNacimientoArray = trabajador.fecha_nacimiento_trabajador;
            // console.log("Fecha de nacimiento:", fechaNacimientoArray); // Verificar el contenido de la fecha de nacimiento

            let fechaNacimiento;
            if (fechaNacimientoArray == null || fechaNacimientoArray.length === 0) {
                fechaNacimiento = `0000-00-00`;
            } else {
                fechaNacimiento = convertToDate(fechaNacimientoArray);
            }

            let cedula = String(trabajador.cedula_trabajador); // Convertir a cadena
            let num = parseFloat(cedula.replace(/\./g, '').replace(/,/g, '.')); // Eliminar puntos y cambiar comas por puntos

            // Formatear el número con el formato de miles
            let formattedNum = num.toLocaleString('es-CO');

            // Crear una nueva fila para cada trabajador
            const row = `
                <tr data-valor="${trabajador.id_trabajador}">
                    <td style="text-align: right;">${trabajador.cedula_trabajador}</td>
                    <td style="text-align: left;">${trabajador.nombre_trabajador}</td>
                    <td style="text-align: left;">${trabajador.primer_apellido_trabajador} ${trabajador.segundo_apellido_trabajador || ''}</td>
                    <td style="text-align: center;">${formattedNum}</td>
                    <td style="text-align: center;">${fechaNacimiento}</td>
                    <td style="text-align: left;">${(trabajador.mail_trabajador == null) ? '' : trabajador.mail_trabajador}</td>
                    <td style="text-align: right;">${(trabajador.celular_trabajador == null) ? '' : trabajador.celular_trabajador}</td> 
                </tr>`;
            // Agregar la fila al tbody
            $('#tablaBodyTrabajadores').append(row);
        });

        console.log("Después de llenar:", $('#tablaBodyTrabajadores').html());
        // Agregar eventos a las filas después de que se hayan creado
        agregarEventosFilas();
    } else {
        // Si no hay resultados, mostrar un mensaje
        $('#tablaBodyTrabajadores').append('<tr><td colspan="8" style="text-align: center;">No se encontraron trabajadores.</td></tr>');
    }
}

// Función para agregar eventos a las filas
function agregarEventosFilas() {

    $('#miTabla').DataTable().clear().rows.add().draw();
    // Verificar si la tabla ya está inicializada y destruirla
    if ($.fn.dataTable.isDataTable('#miTabla')) {
        $('#miTabla').DataTable().destroy(); // Destruir la tabla existente
    }

    // Inicializar una nueva tabla DataTable
    new DataTable('#miTabla', {
        pageLength: 1000, // Muestra 1000 filas
        searching: false, // Oculta la barra de búsqueda
        lengthChange: false, // Oculta la selección de cantidad de datos a ver
        ordering: true, // Habilita el ordenamiento
        order: [] // Desactiva el ordenamiento automático al cargar
    });


    const filas = document.querySelectorAll("#tablaBodyTrabajadores tr"); // Asegúrate de que el selector sea correcto
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
                $('#btnEditarEmpresa').attr('href', '/gestion2/trabajador/' + valor);
            }
        });
    });
}

function convertToDate(dateArray) {
    // Verificar que el arreglo tenga al menos 3 elementos
    if (dateArray === null || dateArray === undefined) {
        return `0000-00-00`;
        throw new Error("El arreglo no puede ser nulo o indefinido.");
    }

    if (dateArray.length < 3) {
        return `0000-00-00`;
        throw new Error("El arreglo debe contener al menos año, mes y día.");
    }

    let year = dateArray[0];
    const month = dateArray[1]; // Mes en formato 1-12
    const day = dateArray[2];

    // Asegurarse de que el año tenga 4 dígitos
    year = String(year).padStart(4, '0');

    // Formatear el mes y el día para que tengan dos dígitos
    const formattedMonth = String(month).padStart(2, '0');
    const formattedDay = String(day).padStart(2, '0');

    // Devolver la fecha en formato YYYY-MM-DD
    return `${year}-${formattedMonth}-${formattedDay}`;
}
