package AFN;


import java.util.HashSet;
import java.util.Set;

import AFN.Estado;

public class Transicion {
	private char simbolo;
	private Set<Estado> destinos =new HashSet<Estado>();
	
	public char getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	public Set<Estado> getDestinos() {
		return destinos;
	}
	public void setDestinos(Set<Estado> destinos) {
		this.destinos = destinos;
	}
	
	
}