package edu.austral.ingsis.ast.exceptions;

import edu.austral.ingsis.tokens.Token;

public class SemicolonAbsentException extends RuntimeException {

  public SemicolonAbsentException(Token token) {
    super(token.toString() + " is illegal line ending. Semicolon required.");
  }
}
