package p4.ej_1;


import java.util.List;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeHuertosAG implements ValuesInRangeData<Integer, SolucionHuertos> {
	public InRangeHuertosAG(String file) {
		DatosHuertos.iniDatos(file);
	}
	public Integer size() {
		return DatosHuertos.getNumeroVariedades();
	}
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}
	public Integer max() {
		return DatosHuertos.getNumeroHuertos();
	}
	public Integer min() {
		return 0;
	}
	public Double fitnessFuntion(List<Integer> cr) {
		//return objetivo(cr)-k*(dle1(cr)+dle2(cr)+dle3(cr));
		return null;
	}
	public Integer dle(List<Integer> cr) {
		Integer i = cr.get(0);
		if(DatosHuertos.getMetrosRequeridosS(cr.get(i))*cr.get(i)-DatosHuertos.getMetrosDisponibleH(cr.get(i))<=0) {
			return 0;
		}else {
			return 1;
		}
	}
	
}
