package SyntaxTree;

import Visitor.Visitor;

public class DeclarationNode extends OpNode{

	public DeclarationNode(String op, Leaf l, Node n) {
		super(op, l, n);
	}
	
	public DeclarationNode(String op, Node...l) {
		super(op, l);
	}

	public Leaf getLeaf(){
		return ((Leaf) this.nodeList().get(0));
	}
	
	public String getName(){
		return this.getLeaf().getVal().toString();
	}
	
	public Node getNode(){
		if(this.nodeList().size()>1) {
			return this.nodeList().get(1);
		}else {
			return null;
		}
		
	}
	
	public String getLeafType() {
		return ((Leaf) this.nodeList().get(0)).getType();
	}
	
	public String getNodeType() {
		return this.nodeList().get(1).getType();
	}
	
	public String getNodeName() {
		return this.nodeList().get(1).getOp();
	}
	
	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}
	
}
