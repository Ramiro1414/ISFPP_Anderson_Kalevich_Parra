package aplicacion;

import modelo.*;
import datos.*;

import negocio.Calculo;
import net.datastructures.TreeMap;

import java.util.List;

import java.util.Scanner;

public class Aplicacion {

	public static void main(String[] args) {
		
		
		TreeMap<String, Parada> paradas = CargarDatos.cargarParadas("parada.txt");
		TreeMap<String, Linea> lineas = CargarDatos.cargarLineas(paradas, "linea.txt");
		List<Tramo> tramos = CargarDatos.cargarTramos(paradas, "tramo.txt");
		
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
