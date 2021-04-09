package edu.austral.ingsis.ast.builders;

import edu.austral.ingsis.ast.DeclarationTable;
import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.exceptions.VariableUndefinedException;
import edu.austral.ingsis.ast.nodes.DeclarationNode;

import java.util.List;
import java.util.stream.Collectors;

public class ReferenceBuilder implements NodeBuilder<DeclarationNode> {

    public boolean predicate(List<Token> tokens, DeclarationTable declarations){
        final List<Token> unknownIdentifiers = tokens.stream().filter(token -> isTokenType(token, TokenType.IDENTIFIER)).filter(token -> !declarations.contains(token.getValue())).collect(Collectors.toList());

        if(!unknownIdentifiers.isEmpty()){
            unknownIdentifiers.forEach(VariableUndefinedException::new);
        }

        return startsWith(tokens, TokenType.IDENTIFIER);
    }

    public DeclarationNode build(List<Token> tokens, DeclarationTable declarations){
        final String identifier = tokens.get(0).getValue();

        return declarations.get(identifier);
    }

}
