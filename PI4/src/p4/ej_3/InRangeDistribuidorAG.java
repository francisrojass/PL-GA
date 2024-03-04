package p4.ej_3;

import java.util.List;

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
	
	public SolucionDistribuidor solucion(List<Integer> ls) {
		return SolucionDistribuidor.of_Range(ls);
	}

}
