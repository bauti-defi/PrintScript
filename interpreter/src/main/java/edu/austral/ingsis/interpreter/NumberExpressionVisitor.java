package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.visitor.ExpressionVisitor;
import lombok.SneakyThrows;

public class NumberExpressionVisitor implements ExpressionVisitor<Integer> {

  private final Context context;

  private NumberExpressionVisitor(Context context) {
    this.context = context;
  }

  public static int process(ExpressionNode node, Context context) {
    final NumberExpressionVisitor visitor = new NumberExpressionVisitor(context);
    return visitor.visit(node);
  }

  @Override
  public Integer visit(BinaryOpNode node) {
    switch (node.getToken().getType()) {
      case PLUS_SYMBOL:
        return this.visit(node.getLeft()) + this.visit(node.getRight());
      case MINUS_SYMBOL:
        return this.visit(node.getLeft()) - this.visit(node.getRight());
      case STAR_SYMBOL:
        return this.visit(node.getLeft()) * this.visit(node.getRight());
      case SLASH_SYMBOL:
        return this.visit(node.getLeft()) / this.visit(node.getRight());
    }
    return null;
  }

  @Override
  public String visit(ValueLiteralNode node) {
    return node.getToken().getValue();
  }

  @SneakyThrows
  @Override
  public Integer visit(ExpressionNode node) {
    switch (node.getNodeType()) {
      case "BINARY_EXPRESSION":
        return this.visit((BinaryOpNode) node);
      case "VALUE_LITERAL":
        return Integer.valueOf(this.visit((ValueLiteralNode) node));
      case "REFERENCE":
        return Integer.valueOf(this.visit((ReferenceNode) node));
    }
    throw new Exception("Unknown symbol: " + node.getToken().getValue());
  }

  @SneakyThrows
  @Override
  public String visit(ReferenceNode node) {
    return this.context.getVariables().getValue(node.getIdentifier());
  }

  @Override
  public boolean visit(LogicalOpNode node) {
    return false;
  }
}
