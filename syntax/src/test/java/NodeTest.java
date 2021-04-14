import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.parsers.NodeParser;
import edu.austral.ingsis.ast.nodes.AbstractNode;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

public class NodeTest implements TokenHelper{

    final NodeParser<?> builder = new NodeParser<AbstractNode>() {
        @Override
        public boolean predicate(List<Token> tokens) {
            return false;
        }

        @Override
        public AbstractNode parse(List<Token> tokens) {
            return null;
        }
    };

    @Test
    public void testContainsToken(){
        // let x:number
        List<Token> tokens = Arrays.asList(
                createMockToken("let", TokenType.LET),
                createMockToken("x", TokenType.IDENTIFIER),
                createMockToken(":", TokenType.COLON),
                createMockToken("number", TokenType.TYPE)
        );

        assertEquals(true, builder.containsToken(tokens, TokenType.COLON));
    }

    @Test
    public void testGetIndexOfToken(){
        // let x:number
        List<Token> tokens = Arrays.asList(
                createMockToken("let", TokenType.LET),
                createMockToken("x", TokenType.IDENTIFIER),
                createMockToken(":", TokenType.COLON),
                createMockToken("number", TokenType.TYPE)
        );

        assertEquals(2, builder.getIndexOfToken(tokens, TokenType.COLON));
    }


    @Test
    public void testIsTokenType(){
        assertEquals(true, builder.isTokenType(createMockToken(":", TokenType.COLON), TokenType.COLON));
    }

    @Test
    public void testStartsWith(){
        // let x:number
        List<Token> tokens = Arrays.asList(
                createMockToken("let", TokenType.LET),
                createMockToken("x", TokenType.IDENTIFIER),
                createMockToken(":", TokenType.COLON),
                createMockToken("number", TokenType.TYPE)
        );

        assertEquals(true, builder.startsWith(tokens, TokenType.LET));
    }

    @Test
    public void testEndsWith(){
        // let x:number
        List<Token> tokens = Arrays.asList(
                createMockToken("let", TokenType.LET),
                createMockToken("x", TokenType.IDENTIFIER),
                createMockToken(":", TokenType.COLON),
                createMockToken("number", TokenType.TYPE)
        );

        assertEquals(true, builder.endsWith(tokens, TokenType.TYPE));
    }




}
