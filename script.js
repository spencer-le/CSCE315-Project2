document.addEventListener("DOMContentLoaded", function() {
    let observer = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if(entry.isIntersecting) {
                entry.target.style.opacity = "1";
                observer.unobserve(entry.target);
            }
        });
    });

    // Observing both columns
    observer.observe(document.querySelector('.about-col-1'));
    observer.observe(document.querySelector('.about-col-2'));
});
