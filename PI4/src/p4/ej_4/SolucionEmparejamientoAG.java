package p4.ej_4;

import java.util.ArrayList;
import java.util.List;

public class SolucionEmparejamientoAG {
	public Integer af_tot;
	public List<Integer> solucion;
	public static SolucionEmparejamientoAG of(List<Integer> ls) {
		return new SolucionEmparejamientoAG(ls);
	}
	public static SolucionEmparejamientoAG empty() {
		return new SolucionEmparejamientoAG();
	}
	private SolucionEmparejamientoAG(List<Integer> cr) {
		af_tot = 0;
		solucion = new ArrayList<Integer>();
		for (int i = 0; i < cr.size() - 1; i += 2) {
            Integer par1 = cr.get(i);
            Integer par2 = cr.get(i+1);
            if (par1!=null || par2!=null) {
            	af_tot+=DatosEmparejamiento.getAfinidad(par1, par2);
            }
        }
		solucion.addAll(cr);
	}
	private SolucionEmparejamientoAG() {
		af_tot = 0;
		solucion = new ArrayList<Integer>();
	}
	@Override
	public String toString() {
		return "Afinidad total: " + af_tot.toString() + "\nParejas: "+ solucion;
	}
	
}
