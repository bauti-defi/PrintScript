package edu.austral.ingsis.ast.nodes;


import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;

public abstract class AbstractNode {

    protected final Token token;

    AbstractNode(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return this.token;
    }

}
