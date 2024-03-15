package p4.ej_1;

import java.util.ArrayList;
import java.util.List;

public class SolucionHuertos {
	List<Integer> solucion;
	long NumeroVariedades;
	
	public static SolucionHuertos of(List<Integer> ls) {
		return new SolucionHuertos(ls);
	}
	
	public static SolucionHuertos empty() {
		return new SolucionHuertos();
	}
	
	private SolucionHuertos(List<Integer> ls) {
	    solucion = new ArrayList<Integer>();
	    for (Integer valor : ls) {
	        solucion.add(valor);
	    }
	    NumeroVariedades = solucion.stream()
	    		.filter(i -> !(i == DatosHuertos.getNumeroHuertos())).count();
	}

	private SolucionHuertos() {
		solucion = new ArrayList<Integer>();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Lista: "+solucion.toString()+"\nNumero de variedades plantadas: " + NumeroVariedades;
	}
}
