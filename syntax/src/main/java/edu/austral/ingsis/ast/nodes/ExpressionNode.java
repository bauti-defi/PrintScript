package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;
import edu.austral.ingsis.tokens.Token;

public class ExpressionNode extends AbstractNode implements Visitable {

  public ExpressionNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return "EXPRESSION";
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
