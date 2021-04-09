package edu.austral.ingsis.ast.visitor;

import edu.austral.ingsis.ast.nodes.*;

public interface Visitor {

    void visit(AssignationNode node);

    void visit(BinaryOpNode node);

    void visit(DeclarationNode node);

    void visit(IdentifierNode node);

    void visit(TypeNode node);

    void visit(ValueLiteralNode node);

    void visit(AbstractNode node);
}
