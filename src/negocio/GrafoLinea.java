package negocio;

import modelo.Linea;
import modelo.Parada;
import modelo.Tramo;
import net.datastructures.AdjacencyMapGraph;
import net.datastructures.Edge;
import net.datastructures.Vertex;

public class GrafoLinea {

	private Linea linea;
	private AdjacencyMapGraph grafo;
	
	public GrafoLinea(Linea linea) {
		this.linea = linea;
		this.grafo = new AdjacencyMapGraph<>(true);
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	
	public Vertex<Parada> agregarVertice(Parada parada) {
		return this.grafo.insertVertex(parada);
	}
	
	public Edge<Tramo> agregarArco(Vertex<Parada> parada1, Vertex<Parada> parada2, Tramo tramo) {
		
		return this.grafo.insertEdge(parada1, parada2, tramo);
		
	}
	
	public Iterable<Vertex<Parada>> vertices() {
		return this.grafo.vertices();
	}
	
	public Vertex<Parada> getVertice(Parada parada) {
		return this.grafo.getVertex(parada);
	}
	
	public Iterable<Edge<Tramo>> arcos(){
		return this.grafo.edges();
	}
	
	public Vertex<Parada>[] endVertices(Edge<Tramo> arcoTramo) {
		return this.grafo.endVertices(arcoTramo);
	}
	
	public Edge<Tramo> getArco (Vertex<Parada> parada1, Vertex<Parada> parada2){
		return this.grafo.getEdge(parada1, parada2);
	}
}
