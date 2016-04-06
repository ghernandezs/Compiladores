package AFN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.omg.IOP.ExceptionDetailMessage;

public class Main {

	public static void main(String[] args) {
		Scanner obj=new Scanner(System.in);
		String path=null;
		AFN afn=new AFN();
		while(true){
			try {
				System.out.print("Ingrese la ruta del archivo a cargar");
				path= obj.nextLine();
				afn=createAFNFromFile(path);
				if(afn.getIdAFN() != null){
					System.out.println("ingrese la cadena a analizar");
					String str=obj.nextLine();
					Analizador.cadena(str, afn);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static  AFN createAFNFromFile(String path){
		AFN afn = new AFN(); 
		BufferedReader br = null;
		Integer i=0;

		try {
			String estados;
			String alfabeto;
			char estadoInicial = ' ';
			String estadosFinales;
			String[] estadosFinalesArray=null;
			 Set<Estado> estadosSet=new HashSet<Estado>();
			List<String>transiciones = new ArrayList<String>();
			String[] alfabetoArray= null; 
			String currentLine;

			br = new BufferedReader(new FileReader(path));
				
			while ((currentLine = br.readLine()) != null) {
				switch (i) {
				case 0:
					 estados = currentLine;
					 String[] estadosArrayString = estados.split(",");
					 estadosSet=createEstados(estadosArrayString);
					break;
				case 1:
					 alfabeto = currentLine;
					 alfabetoArray=alfabeto.split(",");
					break;
				case 2: 
					 estadoInicial = currentLine.charAt(0);
					break;
				case 3:	
					estadosFinales = currentLine;
					estadosFinalesArray = estadosFinales.split(",");
					break;
				default:
					transiciones.add(currentLine);
					break;
				}
				
				i++;
			}
			Estado estadoInicialAFN=getEstado(estadoInicial, estadosSet);
			
			boolean estadoInicialValido=validateEstado(estadoInicialAFN);
			boolean estadosFinalesValidos =validateEstadoArray(estadosFinalesArray, estadosSet);  
			
			if(estadoInicialValido && estadosFinalesValidos){
				
				Set<Estado>  estadosFinalesAFN = new HashSet<Estado>();
				
				
				for (String e : estadosFinalesArray) {
						estadosFinalesAFN.add(getEstado(e.charAt(0), estadosSet));
				}
				
					afn.setEstadoInicial(estadoInicialAFN);
					afn.setEstadosFinales(estadosFinalesAFN);
				
				for (String transicionStr : transiciones) {
					String[] transicionArray  =transicionStr.split(",");
					Estado estadoOrigen = getEstado(transicionArray[0].charAt(0), estadosSet);
					char simbolo=transicionArray[1].charAt(0);
					Estado estadoDestino = getEstado(transicionArray[2].charAt(0), estadosSet);
					if(validateEstado(estadoOrigen) && validateEstado(estadoDestino) && validateAlfabeto(simbolo, alfabetoArray)){
						Set<Estado> destinos = new HashSet<Estado>();
						destinos.add(estadoDestino);
						Transicion t=new Transicion();
							t.setDestinos(destinos);
							t.setSimbolo(simbolo);
						estadoOrigen.addTransicion(t);
					}else{
						System.out.println("Error en las transiciones los estados o simbolos no coinciden");
					}
					
				
				}
			}else{
				if (!estadosFinalesValidos) {
					System.out.println("Uno  o mas estados finales no se rncuentran en la lista");
				}
				if(!estadoInicialValido) {
					System.out.println("El estado inicial no se encuetra en la lista de estados");
				}
			}	
			
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
 			}
		}
		
		return afn;
	}
	
	private static Set<Estado> createEstados(String idStr[]) {
		Set<Estado> estados= new HashSet<Estado>();
		for (String id : idStr) {
			Estado e=new Estado(Integer.valueOf(id));
			estados.add(e);
		}
		
		return estados;
		
	}
	
	private static Estado getEstado(char c,Set<Estado> estados){
		Estado estado=null;
		for (Estado e : estados) {
			System.out.println(e.getIdEstado());
			if(e.getIdEstado() == Integer.valueOf(c)){
				estado=e;
				break;
			}
		}
		return estado;
	} 
	
	private static boolean validateEstado(Estado estado){
		boolean valid=false;
		if(estado != null)
			valid=true;
		return valid;
	}
	
	private static boolean validateEstadoArray(String[] array,Set<Estado> estados){
		boolean valid= true;
		for (String e : array) {
			Estado estado = getEstado(e.charAt(0), estados);
			if(!validateEstado(estado)){
				valid=false;
				break;
			}	
		}
		return valid;
	}
	
	private static  boolean validateAlfabeto(char s ,String[] alfabeto){
		boolean valid = false;
		for (String simbolo : alfabeto) {
			if(simbolo.charAt(0) == s){
				valid= true;
				break;
			}
		}
		
		return valid;
	}

}
