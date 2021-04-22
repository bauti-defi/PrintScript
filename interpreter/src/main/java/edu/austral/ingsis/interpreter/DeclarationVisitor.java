package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.TokenType;
import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.visitor.Visitor;

public class DeclarationVisitor implements Visitor {

  private String identifier;
  private String type;
  private boolean immutable;

  @Override
  public void visit(ReferenceAssignationNode node) {}

  @Override
  public void visit(DeclarationAssignationNode node) {}

  @Override
  public void visit(BinaryOpNode node) {}

  @Override
  public void visit(DeclarationNode node) {
    if (node.getToken().getType() == TokenType.LET) {
      this.immutable = false;
    } else if (node.getToken().getType() == TokenType.CONST) {
      this.immutable = true;
    }
    node.getLeft().accept(this);
    node.getRight().accept(this);
  }

  @Override
  public void visit(IdentifierNode node) {
    this.identifier = node.getToken().getValue();
  }

  @Override
  public void visit(TypeNode node) {
    this.type = node.getToken().getValue();
  }

  @Override
  public void visit(ValueLiteralNode node) {}

  @Override
  public void visit(ExpressionNode node) {}

  @Override
  public void visit(CompoundExpressionNode node) {}

  @Override
  public void visit(PrintNode node) {}

  @Override
  public void visit(IfStatementNode node) {}

  @Override
  public void visit(ReferenceNode node) {}

  public static Declaration process(DeclarationNode node) {
    final DeclarationVisitor visitor = new DeclarationVisitor();
    visitor.visit(node);
    return new Declaration(visitor.identifier, visitor.immutable, visitor.type);
  }
}
