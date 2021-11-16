var pass = document.getElementById('pass');
var img = document.getElementById('ojo');

img.onclick = function() {

	var campo = document.getElementById("clave");
	if (campo.type == "password") {
		this.src = '../css/invisible.png';
		campo.type = "text";
	} else {
		campo.type = "password";
		this.src = '../css/visible.png';
	}
}