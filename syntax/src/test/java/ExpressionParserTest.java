import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.ast.nodes.AbstractNode;
import edu.austral.ingsis.ast.nodes.BinaryOpNode;
import edu.austral.ingsis.ast.nodes.LogicalOpNode;
import edu.austral.ingsis.ast.parsers.ExpressionParser;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
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
            createMockToken(")", TokenType.R_PARENTHESES));

    final ExpressionParser parser = new ExpressionParser();

    if (parser.predicate(tokens)) {
      final AbstractNode node = parser.parse(tokens);
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
            createMockToken(")", TokenType.R_PARENTHESES),
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

  @Test
  public void testBuild3() {
    // (5)-size
    List<Token> tokens =
        Arrays.asList(
            createMockToken("(", TokenType.L_PARENTHESES),
            createMockToken("5", TokenType.LITERAL),
            createMockToken(")", TokenType.R_PARENTHESES),
            createMockToken("==", TokenType.DOUBLE_EQUALS),
            createMockToken("size", TokenType.IDENTIFIER));

    final ExpressionParser expressionParser = new ExpressionParser();

    if (expressionParser.predicate(tokens)) {
      final LogicalOpNode logicalOpParser = (LogicalOpNode) expressionParser.parse(tokens);
      assertEquals(TokenType.DOUBLE_EQUALS, logicalOpParser.getToken().getType());
      assertEquals(TokenType.LITERAL, logicalOpParser.getLeft().getToken().getType());
      assertEquals(TokenType.IDENTIFIER, logicalOpParser.getRight().getToken().getType());
    } else {
      throw new Error();
    }
  }

  @Test
  public void testBuild4() {
    // (5)-size
    List<Token> tokens =
        Arrays.asList(
            createMockToken("(", TokenType.L_PARENTHESES),
            createMockToken("5", TokenType.LITERAL),
            createMockToken(")", TokenType.R_PARENTHESES),
            createMockToken(">=", TokenType.GREATER_THAN_EQUALS),
            createMockToken("10", TokenType.LITERAL),
            createMockToken("+", TokenType.PLUS_SYMBOL),
            createMockToken("10", TokenType.LITERAL));

    final ExpressionParser expressionParser = new ExpressionParser();

    if (expressionParser.predicate(tokens)) {
      final LogicalOpNode logicalOpParser = (LogicalOpNode) expressionParser.parse(tokens);
      assertEquals(TokenType.GREATER_THAN_EQUALS, logicalOpParser.getToken().getType());
      assertEquals(TokenType.LITERAL, logicalOpParser.getLeft().getToken().getType());
      assertEquals(TokenType.PLUS_SYMBOL, logicalOpParser.getRight().getToken().getType());
    } else {
      throw new Error();
    }
  }

  @Test
  public void testBuild5() {
    // (5)-size
    List<Token> tokens =
        Arrays.asList(
            createMockToken("size", TokenType.IDENTIFIER),
            createMockToken("!=", TokenType.NOT_EQUALS),
            createMockToken("10", TokenType.LITERAL),
            createMockToken("+", TokenType.PLUS_SYMBOL),
            createMockToken("10", TokenType.LITERAL));

    final ExpressionParser expressionParser = new ExpressionParser();

    if (expressionParser.predicate(tokens)) {
      final LogicalOpNode logicalOpParser = (LogicalOpNode) expressionParser.parse(tokens);
      assertEquals(TokenType.NOT_EQUALS, logicalOpParser.getToken().getType());
      assertEquals(TokenType.IDENTIFIER, logicalOpParser.getLeft().getToken().getType());

      final BinaryOpNode binaryOpNode = (BinaryOpNode) logicalOpParser.getRight();
      assertEquals(TokenType.PLUS_SYMBOL, binaryOpNode.getToken().getType());
      assertEquals(TokenType.LITERAL, binaryOpNode.getRight().getToken().getType());
      assertEquals(TokenType.LITERAL, binaryOpNode.getLeft().getToken().getType());
    } else {
      throw new Error();
    }
  }
}
