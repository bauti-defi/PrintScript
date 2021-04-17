import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.TokenType;
import edu.austral.ingsis.ast.builders.ExpressionParser;
import edu.austral.ingsis.ast.nodes.AbstractNode;
import edu.austral.ingsis.ast.nodes.BinaryOpNode;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ExpressionParserTest implements TokenHelper {

  @Test
  public void testBuild1() {
    // (5)
    List<Token> tokens =
        Arrays.asList(
            createMockToken("(", TokenType.L_PARENTHESES),
            createMockToken("5", TokenType.LITERAL),
            createMockToken(")", TokenType.R_PARANTHESES));

    final ExpressionParser builder = new ExpressionParser();

    if (builder.predicate(tokens)) {
      final AbstractNode node = builder.parse(tokens);
      assertEquals(TokenType.LITERAL, node.getToken().getType());
    } else {
      throw new Error();
    }
  }

  @Test
  public void testBuild2() {
    // (5)-size
    List<Token> tokens =
        Arrays.asList(
            createMockToken("(", TokenType.L_PARENTHESES),
            createMockToken("5", TokenType.LITERAL),
            createMockToken(")", TokenType.R_PARANTHESES),
            createMockToken("-", TokenType.MINUS_SYMBOL),
            createMockToken("size", TokenType.IDENTIFIER));

    final ExpressionParser expressionParser = new ExpressionParser();

    if (expressionParser.predicate(tokens)) {
      final BinaryOpNode binaryOpNode = (BinaryOpNode) expressionParser.parse(tokens);
      assertEquals(TokenType.MINUS_SYMBOL, binaryOpNode.getToken().getType());
      assertEquals(TokenType.LITERAL, binaryOpNode.getLeft().getToken().getType());
      assertEquals(TokenType.IDENTIFIER, binaryOpNode.getRight().getToken().getType());
    } else {
      throw new Error();
    }
  }
}
