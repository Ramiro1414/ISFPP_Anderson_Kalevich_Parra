package modelo;

import java.util.Objects;

/**
 * Esta clase representa a un pasajero de transporte publico y contiene
 * informacion sobre su origen, destino, identificador y la cantidad de
 * colectivos que ha tenido que esperar.
 * 
 * @author Alan Kalevich
 *
 */
public class Pasajero {

	// atributos de la clase
	private Parada origen; // parada de origen del pasajero
	private Parada destino; // parada de desstino del pasajero
	private String id; // identificador del pasajero

	/**
	 * Constructor del Pasajero
	 * 
	 * @param origen  Parada de origen del pasajero
	 * @param destino Parada de origen del pasajero
	 * @param id      identificador del pasajero
	 */
	public Pasajero(Parada origen, Parada destino, String id) {
		this.origen = origen;
		this.destino = destino;
		this.id = id;
	}

	/**
	 * devuelve el origen
	 * 
	 * @return el origen
	 */
	public Parada getOrigen() {
		return origen;
	}

	/**
	 * devuelve el destino
	 * 
	 * @return el destino
	 */
	public Parada getDestino() {
		return destino;
	}

	/**
	 * devuelve el id
	 * 
	 * @return el id
	 */
	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(destino, id, origen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pasajero other = (Pasajero) obj;
		return Objects.equals(id, other.id); 
	}

	@Override
	public String toString() {
		return "Pasajero [origen=" + origen.getId() + ", destino=" + destino.getId() + ", id=" + id
				+ "]";
	}

}
