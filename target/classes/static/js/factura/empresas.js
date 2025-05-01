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
        pageLength: 100, // Muestra 100 filas
        searching: false, // Oculta la barra de búsqueda
        lengthChange: false, // Oculta la selección de cantidad de datos a ver
        ordering: true, // Habilita el ordenamiento
        order: [] // Desactiva el ordenamiento automático al cargar
    });

    const valorSeleccionado = document.getElementById("valorSeleccionado");
    const empresaSeleccionada = $("#empresa_seleccionada").val();

    $('.divRenovarContrato').hide();

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
            $('#btnEditarEmpresa').attr('href', '/gestion5/factura/' + empresaSeleccionada + '/' + valor);

            const estado = $(this).find("td").eq(12).text(); // Primer columna
            console.log(estado);

            if (estado === 'I') {
                $('.divRenovarContrato').show();
                $('.divTitulo').removeClass('col-md-8').addClass('col-md-6');
            } else {
                $('.divRenovarContrato').hide();
                $('.divTitulo').removeClass('col-md-6').addClass('col-md-8');
            }

        }
    });
    
    $("#miTabla tbody tr").each(function () {
        const celda = $(this).find("td").eq(2); // columna 2
        const valorOriginal = celda.text().trim();

        // Verifica que sea un número antes de formatear
        const numero = parseFloat(valorOriginal.replace(/,/g, ''));
        if (!isNaN(numero)) {
            const valorFormateado = numero.toLocaleString('es-CO'); // o 'en-US', según el formato deseado
            celda.text(valorFormateado);
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
        $('#btnNuevo').attr('href', '/gestion5/factura/' + empresaSeleccionada);
        isSettingValue = false; // Restablece la variable
    } else {
        $('.btnContrato').hide();
    }

});

// Redirigir al cambiar la selección en el select
$("#empresa").change(function () {
    if (!isSettingValue) { // Solo redirigir si no estamos configurando el valor
        window.location.href = '/gestion5/facturas/' + $(this).val();
    }
});

$("#btnRenovarContrato").click(function () {
    var filaValida = $('#valorSeleccionado').text();

    // Cambiar || por && para la verificación
    if (filaValida !== '' && filaValida !== null && filaValida !== undefined) {
        // Obtener la fila seleccionada
        const filaSeleccionada = $("#miTabla tbody tr.selected"); // Asegúrate de que la fila seleccionada tenga la clase 'selected'

        if (filaSeleccionada.length > 0) { // Verifica que haya una fila seleccionada
            const id_contrato = filaSeleccionada.data("valor"); // Obtener el valor del atributo data-valor
            const codigo = filaSeleccionada.find("td").eq(0).text(); // Primer columna

            console.log(id_contrato);
            console.log(codigo);

            $('.id_contrato').val(id_contrato);
            $('.codigo').text(codigo);

            $('#eliminarRegistro').modal('show'); // Mostrar el modal
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

    $("#renovarRegistro").modal("show");
});