package edu.austral.ingsis.ast.parsers;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.TokenType;
import edu.austral.ingsis.ast.nodes.LogicalOpNode;
import java.util.List;

public class LogicalOpParser implements NodeParser<LogicalOpNode> {

  private final ExpressionParser expressionParser = new ExpressionParser();
  private final TokenType type;

  public LogicalOpParser(TokenType type) {
    this.type = type;
  }

  @Override
  public boolean predicate(List<Token> tokens) {
    return containsToken(tokens, type);
  }

  @Override
  public LogicalOpNode parse(List<Token> tokens) {
    int index = getIndexOfToken(tokens, type);

    final LogicalOpNode node = new LogicalOpNode(tokens.get(index));
    node.setLeft(expressionParser.parse(tokens.subList(0, index)));
    node.setRight(expressionParser.parse(tokens.subList(index + 1, tokens.size() - 1)));
    return node;
  }
}
