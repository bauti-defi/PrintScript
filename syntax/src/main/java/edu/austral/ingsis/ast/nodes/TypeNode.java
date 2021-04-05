package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Leaf;
import edu.austral.ingsis.tokens.Token;

public class TypeNode extends AbstractNode implements Leaf {

    public TypeNode(Token token) {
        super(token);
    }
}
