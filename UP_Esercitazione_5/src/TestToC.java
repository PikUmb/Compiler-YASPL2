import java.io.File;
import java.io.FileInputStream;

import SymbolTable.SymbolTablesStack;
import SyntaxTree.ProgramOp;
import Visitor.SemanticVisitor;
import Visitor.YASPL2ToCVisitor;

public class TestToC {

	public static void main(String[] args) {

		File file = new File("inputEser4.txt");
		try {
			
			String[] name = file.getName().split("\\.");
			Lexer lexer = new Lexer(new FileInputStream(file));
			YASPL2Cup parser = new YASPL2Cup(lexer);
			ProgramOp toPrint = (ProgramOp) parser.parse().value;
			
			SymbolTablesStack stack = new SymbolTablesStack();
			toPrint.accept(new SemanticVisitor(stack));
			toPrint.accept(new YASPL2ToCVisitor(name[0]));
			System.out.println(stack.pop());
			
		    
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
	}

}
