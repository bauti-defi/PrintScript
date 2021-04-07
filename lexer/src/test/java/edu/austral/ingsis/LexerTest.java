package edu.austral.ingsis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    static Lexer lexer;

    @BeforeAll
    static void setUp(){
        Map<String, TokenType > keywords = new HashMap<>();
        keywords.put("let", TokenType.LET);
        keywords.put(":", TokenType.COLON);
        keywords.put("=", TokenType.EQUALS);
        keywords.put(";", TokenType.SEMICOLON);
        keywords.put("number", TokenType.NUMBER_TYPE);
        keywords.put("string", TokenType.STRING_TYPE);
        keywords.put("+", TokenType.PLUS_SYMBOL);
        keywords.put("-", TokenType.MINUS_SYMBOL);
        keywords.put("*", TokenType.STAR_SYMBOL);
        keywords.put("/", TokenType.SLASH_SYMBOL);
        keywords.put("println", TokenType.PRINTLN);
        keywords.put("(", TokenType.L_PARENTHESES);
        keywords.put(")", TokenType.R_PARANTHESES);

        lexer = new Lexer(keywords);
    }

    @Test
    public void simpleStatementTest(){
        List<String> str = new ArrayList<>();
        str.add("let x : number = 5 ;");
        List<Token> tokens = lexer.tokenize(str);
        List<Token> expectedToken = Arrays.asList(
                Token.builder().type(TokenType.LET).value("let").index(1).line(1).build(),
                Token.builder().type(TokenType.IDENTIFIER).value("x").index(1).line(1).build(),
                Token.builder().type(TokenType.COLON).value(":").index(1).line(1).build(),
                Token.builder().type(TokenType.NUMBER_TYPE).value("number").index(1).line(1).build(),
                Token.builder().type(TokenType.EQUALS).value("=").index(1).line(1).build(),
                Token.builder().type(TokenType.NUMBER_LITERAL).value("5").index(1).line(1).build(),
                Token.builder().type(TokenType.SEMICOLON).value(";").index(1).line(1).build());

        assertEquals(expectedToken, tokens);
    }

    @Test
    public void twoLinesStatementTest(){
        List<String> str = new ArrayList<>();
        str.add("let x : number = 5 ;");
        str.add("let a : string = 6 ;");
        List<Token> tokens = lexer.tokenize(str);
        List<Token> expectedToken = Arrays.asList(
                Token.builder().type(TokenType.LET).value("let").index(1).line(1).build(),
                Token.builder().type(TokenType.IDENTIFIER).value("x").index(1).line(1).build(),
                Token.builder().type(TokenType.COLON).value(":").index(1).line(1).build(),
                Token.builder().type(TokenType.NUMBER_TYPE).value("number").index(1).line(1).build(),
                Token.builder().type(TokenType.EQUALS).value("=").index(1).line(1).build(),
                Token.builder().type(TokenType.NUMBER_LITERAL).value("5").index(1).line(1).build(),
                Token.builder().type(TokenType.SEMICOLON).value(";").index(1).line(1).build(),
                Token.builder().type(TokenType.LET).value("let").index(1).line(1).build(),
                Token.builder().type(TokenType.IDENTIFIER).value("a").index(1).line(1).build(),
                Token.builder().type(TokenType.COLON).value(":").index(1).line(1).build(),
                Token.builder().type(TokenType.STRING_TYPE).value("string").index(1).line(1).build(),
                Token.builder().type(TokenType.EQUALS).value("=").index(1).line(1).build(),
                Token.builder().type(TokenType.NUMBER_LITERAL).value("6").index(1).line(1).build(),
                Token.builder().type(TokenType.SEMICOLON).value(";").index(1).line(1).build());
        assertEquals(expectedToken, tokens);
    }

    @Test
    public void twoLinesStatementTestWithMathOperator() {
        List<String> str = new ArrayList<>();
        str.add("let x : number = 5 ;");
        str.add("a + 6 ;");
        List<Token> tokens = lexer.tokenize(str);
        List<Token> expectedToken = Arrays.asList(
                Token.builder().type(TokenType.LET).value("let").index(1).line(1).build(),
                Token.builder().type(TokenType.IDENTIFIER).value("x").index(1).line(1).build(),
                Token.builder().type(TokenType.COLON).value(":").index(1).line(1).build(),
                Token.builder().type(TokenType.NUMBER_TYPE).value("number").index(1).line(1).build(),
                Token.builder().type(TokenType.EQUALS).value("=").index(1).line(1).build(),
                Token.builder().type(TokenType.NUMBER_LITERAL).value("5").index(1).line(1).build(),
                Token.builder().type(TokenType.SEMICOLON).value(";").index(1).line(1).build(),
                Token.builder().type(TokenType.IDENTIFIER).value("a").index(1).line(1).build(),
                Token.builder().type(TokenType.PLUS_SYMBOL).value("+").index(1).line(1).build(),
                Token.builder().type(TokenType.NUMBER_LITERAL).value("6").index(1).line(1).build(),
                Token.builder().type(TokenType.SEMICOLON).value(";").index(1).line(1).build());
        assertEquals(expectedToken, tokens);
    }

    @Test
    public void linesStatementTestWithPrintln(){
        List<String> str = new ArrayList<>();
        str.add("println ( a ) ;");
        List<Token> tokens = lexer.tokenize(str);
        List<Token> expectedToken = Arrays.asList(
                Token.builder().type(TokenType.PRINTLN).value("println").index(1).line(1).build(),
                Token.builder().type(TokenType.L_PARENTHESES).value("(").index(1).line(1).build(),
                Token.builder().type(TokenType.IDENTIFIER).value("a").index(1).line(1).build(),
                Token.builder().type(TokenType.R_PARANTHESES).value(")").index(1).line(1).build(),
                Token.builder().type(TokenType.SEMICOLON).value(";").index(1).line(1).build());

        assertEquals(expectedToken, tokens);
    }

}