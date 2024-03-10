package p4.ej_3;

import java.util.ArrayList;
import java.util.List;

import p4.ej_3.DatosDistribuidor.Productos;

public class SolucionDistribuidor {
	public static List<Productos> ListaProductos;
	public static List<Integer> solucion;
	
	public static SolucionDistribuidor of_Range(List<Integer> ls) {
		return new SolucionDistribuidor(ls);
	}
	public static SolucionDistribuidor empty() {
		return new SolucionDistribuidor();
	}
	
	private SolucionDistribuidor() {
		solucion = new ArrayList<Integer>();
		ListaProductos = new ArrayList<DatosDistribuidor.Productos>();
	}
	private SolucionDistribuidor(List<Integer> ls) {
		solucion = new ArrayList<Integer>();
		ListaProductos = new ArrayList<DatosDistribuidor.Productos>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i)>0) {
				
			}
		}
	}
}
