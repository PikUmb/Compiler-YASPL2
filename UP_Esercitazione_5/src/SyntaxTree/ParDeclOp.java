package SyntaxTree;

import Visitor.Visitor;

public class ParDeclOp extends DeclarationNode{
	
	public ParDeclOp(String op, OpNode vars) {
		super(op, vars);
	}

	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}

}
