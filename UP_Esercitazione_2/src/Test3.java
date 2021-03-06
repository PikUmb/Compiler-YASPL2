import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java_cup.runtime.Symbol;

public class Test3 {

	public static void main(String[] args) throws IOException {

		String fileName = "inputErrato2.txt";
		File source = new File(fileName);
		FileInputStream filein = null; 
		PrintWriter fileout = null;
		
		try {
			filein = new FileInputStream(source);
			fileout = new PrintWriter("output2.txt");
			Lexer lexer = new Lexer(filein);
			Symbol toPrint;
			
			while( ((toPrint = lexer.next_token()).sym) != LexerSym.EOF) {
				
				if(toPrint.sym == LexerSym.ID){
					//gli passo la posizione del id all'interno della tabella dei simboli
					toPrint = SymbolTable.lookup((int) toPrint.value);
				}
				
				System.out.println("<"+toPrint.sym+", '"+toPrint.value+"'>");
				fileout.println("<"+toPrint.sym+", '"+toPrint.value+"'>"); 
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			filein.close();
			fileout.close();
		}
		
	}

}
