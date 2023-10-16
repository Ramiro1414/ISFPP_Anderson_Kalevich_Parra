package servicio;

// import java.util.List;
import net.datastructures.List;

public interface ListaServicio<T> {

	void insertar(T tramo);

	void actualizar(T tramo);

	void borrar(T tramo);

	List<T> buscarTodos();

}
