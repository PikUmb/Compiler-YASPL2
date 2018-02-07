package SyntaxTree;

import Visitor.Visitor;

public class UseNode extends DeclarationNode{
	
	public UseNode(String op, Leaf l, Node n) {
		super(op, l, n);
	}
	
	public UseNode(String op, Node...n) {
		super(op, n);
	}

	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
}
