package modelo;

import java.util.Objects;

import net.datastructures.*;

/**
 * Esta clase representa un colectivo y contiene la información relacionada con
 * el colectivo, como su identificador, la línea a la que pertenece, la cantidad
 * de asientos y la capacidad total de pasajeros.
 * 
 * @author Alan Kalevich
 *
 */
public class Colectivo {

	// atributos de la clase
	private String id; // identificador del colectivo ejemplo: C01
	private Linea linea; // linea del colectivo
	private final int asientos; // numero de asientos en el colectivo
	private final int totalPasajeros; // numero total de pasajeros que entran en el colectivo
	private LinkedPositionalList<Pasajero> pasajeros = new LinkedPositionalList<Pasajero>(); // lista de pasajeros en
																								// el colecivo

	/**
	 * Constructor del Colectivo
	 * 
	 * @param id             identificador del colectivo
	 * @param linea          linea a la que pertenece el colectivo
	 * @param asientos       cantidad de asientos del colectivo
	 * @param totalPasajeros capacidad del colectivo
	 */
	public Colectivo(String id, Linea linea, int asientos, int totalPasajeros) {
		this.id = id;
		this.linea = linea;
		this.asientos = asientos;
		this.totalPasajeros = totalPasajeros;
	}

	/**
	 * devuelve la id
	 * 
	 * @return la id
	 */
	public String getId() {
		return id;
	}

	/**
	 * devuelve la cantidad de asientos del colectivo
	 * 
	 * @return la cantidad de asientos
	 */
	public int getAsientos() {
		return asientos;
	}

	/**
	 * devuelve la capacidad del colectivo
	 * 
	 * @return la capacidad del colectivo
	 */
	public int getTotalPasajeros() {
		return totalPasajeros;
	}

	/**
	 * devuelve la linea del colectivo
	 * 
	 * @return la linea del colectivo
	 */
	public Linea getLinea() {
		return linea;
	}

	/**
	 * devuelve la cantidad de pasajeros del colectivo
	 * 
	 * @return la cantidad de pasajeros del colectivo
	 */
	public int getCantPasajeros() {
		return pasajeros.size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colectivo other = (Colectivo) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Colectivo: id: " + id + ", linea: " + linea.getId();
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
