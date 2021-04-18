import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.TokenType;
import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.parsers.DeclarationParser;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DeclartionParserTest implements TokenHelper {

  @Test
  public void testBuild() {

    // let x:number
    List<Token> tokens =
        Arrays.asList(
            createMockToken("let", TokenType.LET),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE));

    final DeclarationParser parser = new DeclarationParser();

    if (parser.predicate(tokens)) {
      final DeclarationNode node = parser.parse(tokens);
      assertEquals(TokenType.LET, node.getToken().getType());
      assertEquals(TokenType.IDENTIFIER, node.getLeft().getToken().getType());
      assertEquals(TokenType.TYPE, node.getRight().getToken().getType());
    } else {
      throw new Error();
    }
  }
}
