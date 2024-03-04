package p4.prueba;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import p4.prueba.Datos2.Producto;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejer2 {
	public static List<Integer> ListaPresupuesto;
	
	public static List<Producto> ListaProductos;
	
	public static Integer id;
	public static Integer precio;
	public static Integer categoria;
	public static Integer valoracion;
	
	public static Integer getNumProductos() {
		return ListaProductos.size();
	}
	public static Integer getPresupuesto() {
		return ListaPresupuesto.get(0);
	}
	public static List<Integer> getListaPresupuesto() {
		return ListaPresupuesto;
	}
	public static Integer getNumCategorias() {
		Set<Integer> conjunto = new HashSet<Integer>();
		for(Producto p: ListaProductos) {
			conjunto.add(p.Categoria());
		}
		return conjunto.size();
	}
	public static Integer getCategoria(Integer i, Integer j){
		if (ListaProductos.get(i).Categoria().equals(j)) {
			return 1;
		} else {
			return 0;
		}
	}
	public static List<Producto> getListaProductos() {
		return ListaProductos;
	}
	public static Integer getValoracion(Integer i) {
		return ListaProductos.get(i).Valoracion();
	}
	public static Integer getPrecio(Integer i) {
		return ListaProductos.get(i).Precio();
	}
	public static void ejemplo2PLE_model() throws IOException {
		Datos2.iniDatos("ficheros/Ejercicio2DatosEntrada1.txt");
		
		ListaPresupuesto = Datos2.getListaPresupuesto();
		ListaProductos = Datos2.getListaProductos();
		
		AuxGrammar.generate(Ejer2.class,"modelos/modelo2PLE.lsi","gurobi_models/ejercicio2PLE.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/ejercicio2PLE.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}
	public static void main(String[] args) throws IOException {	
		ejemplo2PLE_model();
	}
}
