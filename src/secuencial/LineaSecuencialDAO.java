package secuencial;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Hashtable;
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
	private Hashtable<String, Parada> mapaParadas;

	public LineaSecuencialDAO() {
		
		this.mapaParadas = cargarParadas();
		ResourceBundle rb = ResourceBundle.getBundle("config");
		this.nombre = rb.getString("linea");
		actualizar = true;
	}

	private TreeMap<String, Linea> readFromFile(Hashtable<String, Parada> paradas, String nombreArchivo) {

		TreeMap<String, Linea> mapaLineas = new TreeMap<String, Linea>();

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

			outFile = new Formatter(nombreArchivo);

			for (Entry<String, Linea> e : mapaLineas.entrySet()) {
				outFile.format("%s;%s%s\n", e.getValue().getId(), "I", e.getValue().listarParadasIda());
				outFile.format("%s;%s%s\n", e.getValue().getId(), "R", e.getValue().listarParadasRegreso());
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

	public void insertar(Object objeto) {
		Linea linea = (Linea) objeto;
		mapaLineas.put(linea.getId(), linea);
		writeToFile(mapaLineas, nombre);
		actualizar = true;
	}

	public void actualizar(Object objeto) {
		Linea linea = (Linea) objeto;
		Linea lineaVieja = mapaLineas.get(linea.getId());
		if (lineaVieja == null) {
			throw new LineaInexistenteException();
		} else {
			mapaLineas.put(linea.getId(), lineaVieja);
			writeToFile(mapaLineas, nombre);
			actualizar = true;
		}
	}

	public void borrar(Object objeto) {
		Linea linea = (Linea) objeto;
		mapaLineas.remove(linea.getId());
		writeToFile(mapaLineas, nombre);
		actualizar = true;
	}

	public TreeMap<String, Linea> buscarTodos() {
		if (actualizar) {
			mapaLineas = readFromFile(mapaParadas, nombre);
			actualizar = false;
		}
		return mapaLineas;
	}
	
	private Hashtable<String, Parada> cargarParadas() {
		Hashtable<String, Parada> paradas = new Hashtable<String, Parada>();
		ParadaSecuencialDAO paradasDAO = new ParadaSecuencialDAO();
		Map<String,Parada> ds = paradasDAO.buscarTodos();
		for (Entry<String,Parada> iter : ds.entrySet()){
			paradas.put(iter.getValue().getId(), iter.getValue());			
		}
		return paradas;
	}

}