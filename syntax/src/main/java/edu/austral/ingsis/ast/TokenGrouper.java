package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.exceptions.SyntaxException;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import java.util.ArrayList;
import java.util.List;

public class TokenGrouper {

  public static List<List<Token>> group(List<Token> tokens) {
    List<List<Token>> groups = new ArrayList<>();

    int start = 0;
    while (start < tokens.size()) {
      List<Token> group = group(tokens, start);
      groups.add(group);
      start = start + group.size();
    }
    return groups;
  }

  private static List<Token> group(List<Token> tokens, int start) {
    for (int i = start; i < tokens.size(); i++) {
      if (tokens.get(start).getType() == TokenType.LET) {
        if (tokens.get(i).getType() == TokenType.SEMICOLON) {
          return tokens.subList(start, i + 1);
        }
      } else if (tokens.get(start).getType() == TokenType.IF) {
        if (tokens.get(i).getType() == TokenType.R_CURLY_BRACE) {
          if (tokens.size() >= i + 1 && tokens.get(i + 1).getType() == TokenType.ELSE) {
            continue;
          } else {
            return tokens.subList(start, i + 1);
          }
        }
      }
    }
    throw new SyntaxException();
  }
}
