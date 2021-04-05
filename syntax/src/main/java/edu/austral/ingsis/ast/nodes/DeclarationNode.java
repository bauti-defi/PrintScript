package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Joint;
import edu.austral.ingsis.tokens.Token;

public class DeclarationNode extends AbstractNode implements Joint<IdentifierNode, TypeNode> {

    private IdentifierNode identifier;
    private TypeNode type;

    public DeclarationNode(Token token) {
        super(token);
    }

    @Override
    public void setLeft(IdentifierNode node) {
        this.identifier = node;
    }

    @Override
    public IdentifierNode getLeft() {
        return this.identifier;
    }

    @Override
    public void setRight(TypeNode node) {
        this.type = node;
    }

    @Override
    public TypeNode getRight() {
        return this.type;
    }
}
