package edu.austral.ingsis.ast.parsers;

import edu.austral.ingsis.ast.TokenPattern;
import edu.austral.ingsis.ast.exceptions.SyntaxException;
import edu.austral.ingsis.ast.nodes.ExpressionNode;
import edu.austral.ingsis.ast.nodes.PrintlnNode;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import java.util.List;

public class PrintlnParser implements NodeParser<PrintlnNode> {

  private final ExpressionParser expressionParser = new ExpressionParser();

  @Override
  public boolean predicate(List<Token> tokens) {
    return TokenPattern.Builder.of(TokenType.PRINTLN).build().startWith(tokens);
  }

  @Override
  public PrintlnNode parse(List<Token> tokens) {
    int leftParenthesesIndex = getIndexOfToken(tokens, TokenType.L_PARENTHESES);
    int rightParenthesesIndex = getIndexOfToken(tokens, TokenType.R_PARENTHESES);
    if (leftParenthesesIndex == -1 || rightParenthesesIndex == -1) {
      throw new SyntaxException();
    }

    final PrintlnNode printLnNode = new PrintlnNode(tokens.get(0));
    final ExpressionNode expressionNode =
        expressionParser.parse(tokens.subList(leftParenthesesIndex + 1, rightParenthesesIndex));
    printLnNode.setArgs(expressionNode);

    return printLnNode;
  }
}
