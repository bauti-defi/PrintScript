package edu.austral.ingsis.ast.exceptions;

import edu.austral.ingsis.tokens.Token;

public class SyntaxException extends RuntimeException {

  public SyntaxException(Token token) {
    super("Syntax error at: " + token.toString());
  }

  public SyntaxException(String i) {
      super("Unknown Syntax error " + i);
  }
}
