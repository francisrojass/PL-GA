package p4.ej_2;

import java.util.List;

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
	public Double fitnessFunction(List<Integer> ls) {
		double goal=0.;
		double error=0.;
		return -goal -1000*error;
	}

	

}
