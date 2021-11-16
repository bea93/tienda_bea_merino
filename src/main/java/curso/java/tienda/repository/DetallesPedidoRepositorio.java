package curso.java.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.model.DetallesPedido;

public interface DetallesPedidoRepositorio extends JpaRepository<DetallesPedido, Integer>{

	@Query(value = "select * from detalles_pedido where id_pedido=?1", nativeQuery = true)
	List<DetallesPedido> buscarDetallesPedidoXIdPedido(int idPedido);
}
