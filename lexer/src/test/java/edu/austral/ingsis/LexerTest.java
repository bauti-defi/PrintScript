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
        keywords.put(":", TokenType.DOUBLE_DOT);
        keywords.put("=", TokenType.EQUAL);
        keywords.put(";", TokenType.SEMI_COLON);
        keywords.put("number", TokenType.NUMBER);
        keywords.put("string", TokenType.STRING);
        keywords.put("+", TokenType.MATH_OPERATOR);
        keywords.put("-", TokenType.MATH_OPERATOR);
        keywords.put("*", TokenType.MATH_OPERATOR);
        keywords.put("/", TokenType.MATH_OPERATOR);
        keywords.put("println", TokenType.PRINTLN);
        keywords.put("(", TokenType.L_PARENTHESIS);
        keywords.put(")", TokenType.R_PARENTHESIS);

        lexer = new Lexer(keywords);
    }

    @Test
    public void simpleStatementTest(){
        List<String> str = new ArrayList<>();
        str.add("let x : number = 5 ;");
        List<Token> tokens = lexer.tokenize(str);
        List<Token> expectedToken = Arrays.asList(
                Token.builder().tokenType(TokenType.LET).value("let").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.ID).value("x").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.DOUBLE_DOT).value(":").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.NUMBER).value("number").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.EQUAL).value("=").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.NUMBER_VALUE).value("5").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.SEMI_COLON).value(";").index(1).line(1).build());


        assertEquals(expectedToken, tokens);
    }

    @Test
    public void twoLinesStatementTest(){
        List<String> str = new ArrayList<>();
        str.add("let x : number = 5 ;");
        str.add("let a : string = 6 ;");
        List<Token> tokens = lexer.tokenize(str);
        List<Token> expectedToken = Arrays.asList(
                Token.builder().tokenType(TokenType.LET).value("let").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.ID).value("x").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.DOUBLE_DOT).value(":").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.NUMBER).value("number").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.EQUAL).value("=").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.NUMBER_VALUE).value("5").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.SEMI_COLON).value(";").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.LET).value("let").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.ID).value("a").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.DOUBLE_DOT).value(":").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.STRING).value("string").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.EQUAL).value("=").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.NUMBER_VALUE).value("6").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.SEMI_COLON).value(";").index(1).line(1).build());
        assertEquals(expectedToken, tokens);
    }

    @Test
    public void twoLinesStatementTestWithMathOperator() {
        List<String> str = new ArrayList<>();
        str.add("let x : number = 5 ;");
        str.add("a + 6 ;");
        List<Token> tokens = lexer.tokenize(str);
        List<Token> expectedToken = Arrays.asList(
                Token.builder().tokenType(TokenType.LET).value("let").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.ID).value("x").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.DOUBLE_DOT).value(":").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.NUMBER).value("number").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.EQUAL).value("=").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.NUMBER_VALUE).value("5").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.SEMI_COLON).value(";").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.ID).value("a").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.MATH_OPERATOR).value("+").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.NUMBER_VALUE).value("6").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.SEMI_COLON).value(";").index(1).line(1).build());
        assertEquals(expectedToken, tokens);
    }

    @Test
    public void linesStatementTestWithPrintln(){
        List<String> str = new ArrayList<>();
        str.add("println ( a ) ;");
        List<Token> tokens = lexer.tokenize(str);
        List<Token> expectedToken = Arrays.asList(
                Token.builder().tokenType(TokenType.PRINTLN).value("println").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.L_PARENTHESIS).value("(").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.ID).value("a").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.R_PARENTHESIS).value(")").index(1).line(1).build(),
                Token.builder().tokenType(TokenType.SEMI_COLON).value(";").index(1).line(1).build());

        assertEquals(expectedToken, tokens);
    }

}