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
	@Autowired
	private PedidoRepositorio pr;
	
	@PostConstruct
	public void cargarPedido(){
		
	}
	
	public List<Pedido> getListaPedidos() {
    	
    	
        return pr.findAll();
    }
	public List<Pedido> getListaPedidosCliente(int idCliente) {
    	
    	
        return pr.findByIdUsuario(idCliente);
    }

    public Pedido addPedido(Pedido pedido) {
    	
    	return pr.save(pedido);
    }
    
    public void deletePedido(int id) {
    	
    	
    	pr.deleteById(id);
    }
    
    public Pedido getPedido(int id) {
    	Pedido p = pr.getById(id);
    	return p;
    }
    
    public Pedido editPedido(Pedido pedido) {
    	
    	
    	return pr.save(pedido);
    }
}
