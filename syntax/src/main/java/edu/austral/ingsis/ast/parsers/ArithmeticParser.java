package edu.austral.ingsis.ast.parsers;

import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import edu.austral.ingsis.ast.ShuntingYard;
import edu.austral.ingsis.ast.nodes.BinaryOpNode;
import edu.austral.ingsis.ast.nodes.ExpressionNode;
import edu.austral.ingsis.ast.nodes.ValueLiteralNode;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ArithmeticParser implements NodeParser<ExpressionNode> {

  private final ReferenceParser referenceBuilder = new ReferenceParser();

  @Override
  public boolean predicate(List<Token> tokens) {
    return false;
  }

  @Override
  public ExpressionNode parse(List<Token> tokens) {
    final Stack<Token> postFix = ShuntingYard.parse(tokens);
    return parseShuntingYard(postFix);
  }

  // can return binary-op, declaration, or value-literal
  private ExpressionNode parseShuntingYard(Stack<Token> postFix) {
    final Stack<ExpressionNode> nodes = new Stack<>();

    for (Token token : postFix) {
      if (isTokenType(token, TokenType.LITERAL)) {
        nodes.push(new ValueLiteralNode(token));
      } else if (isTokenType(token, TokenType.IDENTIFIER)) {
        nodes.push(referenceBuilder.parse(Arrays.asList(token)));
      } else if (isTokenType(token, TokenType.STAR_SYMBOL)
          || isTokenType(token, TokenType.SLASH_SYMBOL)
          || isTokenType(token, TokenType.PLUS_SYMBOL)
          || isTokenType(token, TokenType.MINUS_SYMBOL)) {
        final BinaryOpNode operator = new BinaryOpNode(token);
        operator.setRight(nodes.pop());
        operator.setLeft(nodes.pop());

        nodes.push(operator);
      }
    }

    // The only node left in the stack should be the head
    return nodes.pop();
  }
}
