package modelo;

public class Tramo {

	private Parada inicio;
	private Parada fin;
	private int tiempo;
	private int tipo;

	public Tramo(Parada parada1, Parada parada2, int tiempo, int tipo) {
		super(); 
		this.inicio = parada1;
		this.fin = parada2;
		this.tiempo = tiempo; 
		this.tipo = tipo;
	}

	public Parada getParada1() {
		return inicio;
	}

	public void setParada1(Parada parada1) { 
		this.inicio = parada1;
	}
 
	public Parada getParada2() {
		return fin;
	}

	public void setParada2(Parada parada2) {
		this.fin = parada2;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Tramo [parada1=" + inicio + ", parada2=" + fin
				+ ", tiempo=" + tiempo + ", tipo=" + tipo + "]";
	}
	
	
}
