package edu.austral.ingsis.ast.builders;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.TokenType;
import edu.austral.ingsis.ast.nodes.ReferenceAssignationNode;
import edu.austral.ingsis.ast.nodes.ReferenceNode;
import java.util.List;

public class ReferenceAssignationParser implements NodeParser<ReferenceAssignationNode> {

  private final ReferenceParser referenceBuilder = new ReferenceParser();
  private final ExpressionParser expressionParser = new ExpressionParser();

  public boolean predicate(List<Token> tokens) {
    return containsToken(tokens, TokenType.EQUALS)
        && (startsWith(tokens, TokenType.LET) || startsWith(tokens, TokenType.CONST));
  }

  public ReferenceAssignationNode parse(List<Token> tokens) {
    int index = getIndexOfToken(tokens, TokenType.EQUALS);

    final List<Token> declaration = tokens.subList(0, index);
    final List<Token> value = tokens.subList(index + 1, tokens.size());

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
