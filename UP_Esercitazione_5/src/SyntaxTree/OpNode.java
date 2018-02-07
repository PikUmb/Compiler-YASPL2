package SyntaxTree;

import java.util.ArrayList;

import Visitor.Visitor;

public class OpNode extends Node {

	private ArrayList<Node> val = new ArrayList<Node>();

	public OpNode(String op, Node...list) {
		super(op);
		for (Node node : list) {
			this.val.add(node);
		}
	}
	
	public Object accept(Visitor v) throws Exception{
		return v.visit(this);
	}

	public void addNode(Node n){
		val.add(n);
	}

	public ArrayList<Node> nodeList(){
		return val;
	}

}
