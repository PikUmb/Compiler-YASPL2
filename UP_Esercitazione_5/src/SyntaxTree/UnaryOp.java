package SyntaxTree;

import Visitor.Visitor;

public class UnaryOp extends OpNode{

	public UnaryOp(String op, Node node) {
		super(op, node);
	}
	
	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
}
