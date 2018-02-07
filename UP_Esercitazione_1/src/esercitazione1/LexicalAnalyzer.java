package esercitazione1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

public class LexicalAnalyzer {
	
	private RandomAccessFile filein;
	private int state;
	private int next; //puntatore del file
	private char c;
	private String lessema;
	private Token toReturn;
	private HashMap<Integer, String> tabSimboli;
	private static int contatore=0;	//utilizzato per la key del HashMap
	
	
	public LexicalAnalyzer(RandomAccessFile filein) {
		this.filein = filein;
		this.state=0;
		this.next=0;
		this.lessema="";
		this.toReturn= null;
		this.tabSimboli=new HashMap<Integer, String>();
		insertKeywordIntoTable();
	}
	
	public Token nextToken() throws Exception {
		
		while(true) {
			
			switch (state) {
			case -1:
				state= 50;
				toReturn= new Token("EOF");
				break;
			
			case 0:
				next= filein.read();
				
				if(next!=-1) {
					c= (char) next;
					
					if(c=='<') {
						state= 1;
					}else if(c=='=') {
						state= 5;
					}else if(c=='>') {
						state= 6;
					}else if(c==':') {
						state= 9;
					}else if(c=='!') {
						state= 12;
					}else if(c=='?') {
						state= 13;
					}else if(c=='-') {
						state= 14;
					}else if(c==';') {
						state= 50;
						toReturn= new Token("PUNCT");
					}else {
						state= 17;
					}
				}else {
					state= -1;
				}
				break;

			case 1:
				next= filein.read();
				
				if(next!=-1) {
					c= (char) next;
					
					if(c=='=') {
						state= 2;
					}else if(c=='>') {
						state= 3;
					}else if(c=='-') {
						state= 40;
					}else {
						state= 4;
					}
				}else {
					state= 50;
					toReturn= new Token("RELOP", "LT");
				}
				break;
				
			case 2:
				state= 50;
				toReturn= new Token("RELOP", "LE");
				break;
				
			case 3:
				state= 50;
				toReturn= new Token("RELOP", "NE");
				break;
				
			case 4:
				state= 50;
				retract(filein);
				toReturn= new Token("RELOP", "LT");
				break;
				
			case 5:
				state= 50;
				toReturn= new Token("RELOP", "EQ");
				break;
				
			case 6:
				next= filein.read();
				
				if(next!=-1) {
					c= (char) next;
					
					if(c=='=') {
						state= 7;
					}else {
						state= 8;
					}
					
				}else {
					state= 50;
					toReturn= new Token("RELOP", "GT");
				}
				break;
				
			case 7:
				state= 50;
				toReturn= new Token("RELOP", "GE");
				break;
				
			case 8:
				state= 50;
				retract(filein);
				toReturn= new Token("RELOP", "GT");
				break;
				
			case 9:
				next= filein.read();
				
				if(next!=-1) {
					c= (char) next;
					
					if(c=='=') {
						state= 10;
					}else {
						state= 11;
					}
				}else {
					state= 50;
					toReturn= new Token("RELOP", "DP");
				}
				break;
				
			case 10:
				state= 50;
				toReturn= new Token("ASSIGN");
				break;
				
			case 11:
				state= 50;
				retract(filein);
				toReturn= new Token("RELOP", "DP");
				break;
				
			case 12:
				state= 50;
				toReturn= new Token("RELOP", "PESCL");
				break;
				
			case 13:
				state= 50;
				toReturn= new Token("RELOP", "PINT");
				break;
				
			case 14:
				next= filein.read();
				
				if(next!=-1) {
					c= (char) next;
					
					if(c=='>') {
						state= 15;
					}else {
						state= 16;
					}
				}else {
					state= 50;
					toReturn= new Token("RELOP","MENO");
				}
				break;
				
			case 15:
				state= 50;
				toReturn= new Token("RELOP", "TFASSIGN");
				break;
				
			case 16:
				state= 50;
				retract(filein);
				toReturn= new Token("RELOP", "MENO");
				break;
				
			case 17:
				lessema="";
				c= (char) next;
				
				if( (Character.isLetter(c)) || (c=='_') || (c=='.') ) {
					state= 18;
					lessema += c;
				}else {
					state= 20;
				}
				break;
				
			case 18:
				next= filein.read();
				
				if(next!=-1) {
					c= (char) next;
					
					if(Character.isLetterOrDigit(c)) {
						lessema += c;
					}else if(c=='_') {
						state= 0;
						retract(filein);
						throw new Exception("Lessema non riconosciuto");
					}else if(c=='.'){
						state= 0;
						retract(filein);
						throw new Exception("Lessema non riconosciuto");
					}else {
						state= 50;
						retract(filein);
						toReturn= insetIntoTable(lessema);
					}
				}else {
					state= 50;
					toReturn= insetIntoTable(lessema);
				}
				break;
				
			case 19:
				state= 50;
				retract(filein);
				toReturn= insetIntoTable(lessema);
				break;
				
			case 20:
				lessema="";
				c= (char) next;
				
				if(Character.isDigit(c)) {
					state= 21;
					lessema += c;
				}else {
					state= 30;
				}
				break;
				
			case 21:
				next= filein.read();
				
				if(next!=-1) {
					c= (char) next;
					
					if(Character.isDigit(c)) {
						lessema += c;
					}else if(c=='.') {
						state= 22;
						lessema += c;
					}else if(c=='E') {
						state= 24;
						lessema += c;
					}else {
						state= 28;
					}
				}else {
					state= 50;
					toReturn= new Token("NUMBER", lessema);
				}
				break;
				
			case 22:
				next= filein.read();
				c= (char) next;
				
				if(Character.isDigit(c)) {
					state= 23;
					lessema += c;
				}else {
					state= 0;
					retract(filein);
					throw new Exception("Lessema non riconosciuto");
				}
				break;
				
			case 23:
				next= filein.read();
				c= (char) next;
				
				if(Character.isDigit(c)) {
					lessema += c;
				}else if(c=='E') {
					state= 24;
					lessema += c;
				}else {
					state= 29;
				}
				break;
				
			case 24:
				next= filein.read();
				c= (char) next;
				
				if( (c=='+') || (c=='-') ) {
					state= 25;
					lessema += c;
				}else if(Character.isDigit(c)) {
					state= 26;
					lessema += c;
				}else {
					state= 0;
					retract(filein);
					throw new Exception("Lessema non riconosciuto");
				}
				break;
				
			case 25:
				next= filein.read();
				c= (char) next;
				
				if(Character.isDigit(c)) {
					state= 26;
					lessema += c;
				}else {
					state= 0;
					retract(filein);
					throw new Exception("Lessema non riconosciuto");
				}
				break;
				
			case 26:
				next= filein.read();
				c= (char) next;
				
				if(Character.isDigit(c)) {
					lessema += c;
				}else {
					state= 27;
				}
				break;
				
			case 27:
				state= 50;
				retract(filein);
				toReturn= new Token("NUMBER", lessema);
				break;
				
			case 28:
				state= 50;
				retract(filein);
				toReturn= new Token("NUMBER", lessema);
				break;
				
			case 29:
				state= 50;
				retract(filein);
				toReturn= new Token("NUMBER", lessema);
				break;
				
			case 30:
				c= (char) next;
				
				if(whitespace(c)) {
					state= 31;
				}else{
					state= -1;
					retract(filein);
					throw new Exception("Lessema non riconosciuto");
				}
				break;
				
			case 31:
				next= filein.read();
				c= (char) next;
				
				if(!whitespace(c)) {
					state= 32;
				}
				break;
				
			case 32:
				state=0;
				if (next!=-1){
					retract(filein);
				}
				break;
				
			case 40:
				state= 50;
				toReturn= new Token("RELOP" ,"FREASSIGN");
				break;
				
			case 50:
				state= 0;
				return toReturn;
				
			default:
				System.out.println("Default");
				break;
			}
			
		}
		
	}
	
