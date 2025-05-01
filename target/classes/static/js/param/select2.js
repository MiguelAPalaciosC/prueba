$(document).ready(function () {
    $('#nuevoRegistro').on('shown.bs.modal', function () {
        $('#departamento').select2({
            theme: "bootstrap-5",
            dropdownParent: $("#nuevoRegistro"),
            width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
            placeholder: $(this).data('placeholder'),
            allowClear: true
        });
    });
    $('#actualizarRegistro').on('shown.bs.modal', function () {
        $('#departamento2').select2({
            theme: "bootstrap-5",
            dropdownParent: $("#actualizarRegistro"),
            width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
            placeholder: $(this).data('placeholder'),
            allowClear: true
        });
    });
});