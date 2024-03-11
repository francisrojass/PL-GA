package p4.ej_1;


import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

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
		return DatosHuertos.getNumeroHuertos()+1;
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
	    
	    goal = IntStream.range(0, size())
	    		.filter(i->!(ls.get(i) == DatosHuertos.getNumeroHuertos()+1))
	    		.distinct()
	    		.count()+0.; // Contar la cantidad de variedades plantadas
	    return goal - (10000 * error);
	}
	/*
	private static Double tiempoQueTardaRestriccion1(List<Integer> cr) {
		//Esto es la solucion es como hay que hacer las funciones distancia bufete abogados
		return IntStream.range(0, cr.size()).boxed()
			.filter(j -> cr.get(j) == 1)
			.mapToDouble(j -> DatosAbogado.getTiempo(i, j))
			.sum();
	}
	private static Double FitnessFuntionAbogado(List<Integer> cr) {
		//Esto es la solucion es como hay que hacer las funciones distancia bufete abogados
		return -IntStream.range(0, DatosHuertos.getNumeroHuertos()).boxed()
				.mapToDouble(i -> tiempoQueTardaRestriccion1(i))
				.max(Comparator.naturalOrder()).get();
		//en este caso no pone -k por que no hay restricciones para penalizar.
	}
	*/

	@Override
	public SolucionHuertos solucion(List<Integer> ls) {
		return SolucionHuertos.of(ls);
	}


}
