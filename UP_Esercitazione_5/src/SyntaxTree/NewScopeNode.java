package SyntaxTree;

import SymbolTable.SymbolTable;
import Visitor.Visitor;

public class NewScopeNode extends DeclarationNode{
	
	private SymbolTable symbolTable;
	
	public NewScopeNode(String op, OpNode n1, OpNode n2) {
		super(op, n1, n2);
		symbolTable = null;
	}
	
	public NewScopeNode(String op, Leaf l, OpNode n1, OpNode n2, OpNode n3) {
		super(op, l, n1, n2, n3);
		symbolTable = null;
	}
	
	public SymbolTable getSymbolTable(){
		return this.symbolTable;
	}
	
	public void setSymbolTable(SymbolTable symbolTable){
		this.symbolTable = symbolTable;
	}
	
	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}

}
