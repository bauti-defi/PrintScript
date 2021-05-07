package edu.austral.ingsis.ast.parsers;

import edu.austral.ingsis.ast.TokenPattern;
import edu.austral.ingsis.ast.nodes.ReferenceAssignationNode;
import edu.austral.ingsis.ast.nodes.ReferenceNode;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import java.util.List;

public class ReferenceAssignationParser implements NodeParser<ReferenceAssignationNode> {

  private final ReferenceParser referenceBuilder = new ReferenceParser();
  private final ExpressionParser expressionParser = new ExpressionParser();

  public boolean predicate(List<Token> tokens) {
    return TokenPattern.Builder.of(TokenType.IDENTIFIER).equals().build().startWith(tokens)
        && endsWithSemicolon(tokens);
  }

  public ReferenceAssignationNode parse(List<Token> tokens) {
    int index = getIndexOfToken(tokens, TokenType.EQUALS);

    final List<Token> declaration = tokens.subList(0, index);
    final List<Token> value = trimSemicolon(tokens.subList(index + 1, tokens.size()));

    final ReferenceAssignationNode referenceAssignationNode =
        new ReferenceAssignationNode(tokens.get(index));

    if (referenceBuilder.predicate(declaration)) {
      // Declare brand new variable
      final ReferenceNode node = referenceBuilder.parse(declaration);
      referenceAssignationNode.setLeft(node);
    }

    // Get value of assignation
    referenceAssignationNode.setRight(expressionParser.parse(value));

    return referenceAssignationNode;
  }
}
