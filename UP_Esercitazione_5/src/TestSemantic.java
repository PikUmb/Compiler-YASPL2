import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import SymbolTable.SymbolTablesStack;
import SyntaxTree.OpNode;
import SyntaxTree.ProgramOp;
import Util.EntryFunzione;
import Util.FunctionTable;
import Visitor.SemanticVisitor;
import Visitor.XMLVisitor;

public class TestSemantic {

	public static void main(String[] args) {

		File file = new File("input.txt");
		try {
			Lexer lexer = new Lexer(new FileInputStream(file));
			YASPL2Cup parser = new YASPL2Cup(lexer);
			ProgramOp toPrint = (ProgramOp) parser.parse().value;
			
			SymbolTablesStack stack = new SymbolTablesStack();
			toPrint.accept(new SemanticVisitor(stack));
			System.out.println(stack.pop());
		    
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
	}

}
