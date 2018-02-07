package SyntaxTree;

import Visitor.Visitor;

public class MathOp extends OpNode{

	public MathOp(String op, Node n1, Node n2) {
		super(op, n1, n2);
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
