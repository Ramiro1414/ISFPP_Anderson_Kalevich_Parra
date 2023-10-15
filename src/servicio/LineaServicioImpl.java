package servicio;

import secuencial.LineaSecuencialDAO;
import dao.LineaDAO;
import modelo.Linea;
import net.datastructures.Map;

public class LineaServicioImpl implements LineaServicio{

	private LineaDAO lineaDAO; 
		
	public LineaServicioImpl(){
		lineaDAO = new LineaSecuencialDAO();
	}
	
	public void insertar(Linea linea) {
		lineaDAO.insertar(linea);				
	}

	public void actualizar(Linea linea) {
		lineaDAO.actualizar(linea);						
	}

	public void borrar(Linea linea) {
		lineaDAO.borrar(linea);
		
	}

	public Map<String,Linea> buscarTodos() {
		return lineaDAO.buscarTodos();
	}
	
}
