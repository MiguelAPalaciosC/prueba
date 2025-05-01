const header = document.getElementById("header");
const titulo = document.getElementById("titulo");
let lastScrollY = window.scrollY;

window.addEventListener("scroll", () => {
    let currentScrollY = window.scrollY;

    if (currentScrollY > lastScrollY) {
        // Scroll hacia abajo: ocultar header, pegar título arriba
        header.hidden = true;
        titulo.style.top = "0";
    } else if (currentScrollY < lastScrollY) {
        // Scroll hacia arriba: mostrar header, ajustar título debajo del header
        header.hidden = false;
        titulo.style.top = "19vh";
    }

    lastScrollY = currentScrollY;
});
