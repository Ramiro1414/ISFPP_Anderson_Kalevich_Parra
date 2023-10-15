package dao;

import modelo.Parada;
import net.datastructures.Map;

public interface ParadaDAO {
	
	void insertar(Parada estacion);

	void actualizar(Parada estacion);

	void borrar(Parada estacion);

	Map<String, Parada> buscarTodos();
}