package aplicacion;

import modelo.*;
import datos.*;

import negocio.Calculo;
import net.datastructures.TreeMap;

import java.io.IOException;
import java.util.List;

import java.util.Scanner;

public class Aplicacion {

	public static void main(String[] args) {
		
		
		// carga de parametros
		try {
			CargarParametros.parametros();
		} catch (IOException e) {
			System.err.print("Error al cargar parametros");
			System.exit(-1);
		}
		
		TreeMap<String, Parada> paradas = CargarDatos.cargarParadas(CargarParametros.getArchivoParada());
		TreeMap<String, Linea> lineas = CargarDatos.cargarLineas(paradas, CargarParametros.getArchivoLinea());
		List<Tramo> tramos = CargarDatos.cargarTramos(paradas, CargarParametros.getArchivoTramo());
		
		Calculo calculo = new Calculo(paradas, tramos);

		Scanner scanner = new Scanner(System.in);
		System.out.print("Desde que parada hasta que parada quiere ir. Introduzca:\nOrigen: ");
		Parada origen = paradas.get(scanner.nextLine());
		System.out.print("Destino: ");
		Parada destino = paradas.get(scanner.nextLine());

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
