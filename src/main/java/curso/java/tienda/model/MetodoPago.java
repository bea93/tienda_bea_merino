package curso.java.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MetodoPago {

	@Id @GeneratedValue
	private int id;
	private String metodoPago;
	
	public MetodoPago() {
		super();
	}
	
	public MetodoPago(int id, String metodoPago) {
		super();
		this.id = id;
		this.metodoPago = metodoPago;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	
}
