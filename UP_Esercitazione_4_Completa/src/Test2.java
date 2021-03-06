import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import SyntaxTree.OpNode;
import Visitor.XMLVisitor;

public class Test2 {

public static void main(String[] args) {
		
		File file = new File("input2.txt");
		try {
			
			Lexer lexer = new Lexer(new FileInputStream(file));
			YASPL2Cup parser = new YASPL2Cup(lexer);
		    OpNode root = (OpNode) parser.parse().value;
			String xmlSource = root.accept(new XMLVisitor()).toString();
			System.out.println(xmlSource);
			FileWriter fw = new FileWriter("output2.xml");
		    fw.write(xmlSource);
		    fw.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
