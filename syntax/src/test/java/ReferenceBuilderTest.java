import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.parsers.ReferenceParser;
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

        final ReferenceParser builder = new ReferenceParser();

        if(builder.predicate(tokens)){
            assertEquals(TokenType.IDENTIFIER, builder.parse(tokens).getToken().getType());
        }else{
            throw new Error();
        }
    }

}
