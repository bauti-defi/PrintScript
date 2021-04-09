package edu.austral.ingsis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.Test;

class LexerTest {

  static Lexer lexer = new Lexer();

  @Test
  public void simpleStatementTest() {
    List<String> str = new ArrayList<>();
    str.add("let x : number = 5 ;");
    List<Token> tokens = lexer.tokenize(str);
    List<Token> expectedToken =
        Arrays.asList(
            Token.builder().tokenType(TokenType.LET).value("let").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.IDENTIFIER).value("x").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.COLON).value(":").index(1).line(1).build(),
            Token.builder()
                .tokenType(TokenType.NUMBER_TYPE)
                .value("number")
                .index(1)
                .line(1)
                .build(),
            Token.builder().tokenType(TokenType.EQUALS).value("=").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.NUMBER_LITERAL).value("5").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.SEMICOLON).value(";").index(1).line(1).build());

    assertEquals(expectedToken, tokens);
  }

  @Test
  public void twoLinesStatementTest() {
    List<String> str = new ArrayList<>();
    str.add("let x : number = 5 ;");
    str.add("let a : string = 6 ;");
    List<Token> tokens = lexer.tokenize(str);
    List<Token> expectedToken =
        Arrays.asList(
            Token.builder().tokenType(TokenType.LET).value("let").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.IDENTIFIER).value("x").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.COLON).value(":").index(1).line(1).build(),
            Token.builder()
                .tokenType(TokenType.NUMBER_TYPE)
                .value("number")
                .index(1)
                .line(1)
                .build(),
            Token.builder().tokenType(TokenType.EQUALS).value("=").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.NUMBER_LITERAL).value("5").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.SEMICOLON).value(";").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.LET).value("let").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.IDENTIFIER).value("a").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.COLON).value(":").index(1).line(1).build(),
            Token.builder()
                .tokenType(TokenType.STRING_TYPE)
                .value("string")
                .index(1)
                .line(1)
                .build(),
            Token.builder().tokenType(TokenType.EQUALS).value("=").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.NUMBER_LITERAL).value("6").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.SEMICOLON).value(";").index(1).line(1).build());
    assertEquals(expectedToken, tokens);
  }

  @Test
  public void twoLinesStatementTestWithMathOperator() {
    List<String> str = new ArrayList<>();
    str.add("let x : number = 5 ;");
    str.add("a + 6 ;");
    List<Token> tokens = lexer.tokenize(str);
    List<Token> expectedToken =
        Arrays.asList(
            Token.builder().tokenType(TokenType.LET).value("let").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.IDENTIFIER).value("x").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.COLON).value(":").index(1).line(1).build(),
            Token.builder()
                .tokenType(TokenType.NUMBER_TYPE)
                .value("number")
                .index(1)
                .line(1)
                .build(),
            Token.builder().tokenType(TokenType.EQUALS).value("=").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.NUMBER_LITERAL).value("5").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.SEMICOLON).value(";").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.IDENTIFIER).value("a").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.PLUS_SYMBOL).value("+").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.NUMBER_LITERAL).value("6").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.SEMICOLON).value(";").index(1).line(1).build());
    assertEquals(expectedToken, tokens);
  }

  @Test
  public void linesStatementTestWithPrintln() {
    List<String> str = new ArrayList<>();
    str.add("println ( a ) ;");
    List<Token> tokens = lexer.tokenize(str);
    List<Token> expectedToken =
        Arrays.asList(
            Token.builder().tokenType(TokenType.PRINTLN).value("println").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.L_PARENTHESES).value("(").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.IDENTIFIER).value("a").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.R_PARANTHESES).value(")").index(1).line(1).build(),
            Token.builder().tokenType(TokenType.SEMICOLON).value(";").index(1).line(1).build());

    assertEquals(expectedToken, tokens);
  }
}
