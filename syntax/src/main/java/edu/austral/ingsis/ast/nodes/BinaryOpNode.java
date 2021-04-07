package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.tokens.Token;

public class BinaryOpNode extends AbstractNode{

    private AbstractNode left, right;

    public BinaryOpNode(Token token) {
        super(token);
    }

    public void setLeft(AbstractNode node) {
        this.left = node;
    }

    public AbstractNode getLeft() {
        return this.left;
    }

    public void setRight(AbstractNode node) {
        this.right = node;
    }

    public AbstractNode getRight() {
        return this.right;
    }

}
