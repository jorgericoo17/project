$(document).ready(function () {
    let language = '//cdn.datatables.net/plug-ins/1.12.1/i18n/es-ES.json';
    if (window.location.href.includes("lang=ga")) {
        language = '//cdn.datatables.net/plug-ins/1.12.1/i18n/gl.json';
    }
    $.extend( $.fn.dataTable.defaults, {
        searching: false
    });
    $('.dataTable').DataTable({
        language: {
            url: language
        }
    });
    $.extend( $.fn.dataTable.defaults, {
        searching: false,
    });
});