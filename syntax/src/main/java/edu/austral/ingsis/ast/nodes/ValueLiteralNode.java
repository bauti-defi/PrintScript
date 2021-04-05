package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Node;
import edu.austral.ingsis.tokens.Token;

public class ValueLiteralNode extends AbstractNode implements Node {

    public ValueLiteralNode(Token token) {
        super(token);
    }
}
