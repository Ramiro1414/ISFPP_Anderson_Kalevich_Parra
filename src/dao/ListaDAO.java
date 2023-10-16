package dao;

import net.datastructures.List;

public interface ListaDAO<T> {
	
	void insertar(T tramo);

	void actualizar(T tramo);

	void borrar(T tramo);

	List<T> buscarTodos();
}