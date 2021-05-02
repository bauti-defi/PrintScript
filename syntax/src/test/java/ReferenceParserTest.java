import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import edu.austral.ingsis.ast.parsers.ReferenceParser;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ReferenceParserTest implements TokenHelper {

  @Test
  public void testBuild1() {

    // let x:number
    List<Token> tokens = Arrays.asList(createMockToken("x", TokenType.IDENTIFIER));

    final ReferenceParser parser = new ReferenceParser();

    if (parser.predicate(tokens)) {
      assertEquals(TokenType.IDENTIFIER, parser.parse(tokens).getToken().getType());
    } else {
      throw new Error();
    }
  }
}
