import edu.austral.ingsis.ast.DeclarationTable;
import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.builders.AssignationBuilder;
import edu.austral.ingsis.ast.builders.DeclarationBuilder;
import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.visitor.Visitor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssignationBuilderTest implements TokenHelper {

    @Test
    public void testBuild(){

        // let x:number = "hola"
        List<Token> tokens = Arrays.asList(
                createMockToken("let", TokenType.LET),
                createMockToken("x", TokenType.IDENTIFIER),
                createMockToken(":", TokenType.COLON),
                createMockToken("number", TokenType.NUMBER_TYPE),
                createMockToken("=", TokenType.EQUALS),
                createMockToken("5", TokenType.NUMBER_LITERAL)
        );

        final DeclarationTable declarations = new DeclarationTable();

        final AssignationBuilder builder = new AssignationBuilder();

        if(builder.predicate(tokens, declarations)){
            final AssignationNode node = builder.build(tokens, declarations);
            assertEquals(TokenType.EQUALS, node.getToken().getType());
            assertEquals(TokenType.COLON, node.getLeft().getToken().getType());
            assertEquals(TokenType.NUMBER_LITERAL, node.getRight().getToken().getType());
        }else{
            throw new Error();
        }
    }

}
