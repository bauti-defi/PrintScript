package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.ast.visitor.Visitable;

public abstract class AssignationNode<K extends AbstractNode> extends AbstractNode
    implements Visitable {

  private K left;
  private ExpressionNode value;

  public AssignationNode(Token token) {
    super(token);
  }

  public void setLeft(K node) {
    this.left = node;
  }

  public K getLeft() {
    return this.left;
  }

  public abstract String getIdentifier();

  public void setRight(ExpressionNode node) {
    this.value = node;
  }

  public ExpressionNode getRight() {
    return this.value;
  }
}
