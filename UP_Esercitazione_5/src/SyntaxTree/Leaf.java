package SyntaxTree;

import Visitor.Visitor;

public class Leaf extends Node{

	private String val;
	
	public Leaf(String op, String val, String type){
		super(op);
		this.val = val;
		this.setType(type);
	}
	
	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}

	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	
}