	public boolean whitespace(char c){
		return ((c == ' ') || (c == '\n') || (c == '\r'));
    }
	
	public Token insetIntoTable(String lessema) {
		
		Token out;
		lessema= lessema.toUpperCase();
		
		//se il lessema è già contenuto nella tab dei simboli non lo aggiungo
		if(tabSimboli.containsValue(lessema)) {
			if(isKeyword(lessema)) {
				out= new Token(lessema);
			}else {
				out= new Token("ID", lessema);
			}
		}else {
			tabSimboli.put(contatore, lessema);
			out= new Token("ID", lessema);
			contatore++;
		}
		return out;
	}
	
	//mi permette di tornare indietro di una posizione nel file
	public void retract(RandomAccessFile filein) {
		try {
			filein.seek(filein.getFilePointer()-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//restituisce true se è una parola chiave, false altrimenti
	public boolean isKeyword(String word) {
		return (word.equals("IF") || word.equals("THEN") || word.equals("ELSE") || word.equals("ENDIF") 
				|| word.equals("NEW") || word.equals("FOR") || word.equals("WHILE") || word.equals("DO") 
				|| word.equals("RETURN") || word.equals("SWITCH") || word.equals("PROVA"));
	}
	
	//inserisce le parole chiave nella tabella dei simboli in modo da dargli priorità quando riconosce un lessema
	public void insertKeywordIntoTable() {
		tabSimboli.put(contatore, "IF");
		contatore++;
		tabSimboli.put(contatore, "THEN");
		contatore++;
		tabSimboli.put(contatore, "ELSE");
		contatore++;
		tabSimboli.put(contatore, "ENDIF");
		contatore++;
		tabSimboli.put(contatore, "NEW");
		contatore++;
		tabSimboli.put(contatore, "FOR");
		contatore++;
		tabSimboli.put(contatore, "WHILE");
		contatore++;
		tabSimboli.put(contatore, "DO");
		contatore++;
		tabSimboli.put(contatore, "RETURN");
		contatore++;
	}

}
