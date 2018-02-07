package Visitor;

/* Visitatore che mi restituisce l'XML */

import org.apache.commons.lang3.StringEscapeUtils;

import SyntaxTree.AssignOp;
import SyntaxTree.CallOp;
import SyntaxTree.ConditionalOp;
import SyntaxTree.DeclarationNode;
import SyntaxTree.IfThenElseOp;
import SyntaxTree.IfThenOp;
import SyntaxTree.Leaf;
import SyntaxTree.MathOp;
import SyntaxTree.NewScopeNode;
import SyntaxTree.Node;
import SyntaxTree.OpNode;
import SyntaxTree.ParDeclOp;
import SyntaxTree.ProcDeclOp;
import SyntaxTree.ProgramOp;
import SyntaxTree.ReadOp;
import SyntaxTree.UnaryOp;
import SyntaxTree.UseNode;
import SyntaxTree.VarDeclOp;
import SyntaxTree.VarOp;
import SyntaxTree.WhileOp;
import SyntaxTree.WriteOp;

public class XMLVisitor implements Visitor{

	public Object visit(Node n){
		return null;
	}
	
	public Object visit(OpNode n) {
		String toPrint = "";
		toPrint += "<"+n.getOp()+">\n";
		for (Node node : n.nodeList()){
			if (node!=null){
				try {
					toPrint += node.accept(this)+"";
				} catch (Exception e) {
					System.out.println(e);;
				}
			}
			else 
				toPrint += "<null/>\n";
		}
		toPrint += "</"+n.getOp()+">\n";
		return toPrint;
	}

	public Object visit(Leaf n) {
		String toPrint = "";
		toPrint += "<" + n.getOp();
		if (n.getVal() != null) {
			toPrint += " attr='" + StringEscapeUtils.escapeXml(n.getVal().toString()) + "'";
		}
		toPrint += "/>\n";
		return toPrint;
	}

	@Override
	public Object visit(DeclarationNode node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(UseNode node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(NewScopeNode node) throws Exception{
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(MathOp node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(UnaryOp node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(ConditionalOp node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(IfThenOp node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(IfThenElseOp node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(CallOp node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(AssignOp node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(WriteOp node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(ReadOp node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(VarDeclOp node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(ProgramOp node) throws Exception {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(ProcDeclOp node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(ParDeclOp node) throws Exception {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(VarOp node) {
		return this.visit((OpNode) node);
	}

	@Override
	public Object visit(WhileOp node) {
		return this.visit((OpNode) node);
	}

}
