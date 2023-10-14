package dao;

//import net.datastructures.List;
import java.util.List;

public interface ListaDAO<T> {

	void insertar(T objeto);

	void actualizar(T objeto);

	void borrar(T objeto);

	List<T> buscarTodos();
	
}
