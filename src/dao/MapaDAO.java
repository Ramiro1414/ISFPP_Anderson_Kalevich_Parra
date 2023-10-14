package dao;

import net.datastructures.Map;

public interface MapaDAO<K, V> {
	
	void insertar(K objeto);

	void actualizar(K objeto);

	void borrar(K objeto);

	Map<K, V> buscarTodos();
	
}