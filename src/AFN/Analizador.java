package AFN;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import AFN.Transicion;


public abstract  class Analizador {
	public static final char EPSILON='\u0000';
	
	public static  boolean cadena(String cadena,AFN afn){
		int index=0;
		boolean belongs=false;
		char caracterActual = cadena.charAt(index);
		Estado i=afn.getEstadoInicial();
		Set<Estado> e=new HashSet<Estado>();
		e.add(i);
		Set<Estado> c=afn.cerraduraEpsilon(e);
		while (cadena.length() > index) {

			caracterActual=cadena.charAt(index);
			Set<Estado> r=afn.irA(c,caracterActual);
			if(r.isEmpty()){
				belongs = false;
			}
			c=r;
			index++;
		}
		
		for (Estado estado : c) {
			if(afn.getEstadosFinales().contains(estado)){
				belongs = true;
			}
		}
		return belongs;
	}
	
	
//	public static boolean imprimirRecorrido() {
//		if (recorrido.size()>0) {
//			for (Estado estado : recorrido) {
//				for (Transicion transicion : recorrido) {
//					
//				}
//			}
//			for (Set<Estado> set : recorrido) {
//				for (Estado estado : set) {
//					Set<Transicion> transiciones=estado.getTransiciones();
//					for (Transicion transicion : transiciones) {
//						if(recorrido.indexOf(set)!=recorrido.size()-1){
//							System.out.print(estado.getIdEstado() +" ( "+ transicion.getSimbolo() + ") ->");
//						}else{
//							System.out.print(estado.getIdEstado() +" ( "+ transicion.getSimbolo() + ") ");
//						}
//					}
//				}
//			}	
//			return true;
//		}else{
//			return false;
//		}
		
//	}
	
	public static void imprimirAFN(AFN afn){
		Estado estadoInicial= afn.getEstadoInicial();
		System.out.println("idAFN: " + afn.getIdAFN());
		System.out.println("Estado Inicial: " + estadoInicial.getIdEstado() );
		System.out.println("Transiciones");
		for (Transicion transicion: estadoInicial.getTransiciones()) {
			System.out.println("simbolo: " + transicion.getSimbolo());
			System.out.println("idDestino: " + transicion.getDestino().getIdEstado() ) ;
		}
		
		System.out.println(" estado intermedio");
		
		System.out.println("estados Finales");
		for (Estado estado : afn.getEstadosFinales()) {
			System.out.println(" idEstado: " + estado.getIdEstado() );
		}
	}
	
}
	