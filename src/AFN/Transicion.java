package AFN;


import java.util.HashSet;
import java.util.Set;

import AFN.Estado;

public class Transicion {
	private char simbolo;
	private Estado destino; 
	
	public char getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	public Estado getDestino() {
		return destino;
	}
	public void setDestino(Estado destino) {
		this.destino = destino;
	}
	
	
	
}