package SyntaxTree;

import Visitor.Visitor;

public class ProgramOp extends NewScopeNode{

	public ProgramOp(String op, OpNode decls, OpNode statements) {
		super(op, decls, statements);
	}

	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
}
