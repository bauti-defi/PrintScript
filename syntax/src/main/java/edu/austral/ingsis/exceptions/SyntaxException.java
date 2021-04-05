package edu.austral.ingsis.exceptions;

import edu.austral.ingsis.tokens.Token;

public class SyntaxException extends RuntimeException{

    public SyntaxException(Token token){
        super("Syntax error at: " + token.toString());
    }
}
