package esercitazione3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ParserDiscesaRicorsiva {
	
	static int puntatore;
	static char[] input;

	public static void main(String[] args) {
		
		try {
			RandomAccessFile filein = new RandomAccessFile("input.txt", "r");
			PrintWriter fileout = new PrintWriter("output.txt");
			String s = "";
			String newS = "";
			String toReturn= "";
			System.out.println("Leggo la stringa in input dal file.");
			
			while( (s=filein.readLine()) != null) {
				newS = newS + s.replaceAll("\\s+","");	//elimino eventuali spazi bianchi
			}
			
			System.out.println("Stringa in input: " + newS);
			
			input = newS.toCharArray();
			
			if(input.length < 2) {
				System.out.println("La stringa in input non è valida!");
				return;
			}
			
			puntatore = 0;
			
			try {
				boolean isValid = P();
				if( (isValid) && (puntatore == input.length) ) {
					toReturn = "La stringa in input è valida";
					System.out.println(toReturn);
				}else {
					toReturn = "La stringa in input non è valida!";
					System.out.println(toReturn);
				}
			}catch (Exception e) {
				e.printStackTrace();
				toReturn = "La stringa in input non è valida!";
				System.out.println(toReturn);
			}
			
			fileout.println(toReturn);
			
			filein.close();
			fileout.close();
	
		}catch (FileNotFoundException ex) {
			System.out.println("File non trovato");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static boolean P() throws Exception{
		
		int backtrack = puntatore;
		
		if( E() == false ) {
			puntatore = backtrack;
			return false;
		}
		
		if( P1() == false ) {
			puntatore = backtrack;
			return false;
		}
		
		return true;
	}
	
	static boolean E() {
		
		int backtrack = puntatore;
		
		if( F() == false ) {
			puntatore = backtrack;
			return false;
		}
		
		if( E1() == false ) {
			puntatore = backtrack;
			return false;
		}
		
		return true;
	}
	
	static boolean E1() {
		
		int backtrack = puntatore;
		
		if( canIncrease() == true ) {
			if( input[puntatore++] != '+' ) {
				puntatore = backtrack;
				return true;
			}
		}else {
			return true;
		}
		
		if( F() == false ) {
			puntatore = backtrack;
			return false;
		}
		
		if( E1() == false) {
			puntatore = backtrack;
			return false;
		}
		
		return true;
	}
	
	static boolean P1() {
		
		int backtrack = puntatore;
				
		if( canIncrease() == true ) {
			if( input[puntatore++] != ';' ) {
				puntatore = backtrack;
				return true;
			}
		}else {
			return true;
		}
		
		if( E() == false ) {
			puntatore = backtrack;
			return false;
		}
		
		if( P1() == false ) {
			puntatore = backtrack;
			return false;
		}
		
		return true;
	}
	
	static boolean F() {
		
		int backtrack = puntatore;
		
		if( T() == false ) {
			puntatore = backtrack;
			return false;
		}
		
		if( F1() == false ) {
			puntatore = backtrack;
			return false;
		}
		
		return true;
	}
	
	static boolean T() {
		
		int backtrack = puntatore;
		
		if( isId() == true ) {
			return true;
		}else {
			
			if( input[puntatore++] != '(' ) {
				puntatore = backtrack;
				return false;
			}
			
			if( E() == false ) {
				puntatore = backtrack;
				return false;
			}
			
			if( input[puntatore++] != ')' ) {
				puntatore = backtrack;
				return false;
			}
			
			return true;
		}
	}
	
	static boolean F1() {
		
		int backtrack = puntatore;
		
		if( canIncrease() == true ) {
			if( input[puntatore++] != '*' ) {
				puntatore = backtrack;
				return true;
			}
		}else {
			return true;
		}
		
		if( T() == false ) {
			puntatore = backtrack;
			return false;
		}
		
		if( F1() == false ) {
			puntatore = backtrack;
			return false;
		}
		
		return true;
	}
	
	//metodo che verifica se sto leggendo un identificatore
	static boolean isId() {
		
		int backtrack = puntatore;
		
		if( (input[puntatore] >= 'a' && input[puntatore] <= 'z') || (input[puntatore] >= 'A' && input[puntatore] <= 'Z') ) {
			
			puntatore++;
			while( puntatore < input.length ) {
				if( (input[puntatore] >= 'a' && input[puntatore] <= 'z') || (input[puntatore] >= 'A' && input[puntatore] <= 'Z') 
						|| (input[puntatore] >= '0' && input[puntatore] <= '9') ) {
					puntatore++;
				}else {
					break;
				}
			}
			return true;
		}else {
			return false;
		}
		
	}
	
	//metodo che controlla se sono giunto alla fine dell'input o meno
	static boolean canIncrease() {
		
		if( puntatore < input.length ) {
			return true;
		}
		
		return false;
	}

}