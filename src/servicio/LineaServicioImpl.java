package servicio;

import dao.MapaDAO;
import secuencial.LineaSecuencialDAO;
import modelo.Linea;
import net.datastructures.Map;

public class LineaServicioImpl implements MapaServicio{

	private MapaDAO lineaDAO; 
		
	public LineaServicioImpl(){
		lineaDAO = new LineaSecuencialDAO();
	}
	
	public void insertar(Object objeto) {
		lineaDAO.insertar(objeto);				
	}

	public void actualizar(Object objeto) {
		lineaDAO.actualizar(objeto);						
	}

	public void borrar(Object objeto) {
		lineaDAO.borrar(objeto);
		
	}

	public Map<String,Linea> buscarTodos() {
		return lineaDAO.buscarTodos();
	}
	
}
