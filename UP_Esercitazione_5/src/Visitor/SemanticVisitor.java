package Visitor;

import java.util.ArrayList;

import SymbolTable.EntryInfo;
import SymbolTable.SymbolTable;
import SymbolTable.SymbolTablesStack;
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
import Util.FunctionTable;

public class SemanticVisitor implements Visitor{
	
	private SymbolTablesStack stack;
	private String type= "no-type";
	//mi serve per prendere il nome della funzione e creare la FunctionTable con i parametri di In e Out della funzione
	private String funzioneIn= null;
	//mi serve per prendere il nome della funzione
	private String funzioneOut= null;
	private FunctionTable functionTable;
	
	public SemanticVisitor(SymbolTablesStack stack) {
		this.stack= stack;
		this.functionTable= new FunctionTable("");
	}

	@Override
	public Object visit(Node node) {
		return null;
	}

	@Override
	public Object visit(OpNode node) throws Exception {
		
		ArrayList<Node> nodes = node.nodeList();
		for (Node n : nodes){
			if (n!=null){
				n.accept(this);
			}
		}
		
		if(node.getOp().equals("CompStatOp")) {
			node.setType("no-type");
		}
		
		return null;
	}

	@Override
	public Object visit(Leaf node) throws Exception {
	
	    if(node.getOp().equals("ID")){
	      EntryInfo ei=stack.lookup(node.getVal());
	      node.setType(ei.getType());
	    }
	    
	    if(funzioneIn != null) {
	    		this.functionTable.setNomeFunzione(funzioneIn);
	    		this.functionTable.addParamInFunction(funzioneIn, node.getType());
	    }
	    
	    if(funzioneOut != null) {
	    		this.functionTable.setNomeFunzione(funzioneOut);
	    		this.functionTable.addParamOutFunction(funzioneOut, node.getType());
	    }
	    
		return null;
	}

	@Override
	public Object visit(DeclarationNode node) throws Exception {
		return null;
	}

	@Override
	public Object visit(UseNode node) {		
		return null;
	}

	@Override
	public Object visit(NewScopeNode node) throws Exception {
		return null;
	}
	
	//nodo creazione scope
	@Override 
	public Object visit(ProgramOp node) throws Exception {
		stack.addIdentifier("HEAD", new EntryInfo("Program"));
		SymbolTable st = new SymbolTable("Programma");
		stack.push(st);
		node.setSymbolTable(st);
		for(Node n:node.nodeList()) {
			n.accept(this);
		}
		stack.pop(); //rilascio quando ho visitato tutti i figli
		System.out.println("Tab corrente: " + node.getSymbolTable());
		
		//System.out.println(functionTable.toString());
		
		return null;
	}

	//nodo creazione scope
	@Override
	public Object visit(ProcDeclOp node) throws Exception {
		
		stack.addIdentifier(node.getName(), new EntryInfo("Procedure"));
		SymbolTable st = new SymbolTable(node.getName());
		stack.push(st);
		node.setSymbolTable(st);
		
		node.nodeList().get(0).accept(this); //accept identificatore
		funzioneIn= node.getName();
		if(node.nodeList().get(1)!=null) { //perchè parametri In possono non esserci
			node.nodeList().get(1).accept(this); //accept parametri input funzione
		}
		funzioneIn= null;
		funzioneOut= node.getName();
		node.nodeList().get(2).accept(this); //accept parametri out funzione
		funzioneOut= null;
		node.nodeList().get(3).accept(this); //accept body funzione
		
		stack.pop(); //rilascio quando ho visitato tutti i figli
		System.out.println("Tab corrente: " + node.getSymbolTable());
		return null;
	}

	@Override
	public Object visit(MathOp node) throws Exception {
		
		node.getFirstOp().accept(this);
		node.getSecondOp().accept(this);
		String etichetta= node.getOp();
		
		if( (etichetta.equals("AndOp")) || (etichetta.equals("OrOp")) ) {
			
			if( (node.getFirstOp().getType().equals("boolean")) && (node.getSecondOp().getType().equals("boolean")) ) {
				node.setType("boolean");
			}else {
				throw new Exception("Type Mismatch");
			}
		}else if( (etichetta.equals("AddOp")) || (etichetta.equals("DiffOp")) || (etichetta.equals("MulOp")) || (etichetta.equals("DivOp")) ) {
			if( (!node.getFirstOp().getType().equals("boolean")) && (!node.getSecondOp().getType().equals("boolean")) ) {
				if( (node.getFirstOp().getType().equals("double")) || (node.getSecondOp().getType().equals("double"))) {
					node.setType("double");
				}else {
					node.setType("integer");
				}
			}else {
				throw new Exception("Type Mismatch");
			}
		}else {// operatori relazionali
			
			if( (!node.getFirstOp().getType().equals("boolean")) && (!node.getSecondOp().getType().equals("boolean")) ) {
				node.setType("boolean");
			}else {
				throw new Exception("Type Mismatch");
			}
		}
		
		return null;
	}

	@Override
	public Object visit(UnaryOp node) throws Exception {
		
		String etichetta= node.getOp();
		if( etichetta.equals("NotOp") ) {
			String type= node.nodeList().get(0).getType();
			if( type.equals("boolean")) {
				node.setType(type);
			}else {
				throw new Exception("Type Mismatch");
			}
			
		}else { //se è UminusOp
			String type= node.nodeList().get(0).getType();
			if( (type.equals("integer")) || (type.equals("double")) ) {
				node.setType(type);
			}else {
				throw new Exception("Type Mismatch");
			}
		}
		
		return null;
	}

