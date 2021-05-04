package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.visitor.Visitor;
import edu.austral.ingsis.tokens.TokenType;
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
          .setValue(node.getIdentifier(), ExpressionEvaluator.evaluate(node.getRight(), context));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void visit(DeclarationAssignationNode node) {
    this.visit(node.getLeft());

    try {
      context
          .getVariables()
          .setValue(node.getIdentifier(), ExpressionEvaluator.evaluate(node.getRight(), context));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void visit(BinaryOpNode node) {}

  @Override
  public void visit(DeclarationNode node) {
    boolean immutable = node.getToken().getType() == TokenType.CONST;
    String identifier = node.getLeft().getToken().getValue();
    String type = node.getRight().getToken().getValue();

    try {
      context.getVariables().insertDeclaration(new Declaration(identifier, immutable, type));
    } catch (Exception e) {
      e.printStackTrace();
    }
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
  public void visit(PrintNode node) {
    System.out.println(ExpressionEvaluator.evaluate(node.getArgs(), context));
  }

  @Override
  public void visit(IfStatementNode node) {}

  @Override
  public void visit(ReferenceNode node) {}

  public static ASTVisitor create(Context context) {
    return new ASTVisitor(context);
  }
}
