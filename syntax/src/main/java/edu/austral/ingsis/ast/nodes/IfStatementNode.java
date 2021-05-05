package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;
import edu.austral.ingsis.tokens.Token;

public class IfStatementNode extends AbstractNode implements Visitable {

  private ExpressionNode left;
  private AST ifBlock;
  private AST elseBlock;

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

  public void setIfBlock(AST ast) {
    this.ifBlock = ast;
  }

  public AST getIfBlock() {
    return this.ifBlock;
  }

  public void setElseBlock(AST elseBlock) {
    this.elseBlock = elseBlock;
  }

  public AST getElseBlock() {
    return elseBlock;
  }

  public void setLeft(ExpressionNode left) {
    this.left = left;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
