package edu.austral.ingsis;

import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LexerTest1_0 {
    static Lexer lexer = new Lexer("1.0");

    @Test
    public void simpleStatementTest() {
        List<String> str = new ArrayList<>();
        str.add("let x:number= 5;");
        List<Token> tokens = lexer.lex(str);
        List<Token> expectedToken =
                Arrays.asList(
                        Token.builder().type(TokenType.LET).value("let").index(0).line(0).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("x").index(1).line(0).build(),
                        Token.builder().type(TokenType.COLON).value(":").index(2).line(0).build(),
                        Token.builder().type(TokenType.TYPE).value("number").index(3).line(0).build(),
                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("5").index(5).line(0).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(6).line(0).build());

        assertEquals(expectedToken, tokens);
    }

    @Test
    public void simpleStatementTestWithoutSpaceBetweenEqualAndNumber() {
        List<String> str = new ArrayList<>();
        str.add("let x :number= 5;");
        List<Token> tokens = lexer.lex(str);
        List<Token> expectedToken =
                Arrays.asList(
                        Token.builder().type(TokenType.LET).value("let").index(0).line(0).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("x").index(1).line(0).build(),
                        Token.builder().type(TokenType.COLON).value(":").index(2).line(0).build(),
                        Token.builder().type(TokenType.TYPE).value("number").index(3).line(0).build(),
                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("5").index(5).line(0).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(6).line(0).build());

        assertEquals(expectedToken, tokens);
    }

    @Test
    public void simpleStatementTestWithSpaceBetweenEqualAndNumber() {
        List<String> str = new ArrayList<>();
        str.add("let x:number = 5;");
        List<Token> tokens = lexer.lex(str);
        List<Token> expectedToken =
                Arrays.asList(
                        Token.builder().type(TokenType.LET).value("let").index(0).line(0).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("x").index(1).line(0).build(),
                        Token.builder().type(TokenType.COLON).value(":").index(2).line(0).build(),
                        Token.builder().type(TokenType.TYPE).value("number").index(3).line(0).build(),
                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("5").index(5).line(0).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(6).line(0).build());

        assertEquals(expectedToken, tokens);
    }

    @Test
    public void simpleStatementWithStringTest() {
        List<String> str = new ArrayList<>();
        str.add("let x : number = 5 ;");
        str.add("let x: string = \"holaaa\";");
        List<Token> tokens = lexer.lex(str);
        List<Token> expectedToken =
                Arrays.asList(
                        Token.builder().type(TokenType.LET).value("let").index(0).line(0).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("x").index(1).line(0).build(),
                        Token.builder().type(TokenType.COLON).value(":").index(2).line(0).build(),
                        Token.builder().type(TokenType.TYPE).value("number").index(3).line(0).build(),
                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("5").index(5).line(0).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(6).line(0).build(),
                        Token.builder().type(TokenType.LET).value("let").index(0).line(1).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("x").index(1).line(1).build(),
                        Token.builder().type(TokenType.COLON).value(":").index(2).line(1).build(),
                        Token.builder().type(TokenType.TYPE).value("string").index(3).line(1).build(),
                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(1).build(),
                        Token.builder().type(TokenType.LITERAL).value("\"holaaa\"").index(5).line(1).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(6).line(1).build());

        assertEquals(expectedToken, tokens);
    }

    @Test
    public void twoLinesStatementTest() {
        List<String> str = new ArrayList<>();
        str.add("let x : number = 5 ;");
        str.add("let a : string =\"hola\" ;");
        List<Token> tokens = lexer.lex(str);
        List<Token> expectedToken =
                Arrays.asList(
                        Token.builder().type(TokenType.LET).value("let").index(0).line(0).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("x").index(1).line(0).build(),
                        Token.builder().type(TokenType.COLON).value(":").index(2).line(0).build(),
                        Token.builder().type(TokenType.TYPE).value("number").index(3).line(0).build(),
                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("5").index(5).line(0).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(6).line(0).build(),
                        Token.builder().type(TokenType.LET).value("let").index(0).line(1).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("a").index(1).line(1).build(),
                        Token.builder().type(TokenType.COLON).value(":").index(2).line(1).build(),
                        Token.builder().type(TokenType.TYPE).value("string").index(3).line(1).build(),
                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(1).build(),
                        Token.builder()
                                .type(TokenType.LITERAL)
                                .value("\"" + "hola" + "\"")
                                .index(5)
                                .line(1)
                                .build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(6).line(1).build());
        assertEquals(expectedToken, tokens);
    }

    @Test
    public void twoLinesStatementTestWithMathOperator() {
        List<String> str = new ArrayList<>();
        str.add("let x : number = 5 ;");
        str.add("a + 6 ;");

        List<Token> tokens = lexer.lex(str);

        List<Token> expectedToken =
                Arrays.asList(
                        Token.builder().type(TokenType.LET).value("let").index(0).line(0).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("x").index(1).line(0).build(),
                        Token.builder().type(TokenType.COLON).value(":").index(2).line(0).build(),
                        Token.builder().type(TokenType.TYPE).value("number").index(3).line(0).build(),
                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("5").index(5).line(0).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(6).line(0).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("a").index(0).line(1).build(),
                        Token.builder().type(TokenType.PLUS_SYMBOL).value("+").index(1).line(1).build(),
                        Token.builder().type(TokenType.LITERAL).value("6").index(2).line(1).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(3).line(1).build());

        assertEquals(expectedToken, tokens);
    }

    @Test
    public void linesStatementTestWithPrintln() {
        List<String> str = new ArrayList<>();
        str.add("println(a);");

        List<Token> tokens = lexer.lex(str);

        List<Token> expectedToken =
                Arrays.asList(
                        Token.builder().type(TokenType.PRINTLN).value("println").index(0).line(0).build(),
                        Token.builder().type(TokenType.L_PARENTHESES).value("(").index(1).line(0).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("a").index(2).line(0).build(),
                        Token.builder().type(TokenType.R_PARENTHESES).value(")").index(3).line(0).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(4).line(0).build());

        assertEquals(expectedToken, tokens);
    }

    @Test
    public void complexMultiLineStatement() {
        List<String> str = new ArrayList<>();
        str.add("let pi: number;");
        str.add("pi = 3.14;");
        str.add("println(pi / 2);");

        List<Token> tokens = lexer.lex(str);

        List<Token> expectedToken =
                Arrays.asList(
                        Token.builder().type(TokenType.LET).value("let").index(0).line(0).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("pi").index(1).line(0).build(),
                        Token.builder().type(TokenType.COLON).value(":").index(2).line(0).build(),
                        Token.builder().type(TokenType.TYPE).value("number").index(3).line(0).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(4).line(0).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("pi").index(0).line(1).build(),
                        Token.builder().type(TokenType.EQUALS).value("=").index(1).line(1).build(),
                        Token.builder().type(TokenType.LITERAL).value("3.14").index(2).line(1).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(3).line(1).build(),
                        Token.builder().type(TokenType.PRINTLN).value("println").index(0).line(2).build(),
                        Token.builder().type(TokenType.L_PARENTHESES).value("(").index(1).line(2).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("pi").index(2).line(2).build(),
                        Token.builder().type(TokenType.SLASH_SYMBOL).value("/").index(3).line(2).build(),
                        Token.builder().type(TokenType.LITERAL).value("2").index(4).line(2).build(),
                        Token.builder().type(TokenType.R_PARENTHESES).value(")").index(5).line(2).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(6).line(2).build());
        assertEquals(expectedToken, tokens);
    }

    @Test
    public void statementWithATypeNameInTheIdentifier() {
        List<String> str = new ArrayList<>();
        str.add("let numberResult: number = 5 * 5 - 8;");
        str.add("println(numberResult);");

        List<Token> tokens = lexer.lex(str);

        List<Token> expectedToken =
                Arrays.asList(
                        Token.builder().type(TokenType.LET).value("let").index(0).line(0).build(),
                        Token.builder()
                                .type(TokenType.IDENTIFIER)
                                .value("numberResult")
                                .index(1)
                                .line(0)
                                .build(),
                        Token.builder().type(TokenType.COLON).value(":").index(2).line(0).build(),
                        Token.builder().type(TokenType.TYPE).value("number").index(3).line(0).build(),
                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("5").index(5).line(0).build(),
                        Token.builder().type(TokenType.STAR_SYMBOL).value("*").index(6).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("5").index(7).line(0).build(),
                        Token.builder().type(TokenType.MINUS_SYMBOL).value("-").index(8).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("8").index(9).line(0).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(10).line(0).build(),
                        Token.builder().type(TokenType.PRINTLN).value("println").index(0).line(1).build(),
                        Token.builder().type(TokenType.L_PARENTHESES).value("(").index(1).line(1).build(),
                        Token.builder()
                                .type(TokenType.IDENTIFIER)
                                .value("numberResult")
                                .index(2)
                                .line(1)
                                .build(),
                        Token.builder().type(TokenType.R_PARENTHESES).value(")").index(3).line(1).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(4).line(1).build());
        assertEquals(expectedToken, tokens);
    }

    @Test
    public void statementWithACompoundKeyboard() {
        List<String> str = new ArrayList<>();
        str.add("const booleanResult: boolean = 5 <= 3;");

        assertThrows(RuntimeException.class, () -> lexer.lex(str));

//        List<Token> tokens = lexer.lex(str);
//
//        List<Token> expectedToken =
//                Arrays.asList(
//                        Token.builder().type(TokenType.CONST).value("const").index(0).line(0).build(),
//                        Token.builder()
//                                .type(TokenType.IDENTIFIER)
//                                .value("booleanResult")
//                                .index(1)
//                                .line(0)
//                                .build(),
//                        Token.builder().type(TokenType.COLON).value(":").index(2).line(0).build(),
//                        Token.builder().type(TokenType.TYPE).value("boolean").index(3).line(0).build(),
//                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(0).build(),
//                        Token.builder().type(TokenType.LITERAL).value("5").index(5).line(0).build(),
//                        Token.builder().type(TokenType.LESS_THAN_EQUALS).value("<=").index(6).line(0).build(),
//                        Token.builder().type(TokenType.LITERAL).value("3").index(7).line(0).build(),
//                        Token.builder().type(TokenType.SEMICOLON).value(";").index(8).line(0).build());
//
//        assertEquals(expectedToken, tokens);
    }

    @Test
    public void statementWithIfElse() {
        List<String> str = new ArrayList<>();
        str.add("let a: boolean = 2 > 1;");
        str.add("if(a) {");
        str.add("println(\"if should not be supported in version 1.0\");");
        str.add("}");

        assertThrows(RuntimeException.class, () -> lexer.lex(str));

//        List<Token> tokens = lexer.lex(str);
//        List<Token> expectedToken =
//                Arrays.asList(
//                        Token.builder().type(TokenType.LET).value("let").index(0).line(0).build(),
//                        Token.builder().type(TokenType.IDENTIFIER).value("a").index(1).line(0).build(),
//                        Token.builder().type(TokenType.COLON).value(":").index(2).line(0).build(),
//                        Token.builder().type(TokenType.TYPE).value("boolean").index(3).line(0).build(),
//                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(0).build(),
//                        Token.builder().type(TokenType.LITERAL).value("2").index(5).line(0).build(),
//                        Token.builder().type(TokenType.GREATER_THAN).value(">").index(6).line(0).build(),
//                        Token.builder().type(TokenType.LITERAL).value("1").index(7).line(0).build(),
//                        Token.builder().type(TokenType.SEMICOLON).value(";").index(8).line(0).build(),
//                        Token.builder().type(TokenType.IF).value("if").index(0).line(1).build(),
//                        Token.builder().type(TokenType.L_PARENTHESES).value("(").index(1).line(1).build(),
//                        Token.builder().type(TokenType.IDENTIFIER).value("a").index(2).line(1).build(),
//                        Token.builder().type(TokenType.R_PARENTHESES).value(")").index(3).line(1).build(),
//                        Token.builder().type(TokenType.L_CURLY_BRACE).value("{").index(4).line(1).build(),
//                        Token.builder().type(TokenType.PRINTLN).value("println").index(0).line(2).build(),
//                        Token.builder().type(TokenType.L_PARENTHESES).value("(").index(1).line(2).build(),
//                        Token.builder()
//                                .type(TokenType.LITERAL)
//                                .value("\"if should not be supported in version 1.0\"")
//                                .index(2)
//                                .line(2)
//                                .build(),
//                        Token.builder().type(TokenType.R_PARENTHESES).value(")").index(3).line(2).build(),
//                        Token.builder().type(TokenType.SEMICOLON).value(";").index(4).line(2).build(),
//                        Token.builder().type(TokenType.R_CURLY_BRACE).value("}").index(0).line(3).build());
//        assertEquals(expectedToken, tokens);
    }

    @Test
    public void statementWithMathOperators() {
        List<String> str = new ArrayList<>();
        str.add("let cuenta: number = 5*5-8/4+2;");

        List<Token> tokens = lexer.lex(str);

        List<Token> expectedToken =
                Arrays.asList(
                        Token.builder().type(TokenType.LET).value("let").index(0).line(0).build(),
                        Token.builder().type(TokenType.IDENTIFIER).value("cuenta").index(1).line(0).build(),
                        Token.builder().type(TokenType.COLON).value(":").index(2).line(0).build(),
                        Token.builder().type(TokenType.TYPE).value("number").index(3).line(0).build(),
                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("5").index(5).line(0).build(),
                        Token.builder().type(TokenType.STAR_SYMBOL).value("*").index(6).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("5").index(7).line(0).build(),
                        Token.builder().type(TokenType.MINUS_SYMBOL).value("-").index(8).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("8").index(9).line(0).build(),
                        Token.builder().type(TokenType.SLASH_SYMBOL).value("/").index(10).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("4").index(11).line(0).build(),
                        Token.builder().type(TokenType.PLUS_SYMBOL).value("+").index(12).line(0).build(),
                        Token.builder().type(TokenType.LITERAL).value("2").index(13).line(0).build(),
                        Token.builder().type(TokenType.SEMICOLON).value(";").index(14).line(0).build());

        assertEquals(expectedToken, tokens);
    }

    @Test
    public void statementWithBooleansOperators() {
        List<String> str = new ArrayList<>();
        str.add("let cuenta: boolean = 1==2;");

        assertThrows(RuntimeException.class, () -> lexer.lex(str));

//        List<Token> tokens = lexer.lex(str);

//        List<Token> expectedToken =
//                Arrays.asList(
//                        Token.builder().type(TokenType.LET).value("let").index(0).line(0).build(),
//                        Token.builder().type(TokenType.IDENTIFIER).value("cuenta").index(1).line(0).build(),
//                        Token.builder().type(TokenType.COLON).value(":").index(2).line(0).build(),
//                        Token.builder().type(TokenType.TYPE).value("boolean").index(3).line(0).build(),
//                        Token.builder().type(TokenType.EQUALS).value("=").index(4).line(0).build(),
//                        Token.builder().type(TokenType.LITERAL).value("1").index(5).line(0).build(),
//                        Token.builder().type(TokenType.DOUBLE_EQUALS).value("==").index(6).line(0).build(),
//                        Token.builder().type(TokenType.LITERAL).value("2").index(7).line(0).build(),
//                        Token.builder().type(TokenType.SEMICOLON).value(";").index(8).line(0).build());
//
//        assertEquals(expectedToken, tokens);
    }
}
