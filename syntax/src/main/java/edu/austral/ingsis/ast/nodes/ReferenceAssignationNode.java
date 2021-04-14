package edu.austral.ingsis.ast.nodes;


import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;

public class ReferenceAssignationNode extends AbstractNode implements Visitable {

    private ReferenceNode reference;
    private ExpressionNode value;

    public ReferenceAssignationNode(Token token) {
        super(token);
    }

    public void setLeft(ReferenceNode node) {
        this.reference = node;
    }

    public ReferenceNode getLeft() {
        return this.reference;
    }

    public void setRight(ExpressionNode node) {
        this.value = node;
    }

    public ExpressionNode getRight() {
        return this.value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
