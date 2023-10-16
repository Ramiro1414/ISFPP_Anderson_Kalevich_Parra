package servicio;

import secuencial.TramoSecuencialDAO;

// import java.util.List;

import net.datastructures.List;

import dao.ListaDAO;
import modelo.Tramo;

public class TramoServicioImpl implements ListaServicio {

	private ListaDAO tramoDAO; 
		
	public TramoServicioImpl(){
		tramoDAO = new TramoSecuencialDAO();
	}
	
	public void insertar(Object objeto) {
		tramoDAO.insertar(objeto);				
	}
	
	public void actualizar(Object objeto) {
		tramoDAO.actualizar(objeto);						
	}

	public void borrar(Object objeto) {
		tramoDAO.borrar(objeto);
		
	}

	public List<Tramo> buscarTodos() {
		return tramoDAO.buscarTodos();
	}
	
}
