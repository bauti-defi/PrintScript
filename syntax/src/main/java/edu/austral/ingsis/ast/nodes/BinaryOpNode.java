package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;

public class BinaryOpNode extends CompoundExpressionNode implements Visitable {

  public BinaryOpNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return this.token.getValue();
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
