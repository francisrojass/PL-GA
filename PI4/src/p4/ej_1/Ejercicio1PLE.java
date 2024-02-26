package p4.ej_1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import p4.ej_1.DatosHuertos.Huerto;
import p4.ej_1.DatosHuertos.Variedad;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio1PLE {
	public static List<Huerto> listaHuertos;
	public static List<Variedad> listaVariedad;
	
	public static Integer getMetrosDisponibleH(Integer j){
		return listaHuertos.get(j).metros();
        
	}
	public static Integer getMetrosRequeridosS(Integer i){
		return listaVariedad.get(i).metrosrequeridos();
	}
	/*
	 * Devuelve el numero de huertos.
	 */
	public static Integer getNumeroHuertos(){
		return listaHuertos.size();
	}
	/*
	 * Devuelve el numero de variedades.
	 */
	public static Integer getNumeroVariedades() {
		return listaVariedad.size();
	}
	/*
	 * Dado dos variedades te devuelve 1 si es incompatible y 0 si son compatible.
	 */
	public static Integer esIncompatible(Integer v1, Integer v2) {
		String nameV1 = listaVariedad.get(v1).nombre();
		List<String> incompatibilidadesV2 = listaVariedad.get(v2).variedadesIncompatibles();
		if (incompatibilidadesV2.contains(nameV1)) {
			return 1;
		}else {
			return 0;
		}
	}
	public static List<Variedad> getVariedades(){
		return listaVariedad;
	}
	public static List<Huerto> getHuertos(){
		return listaHuertos;
	}
	public static void ejemplo1PLE_model() throws IOException {
		DatosHuertos.iniDatos("ficheros/Ejercicio1DatosEntrada3.txt");
		
		listaHuertos = DatosHuertos.getHuertos();
		listaVariedad = DatosHuertos.getVariedades();
		
		AuxGrammar.generate(Ejercicio1PLE.class,"modelos/modelo1PLE.lsi","gurobi_models/ejercicio1PLE.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/ejercicio1PLE.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}
	public static void main(String[] args) throws IOException {	
		ejemplo1PLE_model();
	}
	
	
}
