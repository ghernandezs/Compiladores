package AFN;

public final class IdAFN {
	 public static Integer id=0;
		
		public static Integer  nextId(){
			Integer current=id;
			id++;
			return current;
		}
	}