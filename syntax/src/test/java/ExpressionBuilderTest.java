import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.builders.DeclarationBuilder;
import edu.austral.ingsis.ast.builders.ExpressionBuilder;
import edu.austral.ingsis.ast.builders.ReferenceBuilder;
import edu.austral.ingsis.ast.nodes.AbstractNode;
import edu.austral.ingsis.ast.nodes.BinaryOpNode;
import edu.austral.ingsis.ast.nodes.DeclarationNode;
import edu.austral.ingsis.ast.nodes.ReferenceNode;
import org.junit.jupiter.api.Test;

import java.net.IDN;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionBuilderTest implements TokenHelper{

    @Test
    public void testBuild1(){
        //(5)
        List<Token> tokens = Arrays.asList(
                createMockToken("(", TokenType.L_PARENTHESES),
                createMockToken("5", TokenType.LITERAL),
                createMockToken(")", TokenType.R_PARANTHESES)
        );

        final ExpressionBuilder builder = new ExpressionBuilder();

        if(builder.predicate(tokens)){
            final AbstractNode node = builder.build(tokens);
            assertEquals(TokenType.LITERAL, node.getToken().getType());
        }else{
            throw new Error();
        }
    }

    @Test
    public void testBuild2(){
        //(5)-size
        List<Token> tokens = Arrays.asList(
                createMockToken("(", TokenType.L_PARENTHESES),
                createMockToken("5", TokenType.LITERAL),
                createMockToken(")", TokenType.R_PARANTHESES),
                createMockToken("-", TokenType.MINUS_SYMBOL),
                createMockToken("size", TokenType.IDENTIFIER)
        );


        final ExpressionBuilder expressionBuilder = new ExpressionBuilder();

        if(expressionBuilder.predicate(tokens)){
            final BinaryOpNode binaryOpNode = (BinaryOpNode) expressionBuilder.build(tokens);
            assertEquals(TokenType.MINUS_SYMBOL, binaryOpNode.getToken().getType());
            assertEquals(TokenType.LITERAL, binaryOpNode.getLeft().getToken().getType());
            assertEquals(TokenType.IDENTIFIER, binaryOpNode.getRight().getToken().getType());
        }else{
            throw new Error();
        }
    }
}
