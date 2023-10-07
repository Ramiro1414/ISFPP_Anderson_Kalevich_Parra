package aplicacion;

import datos.*;
import modelo.*;
import net.datastructures.*;
import negocio.Calculo;
import java.util.List;
import java.util.ArrayList;

public class Aplicacion {

	public static void main(String[] args) {

		
		LinkedPositionalList<Parada> paradas1 = new LinkedPositionalList<Parada>();
		TreeMap<String, Parada> treeParadas = new TreeMap<String, Parada>();

		Parada p1 = paradas1.addLast(new Parada("p1", "1")).getElement(); 
		Parada p2 = paradas1.addLast(new Parada("p2", "2")).getElement(); 
		Parada p3 = paradas1.addLast(new Parada("p3", "3")).getElement(); 
		Parada p4 = paradas1.addLast(new Parada("p4", "4")).getElement(); 
		Parada p5 = paradas1.addLast(new Parada("p5", "5")).getElement(); 
		
		treeParadas.put("p1", p1);
		treeParadas.put("p2", p2);
		treeParadas.put("p3", p3);
		treeParadas.put("p4", p4);
		treeParadas.put("p5", p5);
		
		List<Tramo> tramos = new ArrayList<Tramo>();
		tramos.add(new Tramo(p1, p2, 1, 0));
		tramos.add(new Tramo(p2, p3, 2, 0));
		tramos.add(new Tramo(p3, p5, 4, 0));
		tramos.add(new Tramo(p1, p4, 40, 0));
		tramos.add(new Tramo(p4, p5, 4, 0)); 
		tramos.add(new Tramo(p2, p4, 5, 0)); 
	
		Linea l1 = new Linea("L1", paradas1);
		Colectivo c1 = new Colectivo("c1", l1, 30, 60); 
		
		Calculo calculo = new Calculo(treeParadas, tramos); 

		List<Tramo> result = calculo.rapido(p2, p4); 
		
		int tmp = 0;
		for(Tramo iter : result) {
			System.out.print(iter + "\n");
			tmp += iter.getTiempo();
		}
		System.out.println("\n\nEl recorido tarda " + tmp + " minutos.");
		
		System.out.println("Finish"); 
	}

}
