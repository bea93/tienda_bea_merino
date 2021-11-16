package curso.java.tienda.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.model.DetallesPedido;
import curso.java.tienda.model.Producto;
import curso.java.tienda.service.ProductoServicio;

@Controller
@RequestMapping("/carrito")
public class CarritoControlador {
	@Autowired
	ProductoServicio ps;
	
	
	@GetMapping("/add/{id}")
	public String add(@PathVariable int id, HttpSession session) {
		ArrayList<DetallesPedido> carrito = (ArrayList<DetallesPedido>)session.getAttribute("carrito");
		boolean existe = false;
		int unidades = 1;
		Producto p = ps.getProductoXId(id);
		
		if(carrito == null) {
			carrito = new ArrayList<DetallesPedido>();
			session.setAttribute("carrito", carrito);
		}
		
		for(DetallesPedido dp : carrito) {
			if(dp.getIdProducto() == (p.getId())) {
				dp.setUnidades(dp.getUnidades() + 1);
				dp.setTotal(dp.getUnidades() * dp.getPrecioUnidad());
				existe = true;
				break;
			}
		}
		
		if(!existe) {
			DetallesPedido dp = new DetallesPedido(0,  p.getId(), p.getNombre(), p.getPrecio(), unidades, (unidades * p.getPrecio()));
			carrito.add(dp);
		}
		
		session.setAttribute("carrito", carrito);
		
		return "redirect:/";
	}
	
	@GetMapping("/del/{id}")
	public String del(@PathVariable int id, HttpSession session) {
		ArrayList<DetallesPedido> carrito = (ArrayList<DetallesPedido>)session.getAttribute("carrito");
		Producto p = ps.getProductoXId(id);

		for(DetallesPedido dp : carrito) {
			if(dp.getIdProducto() == p.getId()) {
				if(dp.getUnidades() > 1) {
					dp.setUnidades(dp.getUnidades() - 1);
					dp.setTotal(dp.getUnidades() * dp.getPrecioUnidad());
				}else{
					carrito.remove(dp);
					break;
				}
			}	
		}
		
		return "carrito";
	}
	
	//Para ir al carrito
		@GetMapping("/pagar")
		public String pagar(HttpSession session, Model model) {
			ArrayList<DetallesPedido> carrito = (ArrayList<DetallesPedido>)session.getAttribute("carrito");
			return "producto/pagar";
		}
	
	//Para ir al carrito
	@GetMapping("")
	public String cargar(HttpSession session, Model model) {
		ArrayList<DetallesPedido> carrito = (ArrayList<DetallesPedido>)session.getAttribute("carrito");
		return "carrito";
	}
}
