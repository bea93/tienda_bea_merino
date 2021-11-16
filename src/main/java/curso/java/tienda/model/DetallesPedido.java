package curso.java.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DetallesPedido {
	
	@Id @GeneratedValue
	private int id;
	private int idPedido;
	private int idProducto;
	private String nombreProducto;
	private double precioUnidad;
	private int unidades;
	private float impuesto;
	private double total;
	
	public DetallesPedido() {
		super();
	}
	
	public DetallesPedido(int id, int idProducto, String nombreProducto, double precioUnidad, int unidades) {
		super();
		this.id = id;
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.precioUnidad = precioUnidad;
		this.unidades = unidades;
	}

	public DetallesPedido(int id, int idProducto, String nombreProducto, double precioUnidad, int unidades, double total) {
		super();
		this.id = id;
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.precioUnidad = precioUnidad;
		this.unidades = unidades;
		this.total = precioUnidad * unidades;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = precioUnidad * unidades;
	}

}
