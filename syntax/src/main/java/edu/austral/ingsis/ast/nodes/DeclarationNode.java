package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;
import edu.austral.ingsis.tokens.Token;

public class DeclarationNode extends AbstractNode implements Visitable {

  private IdentifierNode identifier;
  private TypeNode type;

  public DeclarationNode(Token token) {
    super(token);
  }

  @Override
  public String getNodeType() {
    return "DECLARATION";
  }

  public void setLeft(IdentifierNode node) {
    this.identifier = node;
  }

  public IdentifierNode getLeft() {
    return this.identifier;
  }

  public void setRight(TypeNode node) {
    this.type = node;
  }

  public TypeNode getRight() {
    return this.type;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
