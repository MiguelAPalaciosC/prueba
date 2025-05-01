$(document).ready(function () {

    // Ocultar la alerta de éxito después de 3 segundos
    if ($('.alertas').length) {
        setTimeout(function () {
            $('.alertas').addClass('hidden');
        }, 2000);
    }

    // Filtrar por código
    $('#buscar').on('keyup', function () {
        var valorBuscar = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(0).text().toLowerCase().indexOf(valorBuscar) > -1);
        });
    });

    // Filtrar por descripción
    $('#buscar2').on('keyup', function () {
        var valorBuscar2 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(1).text().toLowerCase().indexOf(valorBuscar2) > -1);
        });
    });

    // Filtrar por descripción
    $('#buscar3').on('keyup', function () {
        var valorBuscar3 = $(this).val().toLowerCase();
        $('#miTabla tbody tr').filter(function () {
            $(this).toggle($(this).find('td').eq(2).text().toLowerCase().indexOf(valorBuscar3) > -1);
        });
    });
});
$('#btnEditar').click(function() {
    var filaValida = $('#valorSeleccionado').text();

    console.log(filaValida);
    
    // Cambiar || por && para la verificación
    if (filaValida !== '' && filaValida !== null && filaValida !== undefined) {
        // Obtener la fila seleccionada
        const filaSeleccionada = $("#miTabla tbody tr.selected"); // Asegúrate de que la fila seleccionada tenga la clase 'selected'

        if (filaSeleccionada.length > 0) { // Verifica que haya una fila seleccionada
            const id_tipo_dosimetro = filaSeleccionada.data("valor"); // Obtener el valor del atributo data-valor
            const orden = filaSeleccionada.find("td").eq(0).text(); // Primer columna
            const nombre = filaSeleccionada.find("td").eq(1).text(); // Primer columna
            const descripcion = filaSeleccionada.find("td").eq(2).text(); // Primer columna

            console.log(id_tipo_dosimetro);
            console.log(nombre);

            $('#id_tipo_dosimetro').val(id_tipo_dosimetro);
            $('#nombre').val(nombre);
            $('#descripcion').val(descripcion);
            $('#orden').val(orden);

            $('#actualizarRegistro').modal('show'); // Mostrar el modal
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

$('#btnEliminar').click(function () {
    var filaValida = $('#valorSeleccionado').text();

    console.log(filaValida);
    
    // Cambiar || por && para la verificación
    if (filaValida !== '' && filaValida !== null && filaValida !== undefined) {
        // Obtener la fila seleccionada
        const filaSeleccionada = $("#miTabla tbody tr.selected"); // Asegúrate de que la fila seleccionada tenga la clase 'selected'

        if (filaSeleccionada.length > 0) { // Verifica que haya una fila seleccionada
            const id_tipo_dosimetro = filaSeleccionada.data("valor"); // Obtener el valor del atributo data-valor
            const nombre = filaSeleccionada.find("td").eq(0).text(); // Primer columna

            console.log(id_tipo_dosimetro);
            console.log(nombre);

            $('.id_tipo_dosimetro').val(id_tipo_dosimetro);
            $('.nombre').text(nombre);

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
});