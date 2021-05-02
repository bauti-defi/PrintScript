package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;

public class IfStatementNode extends AbstractNode implements Visitable {

  private ExpressionNode left;
  private AST body;

  public IfStatementNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return "IF";
  }

  public ExpressionNode getLeft() {
    return left;
  }

  public void setBody(AST ast) {
    this.body = ast;
  }

  public AST getBody() {
    return body;
  }

  public void setLeft(ExpressionNode left) {
    this.left = left;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
