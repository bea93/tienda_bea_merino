package curso.java.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.model.DetallesPedido;
import curso.java.tienda.repository.DetallesPedidoRepositorio;

@Service
public class DetallesPedidoServicio {
	@Autowired
	private DetallesPedidoRepositorio dpr;
	
	@PostConstruct
	public void cargarDetallesPedido() {
	}
	
	
	public List<DetallesPedido> getListaDetallesPedido() {
		return dpr.findAll();
	}
	
	public void addDetallesPedido(DetallesPedido orderDetails) {
		dpr.save(orderDetails);
	}
	
	public void delDetallesPedido(int id) {
		DetallesPedido od = dpr.getById(id);
		dpr.delete(od);
	}
	
	public void editDetallesPedido(DetallesPedido od) {
		dpr.save(od);
		
	}
	
	public DetallesPedido getDetallesPedidoXId(int id) {
		DetallesPedido od = dpr.getById(id);
		return od;
	}
	
	public List<DetallesPedido> buscarDetallesPedidoXIdPedido(int id) {
		return dpr.buscarDetallesPedidoXIdPedido(id);
	}
}
