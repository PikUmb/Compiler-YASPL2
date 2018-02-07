package SyntaxTree;

import Visitor.Visitor;

public class CallOp extends UseNode{

	public CallOp(String op, Leaf l, Node e, Node p) {
		super(op, l, e, p);
	}
	
	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}	
	
}
