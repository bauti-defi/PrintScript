package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;

public class LogicalOpNode extends CompoundExpressionNode implements Visitable {

  public LogicalOpNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return "LOGICAL";
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
