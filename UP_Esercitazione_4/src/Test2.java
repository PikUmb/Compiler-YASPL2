import java.io.File;
import java.io.FileInputStream;

public class Test2 {

	public static void main(String[] args) {
		
		System.out.println("File input2. Risultato attesto: Syntax Error.");
		File file = new File("input2.txt");
		try {
			
			Lexer lexer = new Lexer(new FileInputStream(file));
			YASPL2Cup parser = new YASPL2Cup(lexer);
		    (parser.parse().value).toString();
			
		}catch (Exception e) {
		}

	}

}
