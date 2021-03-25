package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Leaf;
import edu.austral.ingsis.tokens.SyntaxToken;

public class TypeNode extends AbstractNode implements Leaf {

    public TypeNode(SyntaxToken token) {
        super(token);
    }
}
