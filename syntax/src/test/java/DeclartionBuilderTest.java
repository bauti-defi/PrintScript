import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.builders.DeclarationBuilder;
import edu.austral.ingsis.ast.nodes.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

public class DeclartionBuilderTest implements TokenHelper {

    @Test
    public void testBuild(){

        // let x:number
        List<Token> tokens = Arrays.asList(
                createMockToken("let", TokenType.LET),
                createMockToken("x", TokenType.IDENTIFIER),
                createMockToken(":", TokenType.COLON),
                createMockToken("number", TokenType.TYPE)
        );

        final DeclarationBuilder builder = new DeclarationBuilder();

        if(builder.predicate(tokens)){
            final DeclarationNode node  = builder.build(tokens);
            assertEquals(TokenType.COLON, node.getToken().getType());
            assertEquals(TokenType.IDENTIFIER, node.getLeft().getToken().getType());
            assertEquals(TokenType.TYPE, node.getRight().getToken().getType());
        }else{
            throw new Error();
        }
    }

}
