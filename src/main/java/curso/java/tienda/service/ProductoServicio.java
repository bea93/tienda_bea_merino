package curso.java.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.model.Producto;
import curso.java.tienda.repository.ProductoRepositorio;

@Service
public class ProductoServicio {
	@Autowired
	private ProductoRepositorio pr;
	
	@PostConstruct
	public void cargarProductos() {
		Producto p = new Producto(1, "Martillo", 10.0);
		pr.save(p);
		p = new Producto(2, "Clavos", 1.8);
		pr.save(p);
		p = new Producto(3, "Bridas", 2.5);
		pr.save(p);
		p = new Producto(4, "Cuerda", 3.0);
		pr.save(p);
	}
	
	
	public List<Producto> getListaProductos() {
		return pr.findAll();
	}
	
	public void addProducto(Producto producto) {
		pr.save(producto);
	}
	
	public void delProducto(int id) {
		Producto p = pr.getById(id);
		pr.delete(p);
	}
	
	public void editProducto(Producto p) {
		pr.save(p);
		
	}
	
	public Producto getProductoXId(int id) {
		Producto p = pr.getById(id);
		return p;
	}
}
