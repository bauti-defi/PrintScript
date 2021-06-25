package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.tokens.TokenType;
import lombok.SneakyThrows;

public class ExpressionEvaluator implements Evaluator<String> {

  private final Context context;

  private ExpressionEvaluator(Context context) {
    this.context = context;
  }

  public static String evaluate(ExpressionNode node, Context context) {
    final ExpressionEvaluator visitor = new ExpressionEvaluator(context);
    return visitor.visit(node);
  }

  private boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private boolean isDouble(String s) {
    try {
      Double.parseDouble(s);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private String numberToString(double i) {
    String val = String.valueOf(i);
    if (val.contains(".0")) {
      return String.valueOf(Integer.parseInt(val.split("\\.")[0]));
    } else if (isDouble(val)) {
      return String.valueOf(Double.valueOf(val));
    }
    return val;
  }

  private boolean isBoolean(String s) {
    return s.equals("true") || s.equals("false");
  }

  @SneakyThrows
  @Override
  public String visit(BinaryOpNode node) {
    String left = this.visit(node.getLeft());
    String right = this.visit(node.getRight());
    if (isDouble(left) && isDouble(right)) {
      double leftInt = Double.parseDouble(left);
      double rightInt = Double.parseDouble(right);
      switch (node.getToken().getType()) {
        case PLUS_SYMBOL:
          return numberToString(leftInt + rightInt);
        case MINUS_SYMBOL:
          return numberToString(leftInt - rightInt);
        case STAR_SYMBOL:
          return numberToString(leftInt * rightInt);
        case SLASH_SYMBOL:
          return numberToString(leftInt / rightInt);
      }
    } else if (node.getToken().getType() == TokenType.PLUS_SYMBOL
        && !isBoolean(left)
        && !isBoolean(right)) {
      return left + right;
    }
    throw new Exception("Unsupported binary operation: " + node.getToken().getType());
  }

  @Override
  public String visit(ValueLiteralNode node) {
    return node.getToken().getValue().replaceAll("\"", "");
  }

  @SneakyThrows
  @Override
  public String visit(ExpressionNode node) {
    switch (node.getNodeType()) {
      case "BINARY_EXPRESSION":
        return this.visit((BinaryOpNode) node);
      case "LOGICAL_EXPRESSION":
        return this.visit((LogicalOpNode) node);
      case "VALUE_LITERAL":
        return this.visit((ValueLiteralNode) node);
      case "REFERENCE":
        return this.visit((ReferenceNode) node);
    }
    throw new Exception("Unknown symbol: " + node.getToken().getValue());
  }

  @SneakyThrows
  @Override
  public String visit(ReferenceNode node) {
    return this.context.getValue(node.getIdentifier());
  }

  @SneakyThrows
  @Override
  public String visit(LogicalOpNode node) {
    String left = this.visit(node.getLeft());
    String right = this.visit(node.getRight());

    switch (node.getToken().getType()) {
      case NOT_EQUALS:
        return String.valueOf(!left.equals(right));
      case DOUBLE_EQUALS:
        return String.valueOf(left.equals(right));
      case LESS_THAN:
      case LESS_THAN_EQUALS:
      case GREATER_THAN:
      case GREATER_THAN_EQUALS:
        return evaluateIntegerComparison(node.getToken().getType(), left, right);
    }
    throw new Exception("Unsupported logical operation: " + node.getToken().getType());
  }

  @SneakyThrows
  private String evaluateIntegerComparison(TokenType operation, String left, String right) {
    int leftInt = Integer.parseInt(left);
    int rightInt = Integer.parseInt(right);
    switch (operation) {
      case LESS_THAN:
        return String.valueOf(leftInt < rightInt);
      case LESS_THAN_EQUALS:
        return String.valueOf(leftInt <= rightInt);
      case GREATER_THAN:
        return String.valueOf(leftInt > rightInt);
      case GREATER_THAN_EQUALS:
        return String.valueOf(leftInt >= rightInt);
    }
    throw new Exception("Unsupported logical operation: " + operation);
  }
}
