import edu.austral.ingsis.ast.DeclarationTable;
import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.builders.DeclarationBuilder;
import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;
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
                createMockToken("number", TokenType.NUMBER_TYPE)
        );

        final DeclarationTable declarations = new DeclarationTable();

        final DeclarationBuilder builder = new DeclarationBuilder();

        if(builder.predicate(tokens, declarations)){
            final DeclarationNode node  = builder.build(tokens, declarations);
            assertEquals(TokenType.COLON, node.getToken().getType());
            assertEquals(TokenType.IDENTIFIER, node.getLeft().getToken().getType());
            assertEquals(TokenType.NUMBER_TYPE, node.getRight().getToken().getType());
            assertEquals(true, declarations.contains("x"));
        }else{
            throw new Error();
        }
    }

}
