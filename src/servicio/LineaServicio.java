package servicio;

import modelo.Linea;
import net.datastructures.Map;

public interface LineaServicio {

	void insertar(Linea linea);

	void actualizar(Linea linea);

	void borrar(Linea linea);

	Map<String, Linea> buscarTodos();

}
