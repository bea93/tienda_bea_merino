package curso.java.tienda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.model.Producto;
import curso.java.tienda.service.ProductoServicio;

@Controller
@RequestMapping("/producto")
public class ProductoControlador {
	@Autowired
	ProductoServicio ps;
	
	// Muestra el formulario de registro
		@GetMapping("/listar")
		public String listar(Model model) {
			model.addAttribute("catalogo", ps.getListaProductos());
			return "producto/listar";
		}

		// Muestra el formulario de registro
		@GetMapping("/new")
		public String creacion(Model model) {
			model.addAttribute("producto", new Producto());
			return "producto/crear";
		}

		// Recoge los datos del formulario de registro
		@PostMapping("/new")
		public String crear(Model model, @ModelAttribute Producto producto, HttpSession session) {
			ps.addProducto(producto);
			session.setAttribute("mensaje", "Producto creado.");
			return "redirect:/";

		}

		// Muestra el formulario de registro
		@GetMapping("/update/{id}")
		public String edicion(@PathVariable int id, Model model) {
			Producto p = ps.getProductoXId(id);
			model.addAttribute("producto", p);
			return "producto/editar";
		}

		// Recoge los datos del formulario de registro
		@PostMapping("/update/submit")
		public String modificar(@ModelAttribute Producto producto, HttpSession session) {
			ps.editProducto(producto);
			session.setAttribute("mensaje", "Producto actualizado.");
			return "redirect:/";

		}

		@GetMapping("/del/{id}")
		public String eliminar(@PathVariable int id) {
			ps.delProducto(id);
			return "redirect:/producto/listar";
		}

		@GetMapping("/detalle/{id}")
		public String detallesProductoo(@PathVariable int id, Model model) {
			Producto p = ps.getProductoXId(id);
			model.addAttribute("producto", p);
			return "producto/detalle";

		}
}
