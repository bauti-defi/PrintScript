package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;

public class TypeNode extends AbstractNode implements Visitable {

  public TypeNode(Token token) {
    super(token);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
