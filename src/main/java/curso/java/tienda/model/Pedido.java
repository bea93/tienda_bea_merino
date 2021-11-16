package curso.java.tienda.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pedido {
	
	public static String ENVIADO = "Enviado";
	public static String PENDIENTE = "Pendiente";
	public static String PENDIENTE_CANCELAR = "Pendiente de cancelar";
	public static String CANCELADO = "Cancelado";

	@Id
	@GeneratedValue
	private int id;
	private int idUsuario;
	private Date fecha;
	private String metodoPago;
	private String estado;
	private String numFactura;
	private double total;

	public Pedido() {
		super();
	}

	public Pedido(int id, int idUsuario, Date fecha, String metodoPago, String estado, String numFactura,
			double total) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.metodoPago = metodoPago;
		this.estado = estado;
		this.numFactura = numFactura;
		this.total = total;
	}

	public Pedido(int idUsuario, String metodoPago, String estado, String numFactura) {
		super();
		this.idUsuario = idUsuario;
		this.fecha = new Date();
		this.metodoPago = metodoPago;
		this.estado = estado;
		this.numFactura = numFactura;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public static Double calcularTotalDetalles(ArrayList<DetallesPedido> cart) {
		Double total = 0d;

		for (DetallesPedido od : cart)
			total += od.getTotal();

		return total;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", idUsuario=" + idUsuario + ", fecha=" + fecha + ", metodoPago=" + metodoPago
				+ ", estado=" + estado + ", numFactura=" + numFactura + ", total=" + total + "]";
	}
}
