package curso.java.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import curso.java.tienda.model.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer>{
	Producto findByNombre(String nombre);
}
