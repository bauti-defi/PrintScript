import edu.austral.ingsis.ast.DeclarationTable;
import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.builders.DeclarationBuilder;
import edu.austral.ingsis.ast.builders.ReferenceBuilder;
import edu.austral.ingsis.ast.exceptions.VariableUndefinedException;
import edu.austral.ingsis.ast.nodes.DeclarationNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReferenceBuilderTest implements TokenHelper{

    @Test
    public void testBuild1(){

        // let x:number
        List<Token> declarationTokens = Arrays.asList(
                createMockToken("let", TokenType.LET),
                createMockToken("x", TokenType.IDENTIFIER),
                createMockToken(":", TokenType.COLON),
                createMockToken("number", TokenType.NUMBER_TYPE)
        );

        final DeclarationTable declarations = new DeclarationTable();

        final DeclarationBuilder builder = new DeclarationBuilder();

        if(builder.predicate(declarationTokens, declarations)){
            final DeclarationNode declarationNode  = builder.build(declarationTokens, declarations);

            final ReferenceBuilder referenceBuilder = new ReferenceBuilder();
            List<Token> identifier = Arrays.asList(createMockToken("x", TokenType.IDENTIFIER));
            if(referenceBuilder.predicate(identifier, declarations)){
                assertEquals(declarationNode, referenceBuilder.build(identifier, declarations));
            }
        }else{
            throw new Error();
        }
    }

    @Test
    public void testBuildFails(){
        final DeclarationTable declarations = new DeclarationTable();

        final ReferenceBuilder referenceBuilder = new ReferenceBuilder();
        List<Token> identifier = Arrays.asList(createMockToken("x", TokenType.IDENTIFIER));
        Assertions.assertThrows(VariableUndefinedException.class, () -> referenceBuilder.build(identifier, declarations));

    }


}
