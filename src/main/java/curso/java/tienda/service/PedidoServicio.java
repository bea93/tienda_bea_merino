package curso.java.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.model.Pedido;
import curso.java.tienda.repository.PedidoRepositorio;

@Service
public class PedidoServicio {
	
	Logger logger = LogManager.getLogger(PedidoServicio.class.getName());
	@Autowired
	private PedidoRepositorio pr;
	
	@PostConstruct
	public void cargarPedido(){
		
	}
	
	public List<Pedido> getListaPedidos() {
    	
		logger.info("Se ha obtenido la lista de pedidos");
        return pr.findAll();
    }
	public List<Pedido> getListaPedidosCliente(int idCliente) {
    	
		logger.info("Se ha obtenido la lista de pedidos de un cliente");
        return pr.findByIdUsuario(idCliente);
    }

    public Pedido addPedido(Pedido pedido) {
    	logger.info("Se ha añadido un pedido");
    	return pr.save(pedido);
    }
    
    public void deletePedido(int id) {
    	logger.info("Se ha eliminado un pedido");
    	pr.deleteById(id);
    }
    
    public Pedido getPedido(int id) {
    	Pedido p = pr.getById(id);
    	return p;
    }
    
    public Pedido editPedido(Pedido pedido) {
    	logger.info("Se ha editado un pedido");
    	return pr.save(pedido);
    }
}
