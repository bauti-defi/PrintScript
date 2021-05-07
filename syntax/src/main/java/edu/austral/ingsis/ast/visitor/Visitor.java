package edu.austral.ingsis.ast.visitor;

import edu.austral.ingsis.ast.nodes.*;

public interface Visitor {

  void visit(ReferenceAssignationNode node);

  void visit(DeclarationAssignationNode node);

  void visit(BinaryOpNode node);

  void visit(DeclarationNode node);

  void visit(IdentifierNode node);

  void visit(TypeNode node);

  void visit(ValueLiteralNode node);

  void visit(ExpressionNode node);

  void visit(CompoundExpressionNode node);

  void visit(PrintlnNode node);

  void visit(IfStatementNode node);

  void visit(ReferenceNode node);
}
