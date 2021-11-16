package curso.java.tienda.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.model.Configuracion;
public interface ConfiguracionRepositorio extends JpaRepository<Configuracion, Integer> {
	ArrayList<Configuracion> findByClave(String clave);
}
