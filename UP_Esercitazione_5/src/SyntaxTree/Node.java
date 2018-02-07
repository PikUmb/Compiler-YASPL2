package SyntaxTree;

import Visitor.Visitor;

public class Node {

	private String op;
	private String type;
	
	public Node(String op){
		this.op = op;
		this.type = "";
	}
	
	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
	
	public String getOp() {
		return op;
	}
	
	public void setOp(String op) {
		this.op = op;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
}
