const inputPasswd = $('#password');
const inputPasswd2 = $('#password2');
const messagePasswd = $('#passConfirm');
const buttonSave = $('#save');

inputPasswd.on('keyup', function() {
    if(inputPasswd2.val() === $(this).val()) {
        messagePasswd.addClass('d-none');
        buttonSave.removeClass('disabled');
    } else {
        messagePasswd.removeClass('d-none');
        buttonSave.addClass('disabled');
    }
});

inputPasswd2.on('keyup', function() {
    if(inputPasswd.val() === $(this).val()) {
        messagePasswd.addClass('d-none');
        buttonSave.removeClass('disabled');
    } else {
        messagePasswd.removeClass('d-none');
        buttonSave.addClass('disabled');
    }
});
