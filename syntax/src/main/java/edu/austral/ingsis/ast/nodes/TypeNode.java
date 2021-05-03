package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;
import edu.austral.ingsis.tokens.Token;

public class TypeNode extends AbstractNode implements Visitable {

  public TypeNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return "TYPE";
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
