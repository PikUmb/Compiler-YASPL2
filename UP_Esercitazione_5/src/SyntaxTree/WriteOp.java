package SyntaxTree;

import Visitor.Visitor;

/*
 * In questo caso la classe estende OpNode perchè nella produzione per WRITE andiamo a stampare varibili già definite.
 * Il LookUp viene eseguito direttamente nelle foglie (variabili).
 */

public class WriteOp extends OpNode{

	public WriteOp(String op, Node outValues) {
		super(op, outValues);
	}

	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
		
}
