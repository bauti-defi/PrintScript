package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.visitor.Visitor;

public class ExpressionVisitor implements Visitor {

  private final Context context;

  private ExpressionVisitor(Context context) {
    this.context = context;
  }

  @Override
  public void visit(ReferenceAssignationNode node) {}

  @Override
  public void visit(DeclarationAssignationNode node) {}

  @Override
  public void visit(BinaryOpNode node) {}

  @Override
  public void visit(DeclarationNode node) {}

  @Override
  public void visit(IdentifierNode node) {}

  @Override
  public void visit(TypeNode node) {}

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

  public static String process(ExpressionNode node, Context context) {
    final ExpressionVisitor visitor = new ExpressionVisitor(context);
    visitor.visit(node);
    return "";
  }
}
