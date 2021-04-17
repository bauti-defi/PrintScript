package edu.austral.ingsis.ast.exceptions;

import edu.austral.ingsis.Token;

public class SyntaxException extends RuntimeException {

  public SyntaxException(Token token) {
    super("Syntax error at: " + token.toString());
  }

  public SyntaxException() {
    super("Unknown Syntax error ");
  }
}
