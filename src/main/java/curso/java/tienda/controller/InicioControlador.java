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

/**
 * Clase Controlador de inicio de la aplicación
 * @author Bea
 * @version 1.0
 * 
 */

@Controller
@RequestMapping("")
public class InicioControlador {
	
	/**
	 * Llama a los servicios que se vayan a necesitar
	 */
	
	@Autowired
	UsuarioServicio us;
	@Autowired
	ProductoServicio ps;
	
	/**
	 * 
	 * @param model en el que guardaremos los atributos necesarios en la aplicación
	 * @param session para guardar los atributos que queramos conservar durante la sesión del usuario
	 * @return index, el fichero html principal de la aplicación 
	 */
	
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
