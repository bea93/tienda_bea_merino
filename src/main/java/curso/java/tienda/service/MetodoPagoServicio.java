package curso.java.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.model.MetodoPago;
import curso.java.tienda.repository.MetodoPagoRepositorio;

@Service
public class MetodoPagoServicio {
	@Autowired
	private MetodoPagoRepositorio mpr;
	
	@PostConstruct
	public void cargarMetodoPago(){
		MetodoPago mp = new MetodoPago(1, "Tarjeta");
		mpr.save(mp);
		mp = new MetodoPago(2, "Paypal");
		mpr.save(mp);

	}
	
	public List<MetodoPago> getListaMetodoPago(){ 
		return mpr.findAll();
	}
	
	public MetodoPago getMetodoPagoxId(int id) {
		MetodoPago mp = mpr.getById(id);
		return mp;
	}	
}
