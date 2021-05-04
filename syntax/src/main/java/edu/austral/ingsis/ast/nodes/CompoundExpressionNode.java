package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.visitor.Visitor;
import edu.austral.ingsis.tokens.Token;

public class CompoundExpressionNode extends ExpressionNode {

  private ExpressionNode left, right;

  public CompoundExpressionNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return "COMPOUND_EXPRESSION";
  }

  public void setLeft(ExpressionNode node) {
    this.left = node;
  }

  public ExpressionNode getLeft() {
    return this.left;
  }

  public void setRight(ExpressionNode node) {
    this.right = node;
  }

  public ExpressionNode getRight() {
    return this.right;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
