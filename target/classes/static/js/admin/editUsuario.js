$(document).ready(function () {
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
                $('#btnEditar').attr('href', '/admin/usuario/' + valor);
            }
        });
    });

    if ($('#estado')) {
        $('.estado').val($('#estado').val());
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

$('#togglePassword').on('click', function () {
    // Obtener el campo de contraseña
    var passwordField = $('#password');

    // Cambiar el tipo de input entre 'password' y 'text'
    if (passwordField.attr('type') === 'password') {
        passwordField.attr('type', 'text');
        $(this).html('<i class="bi bi-eye-slash"></i>'); // Cambiar el texto del botón
    } else {
        passwordField.attr('type', 'password');
        $(this).html('<i class="bi bi-eye"></i>'); // Cambiar el texto del botón
    }
});