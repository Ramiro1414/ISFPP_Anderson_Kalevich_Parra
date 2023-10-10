package datos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CargarParametros {
	
	private static String archivoLinea;
	private static String archivoTramo;
	private static String archivoParada;

	public static void parametros() throws IOException {
		
		Properties prop = new Properties();

		InputStream input = new FileInputStream("config.properties");

		prop.load(input);
		
		archivoLinea = prop.getProperty("linea");
		archivoTramo = prop.getProperty("tramo");
		archivoParada = prop.getProperty("parada");
		
	}

	public static String getArchivoLinea() {
		return archivoLinea;
	}

	public static String getArchivoTramo() {
		return archivoTramo;
	}

	public static String getArchivoParada() {
		return archivoParada;
	}
	
}