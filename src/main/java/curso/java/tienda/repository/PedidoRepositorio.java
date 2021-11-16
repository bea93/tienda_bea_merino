package curso.java.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.model.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Integer>{
	List<Pedido> findByIdUsuario(int idUsuario);
	List<Pedido> findById(int id);
}
