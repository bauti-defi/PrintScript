package edu.austral.ingsis.util;

import edu.austral.ingsis.tokens.TokenType;
import java.util.HashMap;
import java.util.Map;

public class WordsToken {

  public static Map<String, TokenType> getWords1_0() {
    Map<String, TokenType> keywords = new HashMap<>();
    keywords.put("let", TokenType.LET);
    keywords.put("const", TokenType.CONST);
    keywords.put("number", TokenType.TYPE);
    keywords.put("string", TokenType.TYPE);
    keywords.put("println", TokenType.PRINTLN);

    return keywords;
  }

  public static Map<String, TokenType> getWords1_1() {
    Map<String, TokenType> keywords = new HashMap<>();
    keywords.put("let", TokenType.LET);
    keywords.put("const", TokenType.CONST);
    keywords.put("number", TokenType.TYPE);
    keywords.put("string", TokenType.TYPE);
    keywords.put("println", TokenType.PRINTLN);
    keywords.put("boolean", TokenType.TYPE);
    keywords.put("true", TokenType.LITERAL);
    keywords.put("false", TokenType.LITERAL);

    return keywords;
  }
}
