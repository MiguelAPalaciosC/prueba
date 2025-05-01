$(document).ready(function () {

    // Ocultar la alerta de éxito después de 3 segundos
    if ($('.alertas').length) {
        setTimeout(function () {
            $('.alertas').addClass('hidden');
        }, 2000);
    }
});
$('#btnEditar').click(function () {
    var filaValida = $('#valorSeleccionado').text();

    console.log(filaValida);

    // Cambiar || por && para la verificación
    if (filaValida !== '' && filaValida !== null && filaValida !== undefined) {
        // Obtener la fila seleccionada
        const filaSeleccionada = $("#miTabla tbody tr.selected"); // Asegúrate de que la fila seleccionada tenga la clase 'selected'

        if (filaSeleccionada.length > 0) { // Verifica que haya una fila seleccionada
            const codigo_ciudad = filaSeleccionada.data("valor"); // Obtener el valor del atributo data-valor
            const departamento = filaSeleccionada.find("td").eq(0).text(); // Primer columna
            const nombre_ciudad = filaSeleccionada.find("td").eq(1).text(); // Segunda columna
            const codigo_min_ciudad = filaSeleccionada.find("td").eq(2).text(); // Segunda columna

            console.log(codigo_ciudad);
            console.log(departamento);
            console.log(nombre_ciudad);

            $('#codigo_ciudad').val(codigo_ciudad);

            // Busca el valor correspondiente al texto
            var valorDepartamento = $('#departamento2 option').filter(function () {
                return $(this).text() === departamento; // Compara el texto
            }).val(); // Obtiene el valor del option

            // Establece el valor en el Select2
            if (valorDepartamento) {
                $('#departamento2').val(valorDepartamento).trigger('change'); // Establece el valor y actualiza Select2
            }

            $('#nombre_ciudad').val(nombre_ciudad);
            $('#codigo_min_ciudad').val(codigo_min_ciudad);

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
            const codigo_ciudad = filaSeleccionada.data("valor"); // Obtener el valor del atributo data-valor
            const ciudad = filaSeleccionada.find("td").eq(1).text(); // Primer columna

            console.log(codigo_ciudad);
            console.log(ciudad);

            $('.codigo_ciudad').val(codigo_ciudad);
            $('.ciudad').text(ciudad);

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