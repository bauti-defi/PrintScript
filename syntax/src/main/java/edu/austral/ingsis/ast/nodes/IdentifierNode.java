package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Leaf;
import edu.austral.ingsis.tokens.SyntaxToken;

public class IdentifierNode extends AbstractNode implements Leaf {

    public IdentifierNode(SyntaxToken token) {
        super(token);
    }
}
