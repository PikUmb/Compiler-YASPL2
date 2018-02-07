package SyntaxTree;

import Visitor.Visitor;

public class IfThenElseOp extends ConditionalOp{

	public IfThenElseOp(String op, Node e, Node s1, Node s2) {
		super(op, e, s1, s2);
	}

	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
}
