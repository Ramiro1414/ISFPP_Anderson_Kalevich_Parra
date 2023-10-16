package negocio;

import net.datastructures.List;

import modelo.Parada;
import modelo.Linea;
import modelo.Tramo;
import negocio.Empresa;

import net.datastructures.Map;
import net.datastructures.ArrayList;
import net.datastructures.List;

import servicio.ParadaServicioImpl;
import servicio.LineaServicioImpl;
import servicio.ListaServicio;
import servicio.MapaServicio;
import servicio.TramoServicioImpl;

public class Empresa {

	private static Empresa empresa = null;

	private String nombre;
	private Map<String, Linea> lineas;
	private MapaServicio lineasServicio;
	private Map<String, Parada> paradas;
	private MapaServicio paradasServicio;
	private List<Tramo> tramos;
	private ListaServicio tramosServicio;

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
		tramosServicio = new TramoServicioImpl();
		tramos = tramosServicio.buscarTodos();
	}

	// Lineas
	public void agregarLinea(Linea linea) throws LineaExisteException {
		if (lineas.get(linea.getId()) != null)
			throw new LineaExisteException("Esta linea ya existe! " + linea);
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
	
	// Paradas
	public void agregarParada(Parada parada) {
		if (paradas.get(parada.getId()) != null)
			//
		paradas.put(parada.getId(), parada);
		paradasServicio.insertar(parada);		
	}

	public void modificarParada(Parada parada) {
		if (paradas.get(parada.getId()) != null)
			paradas.put(parada.getId(), parada);			
		paradasServicio.actualizar(parada);		
	}

	public void borrarParada(Parada parada) {
		Parada emp = buscarParada(parada);
		paradas.remove(emp.getId());
		paradasServicio.borrar(parada);		
	}

	public Parada buscarParada(Parada parada) {
		return paradas.get(parada.getId());
	}
	
	// Tramos
	public void agregarTramo(Tramo tramo) throws TramoExisteException {
		if (tramos.contains(tramo))
			throw new TramoExisteException("Este tramo ya existe! " + tramo);
		tramos.add(tramos.size(), tramo);
		tramosServicio.insertar(tramo);	
	}

	public void modificarTramo(Tramo tramo) {
		if (tramos.contains(tramo))
			tramos.set(tramos.indexOf(tramo), tramo);
		tramosServicio.actualizar(tramo);		
	}

	public void borrarTramo(Tramo tramo) {
		tramos.remove(tramo);
		tramosServicio.borrar(tramo);		
	}

	public Tramo buscarTramo(Tramo tramo) {
		return tramos.search(tramo);
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
