package servicio;

import modelo.Parada;
import net.datastructures.Map;

public interface ParadaServicio {

	void insertar(Parada parada);

	void actualizar(Parada parada);

	void borrar(Parada parada);

	Map<String, Parada> buscarTodos();

}
