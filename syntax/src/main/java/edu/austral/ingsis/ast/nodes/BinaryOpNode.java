package edu.austral.ingsis.ast.nodes;


import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;

public class BinaryOpNode extends AbstractNode implements Visitable {

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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
