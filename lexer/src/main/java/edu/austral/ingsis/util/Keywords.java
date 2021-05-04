package edu.austral.ingsis.util;

import edu.austral.ingsis.tokens.TokenType;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class Keywords {

  public static Map<String, TokenType> getKeyword1_0() {
    Map<String, TokenType> keywords = new HashMap<>();
    keywords.put("let", TokenType.LET);
    keywords.put("const", TokenType.CONST);
    keywords.put(":", TokenType.COLON);
    keywords.put("=", TokenType.EQUALS);
    keywords.put(";", TokenType.SEMICOLON);
    keywords.put("number", TokenType.TYPE);
    keywords.put("string", TokenType.TYPE);
    keywords.put("+", TokenType.PLUS_SYMBOL);
    keywords.put("-", TokenType.MINUS_SYMBOL);
    keywords.put("*", TokenType.STAR_SYMBOL);
    keywords.put("/", TokenType.SLASH_SYMBOL);
    keywords.put("println", TokenType.PRINTLN);
    keywords.put("(", TokenType.L_PARENTHESES);
    keywords.put(")", TokenType.R_PARENTHESES);

    return keywords;
  }

  public static Map<String, TokenType> getKeyword1_1() {
    Map<String, TokenType> keywords = new HashMap<>();
    keywords.put("let", TokenType.LET);
    keywords.put("const", TokenType.CONST);
    keywords.put(":", TokenType.COLON);
    keywords.put("=", TokenType.EQUALS);
    keywords.put(";", TokenType.SEMICOLON);
    keywords.put("number", TokenType.TYPE);
    keywords.put("string", TokenType.TYPE);
    keywords.put("+", TokenType.PLUS_SYMBOL);
    keywords.put("-", TokenType.MINUS_SYMBOL);
    keywords.put("*", TokenType.STAR_SYMBOL);
    keywords.put("/", TokenType.SLASH_SYMBOL);
    keywords.put("println", TokenType.PRINTLN);
    keywords.put("boolean", TokenType.TYPE);
    keywords.put("true", TokenType.LITERAL);
    keywords.put("false", TokenType.LITERAL);
    keywords.put("(", TokenType.L_PARENTHESES);
    keywords.put(")", TokenType.R_PARENTHESES);
    keywords.put("if", TokenType.IF);
    keywords.put("else", TokenType.ELSE);
    keywords.put("{", TokenType.L_CURLY_BRACE);
    keywords.put("}", TokenType.R_CURLY_BRACE);
    return keywords;
  }
}
