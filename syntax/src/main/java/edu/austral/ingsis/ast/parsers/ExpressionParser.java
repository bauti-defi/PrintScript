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
          new LogicalOpParser(this, TokenType.DOUBLE_EQUALS), new LogicalOpParser(this, TokenType.NOT_EQUALS));

  private final List<LogicalOpParser> comparisonParsers =
      Arrays.asList(
          new LogicalOpParser(this, TokenType.GREATER_THAN),
          new LogicalOpParser(this, TokenType.GREATER_THAN_EQUALS),
          new LogicalOpParser(this, TokenType.LESS_THAN),
          new LogicalOpParser(this, TokenType.LESS_THAN_EQUALS));

  public boolean predicate(List<Token> tokens) {
    return true;
  }

  public ExpressionNode parse(List<Token> tokens) {
    //Logical parsers first
    for(LogicalOpParser parser: logicalParser){
      if(parser.predicate(tokens)){
        return parser.parse(tokens);
      }
    }

    //Comparison parsers next
    for(LogicalOpParser parser: comparisonParsers){
      if(parser.predicate(tokens)){
        return parser.parse(tokens);
      }
    }

    // Arithmetic parser last
    return arithmeticParser.parse(tokens);
  }

}
