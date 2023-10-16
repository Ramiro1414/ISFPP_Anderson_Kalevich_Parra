package dao;

import net.datastructures.Map;

public interface MapaDAO<T,F> {
	
	void insertar(T linea);

	void actualizar(T linea);

	void borrar(T linea);

	Map<T, F> buscarTodos();
}

