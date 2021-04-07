package edu.austral.ingsis.exceptions;

import edu.austral.ingsis.tokens.Token;

public class SemicolonAbsentException extends RuntimeException{

    public SemicolonAbsentException(Token token) {
        super(token.asString() + " is illegal line ending. Semicolon required.");
    }

}
