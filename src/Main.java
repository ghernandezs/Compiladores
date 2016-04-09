

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.omg.IOP.ExceptionDetailMessage;

import AFN.AFN;
import AFN.Estado;
import AFN.Transicion;

public class Main {

	public static void main(String[] args) {
		Scanner obj=new Scanner(System.in);
		String path=null;
		AFN afn=new AFN();
		while(true){
			try {
				System.out.println("Ingrese la ruta del archivo a cargar");
				path= obj.nextLine();
				afn=createAFNFromFile(path);  // regresa  un objeto tipo AFN a partir de la ruta de un archivo 
				if(afn.getIdAFN() != null){ //si el afn se creo correctamente pide la cadena a evaluar  
					System.out.println("ingrese la cadena a analizar");
					String str=obj.nextLine();
//					
					
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
			String estadoInicial = "";
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
					 estadosSet=createEstados(estadosArrayString); //recibe un arreglo de strings y regresa un Set de tipo Estado crea  
					break;
				case 1:
					 alfabeto = currentLine;
					 alfabetoArray=alfabeto.split(",");
					break;
				case 2: 
					 estadoInicial = currentLine;
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
			Estado estadoInicialAFN=getEstado(estadoInicial, estadosSet); //recibe in String y un Set de Estado y regresa un objteto Tipo estado
			
			boolean estadoInicialValido=validateEstado(estadoInicialAFN); //recibe un String y regresa un booleano
			boolean estadosFinalesValidos =validateEstadoArray(estadosFinalesArray, estadosSet); //recibe un arreglo de strins que representan estados finales y un conjunto de estados regresa un booleano
			
			
			if(estadoInicialValido && estadosFinalesValidos){ //verifica si los estados finales y el inicial se encuentran en en conjunto de estados  
				
				Set<Estado>  estadosFinalesAFN = new HashSet<Estado>();
				
				
				for (String e : estadosFinalesArray) {
						estadosFinalesAFN.add(getEstado(e, estadosSet)); 
				}
				
					afn.setEstadoInicial(estadoInicialAFN);
					afn.setEstadosFinales(estadosFinalesAFN);
				
					//agrega las transiciones a los estados 
					
				for (String transicionStr : transiciones) {
					String[] transicionArray  =transicionStr.split(",");
					Estado estadoOrigen = getEstado(transicionArray[0], estadosSet);
					char simbolo=transicionArray[1].charAt(0);
					Estado estadoDestino = getEstado(transicionArray[2], estadosSet);
					// Valida que los  simbolos y los estados de las transiciones esten definidos en su respectivo elemento de quintupla
					if(validateEstado(estadoOrigen) && validateEstado(estadoDestino) && validateAlfabeto(simbolo, alfabetoArray)){     
						Set<Estado> destinos = new HashSet<Estado>();
//						agrega las transiciones a los estados
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
	
	// recibe un arreglo de strings y por cada uno crea un objeto de tipo estado y lo agrega a un set y lo devuedlve
	private static Set<Estado> createEstados(String idStr[]) { 
		Set<Estado> estados= new HashSet<Estado>();
		for (String id : idStr) {
			Estado e=new Estado(Integer.valueOf(id));
			estados.add(e);
		}
		
		return estados;
		
	}
	 //recibe un string y un conjunto de estados, lo itera y compara el id de cada uno con el valor entero del string y lo devuelve
	private static Estado getEstado(String c,Set<Estado> estados){
		Estado estado=null;
		for (Estado e : estados) {
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
			Estado estado = getEstado(e, estados);
			if(!validateEstado(estado)){
				valid=false;
				break;
			}	
		}
		return valid;
	}
	
	private static  boolean validateAlfabeto(char s ,String[] alfabeto){ //recibe un caracter y un array de String y busca el caracter en el array 
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
