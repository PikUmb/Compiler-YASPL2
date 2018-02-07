package esercitazione1;

import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class Tester1 {

	public static void main(String[] args) {
		
		try {
			RandomAccessFile filein = new RandomAccessFile("input.txt", "r");
			PrintWriter fileout = new PrintWriter("output.txt");
			Token toReturn;
			LexicalAnalyzer lex = new LexicalAnalyzer(filein);
			
			while( !(toReturn= lex.nextToken()).getName().equals("EOF")) {
				
				if(toReturn.getName().equals("ID")) {
					toReturn = new Token("ID", toReturn.getAttribute_value());
				}
				
				fileout.println(toReturn);
				System.out.println(toReturn);
			}
		
			filein.close();
			fileout.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
