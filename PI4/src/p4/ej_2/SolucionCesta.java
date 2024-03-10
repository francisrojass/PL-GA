package p4.ej_2;

import java.util.List;

import p4.ej_2.DatosCesta.Producto;
import us.lsi.common.List2;

public class SolucionCesta {

	//aqui pondria como var, numTotalProductosSeleccionados
	//Lista de los productos, para el string
	private Integer NumProductSelect;
	private List<Integer> productSelect;
	
	public static SolucionCesta create(List<Integer> ls) {
		return new SolucionCesta(ls);
	}
	private SolucionCesta(List<Integer> ls) {
		NumProductSelect=0;
		productSelect = List2.empty();
		for (int i = 0; i < ls.size(); i++) {
			if(ls.get(i)>0) {
				NumProductSelect++;
				productSelect.add(DatosCesta.getListaProductos().get(i).Id_prod());
			}
		}
	}
	@Override
	public String toString() {
		return NumProductSelect.toString();
		
	}
	
}
