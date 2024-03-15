package p4.ej_3;

import java.util.ArrayList;
import java.util.List;

import p4.ej_3.DatosDistribuidor.Productos;

public class SolucionDistribuidor {
	public static List<Productos> ListaProductos;
	public static List<Integer> solucion;
	public static Integer CosteAlmacenamiento;
	
	public static SolucionDistribuidor of_Range(List<Integer> ls) {
		return new SolucionDistribuidor(ls);
	}
	public static SolucionDistribuidor empty() {
		return new SolucionDistribuidor();
	}
	
	private SolucionDistribuidor() {
		solucion = new ArrayList<Integer>();
		CosteAlmacenamiento=0;
		ListaProductos = new ArrayList<DatosDistribuidor.Productos>();
	}
	private SolucionDistribuidor(List<Integer> ls) {
	    solucion = new ArrayList<>();
	    CosteAlmacenamiento = 0;
	    int numDestinos = DatosDistribuidor.getNumDestinos();
	    for (int i = 0; i < ls.size(); i++) {
	        Integer producto = i / numDestinos; // Calcular el índice del producto
	        Integer destino = i % numDestinos; // Calcular el índice del destino
	        solucion.add(ls.get(i));
	        if(ls.get(i)!=0) {
	        	CosteAlmacenamiento += DatosDistribuidor.getAlmacenamientoCoste(producto, destino)*ls.get(i);
	        }
	    }
	}

	@Override
	public String toString() {
		return solucion.toString() + "\nCosteAlmacenamiento = "+CosteAlmacenamiento.toString();
	}
	
}
