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
        List<Token> tokens = Arrays.asList(
                createMockToken("x", TokenType.IDENTIFIER)
        );

        final ReferenceBuilder builder = new ReferenceBuilder();

        if(builder.predicate(tokens)){
            assertEquals(TokenType.IDENTIFIER, builder.build(tokens).getToken().getType());
        }else{
            throw new Error();
        }
    }

}
