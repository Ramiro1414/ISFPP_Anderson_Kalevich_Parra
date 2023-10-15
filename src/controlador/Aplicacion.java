package controlador;

import modelo.*;
import datos.*;
import interfaz.Interfaz;
import negocio.Calculo;
import negocio.Empresa;
import net.datastructures.TreeMap;

import java.io.IOException;
import java.util.List;

import java.util.Scanner;

import secuencial.*;
import controlador.Coordinador;

public class Aplicacion {

	// logica
	private Empresa empresa;
	private Calculo calculo;

	// vista
	private Interfaz interfaz;

	//
	private Coordinador coordinador;

	public static void main(String[] args) {
		
		Aplicacion miAplicacion = new Aplicacion();
		miAplicacion.iniciar();
		miAplicacion.consultar();
		
	}
	
	public void iniciar() {

		this.empresa = Empresa.getEmpresa();
		this.calculo = new Calculo();
		this.coordinador = new Coordinador();
		this.interfaz = new Interfaz();
		
		/* Se establecen las relaciones entre clases */
		calculo.setCoordinador(coordinador);
		interfaz.setCoordinador(coordinador);

		/* Se establecen relaciones con la clase coordinador */
		coordinador.setEmpresa(empresa);
		coordinador.setCalculo(calculo);

		calculo.iniciarCalculo(coordinador.listarParadas(), coordinador.listarTramos());
	}
	
	public void consultar(){
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Desde que parada hasta que parada quiere ir. Introduzca:\nOrigen: ");
		Parada origen = coordinador.listarParadas().get(scanner.nextLine());
		System.out.print("Destino: ");
		Parada destino = coordinador.listarParadas().get(scanner.nextLine());

		List<Tramo> result = calculo.rapido(origen, destino);

		int tmp = 0;
		for (Tramo iter : result) {
			tmp += iter.getTiempo();
		}
		System.out.println("\nEl recorido tarda " + tmp + " minutos.");

		System.out.println("Finish");
		scanner.close();

	}

}
