import java.util.Hashtable;
import java_cup.runtime.Symbol;

public class SymbolTable {
	
	private static Hashtable<Integer, String> symTable = new Hashtable<Integer, String>();
	private static int counter = 1;
	
	public static int addIdentifiers(String lessema){
		int toReturn = 0;
		if (!symTable.containsValue(lessema)){
			symTable.put(counter, lessema);
			toReturn = counter;
			counter++;
		}
		else{
			toReturn = findValue(lessema);
			}
		return toReturn;		
	}
	
	public static Symbol lookup(int key) {
		Symbol sym = null;
		if (symTable.containsKey(key)) {
			sym =  new Symbol(YASPL2Sym.NAME, symTable.get(key));
			}
		return sym;
	}
	
	private static int findValue(String lessema){
		int toReturn = -1;
		for (int i=1; i<= symTable.size(); i++){
			if (lessema.equals(symTable.get(i))){
				toReturn = i;
				break;
			}
		}
		
		return toReturn;
	}
	
}
