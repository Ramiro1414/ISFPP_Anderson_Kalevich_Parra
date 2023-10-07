package modelo;

import java.util.Objects;

import net.datastructures.*;

/**
 * Esta clase representa una línea de colectivo y contiene la información
 * relacionada con la línea, como su identificador y la lista de paradas que
 * tiene.
 * 
 * @author Alan Kalevich
 *
 */
public class Linea {

	// atributos de la clase
	private String id; // identificador de la linea, ejemplo: L1
	private LinkedPositionalList<Parada> paradas;

	/**
	 * Constructor de la Linea
	 * 
	 * @param id      identificador de la linea
	 * @param paradas Lista de paradas de la linea
	 */
	public Linea(String id, LinkedPositionalList<Parada> paradas) {
		this.id = id;
		this.paradas = paradas;
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
	 * devuelve la cantidad de paradas de la linea
	 * 
	 * @return la cantidad de paradas de la linea
	 */
	public int getCantParadas() {
		return paradas.size();
	}

	/**
	 * Devuelve la posicion de la primer parada de la lista de paradas.
	 * 
	 * @return la posicion de la primer parada
	 */
	public Position<Parada> firstParada() {
		return paradas.first();
	}

	/**
	 * Devuelve la posicion de la siguiente parada de la lista dada una posicion que
	 * le pasan como parametro
	 * 
	 * @param p Posicion de la parada que le pasan como parametro
	 * @return Posicion de la siguiente parada
	 */
	public Position<Parada> getNextParada(Position<Parada> p) {
		return paradas.after(p);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, paradas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linea other = (Linea) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Linea [id=" + id + ", paradas=" + paradas + "]";
	}

}
