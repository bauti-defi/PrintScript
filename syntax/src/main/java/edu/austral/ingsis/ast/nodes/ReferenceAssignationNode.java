package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;
import edu.austral.ingsis.tokens.Token;

public class ReferenceAssignationNode extends AssignationNode<ReferenceNode> implements Visitable {

  public ReferenceAssignationNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return "REFERENCE_ASSIGNATION";
  }

  public ReferenceNode getLeft() {
    return this.left;
  }

  @Override
  public String getIdentifier() {
    return this.left.getIdentifier();
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
