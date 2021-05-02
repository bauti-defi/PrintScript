package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;

public class ReferenceAssignationNode extends AssignationNode<ReferenceNode> implements Visitable {

  private ReferenceNode reference;
  private ExpressionNode value;

  public ReferenceAssignationNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return "REFERENCE_ASSIGNATION";
  }

  public void setLeft(ReferenceNode node) {
    this.reference = node;
  }

  public ReferenceNode getLeft() {
    return this.reference;
  }

  @Override
  public String getIdentifier() {
    return this.reference.getIdentifier();
  }

  public void setRight(ExpressionNode node) {
    this.value = node;
  }

  public ExpressionNode getRight() {
    return this.value;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
