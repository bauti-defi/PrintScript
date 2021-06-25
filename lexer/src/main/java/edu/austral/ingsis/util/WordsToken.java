package edu.austral.ingsis.util;

import edu.austral.ingsis.tokens.TokenType;
import java.util.HashMap;
import java.util.Map;

public class WordsToken {

  public static Map<String, TokenType> getWords1_0() {
    Map<String, TokenType> keywords = new HashMap<>();
    keywords.put("let", TokenType.LET);
    keywords.put("number", TokenType.TYPE);
    keywords.put("string", TokenType.TYPE);
    keywords.put("println", TokenType.PRINTLN);
    keywords.put("=", TokenType.EQUALS);
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
    keywords.put("if", TokenType.IF);
    keywords.put("else", TokenType.ELSE);
    keywords.put("=", TokenType.EQUALS);
    keywords.put("==", TokenType.DOUBLE_EQUALS);
    keywords.put(">", TokenType.GREATER_THAN);
    keywords.put("<", TokenType.LESS_THAN);
    keywords.put(">=", TokenType.GREATER_THAN_EQUALS);
    keywords.put("<=", TokenType.LESS_THAN_EQUALS);

    return keywords;
  }
}
