package curso.java.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.tienda.model.Configuracion;
import curso.java.tienda.repository.ConfiguracionRepositorio;

@Service
public class ConfiguracionServicio {
	Logger logger = LogManager.getLogger(ConfiguracionServicio.class.getName());
	
	@Autowired
    private ConfiguracionRepositorio cr;

    
    
    public ConfiguracionServicio(ConfiguracionRepositorio configuracionRepositorio) {
    	
    	
    	logger.info("Insertando Configuracion de prueba");
	}

	public List<Configuracion> getLista() {
    	
    	logger.info("Obteniendo todos los datos de Configuracion");
    	
        return (List<Configuracion>)cr.findAll();
    }
	public List<Configuracion> getListaByClave(String clave) {
    	
    	logger.info("Obteniendo los datos de Configuracion con la calave "+clave);
    	
        return (List<Configuracion>)cr.findByClave(clave);
    }

    public void add(Configuracion configuracion) {
    	
    	logger.info("Añadir datos de Configuracion "+configuracion.toString());
    	
    	cr.save(configuracion);
    }
    
    public void delete(int id) {
    	
    	logger.info("Borrando la Configuracion con el id "+id);
    	
    	cr.deleteById(id);
    }
    
    public Configuracion get(int id) {
    	
    	logger.info("Obtener la Configuracion según el ID "+id);
    	
    	return cr.findById(id).get();
    }
    
    public void edit(Configuracion configuracion) {
    	
    	logger.info("Editar datos de Configuracion "+configuracion.toString());
    	
    	cr.save(configuracion);
    }
}
