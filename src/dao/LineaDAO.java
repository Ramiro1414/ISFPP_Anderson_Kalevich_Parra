package dao;

import modelo.Linea;
import net.datastructures.Map;

public interface LineaDAO {
	void insertar(Linea linea);

	void actualizar(Linea linea);

	void borrar(Linea linea);

	Map<String, Linea> buscarTodos();
}
