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

  public void visit(CodeBlock codeBlock) {
    codeBlock.getNodes().forEach(node -> execute(node));
  }

  private void execute(AbstractNode node) {
    switch (node.getNodeType()) {
      case "DECLARATION_ASSIGNATION":
        this.visit((DeclarationAssignationNode) node);
        break;
      case "REFERENCE_ASSIGNATION":
        this.visit((ReferenceAssignationNode) node);
        break;
      case "PRINTLN":
        this.visit((PrintlnNode) node);
        break;
      case "IF":
        this.visit((IfStatementNode) node);
        break;
    }
  }

  @SneakyThrows
  @Override
  public void visit(ReferenceAssignationNode node) {
    try {
      context.setValue(
          node.getIdentifier(), ExpressionEvaluator.evaluate(node.getRight(), context));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void visit(DeclarationAssignationNode node) {
    this.visit(node.getLeft());

    try {
      context.setValue(
          node.getIdentifier(), ExpressionEvaluator.evaluate(node.getRight(), context));
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
    String type = node.getRight().getToken().getValue().toLowerCase();

    try {
      context.insertDeclaration(new Declaration(identifier, immutable, type));
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
  public void visit(PrintlnNode node) {
    System.out.println(ExpressionEvaluator.evaluate(node.getArgs(), context));
  }

  @Override
  public void visit(IfStatementNode node) {
    boolean predicate =
        Boolean.valueOf(ExpressionEvaluator.evaluate(node.getExpression(), context));
    if (predicate && node.getIfBlock() != null) {
      Interpreter.interpret(node.getIfBlock(), context);
    } else if (node.getElseBlock() != null) {
      Interpreter.interpret(node.getElseBlock(), context);
    }
  }

  @Override
  public void visit(ReferenceNode node) {}

  public static ASTVisitor create(Context context) {
    return new ASTVisitor(context);
  }
}
