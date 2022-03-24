$(function (){
    const urlSearchParams = new URLSearchParams(window.location.search);
    if(urlSearchParams.has('evn') && urlSearchParams.get('evn') === "error"){
        var e_type = urlSearchParams.get('err');
        var msg = "Generic error";
        if(e_type === "bad_credential"){
            msg = "Invalid username or password";
        } else if (e_type === "at_exception") {
            msg = "The username can't contains the character \"@\".";
        } else if (e_type === "not_equal_password") {
            msg = "The passwords inserted don't match.";
        } else if (e_type === "username_existing") {
            msg = "The username you have used already exists.";
        } else if (e_type === "email_existing") {
            msg = "The email you have used already exists.";
        } else if (e_type === "invalid_name") {
            msg = "The name you have inserted is invalid.";
        } else if (e_type === "invalid_surname") {
            msg = "The surname you have inserted is invalid.";
        } else if (e_type === "invalid_username") {
            msg = "The username you have inserted is invalid.";
        } else if (e_type === "invalid_password") {
            msg = "The password you have inserted is invalid.";
        }
        $('#errorContent').text(msg);
        $('#errorModal').modal('show');
    }
});