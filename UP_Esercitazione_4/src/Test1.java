import java.io.File;
import java.io.FileInputStream;

public class Test1 {

	public static void main(String[] args) {
		
		System.out.println("File input. Risultato attesto: Corretto sintatticamente.");
		File file = new File("input.txt");
		try {
			
			Lexer lexer = new Lexer(new FileInputStream(file));
			YASPL2Cup parser = new YASPL2Cup(lexer);
		    (parser.parse().value).toString();
			
		}catch (Exception e) {
		}

	}

}
