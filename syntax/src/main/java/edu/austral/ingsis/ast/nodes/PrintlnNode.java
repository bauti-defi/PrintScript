package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;
import edu.austral.ingsis.tokens.Token;

public class PrintlnNode extends AbstractNode implements Visitable {

  private ExpressionNode args;

  public PrintlnNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return "PRINT";
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
