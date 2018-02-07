package SyntaxTree;

import Visitor.Visitor;

public class VarDeclOp extends DeclarationNode{

	public VarDeclOp(String op, Leaf type, OpNode vars) {
		super(op, type, vars);
	}

	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}

}
