package AFN;

import java.util.HashSet;
import java.util.Set;

public final class EstadosEpsilon {
public static Set<Estado> estadosEpsilon=new HashSet<Estado>();
	
	public static void add(Estado estado){
		estadosEpsilon.add(estado);
	}
	
	public static Set<Estado> getSet(){
		return estadosEpsilon;
	}
}