package servicio;

import dao.MapaDAO;
import modelo.Parada;
import net.datastructures.Map;

import secuencial.ParadaSecuencialDAO;

public class ParadaServicioImpl implements MapaServicio{

	private MapaDAO paradaDAO;

	public ParadaServicioImpl (){
		paradaDAO = new ParadaSecuencialDAO();
	}
	
	public void insertar(Object objeto) {
		paradaDAO.insertar(objeto);
	}

	public void actualizar(Object objeto) {
		paradaDAO.actualizar(objeto);
	}

	public void borrar(Object objeto) {
		paradaDAO.borrar(objeto);
	}

	public Map<String, Parada> buscarTodos() {
		return paradaDAO.buscarTodos();
	}
	
	
}
