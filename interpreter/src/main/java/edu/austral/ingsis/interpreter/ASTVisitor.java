package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.visitor.Visitor;
import lombok.SneakyThrows;

public class ASTVisitor implements Visitor {

  private final Context context;

  private ASTVisitor(Context context) {
    this.context = context;
  }

  @SneakyThrows
  @Override
  public void visit(ReferenceAssignationNode node) {
    try {
      context
          .getVariables()
          .setValue(
              node.getLeft().getIdentifier(),
              ExpressionEvaluator.evaluate(node.getRight(), context));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void visit(DeclarationAssignationNode node) {
    final Declaration declaration = DeclarationVisitor.process(node.getLeft());
    try {
      context.getVariables().insertDeclaration(declaration);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      context
          .getVariables()
          .setValue(
              declaration.getIdentifier(), ExpressionEvaluator.evaluate(node.getRight(), context));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void visit(BinaryOpNode node) {}

  @Override
  public void visit(DeclarationNode node) {
    DeclarationVisitor.process(node);
  }

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

  @SneakyThrows
  @Override
  public void visit(ReferenceNode node) {}

  public static ASTVisitor create(Context context) {
    return new ASTVisitor(context);
  }
}
