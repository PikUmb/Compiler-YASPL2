package Visitor;

/* Visitatore che mi restituisce l'XML */

import org.apache.commons.lang3.StringEscapeUtils;
import SyntaxTree.Leaf;
import SyntaxTree.Node;
import SyntaxTree.OpNode;

public class XMLVisitor implements Visitor{

	public Object visit(Node n){
		return null;
	}
	
	public Object visit(OpNode n) {
		String toPrint = "";
		toPrint += "<"+n.getOp()+">\n";
		for (Node node : n.nodeList()){
			if (node!=null){
				toPrint += node.accept(this)+"";
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
			toPrint += " attr='" + StringEscapeUtils.escapeXml(n.getVal().toString()) + "'"; //converto la stringa nella sua rappresentazione XML
		}
		toPrint += "/>\n";
		return toPrint;
	}

}
