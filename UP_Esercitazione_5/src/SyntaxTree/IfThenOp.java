package SyntaxTree;

import Visitor.Visitor;

public class IfThenOp extends ConditionalOp{

	public IfThenOp(String op, Node e, Node s) {
		super(op, e, s);
	}

	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
}
