package curso.java.tienda.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.model.DetallesPedido;
import curso.java.tienda.service.ProductoServicio;
import curso.java.tienda.service.UsuarioServicio;

@Controller
@RequestMapping("")
public class InicioControlador {
	@Autowired
	UsuarioServicio us;
	@Autowired
	ProductoServicio ps;
	
	@GetMapping("")
	public String setAtributoDeSesion(Model model, HttpSession session) {
		
		ArrayList<DetallesPedido> cart = (ArrayList<DetallesPedido>)session.getAttribute("carrito");
		model.addAttribute("catalogo", ps.getListaProductos());
		//model.addAttribute("list", us.getListaUsuarios());
		
		if(cart == null) {
			cart = new ArrayList<DetallesPedido>();
			session.setAttribute("carrito", cart);
		}
		
		Integer rol = (Integer)session.getAttribute("rol");
		if(rol == null) {
			session.setAttribute("rol", 0);
		}
		
	    return "index";
	}
}
