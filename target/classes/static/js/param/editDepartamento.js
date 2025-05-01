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
            const codigo_departamento = filaSeleccionada.data("valor"); // Obtener el valor del atributo data-valor
            const nombre_departamento = filaSeleccionada.find("td").eq(0).text(); // Primer columna
            const codigo_min_departamento = filaSeleccionada.find("td").eq(1).text(); // Segunda columna

            console.log(codigo_departamento);
            console.log(nombre_departamento);
            console.log(codigo_min_departamento);

            $('#codigo_departamento').val(codigo_departamento);
            $('#nombre_departamento').val(nombre_departamento);
            $('#codigo_min_departamento').val(parseInt(codigo_min_departamento));

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
            const codigo_departamento = filaSeleccionada.data("valor"); // Obtener el valor del atributo data-valor
            const nombre = filaSeleccionada.find("td").eq(0).text(); // Primer columna

            console.log(codigo_departamento);
            console.log(nombre);

            $('.codigo_departamento').val(codigo_departamento);
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