package p4.ej_2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.ag.BinaryData;

public class BinCestaAG implements BinaryData<SolucionCesta>{
	public BinCestaAG(String fichero) {
		DatosCesta.iniDatos(fichero);
	}
	@Override
	public Integer size() {
		return DatosCesta.getNumProductos();
	}
	@Override
	public SolucionCesta solucion(List<Integer> ls) {
		return SolucionCesta.create(ls);
	}
	@Override
	public Double fitnessFunction(List<Integer> cr) {
		Double res= -fitness(cr) - 1000*(distR(cr)+distRR(cr)+distRRR(cr));
		//System.out.println(res);
		return res;
	}
	
	public Double fitness(List<Integer> cr) {
		return IntStream.range(0, size()).boxed()
				.filter(i->cr.get(i)==1)
				.mapToInt(i->DatosCesta.getPrecio(i))
				.sum()+ 0.;
	}
	
	//Esta funcion distancia es para la media de los productos mayor a tres.
	public Double distR(List<Integer> cr) {
		Double res = IntStream.range(0, size()).boxed()
				.filter(i->cr.get(i)==1)
				.mapToDouble(i->DatosCesta.getValoracion(i))
				.average()
				.orElse(0.);
		//System.out.println("1:   "+res);
		if (res>=3.) {
			return 0.;
		}else {
			return res; //Penealizamos
		}		
	}
	
	//Esta funcion distancia agrupa por categorias y revisa si se ha pasado con el presupuesto en una misma categorias.
	public Double distRR(List<Integer> cr) {
		Map<Object, Integer> preciosPorCategoria = IntStream.range(0, size()).boxed()
				.filter(i->cr.get(i)==1)
				.collect(Collectors.groupingBy(i -> DatosCesta.getCategoriaAG(i), 
						Collectors.summingInt(i -> DatosCesta.getPrecio(i))));
		
		if (preciosPorCategoria.values().stream().allMatch(precio -> precio <= DatosCesta.getPresupuesto()))
			return 0.;
		else //penalizamos
			return 1000.;
	}
	
	//Esta restriccion se encarga de que se cumpla que hay que cubrir todas las categorias.
	public Double distRRR(List<Integer> cr) {
		Double res = IntStream.range(0, size()).boxed()
					.filter(i->cr.get(i)==1)
					.mapToInt(i->DatosCesta.getCategoriaAG(i))
					.distinct()
					.count()+0.;
		//System.out.println(res);
		if (res == DatosCesta.getNumCategorias()+0.) {
			
			return 0.;
		} else {// Si no cubre todas las categorias penalizamos
			return DatosCesta.getNumCategorias()+0.;
		}
		
	}
	

}
