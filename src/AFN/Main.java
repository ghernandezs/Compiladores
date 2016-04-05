package AFN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		
	}
	
	public void loadFile(){
		BufferedReader br = null;
		Scanner obj=new Scanner(System.in);
		
		String file;
		Integer i=0;
		System.out.print("Ingrese la ruta del archivo a cargar");
		file = obj.nextLine();
		try {
			String estados;
			String alfabeto;
			char estadoinicial;
			String estadosFinales;
			List<String>transiciones = new ArrayList<String>();
			
			String currentLine;

			br = new BufferedReader(new FileReader(file));
				
			while ((currentLine = br.readLine()) != null) {
				switch (i) {
				case 0:
					 estados = currentLine;
					 List<Estado> estadosList=createEstados(estados);
					break;
				case 1:
					 alfabeto = currentLine;
					break;
				case 2: 
					 estadoinicial = currentLine.charAt(0);
					break;
				case 3:	
					estadosFinales = currentLine;
					break;
				default:
					transiciones.add(currentLine);
					break;
				}
				
				i++;
			}
			
			Set<Transicion> transicionesAfn = new HashSet<Transicion>(); 
			
			for (String t : transiciones) {
				Estado e = new Estado(IdEstado.nextId());
				Transicion transicion = new Transicion();
			}
			
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			obj.close();
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	public List<Estado> createEstados(String estadosStr) {
		List<Estado> estadosList= new ArrayList<Estado>();
		String[] idsStr=estadosStr.split(",");
		for (String id : idsStr) {
			Estado e=new Estado(Integer.valueOf(id));
			estadosList.add(e);
		}
		
		return estadosList;
		
	}
	

}
