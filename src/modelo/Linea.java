package modelo;

import java.util.Objects;

import net.datastructures.LinkedPositionalList;
import net.datastructures.Position;

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
	private LinkedPositionalList<Parada> paradasIda; 
	private LinkedPositionalList<Parada> paradasRegreso;

	/**
	 * Constructor de la Linea
	 * 
	 * @param id      identificador de la linea
	 * @param paradas Lista de paradas de la linea
	 */
	public Linea(String id, LinkedPositionalList<Parada> paradasIda, LinkedPositionalList<Parada> paradasRegreso) {
		this.id = id;
		this.paradasIda = paradasIda;
		this.paradasRegreso = paradasRegreso;
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
		return paradasIda.size();
	}
	
	public void agregarParadaIda (Parada parada) {
		paradasIda.addLast(parada);
	}
	
	public void agregarParadaRegreso (Parada parada) {
		paradasRegreso.addLast(parada); 
	}

	public Parada removerParadaIda(Position<Parada> parada) {
		return paradasIda.remove(parada);
	}
	
	public Parada removerParadaRegreso(Position<Parada> parada) {
		return paradasRegreso.remove(parada); 
	}

	public String listarParadasIda(){
		String paradas = null;
		for (Parada parada : paradasIda){
			paradas += ";" + parada.getId();
		}
		return paradas;
	}

	public String listarParadasRegreso(){
		String paradas = null;
		for (Parada parada : paradasRegreso){
			paradas += ";" + parada.getId();
		}
		return paradas;
	}
	
	public boolean contieneParada(Parada parada) {
		
		if (paradasIda.contains(parada))
			return true;
		
		if (paradasRegreso.contains(parada))
			return true;
		
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, paradasIda, paradasRegreso);
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
		return "Linea [id=" + id + ", paradasIda=" + paradasIda + ", paradasRegreso=" + paradasRegreso + "]";
	}

}
