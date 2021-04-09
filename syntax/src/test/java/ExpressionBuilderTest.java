import edu.austral.ingsis.ast.DeclarationTable;
import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.builders.DeclarationBuilder;
import edu.austral.ingsis.ast.builders.ExpressionBuilder;
import edu.austral.ingsis.ast.nodes.AbstractNode;
import edu.austral.ingsis.ast.nodes.BinaryOpNode;
import edu.austral.ingsis.ast.nodes.DeclarationNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionBuilderTest implements TokenHelper{

    @Test
    public void testBuild1(){
        //(5)
        List<Token> tokens = Arrays.asList(
                createMockToken("(", TokenType.L_PARENTHESES),
                createMockToken("5", TokenType.NUMBER_LITERAL),
                createMockToken(")", TokenType.R_PARANTHESES)
        );

        final DeclarationTable declarations = new DeclarationTable();

        final ExpressionBuilder builder = new ExpressionBuilder();

        if(builder.predicate(tokens, declarations)){
            final AbstractNode node = builder.build(tokens, declarations);
            assertEquals(TokenType.NUMBER_LITERAL, node.getToken().getType());
        }else{
            throw new Error();
        }
    }

    @Test
    public void testBuild2(){
        // let x:number
        List<Token> declTokens = Arrays.asList(
                createMockToken("let", TokenType.LET),
                createMockToken("size", TokenType.IDENTIFIER),
                createMockToken(":", TokenType.COLON),
                createMockToken("number", TokenType.NUMBER_TYPE)
        );

        final DeclarationTable declarations = new DeclarationTable();

        final DeclarationBuilder declarationBuilder = new DeclarationBuilder();
        final DeclarationNode declarationNode  = declarationBuilder.build(declTokens, declarations);


        //(5)-size
        List<Token> tokens = Arrays.asList(
                createMockToken("(", TokenType.L_PARENTHESES),
                createMockToken("5", TokenType.NUMBER_LITERAL),
                createMockToken(")", TokenType.R_PARANTHESES),
                createMockToken("-", TokenType.MINUS_SYMBOL),
                createMockToken("size", TokenType.IDENTIFIER)
        );


        final ExpressionBuilder expressionBuilder = new ExpressionBuilder();

        if(expressionBuilder.predicate(tokens, declarations)){
            final BinaryOpNode binaryOpNode = (BinaryOpNode) expressionBuilder.build(tokens, declarations);
            assertEquals(TokenType.MINUS_SYMBOL, binaryOpNode.getToken().getType());
            assertEquals(TokenType.NUMBER_LITERAL, binaryOpNode.getLeft().getToken().getType());
            assertEquals(declarationNode, binaryOpNode.getRight());
        }else{
            throw new Error();
        }
    }
}
