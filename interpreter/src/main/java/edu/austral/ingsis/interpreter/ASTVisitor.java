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
    String value = null;
    switch (context.getVariables().getDeclaration(node.getLeft().getIdentifier()).getType()) {
      case "number":
        value = String.valueOf(NumberExpressionVisitor.process(node.getRight(), context));
        break;
    }

    try {
      context.getVariables().setValue(node.getLeft().getIdentifier(), value);
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

    String value = null;
    switch (declaration.getType()) {
      case "number":
        value = String.valueOf(NumberExpressionVisitor.process(node.getRight(), context));
        break;
      case "string":
        value = StringExpressionVisitor.process(node.getRight(), context);
        break;
    }

    try {
      context.getVariables().setValue(declaration.getIdentifier(), value);
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
  public void visit(ReferenceNode node) {
    System.out.println("Reference");
  }

  public static ASTVisitor create(Context context) {
    return new ASTVisitor(context);
  }
}
