package controlador;

import negocio.Calculo;
import negocio.Empresa;
import net.datastructures.Map;

import java.util.List;

import interfaz.Interfaz;
import modelo.Linea;
import modelo.Tramo;
import modelo.Parada;


public class Coordinador {

	private Empresa empresa;
	private Calculo calculo;
	private Interfaz interfaz;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Calculo getCalculo() {
		return calculo;
	}

	public void setCalculo(Calculo calculo) {
		this.calculo = calculo;
	}
	
	public Interfaz getInterfaz() {
		return interfaz;
	}

	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}
	
	public Linea buscarLinea(Linea linea) {
		return empresa.buscarLinea(linea);
	}

	public Map<String, Linea> listarLineas() {
		return empresa.getLineas();
	}

	public Map<String, Parada> listarParadas() {
		return empresa.getParadas();
	}

	public List<Tramo> listarTramos() {
		return empresa.getTramos();
	}
}
