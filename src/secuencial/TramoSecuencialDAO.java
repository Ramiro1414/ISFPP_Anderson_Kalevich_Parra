package secuencial;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.ArrayList;

import secuencial.ParadaSecuencialDAO;
import dao.MapaDAO;
import dao.ListaDAO;
import modelo.Tramo;
import net.datastructures.Entry;
import net.datastructures.Map;
import net.datastructures.TreeMap;
import modelo.Parada;

public class TramoSecuencialDAO implements ListaDAO {

	private List<Tramo> listaTramos;
	private String nombre;
	private Hashtable<String, Parada> paradas;
	private boolean actualizar;

	public TramoSecuencialDAO() {
		paradas = this.cargarParadas();
		ResourceBundle rb = ResourceBundle.getBundle("config");
		nombre = rb.getString("tramo");
		actualizar = true;
	}

	private List<Tramo> readFromFile(String nombreArchivo) {
		List<Tramo> listaTramos = new ArrayList<>();
		Scanner read;
		try {
			read = new Scanner(new File(nombreArchivo));
			read.useDelimiter("\\s*;\\s*");
			Parada p1, p2;
			int tiempo, tipo;
			while (read.hasNext()) {
				p1 = paradas.get(read.next());
				p2 = paradas.get(read.next());
				tiempo = read.nextInt();
				tipo = read.nextInt();
				listaTramos.add(new Tramo(p1, p2, tiempo, tipo));
			}
			read.close();
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

		return listaTramos;
	}

	private void writeToFile(List<Tramo> listaTramos, String nombreArchivo) {
		Formatter outFile = null;
		try {
			outFile = new Formatter(nombreArchivo);
			for (Tramo e : listaTramos) {
				outFile.format("%s;%s;%d;%d;\n", e.getParada1().getId(), e.getParada2().getId(), e.getTiempo(),
						e.getTipo());
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
		Tramo tramo = (Tramo) objeto;
		listaTramos.add(tramo);
		writeToFile(listaTramos, nombre);
		actualizar = true;
	}

	@Override
	public void actualizar(Object objeto) {
		Tramo tramo = (Tramo) objeto;
		int pos = listaTramos.indexOf(tramo);
		listaTramos.set(pos, tramo);
		writeToFile(listaTramos, nombre);
		actualizar = true;
	}

	@Override
	public void borrar(Object objeto) {
		Tramo tramo = (Tramo) objeto;
		listaTramos.remove(tramo);
		writeToFile(listaTramos, nombre);
		actualizar = true;
	}

	@Override
	public List<Tramo> buscarTodos() {
		if (actualizar) {
			listaTramos = readFromFile(nombre);
			actualizar = false;
		}
		return listaTramos;
	}

	private Hashtable<String, Parada> cargarParadas() {
		Hashtable<String, Parada> paradas = new Hashtable<String, Parada>();
		MapaDAO<String, Parada> paradasDAO = new ParadaSecuencialDAO();
		Map<String,Parada> ds = paradasDAO.buscarTodos();
		for (Entry<String,Parada> iter : ds.entrySet()){
			paradas.put(iter.getValue().getId(), iter.getValue());			
		}
		return paradas;
	}

}
