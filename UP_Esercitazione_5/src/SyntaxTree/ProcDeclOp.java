package SyntaxTree;

import Visitor.Visitor;

public class ProcDeclOp extends NewScopeNode{

	public ProcDeclOp(String op, Leaf l, OpNode n1, OpNode n2, OpNode n3) {
		super(op, l, n1, n2, n3);
	}
	
	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
	
}
