package SyntaxTree;

import Visitor.Visitor;

public class AssignOp extends UseNode{

	public AssignOp(String op, Leaf name, Node e) {
		super(op, name, e);
	}

	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
	
	public Node getFirstOp(){
		return this.nodeList().get(0);
	}
	
	public Node getSecondOp(){
		return this.nodeList().get(1);
	}

}
