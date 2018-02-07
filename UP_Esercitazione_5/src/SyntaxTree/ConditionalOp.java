package SyntaxTree;


import Visitor.Visitor;

public class ConditionalOp extends OpNode{

	public ConditionalOp(String op, Node... list) {
		super(op, list);
	}

	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
		
}
