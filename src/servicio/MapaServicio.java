package servicio;

import modelo.Linea;
import net.datastructures.Map;

public interface MapaServicio<T, F> {

	void insertar(T linea);

	void actualizar(T linea);

	void borrar(T linea);

	Map<T, F> buscarTodos();

}
