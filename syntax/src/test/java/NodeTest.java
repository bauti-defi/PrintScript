import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.parsers.NodeParser;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class NodeTest implements TokenHelper {

  final NodeParser<?> builder =
      new NodeParser<AbstractNode>() {
        @Override
        public boolean predicate(List<Token> tokens) {
          return false;
        }

        @Override
        public AbstractNode parse(List<Token> tokens) {
          return null;
        }
      };

  @Test
  public void testContainsToken() {
    // let x:number
    List<Token> tokens =
        Arrays.asList(
            createMockToken("let", TokenType.LET),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE));

    assertEquals(true, builder.containsToken(tokens, TokenType.COLON));
  }

  @Test
  public void testGetIndexOfToken() {
    // let x:number
    List<Token> tokens =
        Arrays.asList(
            createMockToken("let", TokenType.LET),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE));

    assertEquals(2, builder.getIndexOfToken(tokens, TokenType.COLON));
  }

  @Test
  public void testIsTokenType() {
    assertEquals(true, builder.isTokenType(createMockToken(":", TokenType.COLON), TokenType.COLON));
  }

  @Test
  public void testStartsWith() {
    // let x:number
    List<Token> tokens =
        Arrays.asList(
            createMockToken("let", TokenType.LET),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE));

    assertEquals(true, builder.startsWith(tokens, TokenType.LET));
  }

  @Test
  public void testEndsWith() {
    // let x:number
    List<Token> tokens =
        Arrays.asList(
            createMockToken("let", TokenType.LET),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE));

    assertEquals(true, builder.endsWith(tokens, TokenType.TYPE));
  }

  @Test
  public void testNodes() {
    BinaryOpNode node = new BinaryOpNode(createMockToken("+", TokenType.PLUS_SYMBOL));
    assertEquals(node.getNodeType(), "BINARY_EXPRESSION");

    CompoundExpressionNode node2 =
        new CompoundExpressionNode(createMockToken("+", TokenType.PLUS_SYMBOL));
    assertEquals(node2.getNodeType(), "COMPOUND_EXPRESSION");

    DeclarationAssignationNode node3 =
        new DeclarationAssignationNode(createMockToken("=", TokenType.EQUALS));
    assertEquals(node3.getNodeType(), "DECLARATION_ASSIGNATION");

    DeclarationNode node4 = new DeclarationNode(createMockToken("=", TokenType.EQUALS));
    assertEquals(node4.getNodeType(), "DECLARATION");

    ExpressionNode node5 = new ExpressionNode(createMockToken("=", TokenType.EQUALS));
    assertEquals(node5.getNodeType(), "EXPRESSION");

    IdentifierNode node6 = new IdentifierNode(createMockToken("=", TokenType.IDENTIFIER));
    assertEquals(node6.getNodeType(), "IDENTIFIER");

    IfStatementNode node7 = new IfStatementNode(createMockToken("=", TokenType.IF));
    assertEquals(node7.getNodeType(), "IF");

    PrintlnNode node8 = new PrintlnNode(createMockToken("=", TokenType.PRINTLN));
    assertEquals(node8.getNodeType(), "PRINTLN");

    ReferenceNode node9 = new ReferenceNode(createMockToken("=", TokenType.IDENTIFIER));
    assertEquals(node9.getNodeType(), "REFERENCE");

    TypeNode node10 = new TypeNode(createMockToken("=", TokenType.TYPE));
    assertEquals(node10.getNodeType(), "TYPE");
  }
}
