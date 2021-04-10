package edu.austral.ingsis.ast.builders;

import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenPattern;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.exceptions.SyntaxException;
import edu.austral.ingsis.ast.exceptions.SyntaxTokenExpectedException;
import edu.austral.ingsis.ast.nodes.DeclarationNode;
import edu.austral.ingsis.ast.nodes.IdentifierNode;
import edu.austral.ingsis.ast.nodes.TypeNode;

import java.util.List;

public class DeclarationParser implements NodeParser<DeclarationNode> {

    public boolean predicate(List<Token> tokens){
        return TokenPattern.Builder.of(TokenType.LET).end().startWith(tokens) || TokenPattern.Builder.of(TokenType.CONST).end().startWith(tokens);
    }

    public DeclarationNode parse(List<Token> tokens){
        int colonIndex = getIndexOfToken(tokens, TokenType.COLON);

        //check that we have an identifier
        if(!TokenPattern.Builder.of(TokenType.LET).identifier().end().startWith(tokens)){
            throw new SyntaxTokenExpectedException(tokens.get(colonIndex - 1), TokenType.IDENTIFIER);
        }

        //Check that there is a colon
        if(!TokenPattern.Builder.of(TokenType.LET).identifier().colon().end().startWith(tokens)){
            throw new SyntaxTokenExpectedException(tokens.get(0).getLine(), TokenType.COLON);
        }

        //Check we have a type
        if(!TokenPattern.Builder.of(TokenType.LET).identifier().colon().type().end().matches(tokens)) {
            throw new SyntaxException(tokens.get(colonIndex + 1));
        }

        final DeclarationNode node = new DeclarationNode(tokens.get(colonIndex));
        final IdentifierNode identifier = new IdentifierNode(tokens.get(colonIndex - 1));
        final TypeNode type = new TypeNode(tokens.get(colonIndex + 1));

        node.setLeft(identifier);
        node.setRight(type);

        return node;
    }


}
