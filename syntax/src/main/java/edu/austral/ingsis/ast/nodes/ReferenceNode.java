package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;
import edu.austral.ingsis.tokens.Token;

public class ReferenceNode extends ExpressionNode implements Visitable {

  public ReferenceNode(Token token) {
    super(token);
  }

  public String getIdentifier() {
    return token.getValue();
  }

  @Override
  public String getNodeType() {
    return "REFERENCE";
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
