package SyntaxTree;

import Visitor.Visitor;

public class VarOp extends DeclarationNode{

	public VarOp(String op, Node...n) {
		super(op, n);
	}

	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
}
