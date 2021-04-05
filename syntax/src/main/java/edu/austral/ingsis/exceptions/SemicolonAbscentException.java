package edu.austral.ingsis.exceptions;

import edu.austral.ingsis.tokens.Token;

public class SemicolonAbscentException extends RuntimeException{

    public SemicolonAbscentException(Token token) {
        super(token.asString() + " is illegal line ending. Semicolon required.");
    }

}
