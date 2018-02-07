package Visitor;

import SyntaxTree.*;

public interface Visitor {

	Object visit(Node node);
	Object visit(OpNode node);
	Object visit(Leaf node);
}
