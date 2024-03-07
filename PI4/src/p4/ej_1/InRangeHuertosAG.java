package p4.ej_1;


import java.util.List;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeHuertosAG implements ValuesInRangeData<Integer, SolucionHuertos> {
	public InRangeHuertosAG(String file) {
		DatosHuertos.iniDatos(file);
	}
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}
	public Integer size() {
		return DatosHuertos.getNumeroVariedades();
	}
	public Integer max(Integer i) {
		return DatosHuertos.getNumeroHuertos();
	}
	@Override
	public Integer min(Integer i) {
		return 0;
	}
	
	public Double fitnessFunction(List<Integer> ls) {
	    double goal = 0., error = 0.;
	    
	    // Primera restricción
	    for (int j = 0; j < DatosHuertos.getNumeroHuertos(); j++) {
	        double metrosRequeridos = 0;
	        for (int i = 0; i < ls.size(); i++) {
	            int huertoSeleccionado = ls.get(i);
	            if (huertoSeleccionado == j) {
	                metrosRequeridos += DatosHuertos.getMetrosRequeridosS(i);
	            }
	        }
	        error += Math.max(0, metrosRequeridos - DatosHuertos.getMetrosDisponibleH(j));
	    }
	    
	    // Segunda restricción
	    for (int i = 0; i < ls.size(); i++) {
	        for (int k = i + 1; k < ls.size(); k++) {
	            if (DatosHuertos.esIncompatible(i, k) == 1 && ls.get(i) == ls.get(k)) {
	                error += 40; // Penalizar cuando dos variedades incompatibles se plantan en el mismo huerto
	            }
	        }
	    }
	    
	    goal = ls.stream().distinct().count(); // Contar la cantidad de variedades plantadas
	    return -goal - (10000 * error);
	}

	@Override
	public SolucionHuertos solucion(List<Integer> ls) {
		return SolucionHuertos.of(ls);
	}


}
