package edu.austral.ingsis.ast.builders;

import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.exceptions.VariableUndefinedException;
import edu.austral.ingsis.ast.nodes.ReferenceNode;

import java.util.List;
import java.util.stream.Collectors;

public class ReferenceBuilder implements NodeBuilder<ReferenceNode> {

    public boolean predicate(List<Token> tokens){
        return startsWith(tokens, TokenType.IDENTIFIER);
    }

    public ReferenceNode build(List<Token> tokens){
        return new ReferenceNode(tokens.get(0));
    }

}
