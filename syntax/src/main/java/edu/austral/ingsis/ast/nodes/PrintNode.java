package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;

public class PrintNode extends AbstractNode implements Visitable {

  private ExpressionNode args;

  public PrintNode(Token token) {
    super(token);
  }

  public void setArgs(ExpressionNode args) {
    this.args = args;
  }

  public ExpressionNode getArgs() {
    return args;
  }

  @Override
  public void accept(Visitor visitor) {}
}
