package edu.austral.ingsis.ast.builders;

import edu.austral.ingsis.ast.DeclarationTable;
import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenHelper;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.exceptions.SyntaxException;
import edu.austral.ingsis.ast.exceptions.SyntaxTokenExpectedException;
import edu.austral.ingsis.ast.nodes.DeclarationNode;
import edu.austral.ingsis.ast.nodes.IdentifierNode;
import edu.austral.ingsis.ast.nodes.TypeNode;

import java.util.List;

public class DeclarationBuilder implements NodeBuilder<DeclarationNode> {

    public boolean predicate(List<Token> tokens, DeclarationTable declarations){
        return startsWith(tokens, TokenType.LET);
    }

    public DeclarationNode build(List<Token> tokens, DeclarationTable table){
        int colonIndex = getIndexOfToken(tokens, TokenType.COLON);

        //Check that there is a colon
        if(colonIndex == -1){
            throw new SyntaxTokenExpectedException(tokens.get(0).getLine(), TokenType.COLON);
        }

        //check that we have an identifier
        if(tokens.get(colonIndex - 1).getType() != TokenType.IDENTIFIER){
            throw new SyntaxTokenExpectedException(tokens.get(colonIndex - 1), TokenType.IDENTIFIER);
        }

        //Check we have a type
        if(tokens.get(colonIndex + 1).getType() != TokenType.STRING_TYPE && tokens.get(colonIndex + 1).getType() != TokenType.NUMBER_TYPE) {
            throw new SyntaxException(tokens.get(colonIndex + 1));
        }

        final DeclarationNode node = new DeclarationNode(tokens.get(0));
        final IdentifierNode identifier = new IdentifierNode(tokens.get(colonIndex - 1));
        final TypeNode type = new TypeNode(tokens.get(colonIndex + 1));

        node.setLeft(identifier);
        node.setRight(type);

        table.put(identifier.getToken().getValue(), node);
        return node;
    }


}
