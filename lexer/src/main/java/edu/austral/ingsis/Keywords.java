package edu.austral.ingsis;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class Keywords {

  public static Map<String, TokenType> getKeyword() {
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

    return keywords;
  }
}
