package negocio;

import java.util.ArrayList;
import java.util.List;

import modelo.Parada;
import modelo.Linea;
import modelo.Tramo;
import negocio.Empresa;
import net.datastructures.Map;
import net.datastructures.TreeMap;
import servicio.ParadaServicioImpl;
import servicio.TramoServicio;
import servicio.LineaServicio;
import servicio.LineaServicioImpl;
import servicio.ParadaServicio;
import servicio.TramoServicioImpl;
import modelo.Parada;
import servicio.ParadaServicioImpl;
import servicio.LineaServicioImpl;
import servicio.TramoServicioImpl;


import secuencial.LineaExisteException;

public class Empresa {

	private static Empresa empresa = null;

	private String nombre;
	private Map<String, Linea> lineas;
	private LineaServicio lineasServicio;
	private Map<String, Parada> paradas;
	private ParadaServicio paradasServicio;
	private List<Tramo> tramos;
	private TramoServicio tramoServicio;

	public static Empresa getEmpresa() {
		if (empresa == null) {
			empresa = new Empresa();
		}
		return empresa;
	}

	private Empresa() {
		super();
		paradasServicio = new ParadaServicioImpl();
		paradas = paradasServicio.buscarTodos();
		lineasServicio = new LineaServicioImpl();
		lineas = lineasServicio.buscarTodos();
		tramoServicio = new TramoServicioImpl();
		tramos = tramoServicio.buscarTodos();
	}

	public void agregarLinea(Linea linea) throws LineaExisteException {
		if (lineas.get(linea.getId()) != null)
			throw new LineaExisteException();
		lineas.put(linea.getId(), linea);
		lineasServicio.insertar(linea);		
	}

	public void modificarLinea(Linea linea) {
		if (lineas.get(linea.getId()) != null)
			lineas.put(linea.getId(), linea);
		lineasServicio.actualizar(linea);		
	}

	public void borrarLinea(Linea linea) {
		Linea emp = buscarLinea(linea);
		lineas.remove(emp.getId());
		lineasServicio.borrar(linea);		
	}

	public Linea buscarLinea(Linea linea) {
		return lineas.get(linea.getId());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Map<String, Linea> getLineas() {
		return lineas;
	}
	
	public Map<String, Parada> getParadas() {
		return paradas;
	}

	public List<Tramo> getTramos() {
		return tramos;
	}

}
