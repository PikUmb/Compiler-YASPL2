package SymbolTable;

import java.util.Hashtable;

public class SymbolTable extends Hashtable<String, EntryInfo>{
	
private String name;
	
	public SymbolTable(String name){
		super();
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String toString() {
		return "["+name+"]: "+super.toString();
	}
	
}
