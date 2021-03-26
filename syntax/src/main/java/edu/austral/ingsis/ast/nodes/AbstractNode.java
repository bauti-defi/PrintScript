package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Node;
import edu.austral.ingsis.tokens.SyntaxToken;

public abstract class AbstractNode implements Node {

    protected final SyntaxToken token;

    AbstractNode(SyntaxToken token) {
        this.token = token;
    }

    @Override
    public SyntaxToken getToken() {
        return this.token;
    }
}
