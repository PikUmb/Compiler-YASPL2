package SyntaxTree;

import Visitor.Visitor;

/*
 * In questo caso la classe estende UseNode perchè nella produzione per READ andiamo
 * ad eseguire il type check tra le var definite in precedenza ed il tipo nell'operazione di read.
 */

public class ReadOp extends UseNode{
	
	public ReadOp(String op, OpNode vars, OpNode types) {
		super(op, vars, types);
	}
	
	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
}
