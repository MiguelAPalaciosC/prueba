$(document).ready(function () {
    var toastElements = document.querySelectorAll('.toast');
    toastElements.forEach(function (toastEl) {
        var toast = new bootstrap.Toast(toastEl, {
            delay: 10000 // Tiempo en milisegundos (5000 ms = 5 segundos)
        });
        toast.show();
    });

    new DataTable('#miTabla', {
        pageLength: 100, // Muestra 100 filas
        searching: false, // Oculta la barra de búsqueda
        lengthChange: false, // Oculta la selección de cantidad de datos a ver
        ordering: true, // Habilita el ordenamiento
        order: [] // Desactiva el ordenamiento automático al cargar
    });

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
            }
        });
    });

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