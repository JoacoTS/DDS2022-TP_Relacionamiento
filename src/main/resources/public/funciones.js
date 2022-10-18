document.getElementById("login").addEventListener("click", function(){
    var nombreUsuario = document.getElementById("nombre_usuario").value;
    var contraseniaUsuario = document.getElementById("contrasenia_usuario").value;
    console.log(nombreUsuario);

    var error = false;
    if (nombreUsuario == '') {
        document.getElementById("nombre_usuario").style.border = '3px solid red';
        error = true;
    }
    if (contraseniaUsuario == '') {
        document.getElementById("contrasenia_usuario").style.border = '3px solid red';
        error = true;
    }
    if (!error) {
        window.location = nombreUsuario+"/"+contraseniaUsuario;
    }
});