package servicio;

import secuencial.TramoSecuencialDAO;

import java.util.List;
import dao.TramoDAO;
import modelo.Tramo;

public class TramoServicioImpl implements TramoServicio {

	private TramoDAO tramoDAO; 
		
	public TramoServicioImpl(){
		tramoDAO = new TramoSecuencialDAO();
	}
	
	public void insertar(Tramo tramo) {
		tramoDAO.insertar(tramo);				
	}
	
	public void actualizar(Tramo tramo) {
		tramoDAO.actualizar(tramo);						
	}

	public void borrar(Tramo tramo) {
		tramoDAO.borrar(tramo);
		
	}

	public List<Tramo> buscarTodos() {
		return tramoDAO.buscarTodos();
	}
	
}
