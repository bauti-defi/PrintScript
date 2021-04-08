package edu.austral.ingsis.ast.builders;

import edu.austral.ingsis.ast.DeclarationTable;
import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.nodes.DeclarationNode;

import java.util.List;

public class ReferenceBuilder implements NodeBuilder<DeclarationNode> {

    public boolean predicate(List<Token> tokens, DeclarationTable declarations){
        return startsWith(tokens, TokenType.IDENTIFIER);
    }

    public DeclarationNode build(List<Token> tokens, DeclarationTable declarations){
        final String identifier = tokens.get(0).getValue();

        return declarations.get(identifier);
    }

}
