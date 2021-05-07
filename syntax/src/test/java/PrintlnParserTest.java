import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.ast.nodes.BinaryOpNode;
import edu.austral.ingsis.ast.nodes.PrintlnNode;
import edu.austral.ingsis.ast.parsers.PrintlnParser;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PrintlnParserTest implements TokenHelper {

  @Test
  public void testParse01() {
    // println(x)
    List<Token> tokens =
        Arrays.asList(
            createMockToken("println", TokenType.PRINTLN),
            createMockToken("(", TokenType.L_PARENTHESES),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(")", TokenType.R_PARENTHESES));

    final PrintlnParser parser = new PrintlnParser();

    if (parser.predicate(tokens)) {
      final PrintlnNode node = parser.parse(tokens);
      assertEquals(TokenType.PRINTLN, node.getToken().getType());
      assertEquals(TokenType.IDENTIFIER, node.getArgs().getToken().getType());
    } else {
      throw new Error();
    }
  }

  @Test
  public void testParse02() {
    // println(x)
    List<Token> tokens =
        Arrays.asList(
            createMockToken("println", TokenType.PRINTLN),
            createMockToken("(", TokenType.L_PARENTHESES),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken("+", TokenType.PLUS_SYMBOL),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(")", TokenType.R_PARENTHESES));

    final PrintlnParser parser = new PrintlnParser();

    if (parser.predicate(tokens)) {
      final PrintlnNode node = parser.parse(tokens);
      assertEquals(TokenType.PRINTLN, node.getToken().getType());
      assertEquals(TokenType.PLUS_SYMBOL, node.getArgs().getToken().getType());
      final BinaryOpNode expression = (BinaryOpNode) node.getArgs();
      assertEquals(TokenType.IDENTIFIER, expression.getLeft().getToken().getType());
      assertEquals(TokenType.IDENTIFIER, expression.getRight().getToken().getType());
    } else {
      throw new Error();
    }
  }

  @Test
  public void testParse03() {
    // println(x)
    List<Token> tokens =
        Arrays.asList(
            createMockToken("println", TokenType.PRINTLN),
            createMockToken("(", TokenType.L_PARENTHESES),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken("+", TokenType.PLUS_SYMBOL),
            createMockToken("bauti", TokenType.LITERAL),
            createMockToken(")", TokenType.R_PARENTHESES));

    final PrintlnParser parser = new PrintlnParser();

    if (parser.predicate(tokens)) {
      final PrintlnNode node = parser.parse(tokens);
      assertEquals(TokenType.PRINTLN, node.getToken().getType());
      assertEquals(TokenType.PLUS_SYMBOL, node.getArgs().getToken().getType());
      final BinaryOpNode expression = (BinaryOpNode) node.getArgs();
      assertEquals(TokenType.IDENTIFIER, expression.getLeft().getToken().getType());
      assertEquals(TokenType.LITERAL, expression.getRight().getToken().getType());
    } else {
      throw new Error();
    }
  }
}
