package p4.ej_3;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import p4.ej_3.DatosDistribuidor.Destino;
import p4.ej_3.DatosDistribuidor.Productos;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3PLE {
	public static List<Destino> ListaDestino;
	public static List<Productos> ListaProductos;
	
	public static Integer getNumDestinos() {
		return ListaDestino.size();
	}
	public static Integer getNumProductos() {
		return ListaProductos.size();
	}
	public static Integer getAlmacenamientoCoste(Integer i, Integer j) {
		return ListaProductos.get(i).coste_almacenamiento().get(j);
	}
	public static Integer getCantidadDisponibleUnidades(Integer i) {
		return ListaProductos.get(i).unidades();
	}
	public static Integer getDemandaMinima(Integer j) {
		return ListaDestino.get(j).demandaMinima();
	}
	public static List<Productos> getProductos(){
		return ListaProductos;
	}
	public static List<Destino> getDestinos(){
		return ListaDestino;
	}
	public static void ejemplo3PLE_model() throws IOException {
		DatosDistribuidor.iniDatos("ficheros/Ejercicio3DatosEntrada3.txt");
		
		ListaProductos = DatosDistribuidor.getProductos();
		ListaDestino = DatosDistribuidor.getDestinos();
		
		AuxGrammar.generate(Ejercicio3PLE.class,"modelos/modelo3PLE.lsi","gurobi_models/ejercicio3PLE.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/ejercicio3PLE.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}
	public static void main(String[] args) throws IOException {	
		ejemplo3PLE_model();
	}
}
