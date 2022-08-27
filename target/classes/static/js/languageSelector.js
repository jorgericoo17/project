//Adds to the URL the lang attribute
$(document).ready(function() {
    let languages = $("#language-dropdown [id^=elem_]");
    languages.each(function() {
        $(this).on('click', function () {
            console.log('click');
            window.location.replace('?lang=' + $(this).data('code'));
        });
    });
});
