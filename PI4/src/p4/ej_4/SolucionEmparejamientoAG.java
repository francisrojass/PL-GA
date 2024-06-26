package p4.ej_4;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.misc.Pair;

public class SolucionEmparejamientoAG {
	public Integer af_tot;
	public List<Pair<Integer, Integer>> solucion;
	public static SolucionEmparejamientoAG of(List<Integer> ls) {
		return new SolucionEmparejamientoAG(ls);
	}
	public static SolucionEmparejamientoAG empty() {
		return new SolucionEmparejamientoAG();
	}
	private SolucionEmparejamientoAG(List<Integer> cr) {
		af_tot = 0;
		solucion = new ArrayList<>();
		
		for (int i = 0; i < cr.size() - 1; i += 2) {
            Integer par1 = cr.get(i);
            Integer par2 = cr.get(i+1);
            if (par1!=null || par2!=null) {
            	af_tot+=DatosEmparejamiento.getAfinidad(par1, par2);
            }
            Pair<Integer, Integer> res = new Pair<Integer, Integer>(par1, par2);
            solucion.add(res);
        }
		
	}
	private SolucionEmparejamientoAG() {
		af_tot = 0;
		solucion = new ArrayList<>();
	}
	@Override
	public String toString() {
		return "Afinidad total: " + af_tot.toString() + "\nParejas: "+ solucion;
	}
	
}
