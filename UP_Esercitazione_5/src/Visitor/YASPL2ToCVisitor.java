package Visitor;

import java.io.FileWriter;
import java.util.ArrayList;
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
import Util.EntryDefinizioneFunzione;

public class YASPL2ToCVisitor implements Visitor{
	
	private String nomeFile;
	private FileWriter fw;
	private boolean waitForPrintf= false; //lo uso per la write, per poter inserire prima la formattazione e poi il nome della var
	private String saveIdentifierToPrint= ""; //salvo nome variabile per poterla scrivere nella write
	private ArrayList<EntryDefinizioneFunzione> nomiVarOut; //var definite come parametro di ritorno
	
	public YASPL2ToCVisitor(String nomeFile) {
		this.nomeFile= nomeFile;
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
		
		return null;
	}

	@Override
	public Object visit(Leaf node) throws Exception {
		
		//se non sono nella write allora scrivo subito nel file, altrimenti scrivo quando ritorno nel nodo write dopo la ricorsione.
		if( !this.waitForPrintf ) {
			fw.write(node.getVal());
		}else {
			//mi salvo il nome della variabile
			this.saveIdentifierToPrint= node.getVal();
		}
		
		return null;
	}

	@Override
	public Object visit(ProgramOp node) throws Exception {

		fw= new FileWriter(nomeFile + ".c");
		
		String toReturn = "#include <stdio.h>\n";
		toReturn += "#include <stdbool.h>\n";
		fw.write(toReturn);
		
		ArrayList<Node> decls= ((OpNode) node.nodeList().get(0)).nodeList();
		ArrayList<Node> stats= ((OpNode) node.nodeList().get(1)).nodeList();
		
		//visita su decls
		for(Node nD:decls) {
			nD.accept(this);
		}
		
		fw.write("int main(void){\n");
		
		//visita su stats
		for(Node nS:stats) {
			nS.accept(this);
		}
		
		toReturn = "return 0;\n}";
		fw.write(toReturn);
		fw.close();
		return null;
	}
	
	//metodo ausiliario per salvare i parametri della definizione di funzione in arraylist
	public ArrayList<EntryDefinizioneFunzione> salvaParametriDefinizioneFunzione(ArrayList<Node> listVarDecl){
		
		ArrayList<EntryDefinizioneFunzione> var= new ArrayList<EntryDefinizioneFunzione>();
		OpNode vars;
		String type;
		EntryDefinizioneFunzione edf;
		
		//scorro tutti i VarDecl
		for(Node varD:listVarDecl) {
			
			type= ((OpNode) varD).nodeList().get(0).getType();
			if( type.equals("integer") ) {
				type= "int";
			}else if( type.equals("boolean") ) {
				type= "bool";
			}
			//prendo la lista di VarOp
			vars = (OpNode) ((OpNode) varD).nodeList().get(1);
			
			//ciclo dove mi salvo tutti i parametri (input-output)
			do{
				edf= new EntryDefinizioneFunzione(((Leaf) vars.nodeList().get(0)).getVal(), type);
				var.add(edf);
				if(vars.nodeList().size()>1)
					vars=(OpNode) vars.nodeList().get(1);
				else
					vars=null;
			   }while(vars!=null);
		}
		return var;
		
	}

