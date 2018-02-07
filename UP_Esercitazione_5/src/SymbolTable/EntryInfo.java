package SymbolTable;

public class EntryInfo {

	private String kind;
	private String type;

	public EntryInfo(String kind){
		this.kind = kind;
		this.type = "";
	}
	
	public EntryInfo(String kind, String type){
		this.kind = kind;
		this.type = type;
	}

	public String getKind(){
		return kind;
	}
	
	public String getType(){
		return type;
	}
	
	public void setKind(String kind){
		this.kind = kind;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String toString(){
		return "("+kind+", "+type+")";
	}
}
