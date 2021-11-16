package curso.java.tienda.controller;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.model.Configuracion;
import curso.java.tienda.model.DetallesPedido;
import curso.java.tienda.model.MetodoPago;
import curso.java.tienda.model.Producto;
import curso.java.tienda.model.Usuario;
import curso.java.tienda.model.Pedido;
import curso.java.tienda.service.ConfiguracionServicio;
import curso.java.tienda.service.DetallesPedidoServicio;
import curso.java.tienda.service.MetodoPagoServicio;
import curso.java.tienda.service.PedidoServicio;
import curso.java.tienda.service.ProductoServicio;
import curso.java.tienda.service.UsuarioServicio;

@Controller
@RequestMapping("/pedido")
public class PedidoControlador {
	@Autowired
	ProductoServicio pros;
	@Autowired
	DetallesPedidoServicio dps;
	@Autowired
	PedidoServicio ps;
	@Autowired
	UsuarioServicio us;
	@Autowired
	MetodoPagoServicio mps;

	// Muestra el formulario de registro
	@GetMapping("/crearPedido")
	public String crearPedido(@ModelAttribute MetodoPago metodoPago, HttpSession session, Model model) {
		ArrayList<DetallesPedido> carrito = (ArrayList<DetallesPedido>) session.getAttribute("carrito");

		Usuario u = (Usuario) session.getAttribute("usuario");
		if(u != null) {
			Pedido p = new Pedido(u.getId(), metodoPago.getMetodoPago(), Pedido.PENDIENTE, "");
	
			ps.addPedido(p);
	
			for (int i = 0; i < carrito.size(); i++) {
				DetallesPedido dp = carrito.get(i);
				dp.setIdPedido(p.getId());
				dps.addDetallesPedido(dp);
			}
	
			carrito.clear();
			session.setAttribute("mensaje", "Pedido realizado");
		
		}
		return "redirect:/";
	}

	@GetMapping("/listaPedidos")
	public String mostrarPedidos(Model model) {
		model.addAttribute("lista", ps.getListaPedidos());
		return "pedido/listaPedidos";
	}

	@GetMapping("/detalles/{id}")
	public String detallesPedido(@PathVariable int id, Model model) {
		List<DetallesPedido> lista = dps.buscarDetallesPedidoXIdPedido(id);
		model.addAttribute("lista", lista);
		return "pedido/detalle";
	}

	@GetMapping("/enviar/{id}")
	public String cambiarEstadoEnviado(@PathVariable int id, Model model) {
		Pedido p = ps.getPedido(id);
		p.setEstado(Pedido.ENVIADO);
		ps.editPedido(p);

		return "redirect:/pedido/listaPedidos";
	}

	@GetMapping("/solicitarCancelar/{id}")
	public String solicitarCancelar(@PathVariable int id, Model model) {
		Pedido p = ps.getPedido(id);
		p.setEstado(p.PENDIENTE_CANCELAR);
		ps.editPedido(p);

		return "redirect:/pedido/listaPedidos";
	}

	@GetMapping("/cancelar/{id}")
	public String cambiarEstadoCancelado(@PathVariable int id, Model model) {
		Pedido p = ps.getPedido(id);
		p.setEstado(p.CANCELADO);
		ps.editPedido(p);
		return "redirect:/pedido/listaPedidos/";
	}
}
