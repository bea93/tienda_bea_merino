package curso.java.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import curso.java.tienda.model.MetodoPago;

public interface MetodoPagoRepositorio extends JpaRepository<MetodoPago, Integer> {
	MetodoPago findById(int id);
}
