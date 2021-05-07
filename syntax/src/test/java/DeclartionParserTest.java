import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.parsers.DeclarationParser;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DeclartionParserTest implements TokenHelper {

  @Test
  public void testLetParse() {

    // let x:number
    List<Token> tokens =
        Arrays.asList(
            createMockToken("let", TokenType.LET),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE));

    final DeclarationParser parser = new DeclarationParser(TokenType.LET);

    if (parser.predicate(tokens)) {
      final DeclarationNode node = parser.parse(tokens);
      assertEquals(TokenType.LET, node.getToken().getType());
      assertEquals(TokenType.IDENTIFIER, node.getLeft().getToken().getType());
      assertEquals(TokenType.TYPE, node.getRight().getToken().getType());
    } else {
      throw new Error();
    }
  }

  @Test
  public void testParseConst() {

    // const x:number
    List<Token> tokens =
        Arrays.asList(
            createMockToken("const", TokenType.CONST),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE));

    final DeclarationParser parser = new DeclarationParser(TokenType.CONST);

    if (parser.predicate(tokens)) {
      final DeclarationNode node = parser.parse(tokens);
      assertEquals(TokenType.CONST, node.getToken().getType());
      assertEquals(TokenType.IDENTIFIER, node.getLeft().getToken().getType());
      assertEquals(TokenType.TYPE, node.getRight().getToken().getType());
    } else {
      throw new Error();
    }
  }

  @Test
  public void testParseBoolean() {
    // const x:boolean
    List<Token> tokens =
        Arrays.asList(
            createMockToken("const", TokenType.CONST),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("boolean", TokenType.TYPE),
                createMockToken(";", TokenType.SEMICOLON));

    final DeclarationParser parser = new DeclarationParser(TokenType.CONST);

    if (parser.predicate(tokens)) {
      final DeclarationNode node = parser.parse(tokens);
      assertEquals(TokenType.CONST, node.getToken().getType());
      assertEquals(TokenType.IDENTIFIER, node.getLeft().getToken().getType());
      assertEquals(TokenType.TYPE, node.getRight().getToken().getType());
    } else {
      throw new Error();
    }
  }
}
