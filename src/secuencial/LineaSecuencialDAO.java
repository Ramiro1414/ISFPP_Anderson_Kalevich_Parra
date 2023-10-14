package secuencial;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Formatter;
import java.util.FormatterClosedException;

import dao.MapaDAO;
import modelo.Linea;
import modelo.Parada;
import net.datastructures.Map;
import net.datastructures.TreeMap;
import net.datastructures.Entry;
import net.datastructures.LinkedPositionalList;

public class LineaSecuencialDAO implements MapaDAO {

	private String nombre;
	private boolean actualizar;
	private TreeMap<String, Linea> mapaLineas;

	public LineaSecuencialDAO() {
		ResourceBundle rb = ResourceBundle.getBundle("config");
		this.nombre = rb.getString("linea");
		actualizar = true;
	}

	public Map<String, Linea> readFromFile(TreeMap<String, Parada> paradas, String nombreArchivo) {

		Map<String, Linea> mapaLineas = new TreeMap<String, Linea>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));

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
				mapaLineas.put(arr[0], nuevaLinea);
				for (Parada parada : paradasIda) {
					parada.agregarLinea(nuevaLinea);
				}

				for (Parada parada : paradasRegreso) {
					parada.agregarLinea(nuevaLinea);
				}

				linea = br.readLine();
			}

			br.close();

		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening file.");
			fileNotFoundException.printStackTrace();

		} catch (NoSuchElementException noSuchElementException) {
			System.err.println("Error in file record structure");
			noSuchElementException.printStackTrace();

		} catch (IllegalStateException illegalStateException) {
			System.err.println("Error reading from file.");
			illegalStateException.printStackTrace();

		} catch (Exception e) {
			System.err.println(e);
			System.exit(-1);
		}

		return mapaLineas;
	}

	private void writeToFile(TreeMap<String, Linea> mapaLineas, String nombreArchivo) {
		Formatter outFile = null;
		try {
			String textoArchivo;

			outFile = new Formatter(nombreArchivo);
			for (Entry<String, Linea> e : mapaLineas.entrySet()) {
				
				for (int i = 1; i <= 6; i++) {

					/*
					 * String idLinea = "L" + i;
					 * 
					 * // L1;I; if (e.getKey() == idLinea) { textoArchivo += e.getValue().getId() +
					 * ";" + "I;"; }
					 */

					String idLinea = "L" + i;
					String tipo = "I"; // O "R" según sea necesario

					String textoArchivo = idLinea + ";" + tipo;

					if (mapaLineas.containsKey(idLinea)) {
						Linea linea = mapaLineas.get(idLinea);
						String[] paradas = linea.getParadas().split(";"); // Supongamos que las paradas están separadas
																			// por punto y coma

						for (String parada : paradas) {
							textoArchivo += ";" + parada;
						}

						outFile.format("%s\n", textoArchivo);
					}

				}

				outFile.format("%s;%s;\n", e.getValue().getId(), e.getNombre());
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error creating file.");
		} catch (FormatterClosedException formatterClosedException) {
			System.err.println("Error writing to file.");
		} finally {
			if (outFile != null)
				outFile.close();
		}
	}

	@Override
	public void insertar(Object objeto) {
		Linea linea = (Linea) objeto;
		mapaLineas.put(linea.getId(), linea);
		writeToFile(mapaLineas, nombre);
		actualizar = true;
	}

	@Override
	public void actualizar(Object objeto) {
		Linea lineaNueva = (Linea) objeto;
		Linea lineaVieja = mapaLineas.get(lineaNueva.getId());
		if (lineaVieja == null) {
			throw new LineaInexistenteException ("Esta linea no existe! " + lineaNueva);
		}
		else {
			mapaLineas.put(lineaNueva.getId(), lineaNueva);			
			writeToFile(mapaLineas, nombre);
			actualizar = true;			
		}
	}

	@Override
	public void borrar(Object objeto) {
		Linea linea = (Linea) objeto;
		mapaLineas.remove(linea.getId());
		writeToFile(mapaLineas, nombre);
		actualizar = true;
	}

	@Override
	public Map<String, Linea> buscarTodos() {
		if (actualizar) {
			mapaLineas = readFromFile(nombre);
			actualizar = false;
		}
		return mapaLineas;
	}

}
