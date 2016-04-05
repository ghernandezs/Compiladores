package AFN;


public final class IdEstado {
 public static Integer id=0;
	
	public static Integer  nextId(){
		Integer current=id;
		id++;
		return current;
	}
}
