package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;
import edu.austral.ingsis.tokens.Token;

public class DeclarationAssignationNode extends AssignationNode<DeclarationNode>
    implements Visitable {

  public DeclarationAssignationNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return "DECLARATION_ASSIGNATION";
  }


  @Override
  public String getIdentifier() {
    return this.left.getLeft().getToken().getValue();
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
