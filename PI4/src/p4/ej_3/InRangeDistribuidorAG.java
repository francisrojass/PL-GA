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
		return DatosDistribuidor.getNumProductos() * DatosDistribuidor.getNumDestinos();
	}
	public Integer min(Integer i) {
		return 0;
	}
	public Integer max(Integer i) {
		return 101;
	}
	
	public SolucionDistribuidor solucion(List<Integer> cr) {
		return SolucionDistribuidor.of_Range(cr);
	}

	@Override
	public Double fitnessFunction(List<Integer> cr) {
		return -fitness(cr)- 1000*(distR(cr)+distRR(cr));
	}
	//La cantidad total enviada de cada tipo de producto no puede exceder la cantidad disponible
	private Double distR(List<Integer> cr) {
		Boolean res = IntStream.range(0, size()).boxed()
				.filter(i -> !(cr.get(i)==0))
				.allMatch(i->cr.get(saberProducto(i))<=DatosDistribuidor.getCantidadDisponibleUnidades(saberProducto(i)));
		System.out.println(res);
		if(res) {
			
			return 0.;
		} else {
			return size()+0.;//Penaliza
		}
	}
	// La cantidad enviada a cada destino debe superar la demandamínima
	private Double distRR(List<Integer> cr) {
		Boolean res= IntStream.range(0, size()).boxed()
				.filter(i -> !(cr.get(i)==0))
				.allMatch(i->cr.get(i)>=DatosDistribuidor.getDemandaMinima(saberDestino(i)));
		
		if(res) {
			return 0.;
		} else {
			return size()+0.;//Penaliza
		}
			
	}
	private Integer saberDestino(Integer i) {
		Integer NumDestinos = DatosDistribuidor.getNumDestinos();
		return i%NumDestinos;
	}
	private Integer saberProducto(Integer i) {
		Integer NumDestinos = DatosDistribuidor.getNumDestinos();
		return i/NumDestinos;
	}
	
	public Double fitness(List<Integer> cr) {
		return IntStream.range(0, size()).boxed()
				.mapToInt(i -> DatosDistribuidor.getAlmacenamientoCoste(saberProducto(i), saberDestino(i)))
				.sum()+0.;
	}

}
