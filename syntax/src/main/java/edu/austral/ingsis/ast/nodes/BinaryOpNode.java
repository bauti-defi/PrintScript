package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Joint;
import edu.austral.ingsis.ast.Node;
import edu.austral.ingsis.tokens.Token;

public class BinaryOpNode extends AbstractNode implements Joint<Node, Node> {

    private Node left, right;

    public BinaryOpNode(Token token) {
        super(token);
    }

    @Override
    public void setLeft(Node node) {
        this.left = node;
    }

    @Override
    public Node getLeft() {
        return this.left;
    }

    @Override
    public void setRight(Node node) {
        this.right = node;
    }

    @Override
    public Node getRight() {
        return this.right;
    }

}
