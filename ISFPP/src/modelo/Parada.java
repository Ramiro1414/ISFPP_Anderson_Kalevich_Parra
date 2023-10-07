package modelo;

import java.util.Objects;
import net.datastructures.*;

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
	private LinkedPositionalList<Pasajero> pasajeros = new LinkedPositionalList<Pasajero>(); // lista de pasajeros,
																								// inicia vacia

	/**
	 * Constructor de la Parada
	 * 
	 * @param id        identificador de la Parada
	 * @param direccion direccion de la Parada
	 */
	public Parada(String id, String direccion) {
		this.id = id;
		this.direccion = direccion;
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

	/**
	 * devuelve la cantidad de pasajeros de la parada
	 * 
	 * @return la cantidad de pasajeros de la parada
	 */
	public int getCantPasajeros() {
		return pasajeros.size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(direccion, id, pasajeros);
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

	/**
	 * Recibe la posicion de un pasajero y la remueve de la lista de pasajeros
	 * 
	 * @param pas Posicion del pasajero
	 * @return Pasajero removido
	 */
	public Pasajero removerPasajero(Position<Pasajero> pas) {
		return pasajeros.remove(pas);
	}

	/**
	 * Recibe un pasajero y lo agrega a la lista de pasajeros
	 * 
	 * @param pas Pasajero a agregar
	 */
	public void agregarPasajero(Pasajero pas) {
		pasajeros.addLast(pas);
	}
}
