package servicio;

import dao.ParadaDAO;
import modelo.Parada;
import net.datastructures.Map;

import secuencial.ParadaSecuencialDAO;

public class ParadaServicioImpl implements ParadaServicio{

	private ParadaDAO paradaDAO;

	public ParadaServicioImpl (){
		paradaDAO = new ParadaSecuencialDAO();
	}
	
	public void insertar(Parada parada) {
		paradaDAO.insertar(parada);
	}

	public void actualizar(Parada parada) {
		paradaDAO.actualizar(parada);
	}

	public void borrar(Parada parada) {
		paradaDAO.borrar(parada);
	}

	public Map<String, Parada> buscarTodos() {
		return paradaDAO.buscarTodos();
	}
	
	
}
