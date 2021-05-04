package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;
import edu.austral.ingsis.tokens.Token;

public class LogicalOpNode extends CompoundExpressionNode implements Visitable {

  public LogicalOpNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return "LOGICAL_EXPRESSION";
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
