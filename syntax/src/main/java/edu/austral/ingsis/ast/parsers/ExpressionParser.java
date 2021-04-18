package edu.austral.ingsis.ast.parsers;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.TokenType;
import edu.austral.ingsis.ast.*;
import edu.austral.ingsis.ast.nodes.ExpressionNode;
import java.util.Arrays;
import java.util.List;

public class ExpressionParser implements NodeParser<ExpressionNode> {

  private final ArithmeticParser arithmeticParser = new ArithmeticParser();
  private final List<LogicalOpParser> logicalParser =
      Arrays.asList(
          new LogicalOpParser(TokenType.DOUBLE_EQUALS), new LogicalOpParser(TokenType.NOT_EQUALS));
  private final List<LogicalOpParser> comparisonParsers =
      Arrays.asList(
          new LogicalOpParser(TokenType.GREATER_THAN),
          new LogicalOpParser(TokenType.GREATER_THAN_EQUALS),
          new LogicalOpParser(TokenType.LESS_THAN),
          new LogicalOpParser(TokenType.LESS_THAN_EQUALS));

  public boolean predicate(List<Token> tokens) {
    return true;
  }

  public ExpressionNode parse(List<Token> tokens) {

    return arithmeticParser.parse(tokens);
  }
}
