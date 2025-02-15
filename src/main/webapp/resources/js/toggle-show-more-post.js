document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".post-content").forEach((content) => {
        if (isTextTruncatedPost(content)) {
            content.classList.add("truncated");
            let showMoreLink = content.closest(".post-container").querySelector(".show-more-link-post");
            if (showMoreLink) {
                showMoreLink.style.display = "inline"; // Mostrar el botón "Ver más"
            }
        }
    });
});

function isTextTruncatedPost(content) {
    const clone = content.cloneNode(true);
    clone.style.position = "absolute";
    clone.style.visibility = "hidden";
    clone.style.height = "auto";
    clone.style.maxHeight = "none";
    clone.style.width = getComputedStyle(content).width;

    document.body.appendChild(clone);
    const fullHeight = clone.scrollHeight;
    document.body.removeChild(clone);

    const computedStyle = window.getComputedStyle(content);
    const lineHeight = parseFloat(computedStyle.lineHeight);
    const maxHeight = lineHeight * 5; // Ajusta según el número de líneas visibles
    return fullHeight > maxHeight;
}

function togglePost(link) {
    let content = link.closest(".post-container").querySelector(".post-content");

    if (content.classList.contains("truncated")) {
        content.classList.remove("truncated");
        content.classList.add("expanded");
        link.textContent = link.getAttribute("data-view-less-post");
    } else {
        content.classList.remove("expanded");
        content.classList.add("truncated");
        link.textContent = link.getAttribute("data-view-more-post");
    }
}

function toggleShowMorePost() {
    document.querySelectorAll(".post-content").forEach(function(content) {
        if (isTextTruncatedPost(content)) {
            content.classList.add("truncated");
            let showMoreLink = content.closest(".post-container").querySelector(".show-more-link-post");
            if (showMoreLink) {
                showMoreLink.style.display = "inline";
            }
        }
    });
}