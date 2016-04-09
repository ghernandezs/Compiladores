package AFN;


import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import AFN.Transicion;

public class AFN {
	private Integer idAFN;
	private Estado estadoInicial;
	private Set<Estado> estadosFinales=new HashSet<Estado>();
	
	public AFN(){
		this.idAFN=IdAFN.nextId();
	}
	
	public AFN(char simbolo){
		this.idAFN=IdAFN.nextId();
		this.estadoInicial=new Estado(IdEstado.nextId());
		Estado estadoPrincipal=new Estado(IdEstado.nextId());
		Estado estadoFinal= new Estado(IdEstado.nextId());	
		
		Transicion t=new Transicion();
			t.setDestino(estadoPrincipal);
			estadoInicial.addTransicion(t);
			EstadosEpsilon.add(estadoInicial);

		 Set<Estado>destinos2=new HashSet<Estado>();
		destinos2.add(estadoFinal);
		Transicion t2=new Transicion();	
		t2.setDestino(estadoPrincipal);
		t2.setSimbolo(simbolo);
		
		estadoPrincipal.addTransicion(t2);
		
		this.estadosFinales.add(estadoFinal);		
	}

	
	public Integer getIdAFN() {
		return idAFN;
	}


	public void setIdAFN(Integer idAFN) {
		this.idAFN = idAFN;
	}


	public Estado getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(Estado estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public Set<Estado> getEstadosFinales() {
		return estadosFinales;
	}

	public void setEstadosFinales(Set<Estado> estadosFinales) {
		this.estadosFinales = estadosFinales;
	}

	public void concatenar(AFN afn){
		
		for (Estado estado : estadosFinales) {
			Transicion t=new Transicion();
				t.setDestino(afn.getEstadoInicial());
		}
		estadosFinales=afn.getEstadosFinales();
		
		
	}
	
	
	public void or(AFN afn){
		Estado nei=new Estado(IdEstado.nextId());
		Estado nef=new Estado(IdEstado.nextId());
		Transicion ti1=new Transicion();
			ti1.setDestino(estadoInicial);
		Transicion ti2=new Transicion();
			ti2.setDestino(afn.getEstadoInicial());
		EstadosEpsilon.add(nei);
		Set<Estado> destinos2= new HashSet<Estado>();
		destinos2.add(nef);
		
		Transicion tf=new Transicion();
			tf.setDestino(nef);
		estadoInicial=nei;
		for (Estado estado : estadosFinales) {
			estado.addTransicion(tf);
		}
		
		for (Estado estado : afn.getEstadosFinales()) {
			estado.addTransicion(tf);
		}
		estadosFinales=destinos2;
	}
	
	public void CerraduraPositiva(){
		Transicion t=new Transicion();
			t.setDestino(estadoInicial);
			
			
		for (Estado estado : estadosFinales) {
				estado.addTransicion(t);
				EstadosEpsilon.add(estado);
		} 	
		
	}
	
	public void cerraduraEstrella(){
		Set<Estado> destinos=new HashSet<Estado>();
		Estado nef= new Estado(IdEstado.nextId());
		destinos.add(estadoInicial);
		destinos.add(nef);
		Transicion t1=new Transicion();
		t1.setDestino(estadoInicial);
		Transicion t2=new Transicion();
			t2.setDestino(nef);
		
		for (Estado estado : estadosFinales) {
			estado.addTransicion(t1);
			estado.addTransicion(t2);
			EstadosEpsilon.add(estado);
		Set<Estado> efilanes= new HashSet<Estado>();	
			 efilanes.add(nef);
			 
			 estadosFinales=efilanes;
		}
		
		Set<Estado>destinos2 = new HashSet<Estado>();
			destinos2.add(nef); 
		Transicion t3=new Transicion();	
			t3.setDestino(nef);
		estadoInicial.addTransicion(t3);	
	} 
	
	public void pregunta(){
		for(Estado e :getEstadosFinales()){
			Transicion t= new Transicion();
			t.setDestino(e);
			estadoInicial.addTransicion(t);
		} 
			
	}
	
	public Set<Estado> cerraduraEpsilon(Set<Estado> e){
		Set<Estado> d=new HashSet<Estado>();
		Stack<Estado> s=new Stack<Estado>();
		for (Estado estado : e) {
			s.push(estado);
		}
		
		while (s.size()>0) {
			Estado c =s.pop();
			if(d.contains(c)){
				continue;
			}
			d.add(c);
			for (Transicion t : c.getTransiciones()) {
				if(t.getSimbolo() == Analizador.EPSILON){
					s.push(t.getDestino());
				}
			}
			
		}
		
		return d;
	}
	
	public Set<Estado> mover(Set<Estado> e,char simbolo){
		Set<Transicion> ts=new HashSet<Transicion>();
		Set<Estado> d=new HashSet<Estado>();
		for (Estado estado : e) {
			ts=estado.getTransiciones();
			for (Transicion transicion: ts) {
				if(transicion.getSimbolo() == simbolo ){
					d.add(transicion.getDestino());
				}
			}
		}
		
		
		return d;
	}
	
	public Set<Estado> irA(Set<Estado> e,char simbolo){
		return cerraduraEpsilon(mover(e,simbolo));
	}
	
	public String validaCadena(String cadena){
		
		String recorrido;
		Queue<Estado> q;
		Integer contador;
		Integer estados;
		Integer index=0;
		char caracterActual = cadena.charAt(index);
		
		 for (Transicion t : estadoInicial.getTransiciones()) {
//			if()
		} 
		
		return null;
	}
	
}	
