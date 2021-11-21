package curso.java.tienda.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.tienda.model.Usuario;
import curso.java.tienda.service.UsuarioServicio;


@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
	@Autowired
	UsuarioServicio us;
	
	// Lista los usuarios
		@GetMapping("/listar")
		public String listar(Model model) {
			model.addAttribute("lista", us.getListaUsuarios());
			return "usuario/listar";
		}

	// Muestra el formulario del login
	@GetMapping("/form")
	public String formulario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario/login";
	}

	// Recoge los datos del formulario del login
	@PostMapping("/login")
	public String logarse(Model model, @ModelAttribute Usuario usuario, HttpSession session) {
		if (us.comprobarLogin(usuario)) {
			Usuario u = us.getUsuarioByEmail(usuario.getEmail());
			model.addAttribute("mensaje", "Bienvenid@");
			session.setAttribute("rol", u.getIdRol());
			session.setAttribute("id", u.getId());
			session.setAttribute("usuario", u);
			return "redirect:/";
		} else {
			model.addAttribute("mensaje", "Login incorrecto");
			return "usuario/login";
		}
	}

	// Muestra el formulario de registro
	@GetMapping("/new")
	public String registro(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario/registro";
	}

	// Recoge los datos del formulario de registro
	@PostMapping("/new")
	public String registrarse(Model model, String clave, Usuario usuario, HttpSession session) {
		
		Base64 base64 = new Base64();
		usuario.setIdRol(3);
		String passEncriptada = new String(base64.encode(usuario.getClave().getBytes()));
		usuario.setClave(passEncriptada);
		us.addUsuario(usuario);
		session.setAttribute("rol", usuario.getIdRol());
		session.setAttribute("mensaje", "Usuario creado. Bienvenid@, " + usuario.getEmail());
		return "redirect:/";
	}

	// Muestra el formulario de registro
	@GetMapping("/update/{id}")
	public String edicion(@PathVariable int id, Model model) {
		Usuario u = us.getUsuarioXId(id);
		model.addAttribute("usuario", u);
		return "usuario/editar";
	}

	// Recoge los datos del formulario de registro
	@PostMapping("/update/submit")
	public String modificar(@ModelAttribute Usuario usuario, HttpSession session) {
		us.editUsuario(usuario);
		session.setAttribute("mensaje", "Usuario actualizado.");
		return "redirect:/";

	}

	// Muestra el formulario de registro
	@GetMapping("/contrasena/{id}")
	public String cambioContra(@PathVariable int id, Model model) {
		Usuario usuario = us.getUsuarioXId(id);
		model.addAttribute("usuario", usuario);
		return "usuario/contrasena";
	}

	// Recoge los datos del formulario de registro
		@PostMapping("/contrasena/update")
		public String modificarPass(HttpSession session, Model model, @RequestParam String clave, @RequestParam String clave2, @RequestParam String clave3) {
			String email = (String) session.getAttribute("email");
			Usuario usuario = us.getUsuarioByEmail(email);
			
			//Encripta la clave pasada en el formulario
			Base64 base64 = new Base64();
			String claveActualEnc = new String(base64.encode(clave.getBytes()));
			
			System.out.println(claveActualEnc);
			System.out.println(usuario.getClave());
			
			if (claveActualEnc.equals(usuario.getClave())) {
				System.out.println("Las dos contraseñas coinciden");
				if(clave2.equals(clave3)) {
					String passNuevaEnc = new String(base64.encode(clave2.getBytes()));
					usuario.setClave(passNuevaEnc);
					System.out.println(usuario.getClave());
					us.editUsuario(usuario);
					model.addAttribute("mensaje", "La contraseña se ha actualizado");
					return "redirect:/";
				}else {
					model.addAttribute("mensaje", "Las contraseñas no coinciden");
				}
			}else {
			model.addAttribute("mensaje", "Las contraseñas no coinciden");
			}
			return "usuario/contrasena";
		}

	// Para borrar usuario
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable int id) {
		us.delUsuario(id);
		return "redirect:/usuario/listar";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
