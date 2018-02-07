package SymbolTable;

import java.util.ArrayList;
import java.util.Hashtable;

public class SymbolTablesStack implements Stack<SymbolTable>{

	private ArrayList<SymbolTable> stack;

	public SymbolTablesStack(){
		this.stack = new ArrayList<SymbolTable>();
		this.push(new SymbolTable("Globals"));
	}

	public SymbolTable top() {
		return stack.get((stack.size() - 1));
	}

	public void push(SymbolTable t) {
		stack.add(t);
	}
	
	public SymbolTable pop() {
		return stack.remove((stack.size() - 1));
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public String addIdentifier(String key, EntryInfo ei) throws Exception {
		if (isEmpty()) {
			throw new Exception("Stack is empty!");
		} 
		else if (!top().containsKey(key)){
			top().put(key, ei);
		}
		else{
			throw new Exception("Multiple declaration of identifier '"+key+"'");
		}
		return key;
	}
	
	public void updateEntry(String key, EntryInfo value) throws Exception{
		if (isEmpty()) {
			throw new Exception("Stack is empty!");
		} else {
			top().replace(key, value);
		}
	}
	
	public EntryInfo lookup(String key) throws Exception {
		for (int top = (stack.size() - 1); top >= 0; top--) {
			if (stack.get(top).containsKey(key)) {
				return stack.get(top).get(key);
			}
		}
		throw new Exception("Identifier '" + key + "' never declared!");
	}
	
}
