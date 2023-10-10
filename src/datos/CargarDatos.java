package datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.*;
import net.datastructures.LinkedPositionalList;
import net.datastructures.TreeMap;

public class CargarDatos {

	/**
	 * Carga todas las paradas que estan cargadas en el archivo de texto parada.txt.
	 * 
	 * En principio se carga el mapa de todas las paradas y en cada parada con una
	 * lista de pasajeros vacia.
	 * 
	 * Ante cualquier excepcion que pueda saltar en el codigo, este metodo lanzara
	 * en pantalla la excepcion, la causa y luego finalizara la ejecucion del
	 * programa para que no siga corriendo bajo parametros inadecuados.
	 * 
	 * @param paradaFile nombre del archivo de paradas
	 * @return mapa con todas las paradas existentes
	 */
	public static TreeMap<String, Parada> cargarParadas(String paradaFile) {
		TreeMap<String, Parada> result = new TreeMap<String, Parada>();

		try {

			BufferedReader br = new BufferedReader(new FileReader(paradaFile));

			String linea = br.readLine();
			while (linea != null) {
				String[] arr = linea.split(";");

				Parada current = new Parada(arr[0], arr[1]);
				result.put(arr[0], current);

				linea = br.readLine();
			}
			br.close();

		} catch (Exception e) {
			System.err.println(e);
			System.exit(-1);
		}

		return result;
	}

	/**
	 * Carga todas las lineas que estan cargadas en el archivo de texto linea.txt.
	 * 
	 * Ante cualquier excepcion que pueda saltar en el codigo, este metodo lanzara
	 * en pantalla la excepcion, la causa y luego finalizara la ejecucion del
	 * programa para que no siga corriendo bajo parametros inadecuados.
	 * 
	 * @param paradas    Un mapa de todas las paradas
	 * @param lineasFile nombre del archivo de lineas
	 * @return mapa con todas las lienas existentes
	 */
	public static TreeMap<String, Linea> cargarLineas(TreeMap<String, Parada> paradas, String lineasFile) {
		TreeMap<String, Linea> result = new TreeMap<String, Linea>();

		try {

			BufferedReader br = new BufferedReader(new FileReader(lineasFile));

			String linea = br.readLine();
			while (linea != null) {
				String[] arr = linea.split(";");

				LinkedPositionalList<Parada> paradasIda = new LinkedPositionalList<Parada>();
				for (int i = 2; i < arr.length; i++) {
					paradasIda.addLast(paradas.get(arr[i]));
				}

				LinkedPositionalList<Parada> paradasRegreso = new LinkedPositionalList<Parada>();
				linea = br.readLine();
				arr = linea.split(";");
				for (int i = 2; i < arr.length; i++) {
					paradasRegreso.addLast(paradas.get(arr[i]));
				}

				Linea nuevaLinea = new Linea(arr[0], paradasIda, paradasRegreso);
				result.put(arr[0], nuevaLinea);

				for(Parada parada : paradasIda) {
					parada.agregarLinea(nuevaLinea);
				}
				
				for(Parada parada : paradasRegreso) {
					parada.agregarLinea(nuevaLinea);
				}
				
				linea = br.readLine();
			}
			br.close();

		} catch (Exception e) {
			System.err.println(e);
			System.exit(-1);
		}

		return result;
	}

	public static List<Tramo> cargarTramos(TreeMap<String, Parada> paradas, String tramosFile) {

		Scanner read;
		List<Tramo> tramos = new ArrayList<Tramo>();

		try {

			read = new Scanner(new File(tramosFile));
			read.useDelimiter("\\s*;\\s*");
			Parada v1, v2;
			int tiempo, tipo;
			while (read.hasNext()) {
				v1 = paradas.get(read.next());
				v2 = paradas.get(read.next());
				tiempo = read.nextInt();
				tipo = read.nextInt();
				tramos.add(0, new Tramo(v1, v2, tiempo, tipo));
			}
			read.close();

		} catch (Exception e) {
			System.err.println(e);
			System.exit(-1);
		}

		return tramos;
	}

}