	@Override
	public Object visit(ConditionalOp node) throws Exception {
		
		ArrayList<Node> nodes = node.nodeList();
		Node first= nodes.get(0);
		first.accept(this);
		if(first.getType().equals("boolean")) {
			node.setType("no-type");
		}else {
			throw new Exception("Type Mismatch");
		}
		
		for (int i=1; i<nodes.size(); i++){
			nodes.get(i).accept(this);
		}
		
		return null;
	}

	@Override
	public Object visit(IfThenOp node) throws Exception {
		return this.visit((ConditionalOp) node);
	}

	@Override
	public Object visit(IfThenElseOp node) throws Exception {
		return this.visit((ConditionalOp) node);
	}

	@Override
	public Object visit(CallOp node) throws Exception {
		
		EntryInfo ei= stack.lookup(node.getName()); //controllo se esiste la firma della funzione
		ArrayList<Leaf> vars=new ArrayList<Leaf>();
		OpNode varOp=(OpNode) node.nodeList().get(2);
		ArrayList<String> paramIn= functionTable.getParamIn(node.getName());
		ArrayList<String> paramOut= functionTable.getParamOut(node.getName());
		ArrayList<Node> exprs= ((OpNode)node.nodeList().get(1)).nodeList();
		
		//controllo i parametri di in
		if( exprs.size()!= paramIn.size() ) {
			throw new Exception("Illegal Number Of Arguments In Function " + node.getName().toUpperCase() + " Param In");
		}
		
		for(int j=0; j<exprs.size(); j++) {
			exprs.get(j).accept(this);
			if( !exprs.get(j).getType().equals(paramIn.get(j)) ) {
				throw new Exception("Type Mismatch In Function " + node.getName().toUpperCase() + " Param In");
			}
		}
		
		//controllo i parametri di out
		do{
		      vars.add((Leaf)varOp.nodeList().get(0));
		      if(varOp.nodeList().size()>1)
		        varOp=(OpNode) varOp.nodeList().get(1);
		      else
		        varOp=null;
		    }while(varOp!=null);
		
		if( vars.size()!= paramOut.size() ) {
			throw new Exception("Illegal Number Of Arguments In Function " + node.getName().toUpperCase() + " Param Out");
		}
				
		for(int i=0; i<vars.size(); i++) {
			EntryInfo eiVar= stack.lookup(vars.get(i).getVal());
			if( !eiVar.getType().equals(paramOut.get(i)) ) {
				throw new Exception("Type Mismatch In Function " + node.getName().toUpperCase() + " Param Out");
			}
		}
		
		return null;
	}

	@Override
	public Object visit(AssignOp node) throws Exception {
		
		EntryInfo ei= stack.lookup(node.getName());
		
	    node.getFirstOp().setType(ei.getType());
	    node.getSecondOp().accept(this);
	    
	    String firstOpType=node.getFirstOp().getType();
	    String secondOpType=node.getSecondOp().getType();
	    
	    if( (!firstOpType.equals("boolean")) && (!secondOpType.equals("boolean")))
	      node.setType("no-type");
	    else
	      throw new Exception("Type Mismatch");
		
		return null;
	}

	@Override
	public Object visit(WriteOp node) throws Exception {
		return this.visit((OpNode) node);
	}
	
	@Override
	public Object visit(ReadOp node) throws Exception {
		
		ArrayList<Leaf> inputs=new ArrayList<Leaf>();
	    OpNode varOp=(OpNode) node.nodeList().get(0);
	    
	    do{
	      inputs.add((Leaf)varOp.nodeList().get(0));
	      if(varOp.nodeList().size()>1)
	        varOp=(OpNode) varOp.nodeList().get(1);
	      else
	        varOp=null;
	    }while(varOp!=null);
	    
	    OpNode typOp=(OpNode) node.nodeList().get(1);
	    ArrayList<Node> types=typOp.nodeList();
	    if(inputs.size()!=types.size())
	      throw new Exception("Illegal number of arguments in Read (Vars-Types)");
	    
	    //controllo che i tipi nella read coincidono con quelli delle var (dichiarate sopra)
	    for(int i=0; i<inputs.size(); i++){
	      EntryInfo ei=stack.lookup(inputs.get(i).getVal());
	      if(ei.getType()!=types.get(i).getType())
	        throw new Exception("Type Mismatch for var: "+inputs.get(i).getVal());
	    }
	    
		return null;
	}

	@Override
	public Object visit(VarDeclOp node) throws Exception{
		
		type= node.getLeafType();
		Node toVisit= node.getNode();
		if(toVisit != null) {
			toVisit.accept(this);
		}
		return null;
	}
	
	@Override
	public Object visit(ParDeclOp node) throws Exception {
		//prendo il primo nodo che è VarDeclOp e mi rifaccio alla sua visita
		node.nodeList().get(0).accept(this);
		Node toVisit= node.getNode();
		if(toVisit != null) {
			toVisit.accept(this);
		}
		return null;
	}

	@Override
	public Object visit(VarOp node) throws Exception {
		
		stack.addIdentifier(node.getName(), new EntryInfo("Variable", type));
		ArrayList<Node> toVisit= node.nodeList();
		if(toVisit != null) {
			for(Node n:toVisit) {
				n.accept(this);
			}
		}
		return null;
	}

	@Override
	public Object visit(WhileOp node) throws Exception {
		return this.visit((ConditionalOp) node);
	}

}