	@Override
	public Object visit(ProcDeclOp node) throws Exception {
		
		//varDecls
		OpNode varDecls= (OpNode) node.nodeList().get(1);
		ArrayList<Node> listVarDecl= varDecls.nodeList();
		
		//mi salvo tutte le variabili (parametri input)
		ArrayList<EntryDefinizioneFunzione> nomiVarIn= new ArrayList<EntryDefinizioneFunzione>();
		//mi salvo tutte le variabili (parametri output)
		nomiVarOut= new ArrayList<EntryDefinizioneFunzione>();
		
		//chiamo il metodo che salva i parametri in IN in un array
		nomiVarIn= salvaParametriDefinizioneFunzione(listVarDecl);
		
		//parDecls
		OpNode parDecls= (OpNode) node.nodeList().get(2);
		listVarDecl= parDecls.nodeList();
		
		//chiamo il metodo che salva i parametri in OUT in un array
		nomiVarOut= salvaParametriDefinizioneFunzione(listVarDecl);
		
		fw.write("void " + node.getName() + "(");
		
		//scrivo i parametri di in nella definizione della funzione
		for(int i=0; i<nomiVarIn.size(); i++) {
			fw.write(nomiVarIn.get(i).getTipo() + " " + nomiVarIn.get(i).getNomeVar() + ", ");
		}
		
		//scrivo i parametri di out nella definizione della funzione
		for(int j=0; j<nomiVarOut.size(); j++) {
			
			fw.write(nomiVarOut.get(j).getTipo() + " *" + nomiVarOut.get(j).getNomeVar());
			
			if(j < nomiVarOut.size()-1) {
				fw.write(", ");
			}
		}
		
		//body
		OpNode body= (OpNode) node.nodeList().get(3);
		
		fw.write("){\n");
		body.accept(this);
		fw.write("};\n");
		
		//svuoto l'array
		nomiVarOut= null;
		
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

	@Override
	public Object visit(MathOp node) throws Exception {
		
		String operatore= node.getOp();
					
		switch (operatore) {
		case "AddOp":
			node.nodeList().get(0).accept(this);
			fw.write(" + ");
			node.nodeList().get(1).accept(this);
			break;
		case "DiffOp":
			node.nodeList().get(0).accept(this);
			fw.write(" - ");
			node.nodeList().get(1).accept(this);
			break;
		case "MulOp":
			node.nodeList().get(0).accept(this);
			fw.write(" * ");
			node.nodeList().get(1).accept(this);
			break;
		case "DivOp":
			node.nodeList().get(0).accept(this);
			fw.write(" / ");
			node.nodeList().get(1).accept(this);
			break;
		case "AndOp":
			node.nodeList().get(0).accept(this);
			fw.write(" && ");
			node.nodeList().get(1).accept(this);
			break;
		case "OrOp":
			node.nodeList().get(0).accept(this);
			fw.write(" || ");
			node.nodeList().get(1).accept(this);
			break;
		case "GtOp":
			node.nodeList().get(0).accept(this);
			fw.write(" > ");
			node.nodeList().get(1).accept(this);
			break;
		case "GeOp":
			node.nodeList().get(0).accept(this);
			fw.write(" >= ");
			node.nodeList().get(1).accept(this);
			break;
		case "LtOp":
			node.nodeList().get(0).accept(this);
			fw.write(" < ");
			node.nodeList().get(1).accept(this);
			break;
		case "LeOp":
			node.nodeList().get(0).accept(this);
			fw.write(" <= ");
			node.nodeList().get(1).accept(this);
			break;
		case "EqOp":
			node.nodeList().get(0).accept(this);
			fw.write(" == ");
			node.nodeList().get(1).accept(this);
			break;
			
		default:
			break;
		}
		
		return null;
	}
	
	@Override
	public Object visit(AssignOp node) throws Exception {
		
		//se ho delle variabili nell'array dei parametri out significa che potrei essere nel corpo di una funzione
		if(nomiVarOut != null) {
			//controllo se è una variabile definita come parametro di ritorno, in tal caso gli associo il puntatore
			for(EntryDefinizioneFunzione ed: nomiVarOut) {
				if(ed.getNomeVar().equals(node.getName())) {
					fw.write("*" + node.getName() + " = ");
				}else {
					fw.write(node.getName() + " = ");
				}
			}
		}else {
			fw.write(node.getName() + " = ");
		}
		
		node.nodeList().get(1).accept(this);
		fw.write("; \n");
		
		return null;
	}

	@Override
	public Object visit(UnaryOp node) throws Exception {
		
		if( node.getOp().equals("UminusOp") ) {
			fw.write("-");
			node.nodeList().get(0).accept(this);
		}
		
		if( node.getOp().equals("NotOp") ) {
			fw.write("!");
			node.nodeList().get(0).accept(this);
		}
		
		return null;
	}

	@Override
	public Object visit(ConditionalOp node) throws Exception {
		return null;
	}

	@Override
	public Object visit(IfThenOp node) throws Exception {
		
		Node expression= node.nodeList().get(0);
		Node statement= node.nodeList().get(1);
		
		fw.write("if( ");
		expression.accept(this);
		fw.write(" ){\n");
		statement.accept(this);
		fw.write("}\n");
		
		return null;
	}

	@Override
	public Object visit(IfThenElseOp node) throws Exception {
		
		Node expression= node.nodeList().get(0);
		Node statement1= node.nodeList().get(1);
		Node statement2= node.nodeList().get(2);
		
		fw.write("if( ");
		expression.accept(this);
		fw.write(" ){\n");
		statement1.accept(this);
		fw.write("}else{\n");
		statement2.accept(this);
		fw.write("}\n");
		
		return null;
	}

	@Override
	public Object visit(WhileOp node) throws Exception {
		
		Node expression= node.nodeList().get(0);
		Node statement= node.nodeList().get(1);
		
		fw.write("while( ");
		expression.accept(this);
		fw.write(" ){\n");
		statement.accept(this);
		fw.write("}\n");
		
		return null;
	}

	@Override
	public Object visit(CallOp node) throws Exception {
		
		//exprs
		OpNode exprs= (OpNode) node.nodeList().get(1);
		ArrayList<Node> expr= exprs.nodeList();
		//vars
		OpNode vars=(OpNode) node.nodeList().get(2);
		ArrayList<Node> var= vars.nodeList();

		fw.write(node.getName() + "(");;
		
		for(int i=0; i<expr.size(); i++) {
			expr.get(i).accept(this);
			fw.write(", ");
		}
		
		for(int k=0; k<var.size(); k++) {
			fw.write("&");
			var.get(k).accept(this);
			if(k < var.size()-1) {
				fw.write(", ");
			}
		}
		
		fw.write(");\n");
		
		return null;
	}

	@Override
	public Object visit(WriteOp node) throws Exception {
		
		OpNode costante= (OpNode) node.nodeList().get(0);
		ArrayList<Node> nodes= costante.nodeList();
		
		//se si tratta di una stringa costante
		if( costante.getOp().equals("OutValuesStringOp") ||  costante.getOp().equals("OutValuesExprList")) {
			Leaf stringa= (Leaf) costante.nodeList().get(0);
			fw.write("printf(\"" + stringa.getVal() + " \\n \"); \n");
		}else {
			
			for(Node n: nodes) {
				fw.write("printf(\"");
				//semaforo usato per salvare il nome della variabile e poterlo stampare dopo aver inserito la formattazione corretta
				waitForPrintf= true;
				n.accept(this);
				if( n.getType().equals("integer") || n.getType().equals("boolean")) {
					fw.write("%d \\n \", ");
				}else {
					fw.write("%lf \\n \", ");
				}
				//stampo il nome della varibile
				fw.write(saveIdentifierToPrint + ");\n");
				waitForPrintf= false;
			}
		}
		
		return null;
	}

	@Override
	public Object visit(ReadOp node) throws Exception {
		
		String toReturn="";
		ArrayList<Leaf> inputs=new ArrayList<Leaf>();
	    OpNode varOp=(OpNode) node.nodeList().get(0);
	    
	    do{
	      inputs.add((Leaf)varOp.nodeList().get(0));
	      if(varOp.nodeList().size()>1)
	        varOp=(OpNode) varOp.nodeList().get(1);
	      else
	        varOp=null;
	    }while(varOp!=null);
	    
	    //tipi
	    OpNode typOp=(OpNode) node.nodeList().get(1);
	    ArrayList<Node> types=typOp.nodeList();
	    
	    for(int i=0; i<inputs.size(); i++){
	    		if( types.get(i).getType().equals("integer") ) {
	    			toReturn= "scanf(\"%d\", &" + inputs.get(i).getVal() + ");\n";
	    		}else if(types.get(i).getType().equals("boolean")) {
	    			toReturn= "scanf(\"%d\", &" + inputs.get(i).getVal() + ");\n";
	    		}else {
	    			toReturn= "scanf(\"%lf\", &" + inputs.get(i).getVal() + ");\n";
	    		}
	    		fw.write(toReturn);
	    }

	    return null;
	}

	@Override
	public Object visit(VarDeclOp node) throws Exception {

		String type= node.getLeafType();

		if( type.equals("integer") ) {
			type= "int";
		}else if(type.equals("boolean")){
			type= "bool";
		}
		
		fw.write(type + " ");
		Node toVisit= node.getNode();
		if(toVisit != null) {
			toVisit.accept(this);
		}
		
		fw.write(";\n");
		return null;
	}

	@Override
	public Object visit(ParDeclOp node) throws Exception {
		
		node.nodeList().get(0).accept(this);
		Node toVisit= node.getNode();
		if(toVisit != null) {
			toVisit.accept(this);
		}
		
		return null;
	}

	@Override
	public Object visit(VarOp node) throws Exception {
		
		ArrayList<Node> toVisit= node.nodeList();
		if(toVisit != null) {
			for(int i=0; i<toVisit.size(); i++) {
				toVisit.get(i).accept(this);
				
				if(i < toVisit.size()-1) {
					fw.write(", ");
				}
			}
		}
		
		return null;
	}
	
}
