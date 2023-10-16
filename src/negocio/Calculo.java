package negocio;

import modelo.Linea;
import modelo.Parada;
import modelo.Tramo;

import net.datastructures.Graph;
import net.datastructures.TreeMap;
import net.datastructures.Vertex;
import net.datastructures.Map;
import net.datastructures.Entry;
import net.datastructures.AdjacencyMapGraph;
import net.datastructures.ProbeHashMap;
import net.datastructures.List;
import net.datastructures.ArrayList;
import net.datastructures.Position;
import net.datastructures.PositionalList;
import net.datastructures.Edge;
import net.datastructures.GraphAlgorithms;

import controlador.Coordinador;

public class Calculo {

	private List<GrafoLinea> colectivos;
	// private Graph<Parada, Tramo> colectivo;
	// private TreeMap<String, Vertex<Parada>> vertices;
	private Coordinador coordinador;

	public Calculo() {
	}

	public void iniciarCalculo(Map<String, Parada> paradas, List<Tramo> tramos, Map<String, Linea> lineas) {

		this.colectivos = new ArrayList<>();

		// Inicializamos grafos en una lista
		for (Entry<String, Linea> linea : lineas.entrySet()) {
			colectivos.add(colectivos.size(), new GrafoLinea(linea.getValue()));
		}

		// colectivo = new AdjacencyMapGraph<>(true);

		// Cargar paradas

		// vertices = new TreeMap<String, Vertex<Parada>>();

		for (GrafoLinea grafo : colectivos) {
			for (Entry<String, Parada> parada : paradas.entrySet()) {
				if (grafo.getLinea().contieneParada(parada.getValue())) {
					grafo.agregarVertice(parada.getValue());
				}
			}
		}

		/*
		 * for (Entry<String, Parada> parada : paradas.entrySet())
		 * vertices.put(parada.getKey(), colectivo.insertVertex(parada.getValue()));
		 */

		// Cargar tramos
		for (GrafoLinea grafo : colectivos) {
			for (Tramo tramo : tramos) {
				if (grafo.getLinea().contieneParada(tramo.getParada1())
						&& grafo.getLinea().contieneParada(tramo.getParada2())) {
					grafo.agregarArco(grafo.getVertice(tramo.getParada1()), grafo.getVertice(tramo.getParada2()),
							tramo);
				}
			}
		}

		/*
		 * for (Tramo tramo : tramos)
		 * colectivo.insertEdge(vertices.get(tramo.getParada1().getId()),
		 * vertices.get(tramo.getParada2().getId()), tramo);
		 */

	}

	public List<Tramo> rapido(Parada parada1, Parada parada2) {
		// copia grafo

		List<List<Tramo>> resultados = new ArrayList<>();

		for (GrafoLinea grafo : colectivos) {
			
			if ( ! (grafo.getLinea().contieneParada(parada1) && grafo.getLinea().contieneParada(parada2) )) {
				continue;
			}
			
			Graph<Parada, Integer> rapido = new AdjacencyMapGraph<>(true);
			Map<Parada, Vertex<Parada>> res = new ProbeHashMap<>();

			for (Vertex<Parada> result : grafo.vertices())
				res.put(result.getElement(), rapido.insertVertex(result.getElement()));

			Vertex<Parada>[] vert;

			for (Edge<Tramo> result : grafo.arcos()) {
				vert = grafo.endVertices(result);
				rapido.insertEdge(res.get(vert[0].getElement()), res.get(vert[1].getElement()),
						result.getElement().getTiempo());
			}
			PositionalList<Vertex<Parada>> lista = GraphAlgorithms.shortestPathList(rapido, res.get(parada1),res.get(parada2));

			List<Tramo> tramos = new ArrayList<Tramo>();

			Vertex<Parada> v1, v2;
			Position<Vertex<Parada>> aux = lista.first();
			while (lista.after(aux) != null) {
				v1 = aux.getElement();
				aux = lista.after(aux);
				v2 = aux.getElement();
				tramos.add(tramos.size(),grafo.getArco(grafo.getVertice(v1.getElement()), grafo.getVertice(v2.getElement())).getElement());
				// grafo.getEdge(vertices.get(v1.getElement().getId()), vertices.get(v2.getElement().getId())).getElement());
			}

			resultados.add(resultados.size(), tramos);
			
		} // end for grafo

		List<Tramo> minResult = resultados.get(0);
		int min = 0;
		
		for (Tramo tramo: resultados.get(0)) { // el tramo minimo es el primero
			min += tramo.getTiempo();
		}
		
		for (List<Tramo> listaTramo : resultados) {
			
			int tmp = 0;
			
			for (Tramo tramo : listaTramo){
				tmp += tramo.getTiempo();
			}
			
			if (tmp < min) {
				minResult = listaTramo;
				min = tmp;
			}
				
		}
		
		return minResult;
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
}
