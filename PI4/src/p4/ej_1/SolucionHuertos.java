package p4.ej_1;

import java.util.List;

public class SolucionHuertos {
	public static SolucionHuertos of(List<Integer> cr) {
		return new SolucionHuertos(cr);
	}
	public static SolucionHuertos empty() {
		return new SolucionHuertos();
	}
	private SolucionHuertos(List<Integer> cr) {
		for (int i = 0; i < cr.size(); i++) {
			if(cr.get(i)>0) {
				
			}
		}
	}
	private SolucionHuertos() {
		
	}
}
