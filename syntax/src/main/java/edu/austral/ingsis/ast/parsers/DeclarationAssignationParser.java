package edu.austral.ingsis.ast.parsers;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.TokenType;
import edu.austral.ingsis.ast.*;
import edu.austral.ingsis.ast.nodes.DeclarationAssignationNode;
import edu.austral.ingsis.ast.nodes.DeclarationNode;

import java.util.Arrays;
import java.util.List;

public class DeclarationAssignationParser implements NodeParser<DeclarationAssignationNode> {

  private final List<DeclarationParser> declarationParsers;
  private final ExpressionParser expressionParser = new ExpressionParser();

  public DeclarationAssignationParser(DeclarationParser... declarationParsers){
    this.declarationParsers = Arrays.asList(declarationParsers);
  }

  public boolean predicate(List<Token> tokens) {
    return containsToken(tokens, TokenType.EQUALS) && declarationParsers.stream().anyMatch(parser -> parser.predicate(tokens));
  }

  public DeclarationAssignationNode parse(List<Token> tokens) {
    int index = getIndexOfToken(tokens, TokenType.EQUALS);

    final List<Token> declaration = tokens.subList(0, index);
    final List<Token> value = tokens.subList(index + 1, tokens.size());

    final DeclarationAssignationNode declarationAssignationNode =
        new DeclarationAssignationNode(tokens.get(index));

    // Declare brand new variable
    for(DeclarationParser parser: declarationParsers){
      if(parser.predicate(declaration)){
        final DeclarationNode node = parser.parse(declaration);
        declarationAssignationNode.setLeft(node);
      }
    }

    // Get value of assignation
    declarationAssignationNode.setRight(expressionParser.parse(value));

    return declarationAssignationNode;
  }
}
