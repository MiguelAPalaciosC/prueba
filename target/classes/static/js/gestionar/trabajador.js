$(document).ready(function () {
    var toastElements = document.querySelectorAll('.toast');
    toastElements.forEach(function (toastEl) {
        var toast = new bootstrap.Toast(toastEl, {
            delay: 10000 // Tiempo en milisegundos (5000 ms = 5 segundos)
        });
        toast.show();
    });

    $('#id_titulo').val($('#id_titulo_select').val());
    $('#sexo_trabajador').val($('#genero_select').val());

    $('#escolaridad option').filter(function () {
        return $(this).text() === $('#escolaridad_select').val(); // Compara el texto
    }).prop('selected', true); // Establece la opción como seleccionada

    $('#escolaridad').select2({
        theme: "bootstrap-5",
        width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
        placeholder: $(this).data('placeholder'),
        allowClear: true
    });

    $('#id_titulo').select2({
        theme: "bootstrap-5",
        width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
        placeholder: $(this).data('placeholder'),
        allowClear: true
    });

    $('#mail_trabajador').change(function (e) {
        var emailRegex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        var EmailId = this.value;
        if (emailRegex.test(EmailId)) {
            this.style.backgroundColor = "";

        } else {
            $('.xmail').hide().removeClass('hide').slideDown('fast');
            this.style.backgroundColor = "LightPink";
        }
    });
});


$('#cedula_trabajador').change(function () {
    var cedula_trabajador = $(this).val();
    var $this = $(this); // Almacena una referencia al elemento

    console.log('change cedula_trabajador');


    if (cedula_trabajador || cedula_trabajador > 0) {
        $.ajax({
            url: '/gestion2/cedula',
            type: 'GET',
            data: { cedula_trabajador: cedula_trabajador },
            dataType: 'json',
            success: function (data) {
                console.log(data);
                // Aquí puedes manejar la respuesta y actualizar la UI según sea necesario
                if (data) {
                    // alert(data.message); // Muestra un mensaje al usuario
                    console.log("length: " + data.length);

                    if (data.length > 0) {
                        if ($('#trabajador').val()) {
                            if (data.length == 1) {
                                if (data[0].id_trabajador != $('#trabajador').val()) {
                                    $this.css('background-color', '#ff6688'); // Cambia el color de fondo a rojo
                                    $('.invalid-codigo').show(); //
                                } else {
                                    $this.css('background-color', '#40b780b5');
                                }
                            } else {
                                $this.css('background-color', '#ff6688'); // Cambia el color de fondo a rojo
                                $('.invalid-codigo').show(); //
                            }
                        } else {
                            $this.css('background-color', '#ff6688'); // Cambia el color de fondo a rojo
                            $('.invalid-codigo').show(); //
                        }
                    } else {
                        $this.css('background-color', '#40b780b5'); // Cambia el color de fondo a verde
                    }
                } else {
                    // alert("No se encontraron empresas para el código proporcionado.");
                    $this.css('background-color', '#40b780b5'); // Cambia el color de fondo a rojo
                }

                // Resetear el color de fondo al cambiar de código
                // Si deseas resetear el color después de un tiempo, puedes usar setTimeout
                setTimeout(function () {
                    $this.css('background-color', '');
                    $('.invalid-codigo').hide(); // Oculta el mensaje de error al código inválido

                    if (data.length > 0) {
                        if ($('#trabajador').val()) {
                            if (data.length == 1) {
                                if (data[0].id_trabajador != $('#trabajador').val()) {
                                    $('#cedula_trabajador').val('');
                                }
                            } else {
                                $('#cedula_trabajador').val('');
                            }
                        } else {
                            $('#cedula_trabajador').val('');
                        }
                    }
                }, 3000); // Resetea el color después de 2 segundos
            },
            error: function (jqXHR, textStatus, errorThrown) {
                // Imprimir el objeto completo en la consola
                console.error('Error id de la empresa:', jqXHR);

                // O acceder a propiedades específicas del error
                console.error('Error status:', textStatus);
                console.error('Error thrown:', errorThrown);

                // Mostrar un mensaje de error al usuario
                // alert("Ocurrió un error al buscar la empresa. Por favor, inténtalo de nuevo.");
            }
        });
    } else {
        console.error('Error cedula de trabajador: código vacío');
        $('#cedula_trabajador').val('');
        // alert("Por favor, ingresa un código válido.");
    }
});
