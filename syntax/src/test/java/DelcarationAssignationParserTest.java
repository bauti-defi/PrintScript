import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.TokenType;
import edu.austral.ingsis.ast.builders.DeclarationAssignationParser;
import edu.austral.ingsis.ast.nodes.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DelcarationAssignationParserTest implements TokenHelper {

  @Test
  public void testBuild01() {

    // let x:number = "hola"
    List<Token> tokens =
        Arrays.asList(
            createMockToken("let", TokenType.LET),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE),
            createMockToken("=", TokenType.EQUALS),
            createMockToken("5", TokenType.LITERAL));

    final DeclarationAssignationParser builder = new DeclarationAssignationParser();

    if (builder.predicate(tokens)) {
      final DeclarationAssignationNode node = builder.parse(tokens);
      assertEquals(TokenType.EQUALS, node.getToken().getType());
      assertEquals(TokenType.LET, node.getLeft().getToken().getType());
      assertEquals(TokenType.LITERAL, node.getRight().getToken().getType());
    } else {
      throw new Error();
    }
  }

  @Test
  public void testBuild02() {

    // let x:number = "hola"
    List<Token> tokens =
        Arrays.asList(
            createMockToken("const", TokenType.CONST),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE),
            createMockToken("=", TokenType.EQUALS),
            createMockToken("5", TokenType.LITERAL));

    final DeclarationAssignationParser builder = new DeclarationAssignationParser();

    if (builder.predicate(tokens)) {
      final DeclarationAssignationNode node = builder.parse(tokens);
      assertEquals(TokenType.EQUALS, node.getToken().getType());
      assertEquals(TokenType.CONST, node.getLeft().getToken().getType());
      assertEquals(TokenType.LITERAL, node.getRight().getToken().getType());
    } else {
      throw new Error();
    }
  }
}
