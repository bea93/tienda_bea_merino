package curso.java.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.model.Usuario;
import curso.java.tienda.repository.UsuarioRepositorio;

@Service
public class UsuarioServicio {

	@Autowired
	private UsuarioRepositorio ur;
	
	@PostConstruct
	public /* ArrayList<Usuario> */ void cargarUsuarios() {
		Base64 base64 = new Base64();
		String password = "admin";
		String passwordEncriptada = new String(base64.encode(password.getBytes()));
		Usuario u = new Usuario(1, 1, "admin@tiendaonline.es", passwordEncriptada);
		ur.save(u);
		String pass = "1234";
		String passEncriptada = new String(base64.encode(pass.getBytes()));
		u = new Usuario(2, 2, "pepe@tiendaonline.es", passEncriptada);
		ur.save(u);
		/*String pass2 = "paso";
		String passEncriptada2 = new String(base64.encode(pass2.getBytes()));
		u = new Usuario(3, 3, "bmerinomacia@gmail.com", passEncriptada2);
		ur.save(u);*/
	}

	public List<Usuario> getListaUsuarios() {
		return ur.findAll();
	}

	public void addUsuario(Usuario usuario) {
		ur.save(usuario);
	}

	public void delUsuario(int id) {
		Usuario u = ur.getById(id);
		ur.delete(u);
	}

	public void editUsuario(Usuario u) {
		ur.save(u);

	}

	public boolean comprobarLogin(Usuario usuario) {
		boolean result = false;
		Base64 base64 = new Base64();
		List<Usuario> lista = ur.buscarUsuarioLogin(usuario.getEmail(), new String(base64.encode(usuario.getClave().getBytes())));
		if (!lista.isEmpty()) {
			result = true;
		}
		return result;
	}

	public Usuario getUsuarioXId(int id) {
		Usuario u = ur.getById(id);
		return u;
	}

	public Usuario getUsuarioByEmail(String email) {
		Usuario u = ur.findByEmail(email);
		return u;
	}
}
