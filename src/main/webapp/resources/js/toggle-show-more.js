document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".message-content").forEach((content) => {
        if (isTextTruncated(content)) {
            content.classList.add("truncated");
            let showMoreLink = content.closest(".message-bubble").querySelector(".show-more-link");
            if (showMoreLink) {
                showMoreLink.style.display = "inline"; // Mostrar el botón "Ver más"
            }
        }
    });
});

function isTextTruncated(content) {
    // Clonamos el elemento para medirlo sin restricciones de truncado
    const clone = content.cloneNode(true);
    // Quitar cualquier clase que afecte la altura
    clone.classList.remove("truncated", "expanded");
    // Establecer estilos para que no se apliquen restricciones y que no interfiera en la maquetación
    clone.style.position = "absolute";
    clone.style.visibility = "hidden";
    clone.style.height = "auto";
    clone.style.maxHeight = "none";
    // Opcional: establecer el ancho igual para que la línea se rompa igual que en el original
    clone.style.width = getComputedStyle(content).width;

    // Insertar temporalmente el clon en el documento
    document.body.appendChild(clone);
    const fullHeight = clone.scrollHeight;
    document.body.removeChild(clone);

    // Obtener el lineHeight real del contenido (asegúrate de que sea numérico)
    const computedStyle = window.getComputedStyle(content);
    const lineHeight = parseFloat(computedStyle.lineHeight);
    const maxHeight = lineHeight * 5; // Altura para 5 líneas

    return fullHeight > maxHeight;
}

function toggleMessage(link) {
    let messageContent = link.closest('.message-bubble').querySelector('.message-content');

    if (messageContent.classList.contains('truncated')) {
        messageContent.classList.remove('truncated');
        messageContent.classList.add('expanded');
        link.textContent = link.getAttribute("data-view-less");
    } else {
        messageContent.classList.remove('expanded');
        messageContent.classList.add('truncated');
        link.textContent = link.getAttribute("data-view-more");
    }
}

function evaluarMensajes() {
    // Recorremos todos los elementos de mensajes
    document.querySelectorAll(".message-content").forEach((content) => {
        // Clonamos el contenido para medirlo sin restricciones
        const clone = content.cloneNode(true);
        // Quitamos las clases que afectan el layout (truncated/expanded)
        clone.classList.remove("truncated", "expanded");
        // Aseguramos que el clon se mida correctamente sin interferir con el layout
        clone.style.position = "absolute";
        clone.style.visibility = "hidden";
        clone.style.height = "auto";
        clone.style.maxHeight = "none";
        clone.style.overflow = "visible";
        clone.style.whiteSpace = "normal";
        clone.style.wordBreak = "break-word";
        // Fijamos el ancho para que el wrapping sea igual que en el original
        clone.style.width = getComputedStyle(content).width;

        document.body.appendChild(clone);
        let fullHeight = clone.scrollHeight;
        document.body.removeChild(clone);

        // Obtenemos el line-height del contenido
        let computedStyle = window.getComputedStyle(content);
        let lineHeight = parseFloat(computedStyle.lineHeight);
        // Si no se obtiene correctamente el line-height, podemos asignar un valor por defecto (ej. 24px)
        if (isNaN(lineHeight)) {
            lineHeight = 24;
        }
        let maxHeight = lineHeight * 5; // Altura máxima para 5 líneas

        // Debug (opcional): muestra en consola la medición para cada mensaje
        // console.log("fullHeight:", fullHeight, "maxHeight:", maxHeight, content);

        // Si el contenido real excede la altura permitida, se le agrega la clase truncated y se muestra el enlace
        if (fullHeight > maxHeight) {
            content.classList.add("truncated");
            let showMoreLink = content.closest(".message-bubble").querySelector(".show-more-link");
            if (showMoreLink) {
                showMoreLink.style.display = "inline";
            }
        } else {
            // Si el contenido es corto, nos aseguramos de ocultar el enlace
            content.classList.remove("truncated");
            let showMoreLink = content.closest(".message-bubble").querySelector(".show-more-link");
            if (showMoreLink) {
                showMoreLink.style.display = "none";
            }
        }
    });
}
