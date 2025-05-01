// Variable de control para evitar bucles
let isSettingValue = false;

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
        fixedHeader: true,
        pageLength: 1500, // Muestra 100 filas
        searching: false, // Oculta la barra de búsqueda
        lengthChange: false, // Oculta la selección de cantidad de datos a ver
        ordering: true, // Habilita el ordenamiento
        order: [] // Desactiva el ordenamiento automático al cargar
    });

    contarColumnas(); // Llama a la función para contar columnas al cargar la página

    const valorSeleccionado = document.getElementById("valorSeleccionado");
    const empresaSeleccionada = $("#empresa_seleccionada").val();

    // Delegación de eventos para las filas de la tabla
    $("#miTabla tbody").on("click", "tr", function () {
        const filas = $("#miTabla tbody tr");

        // Si la fila ya está seleccionada, la deseleccionamos
        if ($(this).hasClass("selected")) {
            $(this).removeClass("selected");
            valorSeleccionado.textContent = "";
        } else {
            // Deseleccionamos todas las filas y seleccionamos la actual
            filas.removeClass("selected");
            $(this).addClass("selected");

            const valor = $(this).data("valor");
            console.log("Fila seleccionada:", valor);
            valorSeleccionado.textContent = "Seleccionaste: " + valor;
            $('#btnEditarEmpresa').attr('href', '/gestion3/dosimetro/' + empresaSeleccionada + '/' + valor);
        }
    });

    $('#empresa').select2({
        theme: "bootstrap-5",
        width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
        placeholder: $(this).data('placeholder'),
        allowClear: true
    });

    // Comprobar si hay un valor seleccionado previamente
    if (empresaSeleccionada) {
        isSettingValue = true; // Indica que estamos configurando el valor
        $("#empresa").val(empresaSeleccionada).trigger('change');
        $('#btnNuevo').attr('href', '/gestion3/dosimetro/' + empresaSeleccionada);
        isSettingValue = false; // Restablece la variable
    } else {
        $('.btnDosimetro').hide();
    }

    // Formatear la columna 1 (índice 0) de la tabla con separador de miles
    $("#miTabla tbody tr").each(function () {
        const celda = $(this).find("td").eq(0); // columna 1
        const valorOriginal = celda.text().trim();

        // Verifica que sea un número antes de formatear
        const numero = parseFloat(valorOriginal.replace(/,/g, ''));
        if (!isNaN(numero)) {
            const valorFormateado = numero.toLocaleString('es-CO'); // o 'en-US', según el formato deseado
            celda.text(valorFormateado);
        }
    });


    // Filtrar por código
    $('#buscar1').on('keyup', function () {
        var valorBuscar = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(1).text().toLowerCase().indexOf(valorBuscar) > -1);
        });

        contarColumnas(); // Llama a la función para contar columnas
    });

    // Filtrar por descripción
    $('#buscar2').on('keyup', function () {
        var valorBuscar2 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(2).text().toLowerCase().indexOf(valorBuscar2) > -1);
        });
        contarColumnas(); // Llama a la función para contar columnas
    });

    // Filtrar por descripción
    $('#buscar3').on('keyup', function () {
        var valorBuscar3 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(3).text().toLowerCase().indexOf(valorBuscar3) > -1);
        });
        contarColumnas(); // Llama a la función para contar columnas
    });

    // Filtrar por descripción
    $('#buscar4').on('keyup', function () {
        var valorBuscar3 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(4).text().toLowerCase().indexOf(valorBuscar3) > -1);
        });
        contarColumnas(); // Llama a la función para contar columnas
    });

    // Filtrar por descripción
    $('#buscar5').on('keyup', function () {
        var valorBuscar3 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(5).text().toLowerCase().indexOf(valorBuscar3) > -1);
        });
        contarColumnas(); // Llama a la función para contar columnas
    });

    // Filtrar por descripción
    $('#buscar6').on('keyup', function () {
        var valorBuscar3 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(6).text().toLowerCase().indexOf(valorBuscar3) > -1);
        });
        contarColumnas(); // Llama a la función para contar columnas
    });

    // Filtrar por descripción
    $('#buscar7').on('keyup', function () {
        var valorBuscar3 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(7).text().toLowerCase().indexOf(valorBuscar3) > -1);
        });
        contarColumnas(); // Llama a la función para contar columnas
    });

    // Filtrar por descripción
    $('#buscar8').on('keyup', function () {
        var valorBuscar3 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(8).text().toLowerCase().indexOf(valorBuscar3) > -1);
        });
        contarColumnas(); // Llama a la función para contar columnas
    });

    // Filtrar por descripción
    $('#buscar9').on('keyup', function () {
        var valorBuscar3 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(9).text().toLowerCase().indexOf(valorBuscar3) > -1);
        });
        contarColumnas(); // Llama a la función para contar columnas
    });

    // Filtrar por descripción
    $('#buscar10').on('keyup', function () {
        var valorBuscar3 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(10).text().toLowerCase().indexOf(valorBuscar3) > -1);
        });
        contarColumnas(); // Llama a la función para contar columnas
    });

    // Filtrar por descripción
    $('#buscar11').on('keyup', function () {
        var valorBuscar3 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(11).text().toLowerCase().indexOf(valorBuscar3) > -1);
        });
        contarColumnas(); // Llama a la función para contar columnas
    });

    // Filtrar por descripción
    $('#buscar12').on('keyup', function () {
        var valorBuscar3 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(12).text().toLowerCase().indexOf(valorBuscar3) > -1);
        });
        contarColumnas(); // Llama a la función para contar columnas
    });

    function contarColumnas() {
        var columnas = $("#miTabla tbody tr").length;

        var filasVisibles = $("#miTabla tbody tr").filter(function () {
            return $(this).css('display') !== 'none' && $(this).css('visibility') !== 'hidden' && $(this).css('opacity') !== '0';
        }).length;

        console.log("Número de columnas:", filasVisibles); // Muestra el número de columnas en la consola

        actualizarContador();
        $('.numero_resultado').text(filasVisibles); // Actualiza el número de columnas en el elemento con clase "numero_resultado"
    }

    function actualizarContador() {
        $('#miTabla tbody tr:visible').each(function (index) {
            $(this).find('td').eq(0).text(index + 1);
        });
    }
});

// Redirigir al cambiar la selección en el select
$("#empresa").change(function () {
    if (!isSettingValue) { // Solo redirigir si no estamos configurando el valor
        window.location.href = '/gestion3/dosimetros/' + $(this).val();
    }
});