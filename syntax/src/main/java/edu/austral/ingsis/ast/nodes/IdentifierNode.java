package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;

public class IdentifierNode extends AbstractNode implements Visitable {

  public IdentifierNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return "IDENTIFIER";
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
