package edu.austral.ingsis.ast.visitor;

import edu.austral.ingsis.ast.nodes.ReferenceNode;
import edu.austral.ingsis.ast.nodes.ValueLiteralNode;

public interface ValueVisitor<T> {

    T visit(ReferenceNode node);

    T visit(ValueLiteralNode node);

}
