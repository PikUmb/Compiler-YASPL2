package SyntaxTree;

import Visitor.Visitor;

public class Leaf extends Node{

	private String val;
	
	public Leaf(String op, String val){
		super(op);
		this.val = val;
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}

	public Object getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	
}
