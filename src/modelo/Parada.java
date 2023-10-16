package modelo;

import java.util.Objects;
import net.datastructures.LinkedPositionalList;
import net.datastructures.Position;

/**
 * Esta clase representa una parada de colectivo y contiene la información
 * relacionada con la parada, como su identificador, dirección y la lista de
 * pasajeros que se encuentran en la parada.
 * 
 * @author Alan Kalevich
 *
 */
public class Parada {

	// atrbutos de la clase
	private String id; // ej: "P1"
	private String direccion; // ej: "28 de julio, 455"
	private LinkedPositionalList<Linea> lineas;
	

	/**
	 * Constructor de la Parada
	 * 
	 * @param id        identificador de la Parada
	 * @param direccion direccion de la Parada
	 */
	public Parada(String id, String direccion) {
		this.id = id;
		this.direccion = direccion;
		lineas = new LinkedPositionalList<Linea>();
	}

	/**
	 * devuelve el id
	 * 
	 * @return el id
	 */
	public String getId() {
		return id;
	}

	/**
	 * devuelve la direccion
	 * 
	 * @return la direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	public void agregarLinea(Linea linea) {
		lineas.addLast(linea);
	}
	
	public Linea removerLinea(Position<Linea> linea) {
		return lineas.remove(linea); 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parada other = (Parada) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Parada [id=" + id + ", direccion=" + direccion + "]";
	}
}
