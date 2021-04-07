package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.tokens.Token;

public class DeclarationNode extends AbstractNode {

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
}
