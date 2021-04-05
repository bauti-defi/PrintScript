package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Joint;
import edu.austral.ingsis.tokens.Token;

public class AssignationNode extends AbstractNode implements Joint<DeclarationNode, ValueLiteralNode> {

    private DeclarationNode declaration;
    private ValueLiteralNode value;

    public AssignationNode(Token token) {
        super(token);
    }

    @Override
    public void setLeft(DeclarationNode node) {
        this.declaration = node;
    }

    @Override
    public DeclarationNode getLeft() {
        return this.declaration;
    }

    @Override
    public void setRight(ValueLiteralNode node) {
        this.value = node;
    }

    @Override
    public ValueLiteralNode getRight() {
        return this.value;
    }
}
