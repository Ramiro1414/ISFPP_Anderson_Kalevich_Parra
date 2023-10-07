package negocio;

import java.util.ArrayList;
import java.util.List;

import modelo.*;

import net.datastructures.*;

public class Calculo {

	private Graph<Parada, Tramo> colectivo;
	private TreeMap<String, Vertex<Parada>> vertices;

	public Calculo(TreeMap<String, Parada> paradas, List<Tramo> tramos) {

		colectivo = new AdjacencyMapGraph<>(false);

		// Cargar estaciones
		vertices = new TreeMap<String, Vertex<Parada>>();
		for (Entry<String, Parada> estacion : paradas.entrySet())
			vertices.put(estacion.getKey(), colectivo.insertVertex(estacion.getValue()));

		// Cargar tramos
		for (Tramo tramo : tramos)
			colectivo.insertEdge(vertices.get(tramo.getParada1().getId()), vertices.get(tramo.getParada2().getId()),
					tramo);
	} 

	public List<Tramo> rapido(Parada estacion1, Parada estacion2) {
		// copia grafo
		Graph<Parada, Integer> rapido = new AdjacencyMapGraph<>(false);
		Map<Parada, Vertex<Parada>> res = new ProbeHashMap<>();

		for (Vertex<Parada> result : colectivo.vertices())
			res.put(result.getElement(), rapido.insertVertex(result.getElement()));

		Vertex<Parada>[] vert;

		for (Edge<Tramo> result : colectivo.edges()) {
			vert = colectivo.endVertices(result);
			rapido.insertEdge(res.get(vert[0].getElement()), res.get(vert[1].getElement()),
					result.getElement().getTiempo());
		}
		PositionalList<Vertex<Parada>> lista = GraphAlgorithms.shortestPathList(rapido, res.get(estacion1),
				res.get(estacion2));

		List<Tramo> tramos = new ArrayList<Tramo>();

		Vertex<Parada> v1, v2;
		Position<Vertex<Parada>> aux = lista.first();
		while (lista.after(aux) != null) {
			v1 = aux.getElement();
			aux = lista.after(aux);
			v2 = aux.getElement();
			tramos.add(colectivo.getEdge(vertices.get(v1.getElement().getId()), vertices.get(v2.getElement().getId()))
					.getElement());
		}
		return tramos;
	}

}
