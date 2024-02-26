package p4.ej_2;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import p4.ej_2.DatosCesta.Producto;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio2PLE {
	/*
	 * PLE hay que hacer un modelo en .lsi

	 * + Minimar el precio total de la cesta de navidad
	 * 		- Entre los productos seleccionados
	 * 
	 * + Solucion
	 * 		- Se quiere saber que productos se seleccionas
	 * 			Bin xi
	 * FICHERO DE ENTRADA
	 * 
	 * Presupuesto=150
	 * id_prod:precio:Categoria:valoracion (Esto es un comentario)
	 * 0:10:0:5
	 * ...
	 * FIN FICHERO ENTRADA
	 * 
	 * 
	 * ALFGORTIMO GENETICO
	 * NO CAMBIAMOS EL MODELO Y EL CROMOSOMA SERA BINARIO, EL MISMO QUE EN PLE.
	 */
	
	public static Integer Presupuesto;
	
	public static List<Producto> ListaProductos;
	
	public static Integer id;
	public static Integer precio;
	public static Integer categoria;
	public static Integer valoracion;
	
	public static Integer getNumProductos() {
		return ListaProductos.size();
	}
	public static Integer getPresupuesto() {
		return Presupuesto;
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
		DatosCesta.iniDatos("ficheros/Ejercicio2DatosEntrada1.txt");
		
		Presupuesto = DatosCesta.getPresupuesto();
		ListaProductos = DatosCesta.getListaProductos();
		
		AuxGrammar.generate(Ejercicio2PLE.class,"modelos/modelo2PLE.lsi","gurobi_models/ejercicio2PLE.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/ejercicio2PLE.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}
	public static void main(String[] args) throws IOException {	
		ejemplo2PLE_model();
	}
}
