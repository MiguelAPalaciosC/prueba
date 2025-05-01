$(document).ready(function () {
    
    $('#departamento').val($('#departamento_seleccionado').val()).trigger('change');
    console.log($('#departamento').val());

    var estadoSeleccionado = $('#estado_seleccionado').val();

    // Verificar el valor y marcar el radio button correspondiente
    if (estadoSeleccionado === 'A') {
        $('#activo').prop('checked', true); // Marca el radio button "Activo"
    } else if (estadoSeleccionado === 'I') {
        $('#inactivo').prop('checked', true); // Marca el radio button "Inactivo"

        $('#divObservaciones').show();
        $('#observaciones').prop('required', true);
    }

    var nit = $('#nit').val();

    $('#nitHidden').val(nit.replace(/\./g, ''));
    let formato = Number(nit).toLocaleString("es-CO");

    $('#nit').val(formato);

    setTimeout(function () {
        $('#ciudad').val($('#ciudad_seleccionado').val()).trigger('change');
        console.log($('#ciudad').val());
    }, 600); // 500 milliseconds delay
});
