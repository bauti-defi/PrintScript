import edu.austral.ingsis.ast.DeclarationTable;
import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.builders.ExpressionBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ExpressionBuilderTest implements TokenHelper{

    @Test
    public void testBuild(){
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

        final DeclarationTable declarations = new DeclarationTable();

        final ExpressionBuilder builder = new ExpressionBuilder();


    }
}
