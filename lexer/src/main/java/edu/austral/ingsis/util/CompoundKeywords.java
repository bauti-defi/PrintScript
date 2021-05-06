package edu.austral.ingsis.util;

import edu.austral.ingsis.tokens.TokenType;
import java.util.HashMap;
import java.util.Map;

public class CompoundKeywords {
  public static Map<String, TokenType> getCompoundKeywords() {
    Map<String, TokenType> keywords = new HashMap<>();
    keywords.put("<", TokenType.LESS_THAN);
    keywords.put(">", TokenType.GREATER_THAN);
    keywords.put("=", TokenType.EQUALS);
    keywords.put(">=", TokenType.GREATER_THAN_EQUALS);
    keywords.put("<=", TokenType.LESS_THAN_EQUALS);
    keywords.put("==", TokenType.DOUBLE_EQUALS);
    keywords.put("!=", TokenType.NOT_EQUALS);

    return keywords;
  }
}
