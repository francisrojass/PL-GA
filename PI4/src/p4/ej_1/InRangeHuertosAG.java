package p4.ej_1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
	
	@Override
	public Double fitnessFunction(List<Integer> cr) {
		return fitness(cr)-1000*(distR(cr)+distRR(cr));
	}

	public Double fitness(List<Integer> cr) {
		return IntStream.range(0, size())
	    		.filter(i->!(cr.get(i) == DatosHuertos.getNumeroHuertos()+1))
	    		.distinct()
	    		.count()+0.;
	}
/*
 *  La cantidad total de espacio utilizado en cada huerto no puede
exceder la disponibilidad
 */
	public Double distR(List<Integer> cr) {
		Double error = 0.;
		Map<Integer, Integer> res = IntStream.range(0, cr.size()) 
                .boxed()
                .filter(i -> !(cr.get(i) == DatosHuertos.getNumeroHuertos()))
                .collect(Collectors.groupingBy(
                        i -> cr.get(i),
                        Collectors.summingInt(DatosHuertos::getMetrosRequeridosS)
                ));
		//System.out.println(res);
		for (Map.Entry<Integer, Integer> entry : res.entrySet()) {
            int valorMapa = entry.getValue();
            if (valorMapa < DatosHuertos.getMetrosDisponibleH(entry.getKey())) {
                error+=0.;
            } else {
            	error+=1000.;
            }
        }
		return error;
	}
	/*
	 * Las variedades incompatibles no pueden plantarse en el mismo
	 * huerto
	 */
	public Double distRR(List<Integer> cr) {
		Double error = 0.;
		for (int i = 0; i < cr.size(); i++) {
	        for (int k = i + 1; k < cr.size(); k++) {
	            if ((DatosHuertos.esIncompatible(i, k) == 1) && (cr.get(i) == cr.get(k))) {
	                error += 1000; // Penalizar cuando dos variedades incompatibles se plantan en el mismo huerto
	            }
	        }
	    }
		return error;
	}
	@Override
	public SolucionHuertos solucion(List<Integer> ls) {
		return SolucionHuertos.of(ls);
	}
	

}
