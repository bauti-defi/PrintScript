import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.builders.DelcarationAssignationBuilder;
import edu.austral.ingsis.ast.nodes.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DelcarationAssignationBuilderTest implements TokenHelper {

    @Test
    public void testBuild(){

        // let x:number = "hola"
        List<Token> tokens = Arrays.asList(
                createMockToken("let", TokenType.LET),
                createMockToken("x", TokenType.IDENTIFIER),
                createMockToken(":", TokenType.COLON),
                createMockToken("number", TokenType.TYPE),
                createMockToken("=", TokenType.EQUALS),
                createMockToken("5", TokenType.LITERAL)
        );


        final DelcarationAssignationBuilder builder = new DelcarationAssignationBuilder();

        if(builder.predicate(tokens)){
            final DeclarationAssignationNode node = builder.build(tokens);
            assertEquals(TokenType.EQUALS, node.getToken().getType());
            assertEquals(TokenType.COLON, node.getLeft().getToken().getType());
            assertEquals(TokenType.LITERAL, node.getRight().getToken().getType());
        }else{
            throw new Error();
        }
    }

}
