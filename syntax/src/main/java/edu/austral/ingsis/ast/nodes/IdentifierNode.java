package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Leaf;
import edu.austral.ingsis.tokens.Token;

public class IdentifierNode extends AbstractNode implements Leaf {

    public IdentifierNode(Token token) {
        super(token);
    }
}
