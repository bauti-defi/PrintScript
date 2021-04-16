package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;

public class DeclarationNode extends AbstractNode implements Visitable {

  private IdentifierNode identifier;
  private TypeNode type;

  public DeclarationNode(Token token) {
    super(token);
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
