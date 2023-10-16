package secuencial;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Hashtable;

import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import dao.MapaDAO;
import modelo.Linea;
import modelo.Parada;
import net.datastructures.TreeMap;
import net.datastructures.Entry;
import net.datastructures.Map;

public class ParadaSecuencialDAO implements MapaDAO {

	private Hashtable<String, Linea> lineas;
	private TreeMap<String, Parada> mapaParadas;
	private String nombre;
	private boolean actualizar;

	public ParadaSecuencialDAO() {
		ResourceBundle rb = ResourceBundle.getBundle("config");
		nombre = rb.getString("parada");
		actualizar = true;
	}

	private TreeMap<String, Parada> readFromFile(String nombreArchivo) {

		TreeMap<String, Parada> mapaParadas = new TreeMap<String, Parada>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
			String linea = br.readLine();
			while (linea != null) {
				String[] arr = linea.split(";");
				Parada current = new Parada(arr[0], arr[1]);
				mapaParadas.put(arr[0], current);
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
		return mapaParadas;
	}

	private void writeToFile(TreeMap<String, Parada> mapaParadas, String nombreArchivo) {
		Formatter outFile = null;
		try {
			outFile = new Formatter(nombreArchivo);
			for (Entry<String, Parada> iter : mapaParadas.entrySet()) {
				outFile.format("%s;%s;\n", iter.getValue().getId(), iter.getValue().getDireccion());
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
		Parada parada = (Parada) objeto;
		mapaParadas.put(parada.getId(), parada);
		writeToFile(mapaParadas, nombre);
		actualizar = true;
	}

	@Override
	public void actualizar(Object objeto) {
		Parada paradaNueva = (Parada) objeto;
		Parada paradaVieja = mapaParadas.get(paradaNueva.getId());
		if (paradaVieja == null) {
			throw new ParadaInexistenteException("Esta parada no existe! " + paradaNueva);
		} else {
			mapaParadas.put(paradaNueva.getId(), paradaNueva);
			writeToFile(mapaParadas, nombre);
			actualizar = true;
		}
	}

	@Override
	public void borrar(Object objeto) {
		Parada parada = (Parada) objeto;
		mapaParadas.remove(parada.getId());
		writeToFile(mapaParadas, nombre);
		actualizar = true;
	}

	@Override
	public TreeMap<String, Parada> buscarTodos() {
		if (actualizar) {
			mapaParadas = readFromFile(nombre);
			actualizar = false;
		}
		return mapaParadas;
	}
	
}
