
import edu.austral.ingsis.ast.ShuntingYard;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShuntingYardTest {

    private Token createMockToken(String value, TokenType type){
        return new Token() {
            @Override
            public String getValue() {
                return value;
            }

            @Override
            public Integer getLineNumber() {
                return 0;
            }

            @Override
            public Integer getLineIndex() {
                return 0;
            }

            @Override
            public TokenType getType() {
                return type;
            }
        };
    }

    @Test
    public void testProcessShuntingYard01(){
        //(5*4+3*2)-1
        List<Token> tokens = Arrays.asList(
                createMockToken("(", TokenType.L_PARENTHESES),
                createMockToken("5", TokenType.NUMBER_LITERAL),
                createMockToken("*", TokenType.STAR_SYMBOL),
                createMockToken("4", TokenType.NUMBER_LITERAL),
                createMockToken("+", TokenType.PLUS_SYMBOL),
                createMockToken("3", TokenType.NUMBER_LITERAL),
                createMockToken("*", TokenType.STAR_SYMBOL),
                createMockToken("2", TokenType.NUMBER_LITERAL),
                createMockToken(")", TokenType.R_PARANTHESES),
                createMockToken("-", TokenType.MINUS_SYMBOL),
                createMockToken("1", TokenType.NUMBER_LITERAL)
        );

        List<String> result = ShuntingYard.process(tokens).stream().map(Token::getValue).collect(Collectors.toList());

        List<String> expected = Arrays.asList("5", "4", "*", "3", "2", "*", "+", "1", "-");

        assertEquals(expected, result);
    }

    @Test
    public void testProcessShuntingYard02(){
        //(5*4+3*x)-size
        List<Token> tokens = Arrays.asList(
                createMockToken("(", TokenType.L_PARENTHESES),
                createMockToken("5", TokenType.NUMBER_LITERAL),
                createMockToken("*", TokenType.STAR_SYMBOL),
                createMockToken("4", TokenType.NUMBER_LITERAL),
                createMockToken("+", TokenType.PLUS_SYMBOL),
                createMockToken("3", TokenType.NUMBER_LITERAL),
                createMockToken("*", TokenType.STAR_SYMBOL),
                createMockToken("x", TokenType.IDENTIFIER),
                createMockToken(")", TokenType.R_PARANTHESES),
                createMockToken("-", TokenType.MINUS_SYMBOL),
                createMockToken("size", TokenType.IDENTIFIER)
        );

        List<String> result = ShuntingYard.process(tokens).stream().map(Token::getValue).collect(Collectors.toList());

        List<String> expected = Arrays.asList("5", "4", "*", "3", "x", "*", "+", "size", "-");

        assertEquals(expected, result);
    }

    @Test
    public void testProcessShuntingYard03(){
        //5
        List<Token> tokens = Arrays.asList(
                createMockToken("5", TokenType.NUMBER_LITERAL)
        );

        List<String> result = ShuntingYard.process(tokens).stream().map(Token::getValue).collect(Collectors.toList());

        List<String> expected = Arrays.asList("5");

        assertEquals(expected, result);
    }

    @Test
    public void testProcessShuntingYard04(){
        //(5)-size
        List<Token> tokens = Arrays.asList(
                createMockToken("(", TokenType.L_PARENTHESES),
                createMockToken("5", TokenType.NUMBER_LITERAL),
                createMockToken(")", TokenType.R_PARANTHESES),
                createMockToken("-", TokenType.MINUS_SYMBOL),
                createMockToken("size", TokenType.IDENTIFIER)
        );

        List<String> result = ShuntingYard.process(tokens).stream().map(Token::getValue).collect(Collectors.toList());

        List<String> expected = Arrays.asList("5", "size", "-");

        assertEquals(expected, result);
    }

    @Test
    public void testEvaluateSimpleShuntingYard(){

    }
}
