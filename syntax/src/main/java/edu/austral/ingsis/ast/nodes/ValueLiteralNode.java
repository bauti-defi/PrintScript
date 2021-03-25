package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Node;
import edu.austral.ingsis.tokens.SyntaxToken;

public class ValueLiteralNode extends AbstractNode implements Node {

    public ValueLiteralNode(SyntaxToken token) {
        super(token);
    }
}
