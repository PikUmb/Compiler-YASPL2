
import java.io.File;
import java.io.FileInputStream;
import SyntaxTree.*;
import Visitor.XMLVisitor;
import java.io.FileWriter;
import SymbolTable.*;

public class Test1 {

	public static void main(String[] args) throws Exception {
		File file = new File("inputEser5.txt");
		try {
			Lexer lexer = new Lexer(new FileInputStream(file));
			YASPL2Cup parser = new YASPL2Cup(lexer);
			OpNode root = (OpNode) parser.parse().value;
			String xmlSource = root.accept(new XMLVisitor()).toString();
			System.out.println(xmlSource);
			FileWriter fw = new FileWriter("outputEser5.xml");
		    fw.write(xmlSource);
		    fw.close();
		    
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
}
