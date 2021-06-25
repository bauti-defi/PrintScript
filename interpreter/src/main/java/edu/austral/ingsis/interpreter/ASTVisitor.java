package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.ast.exceptions.SyntaxException;
import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.visitor.Visitor;
import edu.austral.ingsis.tokens.TokenType;
import java.util.function.Consumer;
import lombok.SneakyThrows;

public class ASTVisitor implements Visitor {

  private Consumer<String> stdOut;

  private final Context context;

  private ASTVisitor(Context context) {
    this.context = context;
  }

  private ASTVisitor(Context context, Consumer<String> stdOut) {
    this.context = context;
    this.stdOut = stdOut;
  }

  public void visit(CodeBlock codeBlock) {
    for (AbstractNode node: codeBlock.getNodes()) {
      execute(node);
    }
  }

  private void execute(AbstractNode node) {
    switch (node.getNodeType()) {
      case "DECLARATION_ASSIGNATION":
        this.visit((DeclarationAssignationNode) node);
        break;
      case "REFERENCE_ASSIGNATION":
        this.visit((ReferenceAssignationNode) node);
        break;
      case "DECLARATION":
        this.visit((DeclarationNode) node);
        break;
      case "PRINTLN":
        this.visit((PrintlnNode) node);
        break;
      case "IF":
        this.visit((IfStatementNode) node);
        break;
    }
  }

  @Override
  public void visit(ReferenceAssignationNode node) {
      context.setValue(
          node.getIdentifier(), ExpressionEvaluator.evaluate(node.getRight(), context));
  }

  @Override
  public void visit(DeclarationAssignationNode node) {
    this.visit(node.getLeft());

      context.setValue(
          node.getIdentifier(), ExpressionEvaluator.evaluate(node.getRight(), context));

  }

  @Override
  public void visit(BinaryOpNode node) {}

  @Override
  public void visit(DeclarationNode node) {
    boolean immutable = node.getToken().getType() == TokenType.CONST;
    String identifier = node.getLeft().getToken().getValue();
    String type = node.getRight().getToken().getValue().toLowerCase();

    context.insertDeclaration(new Declaration(identifier, immutable, type));

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
    stdOut.accept(ExpressionEvaluator.evaluate(node.getArgs(), context));
  }

  @Override
  public void visit(IfStatementNode node) {
    String result = ExpressionEvaluator.evaluate(node.getExpression(), context);
    if (result.equals("true") || result.equals("false")) {
      boolean predicate = Boolean.parseBoolean(result);
      if (predicate && node.getIfBlock() != null) {
        Interpreter.interpret(node.getIfBlock(), context, stdOut);
      } else if (node.getElseBlock() != null) {
        Interpreter.interpret(node.getElseBlock(), context, stdOut);
      }
    } else {
      throw new SyntaxException("only booleans allowed in if statements");
    }
  }

  @Override
  public void visit(ReferenceNode node) {}

  public static ASTVisitor create(Context context, Consumer<String> stdOut) {
    return new ASTVisitor(context, stdOut);
  }
}
