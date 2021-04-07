package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.tokens.Token;

public class AssignationNode extends AbstractNode {

    private DeclarationNode declaration;
    private AbstractNode value;

    public AssignationNode(Token token) {
        super(token);
    }

    public void setLeft(DeclarationNode node) {
        this.declaration = node;
    }

    public DeclarationNode getLeft() {
        return this.declaration;
    }

    public void setRight(AbstractNode node) {
        this.value = node;
    }

    public AbstractNode getRight() {
        return this.value;
    }
}
