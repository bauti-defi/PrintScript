package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Joint;
import edu.austral.ingsis.ast.Node;
import edu.austral.ingsis.tokens.Token;

public class AssignationNode extends AbstractNode implements Joint<DeclarationNode, Node> {

    private DeclarationNode declaration;
    private Node value;

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
    public void setRight(Node node) {
        this.value = node;
    }

    @Override
    public Node getRight() {
        return this.value;
    }
}
