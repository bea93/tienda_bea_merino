package curso.java.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
	Usuario findByEmail(String email);

	@Query(value = "select * from Usuario where email=?1 and clave=?2", nativeQuery = true)
	List<Usuario> buscarUsuarioLogin(String email, String clave);
}
