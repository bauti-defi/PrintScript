package edu.austral.ingsis.ast.parsers;

import edu.austral.ingsis.ast.TokenPattern;
import edu.austral.ingsis.ast.exceptions.SyntaxException;
import edu.austral.ingsis.ast.exceptions.SyntaxTokenExpectedException;
import edu.austral.ingsis.ast.nodes.DeclarationNode;
import edu.austral.ingsis.ast.nodes.IdentifierNode;
import edu.austral.ingsis.ast.nodes.TypeNode;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import java.security.InvalidParameterException;
import java.util.List;

public class DeclarationParser implements NodeParser<DeclarationNode> {

  private final TokenType type;

  public DeclarationParser(TokenType type) {
    if (type != TokenType.LET && type != TokenType.CONST) {
      throw new InvalidParameterException(type + " is not a valid declaration keyword");
    }
    this.type = type;
  }

  public boolean predicate(List<Token> tokens) {
    return TokenPattern.Builder.of(type).identifier().colon().type().build().startWith(tokens);
  }

  public DeclarationNode parse(List<Token> tokens) {
    int colonIndex = getIndexOfToken(tokens, TokenType.COLON);

    // check that we have an identifier
    if (!TokenPattern.Builder.of(this.type).identifier().build().startWith(tokens)) {
      throw new SyntaxTokenExpectedException(tokens.get(colonIndex - 1), TokenType.IDENTIFIER);
    }

    // Check that there is a colon
    if (!TokenPattern.Builder.of(this.type).identifier().colon().build().startWith(tokens)) {
      throw new SyntaxTokenExpectedException(tokens.get(0).getLine(), TokenType.COLON);
    }

    // Check we have a type
    if (!TokenPattern.Builder.of(this.type).identifier().colon().type().build().matches(tokens)) {
      throw new SyntaxException(tokens.get(colonIndex + 1));
    }

    final DeclarationNode node = new DeclarationNode(tokens.get(0));
    final IdentifierNode identifier = new IdentifierNode(tokens.get(colonIndex - 1));
    final TypeNode type = new TypeNode(tokens.get(colonIndex + 1));

    node.setLeft(identifier);
    node.setRight(type);

    return node;
  }
}
