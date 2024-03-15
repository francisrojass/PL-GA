package p4.ej_4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class PermutationEmparejamiento implements SeqNormalData<SolucionEmparejamientoAG> {
	public PermutationEmparejamiento(String file) {
		DatosEmparejamiento.iniDatos(file);
	}
	
	@Override
	public ChromosomeType type() {
		return ChromosomeType.PermutationSubList;
	}
	public Integer k() {
		Integer k=0;
		for (int i = 0; i < DatosEmparejamiento.ListaPersonas.size(); i++) {
			k += DatosEmparejamiento.ListaPersonas.get(i).afinidades().values().stream().mapToInt(Integer::intValue).max().getAsInt();
		}
		return k;
	}
	@Override
	public Double fitnessFunction(List<Integer> cr) {
		return fitness(cr) - k()*(distR(cr)+ distRR(cr)+ distRRR(cr));
	}

	private Double fitness(List<Integer> cr) {
		Double afinidad_total = 0.;
		for (int i = 0; i < cr.size() - 1; i += 2) {
            Integer par1 = cr.get(i);
            Integer par2 = cr.get(i+1);
            if (par1!=null || par2!=null) {
            	afinidad_total+=DatosEmparejamiento.getAfinidad(par1, par2);
            }
        }
		//System.out.println(afinidad_total);
		return afinidad_total;
	}
	// Al menos un idioma en comun
	private Double distR(List<Integer> cr) {
		Double error = 0.;
		for (int i = 0; i < cr.size() - 1; i += 2) {
            Integer par1 = cr.get(i);
            Integer par2 = cr.get(i+1);
            Set<String> setPersona1 = new HashSet<>(DatosEmparejamiento.getIdiomas(par1));
            Set<String> setPersona2 = new HashSet<>(DatosEmparejamiento.getIdiomas(par2));
            setPersona1.retainAll(setPersona2);
            if (!setPersona1.isEmpty()) {
                error+=0.;
            }else {
            	error+=10000.;//penaliza si esta vacio, ya que no tienen idioma en comun
            }
        }
		return error;
	}
	// Como maximo 5 años de diferencia de edad
	private Double distRR(List<Integer> cr) {
		Double error = 0.;
		for (int i = 0; i < cr.size() - 1; i += 2) {
            Integer edad1 = DatosEmparejamiento.getEdad(cr.get(i));
            Integer edad2 = DatosEmparejamiento.getEdad(cr.get(i+1));
            Integer check = edad1-edad2;
            if (check <= 5 || check >= -5) {
            	error+=0.;
			} else {
				error+=10000;
			}
        }
		return error;
	}
	//Que no sean nacionalidad igual
	private Double distRRR(List<Integer> cr) {
		Double error = 0.;
		for (int i = 0; i < cr.size() - 1; i += 2) {
            String edad1 = DatosEmparejamiento.getNacionalidad(cr.get(i));
            String edad2 = DatosEmparejamiento.getNacionalidad(cr.get(i+1));
            
            if (!(edad1.equals(edad2))) {
            	error+=0.;
			} else {
				error+=10000;
			}
        }
		return error;
	}

	@Override
	public Integer itemsNumber() {
		return DatosEmparejamiento.getNumeroPersonas();
	}

	@Override
	public SolucionEmparejamientoAG solucion(List<Integer> cr) {
		// TODO Auto-generated method stub
		return SolucionEmparejamientoAG.of(cr);
	}
	
}
