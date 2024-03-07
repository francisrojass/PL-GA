package p4.ej_1;

import java.util.ArrayList;
import java.util.List;

public class SolucionHuertos {
	List<Integer> solucion;
	
	
	public static SolucionHuertos of(List<Integer> ls) {
		return new SolucionHuertos(ls);
	}
	
	public static SolucionHuertos empty() {
		return new SolucionHuertos();
	}
	
	private SolucionHuertos(List<Integer> ls) {
	    solucion = new ArrayList<Integer>();
	    int numHuertos = DatosHuertos.getNumeroHuertos();
	    for (Integer valor : ls) {
	        if (valor > 0 && valor < numHuertos) {
	            
	            solucion.add(valor);
	        }
	    }
	    System.out.println(ls);
	}

	private SolucionHuertos() {
		
		solucion = new ArrayList<Integer>();
	}
}
