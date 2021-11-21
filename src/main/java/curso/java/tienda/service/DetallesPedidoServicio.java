package curso.java.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.model.DetallesPedido;
import curso.java.tienda.repository.DetallesPedidoRepositorio;

@Service
public class DetallesPedidoServicio {
	
	Logger logger = LogManager.getLogger(DetallesPedidoServicio.class.getName());
	
	@Autowired
	private DetallesPedidoRepositorio dpr;
	
	@PostConstruct
	public void cargarDetallesPedido() {
	}
	
	
	public List<DetallesPedido> getListaDetallesPedido() {
		logger.info("Se ha obtenido la lista de detalles de pedidos");
		return dpr.findAll();
	}
	
	public void addDetallesPedido(DetallesPedido detallesPedido) {
		dpr.save(detallesPedido);
		logger.info("Se han añadido los detalles de un pedido");
	}
	
	public void delDetallesPedido(int id) {
		DetallesPedido dp = dpr.getById(id);
		dpr.delete(dp);
		logger.info("Se han eliminado los detalles de un pedido");
	}
	
	public void editDetallesPedido(DetallesPedido dp) {
		dpr.save(dp);
		logger.info("Se han editado los detalles de un pedido");
	}
	
	public DetallesPedido getDetallesPedidoXId(int id) {
		DetallesPedido dp = dpr.getById(id);
		return dp;
	}
	
	public List<DetallesPedido> buscarDetallesPedidoXIdPedido(int id) {
		return dpr.buscarDetallesPedidoXIdPedido(id);
	}
}
