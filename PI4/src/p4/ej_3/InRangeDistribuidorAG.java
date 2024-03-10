package p4.ej_3;

import java.util.List;
import java.util.stream.IntStream;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeDistribuidorAG implements ValuesInRangeData<Integer, SolucionDistribuidor> {

	public InRangeDistribuidorAG(String entrada) {
		DatosDistribuidor.iniDatos(entrada);
	}
	
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}
	public Integer size() {
		return DatosDistribuidor.getNumProductos();
	}
	public Integer min(Integer i) {
		return 0;
	}
	public Integer max(Integer i) {
		return DatosDistribuidor.getNumDestinos();
	}
	
	public SolucionDistribuidor solucion(List<Integer> cr) {
		return SolucionDistribuidor.of_Range(cr);
	}

	@Override
	public Double fitnessFunction(List<Integer> cr) {
		return -fitness(cr)- (distR(cr)*distRR(cr));
	}
	private Double distRR(List<Integer> cr) {
		
		return 0.;
	}

	private Double distR(List<Integer> cr) {
		
		return 0.;
	}

	public Double fitness(List<Integer> cr) {
		return IntStream.range(0, size()).boxed()
				.mapToInt(i -> DatosDistribuidor.getAlmacenamientoCoste(i, cr.get(i)))
				.sum()+0.;
	}

}
