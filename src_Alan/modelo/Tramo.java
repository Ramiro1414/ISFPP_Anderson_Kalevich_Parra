package modelo;

public class Tramo {

	private Parada parada1;
	private Parada parada2;
	private int tiempo;
	private int tipo;

	public Tramo(Parada parada1, Parada parada2, int tiempo, int tipo) {
		super(); 
		this.parada1 = parada1;
		this.parada2 = parada2;
		this.tiempo = tiempo; 
		this.tipo = tipo;
	}

	public Parada getParada1() {
		return parada1;
	}

	public void setParada1(Parada parada1) { 
		this.parada1 = parada1;
	}
 
	public Parada getParada2() {
		return parada2;
	}

	public void setParada2(Parada parada2) {
		this.parada2 = parada2;
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
		return "Tramo [parada1=" + parada1 + ", parada2=" + parada2
				+ ", tiempo=" + tiempo + ", tipo=" + tipo + "]";
	}
	
	
}
