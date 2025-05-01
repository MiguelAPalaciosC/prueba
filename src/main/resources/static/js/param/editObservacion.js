$(document).ready(function () {

    // Ocultar la alerta de éxito después de 3 segundos
    if ($('.alertas').length) {
        setTimeout(function () {
            $('.alertas').addClass('hidden');
        }, 2000);
    }
});
$('#btnEditar').click(function() {
    var filaValida = $('#valorSeleccionado').text();

    console.log(filaValida);
    
    // Cambiar || por && para la verificación
    if (filaValida !== '' && filaValida !== null && filaValida !== undefined) {
        // Obtener la fila seleccionada
        const filaSeleccionada = $("#miTabla tbody tr.selected"); // Asegúrate de que la fila seleccionada tenga la clase 'selected'

        if (filaSeleccionada.length > 0) { // Verifica que haya una fila seleccionada
            const id_observacion = filaSeleccionada.data("valor"); // Obtener el valor del atributo data-valor
            const codigo = filaSeleccionada.find("td").eq(0).text(); // Primer columna
            const descripcion = filaSeleccionada.find("td").eq(1).text(); // Segunda columna

            console.log(id_observacion);
            console.log(codigo);
            console.log(descripcion);

            $('#id_observacion').val(id_observacion);
            $('#codigo_observacion').val(codigo);
            $('#descripcion_observacion').val(descripcion);

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
            const id_observacion = filaSeleccionada.data("valor"); // Obtener el valor del atributo data-valor
            const codigo = filaSeleccionada.find("td").eq(0).text(); // Primer columna

            console.log(id_observacion);
            console.log(codigo);

            $('.id_observacion').val(id_observacion);
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
});