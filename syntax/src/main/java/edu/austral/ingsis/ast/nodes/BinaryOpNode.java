package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Joint;
import edu.austral.ingsis.tokens.SyntaxToken;

public class BinaryOpNode extends AbstractNode implements Joint<ValueLiteralNode, ValueLiteralNode> {

    private ValueLiteralNode left, right;

    public BinaryOpNode(SyntaxToken token) {
        super(token);
    }

    @Override
    public void setLeft(ValueLiteralNode node) {
        this.left = node;
    }

    @Override
    public ValueLiteralNode getLeft() {
        return this.left;
    }

    @Override
    public void setRight(ValueLiteralNode node) {
        this.right = node;
    }

    @Override
    public ValueLiteralNode getRight() {
        return this.right;
    }

}
