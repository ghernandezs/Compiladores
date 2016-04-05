package AFN;


import java.util.HashSet;
import java.util.Set;

public class Estado {
	private Integer idEstado;
	private Set<Transicion> transiciones=new HashSet<Transicion>();
	
	
	public Estado(Integer idEstado){
		this.idEstado=idEstado;
	}
	
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public Set<Transicion> getTransiciones() {
		return transiciones;
	}
	public void setTransiciones(Set<Transicion> transiciones) {
		this.transiciones = transiciones;
	}
	
	public boolean addTransicion(Transicion t){
		if (t.getSimbolo() == '\u0000' ) {
			EstadosEpsilon.add(this);
		}
		return transiciones.add(t);
	}
}