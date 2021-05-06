import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.ast.nodes.ReferenceAssignationNode;
import edu.austral.ingsis.ast.parsers.ReferenceAssignationParser;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ReferenceAssignationParserTest implements TokenHelper {

  @Test
  public void testBuild1() {

    // let x:number
    List<Token> tokens =
        Arrays.asList(
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken("=", TokenType.EQUALS),
            createMockToken("5", TokenType.LITERAL));

    final ReferenceAssignationParser parser = new ReferenceAssignationParser();

    if (parser.predicate(tokens)) {
      ReferenceAssignationNode node = parser.parse(tokens);
      assertEquals(TokenType.EQUALS, node.getToken().getType());
      assertEquals(TokenType.IDENTIFIER, node.getLeft().getToken().getType());
      assertEquals(TokenType.LITERAL, node.getRight().getToken().getType());
    } else {
      throw new Error();
    }
  }
}
