package p4.ej_3;

import java.util.List;
import java.util.stream.IntStream;

import p4.ej_3.DatosDistribuidor.Destino;
import p4.ej_3.DatosDistribuidor.Productos;
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
		return (DatosDistribuidor.getNumProductos() * DatosDistribuidor.getNumDestinos());
	}
	public Integer min(Integer i) {
		return 0;
	}
	public Integer max(Integer i) {
		return DatosDistribuidor.getCantidadDisponibleUnidades(saberProducto(i));
	}
	
	public SolucionDistribuidor solucion(List<Integer> cr) {
		return SolucionDistribuidor.of_Range(cr);
	}
	@Override
	public Double fitnessFunction(List<Integer> cr) {
		Double res = -fitness(cr) - 1000*(distR(cr)+distRR(cr));
		//System.out.println(res);
		return res;
	}
	/*
	 * El coste de almacenamiento es unitario, por unidad enviada
	 */
	
	//La cantidad total enviada de cada tipo de producto no puede exceder la cantidad disponible
	
	public Double distR(List<Integer> cr) {
		Double error = 0.;
		//a sera el numero de elementos por productos, en este caso 5
		Integer a = cr.size()/DatosDistribuidor.getNumProductos();
		Integer b = 0;
		
		for (int k = 0; k < DatosDistribuidor.getNumProductos(); k++) {
			Integer UnidadesDeProducto = DatosDistribuidor.getCantidadDisponibleUnidades(k);
			for (int i = b; i < a; i++) {
				UnidadesDeProducto = UnidadesDeProducto - cr.get(i);
				
				if(UnidadesDeProducto<0) {
					error+=100.;
				}
			}
			b=b+a;
		}
		return error;	
	}
	
	
	// La cantidad enviada a cada destino debe superar la demandamínima
	public Double distRR(List<Integer> cr) {
		Double error=0.;
		Boolean res = IntStream.range(0, size()).boxed()
				.filter(i -> !(cr.get(i)==0))
				.allMatch(i->cr.get(i)>=DatosDistribuidor.getDemandaMinima(saberDestino(i)));
		
		Integer res2 = IntStream.range(0, size()).boxed().mapToInt(i->cr.get(i)).sum(); 
		if(res) {
			error+=0.;
		} else {
			error+=100.;//Penaliza
		}
		if(res2==0) {
			error+=100.;
		}
		return error;
			
	}
	
	//(n*j)+i
	public Integer saberDestino(Integer i) {
		Integer NumDestinos = DatosDistribuidor.getNumDestinos();
		return i%NumDestinos;
	}
	public Integer saberProducto(Integer i) {
		Integer NumDestinos = DatosDistribuidor.getNumDestinos();
		return i/NumDestinos;
	}
	
	public Double fitness(List<Integer> cr) {
		return IntStream.range(0, size()).boxed()
				.filter(i -> !(cr.get(i)==0))
				.mapToInt(i -> DatosDistribuidor.getAlmacenamientoCoste(saberProducto(i), saberDestino(i))*cr.get(i))
				.sum()+0.;
	}
}
